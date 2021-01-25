<template>
  <div>
    <b-form-group label="관심 개발 분야" v-slot="{ ariaDescribedby }">
      <p>1순위부터 체크해주세요</p>
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
        <!-- <b-form-checkbox-group
          id="checkbox-group-1"
          v-model="selected"
          :options="options"
          :aria-describedby="ariaDescribedby"
          name="flavour-1"
        >
        </b-form-checkbox-group> -->

      </b-container>
    </b-form-group>

    <div>1순위 <strong>{{ selected[0] }}</strong></div>
    <div>2순위 <strong>{{ selected[1] }}</strong></div>
    <div>3순위 <strong>{{ selected[2] }}</strong></div>
  </div>
</template>

<script>
  export default {
    data() {
      return {
        selected: [],
        options: [
          { text: 'Python', value: 'Python'},
          { text: 'JAVA', value: 'JAVA'},
          { text: 'C', value: 'C'},
          { text: 'Vue', value: 'Vue'},
          { text: 'Spring', value: 'Spring'},
          { text: 'Frontend', value: 'Frontend'},
          { text: 'Backend', value: 'Backend'},
          { text: 'Database', value: 'Database'},
          { text: 'AI', value: 'AI'},
          { text: '기타', value: '기타'},
        ],
        
      }
    }
  }
</script>

<style>

</style>