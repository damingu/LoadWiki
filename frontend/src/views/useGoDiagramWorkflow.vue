<template>
  <div>
    <base-header class="pb-6 pb-8 pt-5 pt-md-8 bg-gradient-success">
      <!-- Card stats -->
      <b-row>
        <b-col xl="3" md="6">
          <stats-card title="Total traffic"
                      type="gradient-red"
                      sub-title="350,897"
                      icon="ni ni-active-40"
                      class="mb-4">

            <template slot="footer">
              <span class="text-success mr-2">3.48%</span>
              <span class="text-nowrap">Since last month</span>
            </template>
          </stats-card>
        </b-col>
        <b-col xl="3" md="6">
          <stats-card title="Total traffic"
                      type="gradient-orange"
                      sub-title="2,356"
                      icon="ni ni-chart-pie-35"
                      class="mb-4">

            <template slot="footer">
              <span class="text-success mr-2">12.18%</span>
              <span class="text-nowrap">Since last month</span>
            </template>
          </stats-card>
        </b-col>
        <b-col xl="3" md="6">
          <stats-card title="Sales"
                      type="gradient-green"
                      sub-title="924"
                      icon="ni ni-money-coins"
                      class="mb-4">

            <template slot="footer">
              <span class="text-danger mr-2">5.72%</span>
              <span class="text-nowrap">Since last month</span>
            </template>
          </stats-card>

        </b-col>
        <b-col xl="3" md="6">
          <stats-card title="Performance"
                      type="gradient-info"
                      sub-title="49,65%"
                      icon="ni ni-chart-bar-32"
                      class="mb-4">

            <template slot="footer">
              <span class="text-success mr-2">54.8%</span>
              <span class="text-nowrap">Since last month</span>
            </template>
          </stats-card>
        </b-col>
      </b-row>
    </base-header>

    <b-container fluid class="mt--7">
      <b-row>
        <b-col>
          <b-card no-body class="border-0">
            <div style="width: 100%;">
              <div style="width: 100%; display: flex; justify-content: space-between; vertical-align: baseline;">
                <div ref="myDiagramDiv" style="flex-grow: 1; height: 750px; background-color: #2565AB"></div>
              </div>
              <div class="col three" @click="updateRoadMap">				
			          <a class="btn">로드맵 수정하기</a>			
              </div>
            </div>
          </b-card>
        </b-col>
      </b-row>
    </b-container>
  </div>
</template>


<script>
import RoadMap from '@/views/Roadmap/RoadMap'

// 코드 변환 시작 
let go = window.go
let $ = go.GraphObject.make
let myDiagram;
export default {
  name: '',
  data() {
    return {
      test: {
          class: "go.GraphLinksModel",
          linkFromPortIdProperty: "fromPort",
          linkToPortIdProperty: "toPort",
          nodeDataArray: [
            {"key":-1, "category":"Start", "loc":"175 0", "text":"웹 개발"},
            {"key":0, "loc":"-5 75", "text":"front"},
            {"key":1, "loc":"175 100", "text":"back"},
            {"key":2, "loc":"175 200", "text":"Gradually beat in 1 cup sugar and 2 cups sifted flour"},
            {"key":3, "loc":"175 290", "text":"Mix in 6 oz (1 cup) Nestle's Semi-Sweet Chocolate Morsels"},
            {"key":4, "loc":"175 380", "text":"Press evenly into ungreased 15x10x1 pan"},
            {"key":5, "loc":"355 85", "text":"Finely chop 1/2 cup of your choice of nuts"},
            {"key":6, "loc":"175 450", "text":"Sprinkle nuts on top"},
            {"key":7, "loc":"175 515", "text":"Bake for 25 minutes and let cool"},
            {"key":8, "loc":"175 585", "text":"Cut into rectangular grid"},
            {"key":-2, "category":"End", "loc":"175 660", "text":"Enjoy!"}
            ],
          linkDataArray: [
            {"from":1, "to":2, "fromPort":"B", "toPort":"T"},
            {"from":2, "to":3, "fromPort":"B", "toPort":"T"},
            {"from":3, "to":4, "fromPort":"B", "toPort":"T"},
            {"from":4, "to":6, "fromPort":"B", "toPort":"T"},
            {"from":6, "to":7, "fromPort":"B", "toPort":"T"},
            {"from":7, "to":8, "fromPort":"B", "toPort":"T"},
            {"from":8, "to":-2, "fromPort":"B", "toPort":"T"},
            {"from":-1, "to":0, "fromPort":"B", "toPort":"T"},
            {"from":-1, "to":1, "fromPort":"B", "toPort":"T"},
            {"from":-1, "to":5, "fromPort":"B", "toPort":"T"},
            {"from":5, "to":4, "fromPort":"B", "toPort":"T"},
            {"from":0, "to":4, "fromPort":"B", "toPort":"T"}
      ]},
    }
  },
  mounted() {
    let self = this
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
              { fill: "#F0DC45", stroke: "#E6E9F0", strokeWidth:  7, strokeJoin: "round", strokeCap: "square" },
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
              { desiredSize: new go.Size(70, 70), fill: "#ffffff", stroke: "#5F7044", strokeWidth: 3.5 }),
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
              { desiredSize: new go.Size(60, 60), fill: "#ffffff", stroke: "#E17E23", strokeWidth: 3.5 }),
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
          { isPanelMain: true, strokeWidth: 8, stroke: "transparent", name: "HIGHLIGHT" }),
        $(go.Shape,  // the link path shape
          { isPanelMain: true, stroke: "#112812", strokeWidth: 2.5 },
          new go.Binding("stroke", "isSelected", function(sel) { return sel ? "#112812" : "#112812"; }).ofObject()),
        $(go.Shape,  // the arrowhead
          { toArrow: "Triangle", strokeWidth: 1.5, stroke: "#002942", fill: "#002942"}),
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
      // myDiagram.isReadOnly = true ;

      this.load();

      // canvas 내의 node 요소 잡기 
      myDiagram.addDiagramListener("ObjectSingleClicked", function(e) {
      console.log(e.subject.part.data.key);
      console.log(e.subject.part.data.text);
});

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

    // 외부 json파일 초기하면에 출력
    load() {
      myDiagram.model = go.Model.fromJson(this.test);
    },
    // 로드맵 수정버튼 
    updateRoadMap() {
      this.$router.push({ name: 'roadmap' })
    },
    
  },
}
</script>

<style>

</style>