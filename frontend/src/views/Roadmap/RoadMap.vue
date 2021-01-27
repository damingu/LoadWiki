<template>
  <div>
    <base-header class="pb-5 pb-2 pt-2 pt-md-2 bg-gradient-success">
      <!-- Card stats -->
    <a :href="goToBack" class="btn">돌아가기</a> 
    <a v-if="mode" class="btn" @click="updateRoadmap">수정완료</a>
    <a v-else class="btn" @click="createRoadmap">생성완료</a>
    <input v-model="roadmapname" placeholder="로드맵 제목을 입력해 주세요.">

    </base-header>

    <b-container fluid class="mt-1">
      <b-row>
        <b-col>
          <b-card no-body class="border-0">
            <div style="width: 100%;">
              <div style="width: 100%; display: flex; justify-content: space-between; vertical-align: baseline;">
                <div ref="myPaletteDiv" style="width: 150px; margin-right: 2px; background-color: #282c34;"></div>
                <div ref="myDiagramDiv" style="flex-grow: 1; height: 900px; background-color: #282c34;"></div>
              </div>
            </div>
          </b-card>
        </b-col>
      </b-row>
    </b-container>
  </div>
</template>




<script>
// src\views\Roadmap\RoadMap.vue
// Roadmap 폴더 명 변경을 위한 주석
// 코드 변환 시작 
let go = window.go
let $ = go.GraphObject.make
let myDiagram;
export default {
  name: '',
  components: {
  },
  props: {
      rmid: {
          type: Number,
      },
      mode: {
        type : Number,
      },
  },
  data() {
    return {
      goToBack: '#/godiagram',
        test: { "class": "GraphLinksModel",
  "linkFromPortIdProperty": "fromPort",
  "linkToPortIdProperty": "toPort",
  "nodeDataArray": [ 
{"key":1, "loc":"170 3", "text":"Backend"},
{"text":"Internet", "key":-2, "loc":"170 65"},
{"text":"OS and General Knowledge", "key":-3, "loc":"170.44868850708008 158.388037109375"},
{"text":"Basic \nFrontend \nKnowledge", "key":-4, "loc":"34 65"},
{"text":"CSS", "key":-5, "loc":"-103 65"},
{"text":"JavaScript", "key":-6, "loc":"-81 141"},
{"text":"Terminal Usage", "key":-7, "loc":"338 158"},
{"text":"Learn a Language", "key":-8, "loc":"170.44868850708008 247.38803710937498"},
{"text":"Python", "key":-9, "loc":"-48 207"},
{"text":"Java", "key":-10, "loc":"-47 255"},
{"text":"JavaScript", "key":-11, "loc":"345 212"},
{"text":"Ruby", "key":-12, "loc":"346 262"},
{"text":"C#", "key":-13, "loc":"-47 310"},
{"text":"PHP", "key":-14, "loc":"348 317"},
{"category":"Comment", "text":"Comment", "key":-15, "loc":"341 83"},
{"text":"Version Control\nSystems", "key":-16, "loc":"171.15762499999988 373.94062499999995"},
{"text":"GitHub", "key":-17, "loc":"-32.42375000000001 373.47287500000004"},
{"text":"Relational \nDatabases", "key":-18, "loc":"171.3285 474.62625"},
{"text":"PostgreSQL", "key":-19, "loc":"351.918 434.10937500000006"},
{"text":"MySQL", "key":-20, "loc":"354.23325000000006 483.88725000000005"},
{"text":"More about \nDatabases", "key":-21, "loc":"171.32850000000008 584.600625"}
 ],
  "linkDataArray": [ 
{"from":1, "to":-2, "fromPort":"B", "toPort":"T", "points":[170,21.61196289062501,170,31.61196289062501,170,34.00000000000001,170,34.00000000000001,170,36.388037109375006,170,46.388037109375006]},
{"from":-2, "to":-4, "fromPort":"L", "toPort":"R", "points":[133.11070251464844,65,123.11070251464844,65,106.95141220092773,65,106.95141220092773,65,90.79212188720703,65,80.79212188720703,65]},
{"from":-4, "to":-6, "fromPort":"B", "toPort":"T", "points":[34,101.335888671875,34,111.335888671875,34,111.861962890625,-81,111.861962890625,-81,112.388037109375,-81,122.388037109375]},
{"from":-4, "to":-5, "fromPort":"L", "toPort":"R", "points":[-12.792121887207031,65,-22.79212188720703,65,-46.63113784790039,65,-46.63113784790039,65,-70.47015380859375,65,-80.47015380859375,65]},
{"from":-2, "to":-3, "fromPort":"B", "toPort":"T", "points":[170,83.61196289062501,170,93.61196289062501,170,107.263037109375,170.44868850708008,107.263037109375,170.44868850708008,120.91411132812502,170.44868850708008,130.91411132812502]},
{"from":-3, "to":-7, "fromPort":"R", "toPort":"L", "points":[231.01752090454102,158.38803710937503,241.01752090454102,158.38803710937503,254.5450267791748,158.38803710937503,254.5450267791748,158,268.0725326538086,158,278.0725326538086,158]},
{"from":-3, "to":-8, "fromPort":"B", "toPort":"T", "points":[170.44868850708008,185.86196289062502,170.44868850708008,195.86196289062502,170.44868850708008,207.3190185546875,170.44868850708008,207.3190185546875,170.44868850708008,218.77607421874995,170.44868850708008,228.77607421874995]},
{"from":-8, "to":-9, "fromPort":"L", "toPort":"R", "points":[104.1624641418457,238.08205566406247,94.1624641418457,238.08205566406247,44.87113380432129,238.08205566406247,44.87113380432129,207,-4.420196533203125,207,-14.420196533203125,207]},
{"from":-8, "to":-10, "fromPort":"L", "toPort":"R", "points":[104.1624641418457,247.38803710937495,86.1624641418457,247.38803710937495,36.78439140319824,247.38803710937495,36.78439140319824,254.99999999999997,-12.593681335449219,254.99999999999997,-22.59368133544922,254.99999999999997]},
{"from":-8, "to":-13, "fromPort":"L", "toPort":"R", "points":[104.1624641418457,256.69401855468743,94.1624641418457,256.69401855468743,38.0393123626709,256.69401855468743,38.0393123626709,310,-18.083839416503906,310,-28.083839416503906,310]},
{"from":-8, "to":-11, "fromPort":"R", "toPort":"L", "points":[236.73491287231445,238.08205566406247,246.73491287231445,238.08205566406247,268.96854972839355,238.08205566406247,268.96854972839355,211.99999999999997,291.20218658447266,211.99999999999997,301.20218658447266,211.99999999999997]},
{"from":-8, "to":-12, "fromPort":"R", "toPort":"L", "points":[236.73491287231445,247.38803710937495,254.73491287231445,247.38803710937495,282.0244846343994,247.38803710937495,282.0244846343994,262,309.3140563964844,262,319.3140563964844,262]},
{"from":-8, "to":-14, "fromPort":"R", "toPort":"L", "points":[236.73491287231445,256.69401855468743,246.73491287231445,256.69401855468743,280.140474319458,256.69401855468743,280.140474319458,317,313.54603576660156,317,323.54603576660156,317]},
{"from":-8, "to":-16, "fromPort":"B", "toPort":"T", "points":[170.44868850708008,265.99999999999994,170.44868850708008,275.99999999999994,170.44868850708008,306.23334960937495,171.15762499999988,306.23334960937495,171.15762499999988,336.46669921874997,171.15762499999988,346.46669921874997]},
{"from":-16, "to":-17, "fromPort":"L", "toPort":"R", "points":[109.56991748046863,373.94062499999995,99.56991748046863,373.94062499999995,55.45277581787103,373.94062499999995,55.45277581787103,373.47287500000004,11.335634155273425,373.47287500000004,1.335634155273425,373.47287500000004]},
{"from":-16, "to":-18, "fromPort":"B", "toPort":"T", "points":[171.15762499999988,401.41455078124994,171.15762499999988,411.41455078124994,171.15762499999988,424.2834375,171.3285,424.2834375,171.3285,437.15232421875004,171.3285,447.15232421875004]},
{"from":-18, "to":-19, "fromPort":"R", "toPort":"L", "points":[215.40852471923827,474.62625,225.40852471923827,474.62625,259.3051423950195,474.62625,259.3051423950195,434.109375,293.2017600708008,434.109375,303.2017600708008,434.109375]},
{"from":-18, "to":-20, "fromPort":"R", "toPort":"L", "points":[215.40852471923827,483.7842252604167,225.40852471923827,483.7842252604167,267.79092672729496,483.7842252604167,267.79092672729496,483.88725,310.1733287353516,483.88725,320.1733287353516,483.88725]},
{"from":-18, "to":-21, "fromPort":"B", "toPort":"T", "points":[171.3285,502.10017578125,171.3285,512.1001757812501,171.3285,529.6134375,171.32850000000008,529.6134375,171.32850000000008,547.12669921875,171.32850000000008,557.12669921875]}
 ]},
        roadmapname: '',
        rmorder: '',
    }
  },
  created(){
   console.log(this.test);
  },
  mounted() {
    let self = this
    myDiagram = 
        $(go.Diagram, this.$refs.myDiagramDiv,
          {
            initialContentAlignment: go.Spot.Center,      
            "InitialAnimationStarting": this.animateFadeDown, 
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
            $(go.Shape, "Rectangle",
              { fill: "#282c34", stroke: "#00A9C9", strokeWidth: 3.5 },
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
              { fill: "#282c34", stroke: "#00A9C9", strokeWidth: 3.5 },
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
              { desiredSize: new go.Size(70, 70), fill: "#282c34", stroke: "#09d3ac", strokeWidth: 3.5 }),
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
              { desiredSize: new go.Size(60, 60), fill: "#282c34", stroke: "#DC3C00", strokeWidth: 3.5 }),
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
          mouseEnter: function(e, link) { link.findObject("HIGHLIGHT").stroke = "rgba(30,144,255,0.2)"; },
          mouseLeave: function(e, link) { link.findObject("HIGHLIGHT").stroke = "transparent"; },
          selectionAdorned: false
        },
        new go.Binding("points").makeTwoWay(),
        $(go.Shape,  // the highlight shape, normally transparent
          { isPanelMain: true, strokeWidth: 8, stroke: "transparent", name: "HIGHLIGHT" }),
        $(go.Shape,  // the link path shape
          { isPanelMain: true, stroke: "gray", strokeWidth: 2 },
          new go.Binding("stroke", "isSelected", function(sel) { return sel ? "dodgerblue" : "gray"; }).ofObject()),
        $(go.Shape,  // the arrowhead
          { toArrow: "standard", strokeWidth: 0, fill: "gray" }),
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
        console.log(e.subject.part.data.key);
        console.log(e.subject.part.data.text);
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
  },
  watch:{},
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
        stroke: "#F8F8F8"
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
      //   this.test = { "class": "go.GraphLinksModel",
      //   "linkFromPortIdProperty": "fromPort",
      //   "linkToPortIdProperty": "toPort",
      //   "nodeDataArray": [
      // ],
      //   "linkDataArray": [
      // ]}
       myDiagram.model = go.Model.fromJson(this.test);
      }else{
      axios.get(`${this.$store.getters.getServer}/roadmap/get/${this.rmid}`)
        .then((res) => {
          this.test =  JSON.parse(res.data['roadmaps'].tmp);
          this.roadmapname = res.data['roadmaps'].name;
          this.rmorder = res.data['roadmaps'].rmorder
          // console.log('check', this.roadmapname, this.rmorder)
          myDiagram.model = go.Model.fromJson(this.test);
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
        console.log(res)
        console.log('응답')
        this.$router.push({ name: 'godiagram' })
      })
      .catch((err) =>{
        console.error(err)
      })
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
        console.log(res)
        console.log('응답')
        this.$router.push({ name: 'godiagram' })
      })
    },
  },
}
</script>

<style>

</style>