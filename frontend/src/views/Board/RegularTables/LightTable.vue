<template>
  <b-card no-body>
    <b-card-header class="border-0">
      <h3 class="mb-0">Light table</h3>
    </b-card-header>

    <el-table
      class="table-responsive table"
      header-row-class-name="thead-light"
      :data="postings"
    >
      <el-table-column label="TITLE" min-width="360px" prop="title">
        <template v-slot="{ row }">
          <b-media no-body class="align-items-center">
            <a href="#" class="avatar rounded-circle mr-3">
              <img alt="Image placeholder" :src="row.image" />
            </a>
            <b-media-body @click="openDetail(row)">
              <span class="font-weight-600 name mb-0 text-sm">{{
                row.title
              }}</span>
            </b-media-body>
          </b-media>
        </template>
      </el-table-column>

      <el-table-column label="NAME" min-width="120px" prop="name">
        <template v-slot="{ row }">
          <b-media no-body class="align-items-center">
            <b-media-body>
              <span class="font-weight-600 name mb-0 text-sm">{{
                row.uid
              }}</span>
            </b-media-body>
          </b-media>
        </template>
      </el-table-column>

      <el-table-column label="TAG" prop="tag" min-width="120px">
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

      <el-table-column label="LIKE" prop="like" min-width="60px">
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

      <el-table-column label="CREATEDATE" prop="createdate" min-width="120px">
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
        :total="50"
      ></base-pagination>
    </b-card-footer>
  </b-card>
</template>
<script>
import moment from "vue-moment";
import projects from "./../projects";
import { Table, TableColumn } from "element-ui";
// TableColumn.rowKey = ["pid", "uid", "tag", "title", "createDate"];
export default {
  name: "light-table",
  components: {
    [Table.name]: Table,
    //   [TableColumn.name]: TableColumn
    [TableColumn.name]: TableColumn
  },
  computed: {
    getRegDate: function(obj) {}
  },
  data() {
    return {
      projects,
      currentPage: 1,
      word: "",
      selector: "none",
      tag: "",
      postings: []
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
          this.postings = response.data.postings;
          console.log(this.postings);
        })
        .catch(response => {
          console.log("FAIL", response);
        });
    },
    openDetail: function(obj) {
      console.log(obj);
    },
    getName: function(obj) {
      console.log(obj);
    }
  },
  created() {
    this.getList();
  }
};
</script>
