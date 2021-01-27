<template>
  <div class="nothing-main">
    <b-navbar-brand to="/" class="mt-5 ml-5">
      <img src="img/brand/logo_blue.png" width="250rem;">
    </b-navbar-brand>
    <!-- <base-nav
      :transparent="true"
      class="navbar-horizontal navbar-main navbar-top navbar-dark"
      expand="lg"
    >
      <div slot="brand" class="navbar-wrapper">
        <b-navbar-brand to="/">
          <img src="img/brand/green.png">
        </b-navbar-brand>
      </div>
    </base-nav> -->
    <b-container>
      <b-jumbotron class="mt-5">
        <template #header>
          <div class="display-1">
            개발자 로드맵을 그려보세요
          </div>
          <h1>with ROADWIKI</h1>
        </template>
        <template #lead>
        </template>
        <p>
          For more information visit websiteFor more information visit websiteFor more information visit website
        </p>
        <LoginContent class="mb-3"/>
        계정이 없다면?
        <b-link href="/register"><strong>가입하러 가기</strong></b-link>
      </b-jumbotron>
    </b-container>
  </div>
</template>

<script>
import LoginContent from '@/components/Login/LoginContent.vue'

export default {
  components: {
    LoginContent,
  },
}
</script>

<style>
.nothing-main {
  background-image: url("../../public/img/brand/background_dark.png");
  background-repeat: no-repeat;
  background-size: cover;
  height: 100vh;
  width: 100vw;
}
.jumbotron {
  background-color: rgba(0, 0, 0, 0);
  color: rgba(255, 255, 255, 0.8);
}
.jumbotron h1 {
  background-color: rgba(0, 0, 0, 0);
  color: rgba(255, 255, 255, 0.8);
}
</style>