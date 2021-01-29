<template>
  <div>
    <base-header class="pb-5 pb-2 pt-2 pt-md-2 bg-gradient-default">
      <!-- Card stats -->
    <a :href="goToBack" class="btn" style="background-color: rgb(242, 214, 174);">돌아가기</a> 
    <button v-if="mode" class="btn" @click="updateRoadmap" style="background-color: rgba(256, 256, 256, 0.95);">수정완료</button>
    <button v-else class="btn" @click="createRoadmap" style="background-color: rgb(181, 199, 211);">생성완료</button>
    <!-- 사용법 modal / start -->
      <b-button 
        v-b-modal.modal-1
        type="button" 
        class="btn ml-4" 
        title=""
        data-original-title="Copy to clipboard"
      >
        <div>
          <i class="ni ni-air-baloon"></i>
          <span>How to use</span>
        </div>
      </b-button>
    <b-form-input v-model="roadmapname" class="inline-block" placeholder="로드맵 제목을 입력해 주세요." style="width:30%; display:inline-block;"></b-form-input>
    <!-- 커리큘럼 히스토리 보여주기 -->
    <div>
    <!-- 승환님 이거 잘되는지 확인해주세욜~~ -->
    <!--부트스트랩 드롭다운-->
    <div>
      <b-dropdown id="dropdown-1" text="이전 수정 기록" class="m-md-2">
        <b-dropdown-item 
          @click="previewRoadmap(item.rmid, index)" 
          v-for="(item, index) in logData" 
          :key="index">{{ item.createDate }} | {{ item.name }}
        </b-dropdown-item>
      </b-dropdown>
    </div>
    </div>
    <!-- 커리큘럼 히스토리 끝 -->

      <b-modal id="modal-1" title="BootstrapVue">
        <h3>로드위키 사용법</h3>
        <h4>❤ Read</h4>
          <li>
            '내 로드맵 보기'에서 나만의 로드맵을 볼 수 있습니다.
          </li>
          <li>
            상위의 리스트에서 파일을 클릭하시면 원하시는 로드맵을 볼 수 있습니다.
          </li>
          <li>
            수정버튼을 누르시면 로드맵을 수정 할 수 있는 페이지로 넘어갑니다.
          </li>
        <h4>❤ Create</h4>
          <li>
            원하시는 커리큘럼을 선택하세요.
          </li>
          <li>
            원하시는 커리큘럼에서 내보내기 버튼을 누르시면 내 로드맵으로 불러오기가 가능합니다.
          </li>
          <li>
            서비스에서 제공하는 로드맵에서 나만의 로드맵으로 맞춤 설정이 가능합니다!
          </li>
        <h4>❤ Update</h4>
        <li>
            수정하고 싶은 요소를 클릭해 delete버튼을 누르시면 요소가 삭제됩니다.
          </li>
          <li>
            오른쪽에는 커리큘럼의 정보가 제공됩니다.
          </li>        
          <li>
            왼쪽에는 서비스에서 추천해주는 로드맵 요소들을 끌어다 내 로드맵에 옮길 수 있습니다.
          </li>
          <li>
            선 이수체계에 맞도록 요소의 상, 하, 좌, 우에서 가지를 요소에 연결해 보세요.
          </li>
        <h4>❤ Delete</h4>
          <li>
            로드맵이 마음에 들지 않으시다면 삭제도 가능합니다.
          </li>
          <li>
            삭제버튼을 눌러 로드맵을 삭제하세요.
          </li>
      </b-modal>
    <!-- 사용법 modal / end -->
    
    <!-- 커리큘럼 히스토리 보여주기 -->
    </base-header>

    <b-container fluid class="mt-1">
      <b-row>
        <b-col>
          <b-card no-body class="border-0">
            <div style="width: 100%;">
              <div style="width: 100%; display: flex; justify-content: space-between; vertical-align: baseline;">
                <div ref="myPaletteDiv" style="width: 150px; margin-right: 2px; background-color: #F9F8F3;"></div>
                <div ref="myDiagramDiv" style="flex-grow: 1; height: 900px; background-color: #F9F8F3;" @click="checkCur">
                </div>
                
                <b-card
                  title="Curriculum Information"
                  style="width: 252px;"
                >
                <hr>
                <h2>{{ headertext }}</h2>
                  
                  <hr>
                  <b-card-text>
                    <base-input label="시작날짜-종료날짜">
                    <flat-pickr slot-scope="{focus, blur}"
                    @on-open="focus"
                    @on-close="blur"
                    :config="{allowInput: true, mode: 'range',}"
                    class="form-control datepickr"
                    v-model="dates.range"
                    >
                    </flat-pickr>
                    </base-input>
                  </b-card-text>
                  <hr>
                  <span>커리큘럼 설명</span>
                  <hr>
                  <b-card-text>
                   <b-form-input v-model="memotext" placeholder="Enter your memo" ></b-form-input>
                  </b-card-text>
                  
                </b-card>
              </div>
            </div>
          </b-card>
        </b-col>
      </b-row>
    </b-container>
  </div>
</template>




<script>
// carousel 
import { Carousel, Slide } from 'vue-carousel'; 
// 날짜  
import flatPickr from "vue-flatpickr-component";
import "flatpickr/dist/flatpickr.css";
import 'flatpickr/dist/themes/material_blue.css';
import {Hindi} from 'flatpickr/dist/l10n/hi.js';
import dropdown from 'vue-dropdowns';

// src\views\Roadmap\RoadMap.vue
// Roadmap 폴더 명 변경을 위한 주석
// 코드 변환 시작 
let go = window.go
let $ = go.GraphObject.make
let myDiagram;
let head;
export default {
  name: '',
  components: {
    'dropdown': dropdown,
  },
  props: {
      rmid: {
          type: Number,
      },
      rmorder :{
        type : Number,
      },
      mode: {
        type : Number,
      },
  },
  data() {
    return {
      goToBack: '#/godiagram',
      test: '',
      roadmapname: '',
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
      logData: [],      
    }
  },
  created(){
   console.log(this.test);
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

      // GUI 시작 
      myDiagram.nodeTemplateMap.add("",  // the default category
        $(go.Node, "Table", this.nodeStyle(),
          // the main object is a Panel that surrounds a TextBlock with a rectangular Shape
          $(go.Panel, "Auto",
            $(go.Shape, "RoundedRectangle",
              { fill: "#D4E0DE", stroke: "#307363", strokeWidth: 3.5, strokeJoin: "round", strokeCap: "square" },
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

      myDiagram.nodeTemplateMap.add("Conditional",
        $(go.Node, "Table", this.nodeStyle(),
          // the main object is a Panel that surrounds a TextBlock with a rectangular Shape
          $(go.Panel, "Auto",
            $(go.Shape, "Diamond",
              { fill: "#D4E0DE", stroke: "#307363", strokeWidth: 3.5 },
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
          // four named ports, one on each side:
          this.makePort("T", go.Spot.Top, go.Spot.Top, false, true),
          this.makePort("L", go.Spot.Left, go.Spot.Left, true, true),
          this.makePort("R", go.Spot.Right, go.Spot.Right, true, true),
          this.makePort("B", go.Spot.Bottom, go.Spot.Bottom, true, false)
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

    myDiagram.nodeTemplateMap.add("Comment",
      $(go.Node, "Auto", this.nodeStyle(),
        $(go.Shape, "File",
          { fill: "#D4E0DE", stroke: "#AA8A71", strokeWidth: 3 }),
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
          mouseEnter: function(e, link) { link.findObject("HIGHLIGHT").stroke = "rgba(30,144,255,0.2)"; },
          mouseLeave: function(e, link) { link.findObject("HIGHLIGHT").stroke = "transparent"; },
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
            { fill: "#F8F8F8", strokeWidth: 0 }),
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
      
      // 어떤 커리큘럼을 눌렀는지 체크 => 커리큘럼 추천에 활용할 데이터 추출
      myDiagram.addDiagramListener("ObjectSingleClicked", function(e) {
        head = e.subject.part.data.text;
      });

      // LinkingTool 및 RelinkingTool에서 사용하는 임시 링크도 직교합니다.
      myDiagram.toolManager.linkingTool.temporaryLink.routing = go.Link.Orthogonal;
      myDiagram.toolManager.relinkingTool.temporaryLink.routing = go.Link.Orthogonal;

      console.log('309', myDiagram)

      
      // 팔레트 설정 관련 코드
      let myPalette =
        $(go.Palette, this.$refs.myPaletteDiv, // must name or refer to the DIV HTML element
          {
            // Instead of the default animation, use a custom fade-down
            "animationManager.initialAnimationStyle": go.AnimationManager.None,
            "InitialAnimationStarting": this.animateFadeDown, // Instead, animate with this function
  
            nodeTemplateMap: myDiagram.nodeTemplateMap,  // share the templates used by myDiagram
            model: new go.GraphLinksModel([  // specify the contents of the Palette
              { category: "Start", text: "Start" },
              { text: "알고리즘 심화" },
              { category: "Conditional", text: "???" },
              { category: "End", text: "End" },
              { category: "Comment", text: "Comment" }
            ])
          })
          
      this.readRoadmap();
      // 수정로그 가져오기
      this.readRoadmapLog();
  },
  watch:{},
  computed: {},
  methods: {
      methodToRunOnSelect(payload) {
        this.object = payload;
      },
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
    
    //이것은 위쪽이 아닌 아래쪽에서 페이드 인 된다는 점을 제외하면 기본 애니메이션을 다시 구현 한 것입니다.
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
    // read 요청보내기
    readRoadmap() {
      // 외부 json파일 초기하면에 출력
      if(this.rmid == 0){
        this.test = { "class": "go.GraphLinksModel",
        "linkFromPortIdProperty": "fromPort",
        "linkToPortIdProperty": "toPort",
        "nodeDataArray": [
      ],
        "linkDataArray": [
      ]}
       myDiagram.model = go.Model.fromJson(this.test);
      }else{
      axios.get(`${this.$store.getters.getServer}/roadmap/get/${this.rmid}`)
        .then((res) => {
          if(res.data.msg == 'success'){
          this.test =  JSON.parse(res.data['roadmaps'].tmp);
          this.roadmapname = res.data['roadmaps'].name;
          // console.log('check', this.roadmapname, this.rmorder)
          myDiagram.model = go.Model.fromJson(this.test);
          }else{
            alert("데이터 로드에 실패했습니다.")
          }
        }).catch((e) =>{
          alert("axois 오류")
        });
      }
    },
    // 로드맵 로그 가져오는 함수(mounted에서 rmorder를 불러온뒤 실행)
    readRoadmapLog(){
      if(this.mode == 1){
        console.log(`${this.$store.getters.getServer}/roadmap/log/${this.$store.getters.getUid}/${this.rmorder}`);
         axios.get(`${this.$store.getters.getServer}/roadmap/log/${this.$store.getters.getUid}/${this.rmorder}`)
        .then((res) => {
          if(res.data.msg == 'success'){
            this.logData = res.data['roadmaps'];  
          }else{
            console.log(e);
            alert("데이터 로드에 실패했습니다.")
          }
        }).catch((e) =>{
          alert("axois 오류")
        });
      }
    },
    // update 요청보내기
    updateRoadmap() {
      console.log('실행')
      this.test = myDiagram.model.toJson();
      myDiagram.isModified = false;
      axios.post(`${this.$store.getters.getServer}/roadmap/update`,
        {
          // login기능 완료되면 store에서 가져오기로 수정!!!!!!!!!!
          uid: this.$store.getters.getUid,
          rmorder: this.rmorder,
          name: this.roadmapname,
          tmp: JSON.stringify(this.test)
        }
      )
      .then((res) => {
        if(res.data.msg == 'success'){
        this.$router.push({ name: 'godiagram' })
          }else{
            alert("업데이트 실패했습니다.")
          }
        }).catch((e) =>{
          alert("axois 오류")
        });
    },
    createRoadmap() {
      console.log('실행')
      this.test = myDiagram.model.toJson();
      myDiagram.isModified = false;
      axios.post(`${this.$store.getters.getServer}/roadmap/create`,
        {
          // login기능 완료되면 store에서 가져오기로 수정!!!!!!!!!!
          uid: this.$store.getters.getUid,
          name: this.roadmapname,
          tmp: JSON.stringify(this.test)
        }
      )
      .then((res) => {
        if(res.data.msg == 'success'){
        console.log(res)
        console.log('응답')
        this.$router.push({ name: 'godiagram' })
        }else
          alert("생성에 실패했습니다.")
        }).catch((e) =>{
          alert('axios 오류')
        });
    },
    checkCur(e) {
      // 차후에 DB에 요청을 보낸다음 DB정보로 반영
      this.headertext = head
    },
    
    previewRoadmap(clickrmid, index) {
      
      
        axios.get(`${this.$store.getters.getServer}/roadmap/get/${clickrmid}`)
        .then((res) => {
          if(res.data.msg == 'success'){
          this.test = JSON.parse(res.data['roadmaps'].tmp);
          this.load();
          }else{
            console.log('previewRoadmap');
            alert("데이터 로드에 실패했습니다. log")
          }
        }).catch((e) =>{
          console.log(e);
          alert("axois 오류")
        });
    },
    load() {
      myDiagram.model = go.Model.fromJson(this.test);
      this.ismounted = true
    },
  },
}
</script>

<style>

</style>