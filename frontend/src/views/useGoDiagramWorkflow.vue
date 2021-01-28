<template>

  <div>
    <base-header class="pb-6 pb-8 pt-5 pt-md-8 bg-gradient-default">
      <!-- Card stats -->
      <br>
        <carousel :per-page="4"   :mouse-drag="true" >
          <slide v-for="(item, index) in curriculumData" :key="index" >
            <b-col  @click="previewRoadmap(item.rmid, index)">
                  <stats-card type="gradient-red"
                            :sub-title="item.name"
                            icon="ni ni-active-40"
                            class="mb-4 btn" 
                            :rmid="item.rmid"
                            >
                  <template slot="footer">
                    <span class="text-success mr-2">{{ item.createDate }}</span>
                  </template>
                </stats-card>
                </b-col>
          </slide>
        </carousel>
    </base-header>

    

    
    <b-container fluid class="mt--7">
      <b-row>
        <b-col>
          <div style="text-align: right;">
            <button class="btn" style="background-color: rgb(256, 256, 256);" @click="goToCreate">생성하기</button>
            <router-link :to="{ name : 'roadmap', params: { rmid: this.rmid, mode : 1 }}" class= "btn" style=" background-color:#F9F8F3" >수정하기</router-link>
          </div>
          <b-card no-body class="border-0">
            <div class="inline-block" style="width: 100%;">

              <!--goJS/start-->
              <div style="width: 100%; display: flex; justify-content: space-between; z-index:1;">
                <div ref="myDiagramDiv" style="flex-grow: 1; height: 750px; background-color: #F9F8F3;" @click="checkCur">
                </div>

                 <!-- 커리큘럼 데이터 출력 카드/start -->
                <b-card
                  title="Curriculum Information"
                  style="width: 252px;"
                >
                  <h3>{{ headertext }}</h3>
                  <hr>
                  <b-card-text>
                    <base-input label="시작날짜-종료날짜">
                    <flat-pickr slot-scope="{focus, blur}"
                    @on-open="focus"
                    @on-close="blur"
                    :config="{allowInput: true, mode: 'range',}"
                    class="form-control datepickr"
                    v-model="dates.range"
                    disabled>
                    </flat-pickr>
                    </base-input>
                  </b-card-text>
                  <hr>
                  <span>커리큘럼 설명</span>
                  <hr>
                  <b-card-text>
                   <b-form-input v-model="memotext" placeholder="Enter your memo" readonly ></b-form-input>
                  </b-card-text>
                  
                </b-card>
                
                <!-- 커리큘럼 데이터 출력 카드/end -->

              </div>
              <!--goJs/end-->

            </div>
          </b-card>
        </b-col>
      </b-row>
    </b-container>
   
  </div>
</template>
<script>
import RoadMap from '@/views/Roadmap/RoadMap'
import router from '@/routes/router'
import { Carousel, Slide } from 'vue-carousel';
import flatPickr from "vue-flatpickr-component";
import "flatpickr/dist/flatpickr.css";
import 'flatpickr/dist/themes/material_blue.css';
import {Hindi} from 'flatpickr/dist/l10n/hi.js';

// 코드 변환 시작 
let go = window.go
let $ = go.GraphObject.make
let myDiagram;
// node 속성 체크위한 전역변수(여기서만 사용)
let head;
export default {
  router,
  name: '',
  componenets: {
    RoadMap,
    Carousel,
    Slide,
    flatPickr,
  },
  data() {
    return {
      test: { "class": "go.GraphLinksModel",
        "linkFromPortIdProperty": "fromPort",
        "linkToPortIdProperty": "toPort",
        "nodeDataArray": [
      ],
        "linkDataArray": [
      ]},  
      curriculumData: [],
      rmid: 0,
      ismounted: false,
      headertext: '',
      dates :{
        range : ''
      },
      memotext : '',
        // Get more form https://flatpickr.js.org/options/
      config: {
        wrap: true, // set wrap to true only when using 'input-group'
        altFormat: 'M j, Y',
        altInput: true,
        dateFormat: 'Y-m-d',
        locale: Hindi, // locale for this instance only          
      },

    }
  },
  created() {
      
      
      const uid = String(this.$store.getters.getUid)
      // page => 차후 수정해야됨

      
      const page = '1'
      axios.get(`${this.$store.getters.getServer}/roadmap/list/${uid}/${page}`)
        .then((res) => {
        if(res.data.msg == 'success')
          this.curriculumData = res.data['roadmaps'];
          else
            alert("데이터 로드에 실패했습니다.")
          
        }).catch((e) =>{
          alert("axois 오류")
        });
    
  },
  mounted() {
    myDiagram = 
        $(go.Diagram, this.$refs.myDiagramDiv,
          {
            initialContentAlignment: go.Spot.Center, 
        })

      // 페이지에 변화가 있을 때 title 및 save 버튼 활성화
      // when the document is modified, add a "*" to the title and enable the "Save" button
      myDiagram.addDiagramListener("Modified", function(e) {
        var button = document.getElementById("SaveButton");
        if (button) button.disabled = !myDiagram.isModified;
        var idx = document.title.indexOf("*");
        if (myDiagram.isModified) {
          if (idx < 0) document.title += "*";
        } else {
          if (idx >= 0) document.title = document.title.substr(0, idx);
        }
      });

      // GUI 시작 : node 모양 
      myDiagram.nodeTemplateMap.add("",  // the default category
        $(go.Node, "Table", this.nodeStyle(),
          // the main object is a Panel that surrounds a TextBlock with a rectangular Shape
          $(go.Panel, "Auto",
            $(go.Shape, "RoundedRectangle",
              { fill: "#D4E0DE", stroke: "#307363", strokeWidth:  3.5, strokeJoin: "round", strokeCap: "square" },
              new go.Binding("figure", "figure")),
            $(go.TextBlock, this.textStyle(),
              {
                margin: 8,
                maxSize: new go.Size(160, NaN),
                wrap: go.TextBlock.WrapFit,
                editable: true
              },
              new go.Binding("text").makeTwoWay())
          ),
          // four named ports, one on each side: node의 가지 옵션 
          this.makePort("T", go.Spot.Top, go.Spot.TopSide, false, true),
          this.makePort("L", go.Spot.Left, go.Spot.LeftSide, true, true),
          this.makePort("R", go.Spot.Right, go.Spot.RightSide, true, true),
          this.makePort("B", go.Spot.Bottom, go.Spot.BottomSide, true, false)
        ));  


      myDiagram.nodeTemplateMap.add("Start",
        $(go.Node, "Table", this.nodeStyle(),
          $(go.Panel, "Spot",
            $(go.Shape, "Circle",
              { desiredSize: new go.Size(70, 70), fill: "#ffffff", stroke: "#F04A5E", strokeWidth: 3.5 }),
            $(go.TextBlock, "Start", this.textStyle(),
              new go.Binding("text"))
          ),
          // three named ports, one on each side except the top, all output only:
          this.makePort("L", go.Spot.Left, go.Spot.Left, true, false),
          this.makePort("R", go.Spot.Right, go.Spot.Right, true, false),
          this.makePort("B", go.Spot.Bottom, go.Spot.Bottom, true, false)
        ));
      
      myDiagram.nodeTemplateMap.add("End",
        $(go.Node, "Table", this.nodeStyle(),
          $(go.Panel, "Spot",
            $(go.Shape, "Circle",
              { desiredSize: new go.Size(60, 60), fill: "#ffffff", stroke: "#8D2040", strokeWidth: 3.5 }),
            $(go.TextBlock, "End", this.textStyle(),
              new go.Binding("text"))
          ),
          // three named ports, one on each side except the bottom, all input only:
          this.makePort("T", go.Spot.Top, go.Spot.Top, false, true),
          this.makePort("L", go.Spot.Left, go.Spot.Left, false, true),
          this.makePort("R", go.Spot.Right, go.Spot.Right, false, true)
        ));
      
      // taken from ../extensions/Figures.js:
      go.Shape.defineFigureGenerator("File", function(shape, w, h) {
        var geo = new go.Geometry();
        var fig = new go.PathFigure(0, 0, true); // starting point
        geo.add(fig);
        fig.add(new go.PathSegment(go.PathSegment.Line, .75 * w, 0));
        fig.add(new go.PathSegment(go.PathSegment.Line, w, .25 * h));
        fig.add(new go.PathSegment(go.PathSegment.Line, w, h));
        fig.add(new go.PathSegment(go.PathSegment.Line, 0, h).close());
        var fig2 = new go.PathFigure(.75 * w, 0, false);
        geo.add(fig2);
        // The Fold
        fig2.add(new go.PathSegment(go.PathSegment.Line, .75 * w, .25 * h));
        fig2.add(new go.PathSegment(go.PathSegment.Line, w, .25 * h));
        geo.spot1 = new go.Spot(0, .25);
        geo.spot2 = go.Spot.BottomRight;
        return geo;
      });
    // 메모 GUI
    myDiagram.nodeTemplateMap.add("Comment",
      $(go.Node, "Auto", this.nodeStyle(),
        $(go.Shape, "File",
          { fill: "#282c34", stroke: "#DEE0A3", strokeWidth: 3 }),
        $(go.TextBlock, this.textStyle(),
          {
            margin: 8,
            maxSize: new go.Size(200, NaN),
            wrap: go.TextBlock.WrapFit,
            textAlign: "center",
            editable: true
          },
          new go.Binding("text").makeTwoWay())
        // no ports, because no links are allowed to connect with a comment
      ));

    // replace the default Link template in the linkTemplateMap
    // 링크 결합, 설정관련 코드
    myDiagram.linkTemplate =
      $(go.Link,  // the whole link panel
        {
          routing: go.Link.AvoidsNodes,
          curve: go.Link.JumpOver,
          corner: 5, toShortLength: 4,
          relinkableFrom: true,
          relinkableTo: true,
          reshapable: true,
          resegmentable: true,
          // mouse-overs subtly highlight links:
          // mouseEnter: function(e, link) { link.findObject("HIGHLIGHT").stroke = "rgba(30,144,255,0.2)"; },
          // mouseLeave: function(e, link) { link.findObject("HIGHLIGHT").stroke = "transparent"; },
          selectionAdorned: false
        },
        new go.Binding("points").makeTwoWay(),
        $(go.Shape,  // the highlight shape, normally transparent
          { isPanelMain: true, strokeWidth: 5, stroke: "transparent", name: "HIGHLIGHT" }),
        $(go.Shape,  // the link path shape
          { isPanelMain: true, stroke: "#1B443C", strokeWidth: 2.5 },
          new go.Binding("stroke", "isSelected", function(sel) { return sel ? "#1B443C" : "#1B443C"; }).ofObject()),
        $(go.Shape,  // the arrowhead
          { toArrow: "Triangle", strokeWidth: 1.5, stroke: "#1B443C", fill: "#307362"}),
        $(go.Panel, "Auto",  // the link label, normally not visible
          { visible: false, name: "LABEL", segmentIndex: 2, segmentFraction: 0.5 },
          new go.Binding("visible", "visible").makeTwoWay(),
          $(go.Shape, "RoundedRectangle",  // the label shape
            { fill: "#F8F8F8", strokeWidth: 0}),
          $(go.TextBlock, "Yes",  // the label
            {
              textAlign: "center",
              font: "10pt helvetica, arial, sans-serif",
              stroke: "#333333",
              editable: true
            },
            new go.Binding("text").makeTwoWay())
        )
      );

      // LinkingTool 및 RelinkingTool에서 사용하는 임시 링크도 직교합니다.
      myDiagram.toolManager.linkingTool.temporaryLink.routing = go.Link.Orthogonal;
      myDiagram.toolManager.relinkingTool.temporaryLink.routing = go.Link.Orthogonal;

      // 수정 없이 읽기 
      myDiagram.isReadOnly = true ;
      
      this.load();

      // canvas 내의 node 요소 잡기 
      myDiagram.addDiagramListener("ObjectSingleClicked", function(e) {
        head = e.subject.part.data.text;
       });
  },
  computed: {},
  methods: {

    nodeStyle() {
      return [
        // The Node.location comes from the "loc" property of the node data,
        // converted by the Point.parse static method.
        // If the Node.location is changed, it updates the "loc" property of the node data,
        // converting back using the Point.stringify static method.
        new go.Binding("location", "loc", go.Point.parse).makeTwoWay(go.Point.stringify),
        {
          // the Node.location is at the center of each node
          locationSpot: go.Spot.Center
        }
      ];
    },
    

    makePort(name, align, spot, output, input) {
      var horizontal = align.equals(go.Spot.Top) || align.equals(go.Spot.Bottom);
      // the port is basically just a transparent rectangle that stretches along the side of the node,
      // and becomes colored when the mouse passes over it
      return $(go.Shape,
        {
          fill: "transparent",  // changed to a color in the mouseEnter event handler
          strokeWidth: 0,  // no stroke
          width: horizontal ? NaN : 8,  // if not stretching horizontally, just 8 wide
          height: !horizontal ? NaN : 8,  // if not stretching vertically, just 8 tall
          alignment: align,  // align the port on the main Shape
          stretch: (horizontal ? go.GraphObject.Horizontal : go.GraphObject.Vertical),
          portId: name,  // declare this object to be a "port"
          fromSpot: spot,  // declare where links may connect at this port
          fromLinkable: output,  // declare whether the user may draw links from here
          toSpot: spot,  // declare where links may connect at this port
          toLinkable: input,  // declare whether the user may draw links to here
          cursor: "pointer",
      })
    },

    textStyle() {
      return {
        font: "bold 11pt Lato, Helvetica, Arial, sans-serif",
        stroke: "#000000"
      }
    },
    
    showLinkLabel(e) {
      var label = e.subject.findObject("LABEL");
      if (label !== null) label.visible = (e.subject.fromNode.data.category === "Conditional");
    },
    
    // 로드될때 위에서 아래로 올라오는 애니메이션 
    animateFadeDown(e) {
      var diagram = e.diagram; 
      var animation = new go.Animation();
      animation.isViewportUnconstrained = true; // So Diagram positioning rules let the animation start off-screen
      animation.easing = go.Animation.EaseOutExpo;
      animation.duration = 900;
      // Fade "down", in other words, fade in from above
      animation.add(diagram, 'position', diagram.position.copy().offset(0, 200), diagram.position);
      animation.add(diagram, 'opacity', 0, 1);
      animation.start();
    },
    // 리스트
    previewRoadmap(clickrmid, index) {
      this.rmid = clickrmid;
        axios.get(`${this.$store.getters.getServer}/roadmap/get/${clickrmid}`)
        .then((res) => {
          if(res.data.msg == 'success'){
          this.test = JSON.parse(res.data['roadmaps'].tmp);
          this.load();
          }else{
            alert("데이터 로드에 실패했습니다.")
          }
        }).catch((e) =>{
          alert("axois 오류")
        });
    },
    // 외부 json파일 초기화면에 출력
    load() {
      myDiagram.model = go.Model.fromJson(this.test);
      this.ismounted = true
    },
    // 로드맵 수정버튼 
    updateRoadMap() {
      this.$router.push({ name: 'roadmap' })
    },
    checkCur(e) {
      // 차후에 DB에 요청을 보낸다음 DB정보로 반영
      this.headertext = head
    },
    goToCreate() {
      this.$router.push({ name : 'roadmap', params: { rmid: 0, mode : 0 } })
    }
  },
}
</script>

<style>

</style>