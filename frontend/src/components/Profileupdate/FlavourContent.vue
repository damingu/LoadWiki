<template>
  <div>
    <b-button variant="default" size="sm" @click="modalShow = !modalShow">수정하기</b-button>

    <b-modal v-model="modalShow" hide-footer>
      <template #modal-title>
        <h1>관심 개발 분야</h1>
      </template>
      <div>
        <b-form-group label="" v-slot="{ ariaDescribedby }">
          <p>가장 관심있는 것부터 순서대로 체크해주세요. (3개 이상 선택 가능)</p>
          <b-container>
            <b-form-checkbox
              v-for="option in options"
              v-model="selected"
              :key="option.value"
              :value="option.value"
              :aria-describedby="ariaDescribedby"
              name="flavour-3a"
            >
              {{ option.text }}
            </b-form-checkbox>
            <hr>
            <b-row class="justify-content-md-center">
              <b-col class="ml-3">1순위</b-col> 
              <b-col v-if="selected.length > 0">{{ options[selected[0]-1].text }}</b-col>
            </b-row>
            <b-row class="justify-content-md-center">
              <b-col class="ml-3">2순위</b-col> 
              <b-col v-if="selected.length > 1">{{ options[selected[1]-1].text }}</b-col>
            </b-row>
            <b-row class="justify-content-md-center">
              <b-col class="ml-3">3순위</b-col> 
              <b-col v-if="selected.length > 2">{{ options[selected[2]-1].text }}</b-col>
            </b-row>
          </b-container>
        </b-form-group>
      </div>
      <div class="text-center">
        <base-button type="primary" native-type="submit" class="my-4" @click="sendFlavour">확인</base-button>
      </div>
    </b-modal>
  </div>
  <!-- <b-modal id="modal-login">
  </b-modal> -->
</template>

<script>
export default {
  data() {
    return {
      modalShow: false,
        selected: [],
        options: [
          { text: 'Python', value: 1},
          { text: 'JAVA', value: 2},
          { text: 'C', value: 3},
          { text: 'Vue', value: 4},
          { text: 'Spring', value: 5},
          { text: 'Frontend', value: 6},
          { text: 'Backend', value: 7},
          { text: 'Database', value: 8},
          { text: 'AI', value: 9},
          { text: '기타', value: 10},
        ],
      }
    },
  methods: {
    sendFlavour() {
      this.modalShow = false
    },
  },
}
</script>

<style>

</style>