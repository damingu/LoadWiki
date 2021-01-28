<template>
  <b-card no-body>
    <b-card-header class="border-0">
      <b-button class="mb-2" variant="info" size="sm" @click="goBack"
        >go back</b-button
      >
    </b-card-header>

    <!-- class="table-responsive table" -->
    <b-container fluid="lg" :items="frame">
      <template v-for="(item, idx) in frame">
        <b-container fluid="lg" :key="idx">
          <b-row class="mb-2">
            <b-col class="text-sm-center" sm="3" width="120px">
              <span class="font-weight-600 name mb-0 text-sm">
                {{ item.desc }}
              </span>
            </b-col>
            <b-col sm="5" width="120px">
              <span class="font-weight-600 name mb-0 text-sm">
                {{ item.data }}
              </span>
            </b-col>
          </b-row>
          <template #row-details="item">
            <b-row class="mb-2">
              <b-col sm="3" class="text-sm-right" min-height="500px">
                {{ item.content }}
              </b-col>
            </b-row>
          </template>
        </b-container>
      </template>
      <!-- <b-col min-width="240px">
        <template v-slot="{ row }">
          <b-media no-body class="align-items-center">
            <b-media-body>
              <span class="font-weight-600 name mb-0 text-sm">{{
                row.data
              }}</span>
            </b-media-body>
          </b-media>
        </template>
      </b-col>
      <template #row-details="row">
        <b-row class="mb-2">
          <b-col sm="3" class="text-sm-right" min-height="500px">
            {{ row.item.content }}
          </b-col>
        </b-row>
      </template> -->
    </b-container>

    <b-card-footer class="py-4 d-flex justify-content-end"> </b-card-footer>
  </b-card>
</template>
<script>
export default {
  data() {
    return {
      desc: "DESC",
      frame: [
        {
          desc: "TITLE",
          data: ""
        },
        {
          desc: "NAME",
          data: ""
        },
        {
          desc: "TIME",
          data: ""
        },
        {
          desc: "CONTENT",
          data: "",
          content: "",
          _showDetails: true
        },
        {
          desc: "LIKE",
          data: ""
        }
      ]
    };
  },
  props: ["pid"],
  watch: {
    pid: function(pid, old) {
      this.getPosting();
    }
  },
  methods: {
    getPosting() {
      var adr = `${this.$store.getters.getServer}/freeboard/posting/${this.pid}`;
      axios
        .get(adr)
        .then(response => {
          var posting = response.data.posting;
          this.frame[0].data = posting.title;
          this.frame[1].data = response.data.name;
          this.frame[2].data = posting.createDate;
          this.frame[3].data = posting.content;
          this.frame[4].data = posting.likeCnt;
        })
        .catch(response => {
          console.log("FAIL", response);
        });
    },
    goBack() {
      this.$emit("goBack");
    }
  },
  created() {
    this.getPosting();
  }
};
</script>
<style></style>
