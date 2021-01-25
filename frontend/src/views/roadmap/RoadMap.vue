<template>
  <div>
    <base-header class="pb-6 pb-8 pt-5 pt-md-8 bg-gradient-success">
      <!-- Card stats -->
      
    </base-header>

    <b-container fluid class="mt--7">
      <b-row>
        <b-col>
          <b-card no-body class="border-0">
            <div style="width: 150%; text-align: left;">
              <!-- diagram -->
              <div id="myDiagramDiv" style="width: 66%; height: 500px; border: 1px solid black;">

              </div>
            </div>
          </b-card>
        </b-col>
      </b-row>
    </b-container>
  </div>
</template>

<script>
export default {
  name: '',
  data() {
    return {
      nodeDataArray: [
        { key: "Alpha", color: "lime", size: 10 },
        { key: "Beta", color: "cyan" },
        { key: "Zeta", isGroup: true },
        { key: "Delta", color: "pick", group: "Zeta" },
        { key: "Gamma", color: "marron", group: "Zeta" }
      ],
      linkDataArray: [
        { to: "Beta", from: "Alpha", color: "red" },
        { from: "Alpha", to: "Zeta" }
      ]
    }
  },
  mounted() {
    this.init()  
  },
  created: {
  },
  methods: {
    init () {
      const $ = go.GraphObject.make;
      const myDiagram = $(go.Diagram, "myDiagramDiv");
      myDiagram.model = new go.GraphLinksModel(this.nodeDataArray, this.linkDataArray)
      myDiagram.nodeTemplate = 
        $(go.Node, "Auto",
          $(go.Shape, "RoundedRectangle", { fill: "white"},
            new go.Binding("fill", "color")
          ),
          $(go.TextBlock, "text", { margin: 20 },
            new go.Binding("text", "key"),
            new go.Binding("margin", "size")
          )  
        )
      console.log('hi')
      myDiagram.linkTemplate = 
        $(go.Link,
          $(go.Shape, { strokeWidth: 3 },
            new go.Binding("stroke", "color")  
          ),
          $(go.Shape, { toArrow: "Standard", stroke: null},
            new go.Binding("fill", "color")
          ),
      )
    } 
  }
}
</script>

<style>

</style>