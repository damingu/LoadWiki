<template>
  <div id="sample">
    <div style="width: 100%; display: flex; justify-content: space-between">
      <div ref="myPaletteDiv" style="width: 100px; margin-right: 2px; background-color: #282c34;"></div>
      <div ref="myDiagramDiv" style="flex-grow: 1; height: 750px; background-color: #282c34;"></div>
    </div>
    <p>
    The FlowChart sample demonstrates several key features of GoJS,
    namely <a href="../intro/palette.html">Palette</a>s,
    <a href="../intro/links.html">Linkable nodes</a>, Drag/Drop behavior,
    <a href="../intro/textBlocks.html">Text Editing</a>, and the use of
    <a href="../intro/templateMaps.html">Node Template Maps</a> in Diagrams.
    </p>
    <p>
    Mouse-over a Node to view its ports.
    Drag from these ports to create new Links.
    Selecting Links allows you to re-shape and re-link them.
    Selecting a Node and then clicking its TextBlock will allow
    you to edit text (except on the Start and End Nodes).
    </p>
    <button id="SaveButton" onclick="save()">Save</button>
    <button onclick="load()">Load</button>
    Diagram Model saved in JSON format:
    <textarea id="mySavedModel" style="width:100%;height:300px">{ "class": "go.GraphLinksModel",
    "linkFromPortIdProperty": "fromPort",
    "linkToPortIdProperty": "toPort",
    "nodeDataArray": [
  {"category":"Comment", "loc":"360 -10", "text":"Kookie Brittle", "key":-13},
  {"key":-1, "category":"Start", "loc":"175 0", "text":"Start"},
  {"key":0, "loc":"-5 75", "text":"Preheat oven to 375 F"},
  {"key":1, "loc":"175 100", "text":"In a bowl, blend: 1 cup margarine, 1.5 teaspoon vanilla, 1 teaspoon salt"},
  {"key":2, "loc":"175 200", "text":"Gradually beat in 1 cup sugar and 2 cups sifted flour"},
  {"key":3, "loc":"175 290", "text":"Mix in 6 oz (1 cup) Nestle's Semi-Sweet Chocolate Morsels"},
  {"key":4, "loc":"175 380", "text":"Press evenly into ungreased 15x10x1 pan"},
  {"key":5, "loc":"355 85", "text":"Finely chop 1/2 cup of your choice of nuts"},
  {"key":6, "loc":"175 450", "text":"Sprinkle nuts on top"},
  {"key":7, "loc":"175 515", "text":"Bake for 25 minutes and let cool"},
  {"key":8, "loc":"175 585", "text":"Cut into rectangular grid"},
  {"key":-2, "category":"End", "loc":"175 660", "text":"Enjoy!"}
  ],
    "linkDataArray": [
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
  ]}
    </textarea>
    <button onclick="printDiagram()">Print Diagram Using SVG</button>
  </div>
</template>

<script>

// 코드 변환 시작 
let go = window.go
let $ = go.GraphObject.make

export default {
  name: '',
  data() {
    return {
      nodeDataArray: [
  {"category":"Comment", "loc":"360 -10", "text":"Kookie Brittle", "key":-13},
  {"key":-1, "category":"Start", "loc":"175 0", "text":"Start"},
  {"key":0, "loc":"-5 75", "text":"Preheat oven to 375 F"},
  {"key":1, "loc":"175 100", "text":"In a bowl, blend: 1 cup margarine, 1.5 teaspoon vanilla, 1 teaspoon salt"},
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
  ]
    }
  },
  mounted() {
    let self = this
    let myDiagram =
        $(go.Diagram, this.$refs.myDiagramDiv,
          {
            initialContentAlignment: go.Spot.Center,      
        })
      // 외부 json파일 초기하면에 출력 
      myDiagram.model = new go.GraphLinksModel(this.nodeDataArray, this.linkDataArray)

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
    console.log(myPalette)
    // replace the default Link template in the linkTemplateMap
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
      ) ;
    // 왼쪽 node option부분 
    console.log(this.$refs)
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
    this.diagram = myDiagram
  },
  watch:{},
  computed: {},
  methods: {
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
    textStyle() {
        return {
          font: "bold 11pt Lato, Helvetica, Arial, sans-serif",
          stroke: "#F8F8F8"
        }
      }
  },
  model: function () {
            return this.diagram.model
        },

}
</script>

<style>

</style>