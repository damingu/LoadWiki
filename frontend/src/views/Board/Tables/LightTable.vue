<template>
  <b-card class="m-2" no-body>
    <!-- <b-card-header class="border-0">
      <h3 class="mb-0">TMP_BOARD</h3>
    </b-card-header> -->

    <el-table
      class="table-responsive table"
      header-row-class-name="thead-light"
      :data="postings"
      @row-click="openDetail"
    >
      <el-table-column label="ID" min-width="120px" prop="ID">
        <template v-slot="{ row }">
          <b-media no-body class="align-items-center">
            <b-media-body>
              <span class="font-weight-600 name mb-0 text-sm">{{
                row.pid
              }}</span>
            </b-media-body>
          </b-media>
        </template>
      </el-table-column>

      <el-table-column label="TITLE" min-width="300px" prop="title">
        <template v-slot="{ row }">
          <b-media no-body class="align-items-center">
            <b-media-body>
              <span class="font-weight-600 name mb-0 text-sm">{{
                row.title
              }}</span>
            </b-media-body>
          </b-media>
        </template>
      </el-table-column>

      <el-table-column
        label="NAME"
        min-width="180px"
        prop="name"
        v-if="!isClicked"
      >
        <template v-slot="{ row }">
          <b-media no-body class="align-items-center">
            <b-media-body>
              <span class="font-weight-600 name mb-0 text-sm">{{
                row.name
              }}</span>
            </b-media-body>
          </b-media>
        </template>
      </el-table-column>

      <el-table-column
        label="LIKE"
        prop="like"
        min-width="120px"
        v-if="!isClicked"
      >
        <template v-slot="{ row }">
          <div class="d-flex align-items-center">
            <div>
              <span class="font-weight-600 name mb-0 text-sm">{{
                row.likeCnt
              }}</span>
            </div>
          </div>
        </template>
      </el-table-column>

      <el-table-column
        label="TAG"
        prop="tag"
        min-width="120px"
        v-if="!isClicked"
      >
        <template v-slot="{ row }">
          <div class="d-flex align-items-center">
            <div>
              <span class="font-weight-600 name mb-0 text-sm">{{
                row.tag
              }}</span>
            </div>
          </div>
        </template>
      </el-table-column>

      <el-table-column
        label="CREATEDATE"
        prop="createdate"
        min-width="160px"
        v-if="!isClicked"
      >
        <template v-slot="{ row }">
          <div class="d-flex align-items-center">
            <div>
              <span class="font-weight-600 name mb-0 text-sm">{{
                row.createDate
              }}</span>
            </div>
          </div>
        </template>
      </el-table-column>
    </el-table>
    <b-card-footer class="py-4 d-flex justify-content-end">
      <base-pagination
        v-model="currentPage"
        :per-page="10"
        :total="30"
      ></base-pagination>
    </b-card-footer>
  </b-card>
</template>
<script>
import { Table, TableColumn } from "element-ui";
// TableColumn.rowKey = ["pid", "uid", "tag", "title", "createDate"];

export default {
  name: "light-table",
  components: {
    [Table.name]: Table,
    //   [TableColumn.name]: TableColumn
    [TableColumn.name]: TableColumn
  },
  props: ["isClicked"],
  data() {
    return {
      currentPage: 1,
      word: "",
      selector: "none",
      tag: "",
      postings: [],
      names: []
    };
  },
  methods: {
    getList: function() {
      var adr = `${this.$store.getters.getServer}/freeboard/list/board/${this.selector}`;
      if (this.word === "") adr += "/ ";
      else adr + `/${this.word}`;
      adr += `/${this.currentPage}`;
      if (this.tag != "") adr += `${this.tag}`;
      axios
        .get(adr)
        .then(response => {
          console.log("SUCCESS");
          this.postings = response.data.postings;
          this.names = response.data.names;
          for (var i = 0; i < this.postings.length; i++) {
            this.postings[i].createDate = this.$moment(
              this.postings[i].createDate
            ).format("MM/DD HH:mm");
            this.postings[i].name = this.names[i];
          }
        })
        .catch(response => {
          console.log("FAIL", response);
        });
    },
    openDetail: function(row) {
      this.$emit("clickRow", row.pid);
    }
  },
  created() {
    this.getList();
  }
};
</script>
