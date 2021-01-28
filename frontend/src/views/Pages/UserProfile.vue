<template>
  <div>
    <div class="header pb-8 pt-5 pt-lg-8 d-flex align-items-center profile-header"
        style="min-height: 600px; background-image: url(img/theme/profile-cover.jpg); background-size: cover; background-position: center top;">
      <b-container fluid>
        <!-- Mask -->
        <span class="mask bg-gradient-default opacity-8"></span>
        <!-- Header container -->
        <b-container fluid class="d-flex align-items-center">
          <b-row >
            <b-col lg="7" md="10">
              <!-- 닉네임 들어갈 부분 -->
              <h1 class="display-2 text-white opacity-8">{{ nickname }}</h1>
              <!-- 한줄 소개 들어갈 부분 -->
              <p class="text-white mt-0 mb-3">
                술잔을 들자하니 천하가 내발아래 있고 6팀 친구들 또한 옆에 있으니 염라대왕 두렵지 않구나
              </p>
              <div>
                <svg xmlns="http://www.w3.org/2000/svg" width="24" height="24" viewBox="0 0 24 24">
                  <path d="M19 0h-14c-2.761 0-5 2.239-5 5v14c0 2.761 2.239 5 5 5h14c2.762 0 5-2.239 5-5v-14c0-2.761-2.238-5-5-5zm-4.466 19.59c-.405.078-.534-.171-.534-.384v-2.195c0-.747-.262-1.233-.55-1.481 1.782-.198 3.654-.875 3.654-3.947 0-.874-.312-1.588-.823-2.147.082-.202.356-1.016-.079-2.117 0 0-.671-.215-2.198.82-.64-.18-1.324-.267-2.004-.271-.68.003-1.364.091-2.003.269-1.528-1.035-2.2-.82-2.2-.82-.434 1.102-.16 1.915-.077 2.118-.512.56-.824 1.273-.824 2.147 0 3.064 1.867 3.751 3.645 3.954-.229.2-.436.552-.508 1.07-.457.204-1.614.557-2.328-.666 0 0-.423-.768-1.227-.825 0 0-.78-.01-.055.487 0 0 .525.246.889 1.17 0 0 .463 1.428 2.688.944v1.489c0 .211-.129.459-.528.385-3.18-1.057-5.472-4.056-5.472-7.59 0-4.419 3.582-8 8-8s8 3.581 8 8c0 3.533-2.289 6.531-5.466 7.59z"/>
                </svg>
                <b-link href="https://github.com/Jo-Myounghee" class="text-white opacity-8">
                  https://github.com/Jo-Myounghee
                </b-link>
              </div>
              <br>
              <router-link :to="{ name : 'profile-update' }" class="btn btn-primary">수정하기</router-link>
              <!-- <a href="#!" class="btn btn-info">Edit profile</a> -->
              
            </b-col>
          </b-row>
        </b-container>
      </b-container>
    </div>

    <b-container fluid class="mt--6">
      <b-card no-body class="card-profile" alt="Image placeholder" img-top>
        <b-row class="justify-content-center">
          <b-col lg="3" class="order-lg-2">
            <div class="card-profile-image">
              <a href="#">
                <b-img src="img/theme/team-4.jpg" rounded="circle" />
              </a>
            </div>
          </b-col>
        </b-row>

        <b-card-header class="text-center border-0 pt-8 pt-md-4 pb-0 pb-md-4 mb-4">
          <!-- <div class="d-flex justify-content-between">
            <a href="#" class="btn btn-sm btn-info mr-4">Connect</a>
            <a href="#" class="btn btn-sm btn-default float-right">Message</a>
          </div> -->
        </b-card-header>
        <b-card-body class="pt-0">
          <b-row>
            <b-col>
              <div class="card-profile-stats d-flex justify-content-center mt-md-5">
                <div>
                  <span class="heading">22</span>
                  <span class="description">follower</span>
                </div>
                <div>
                  <span class="heading">10</span>
                  <span class="description">following</span>
                </div>
                <div>
                  <span class="heading">8</span>
                  <span class="description">게시글</span>
                </div>
                <div>
                  <span class="heading">89</span>
                  <span class="description">댓글</span>
                </div>
              </div>
            </b-col>
          </b-row>
          <div class="text-center">
            <h1 class="display-2">
              {{ nickname }}
            </h1>
            <div class="mb-2 h5">
              <i class="ni ni-hat-3 mr-2"></i>기계공학
            </div>
            <div class="h3 font-weight-300">
              <b-badge variant="warning" class="mx-1" v-for="(keyword, idx) in keywords" :key="idx">
                {{ keyword }}
              </b-badge>
            </div>
            <hr class="my-4">
            <h3>
              <i class="ni ni-palette mr-2"></i>
              생성한 로드맵 모음
            </h3>
            <hr class="my-4">
            <!-- <p>Ryan — the name taken by Melbourne-raised, Brooklyn-based Nick Murphy — writes, performs and records all of his own music.</p> -->
            <!-- <b-button variant="primary" class="mt-4" @click="withDrawal">회원탈퇴</b-button> -->

          </div>
        </b-card-body>
      </b-card>
      <!-- <b-row> -->
        <!-- <b-col xl="4" class="order-xl-2 mb-5"> -->
          <!-- <user-card></user-card> -->
        <!-- </b-col> -->
        <!-- <b-col xl="8" class="order-xl-1"> -->
          <!-- <edit-profile-form></edit-profile-form> -->
        <!-- </b-col> -->
      <!-- </b-row> -->
    </b-container>
  </div>
</template>
<script>
  // import EditProfileForm from './UserProfile/EditProfileForm.vue';
  import UserCard from './UserProfile/UserCard.vue';

  export default {
    components: {
      // EditProfileForm,
      UserCard,
    },
    data() {
      return{
        nickname: '',
        sentence: '',
        address: '',
        profileImg: '',
        backImg: '',
        keywords: [],
        follower: '',
        following: '',
        boards: '',
        comments: '',
        major: '',
        email: '',
      }
    },
    created() {
      axios.get(`${this.$store.getters.getServer}/user/info`)
      .then((res) => {
        console.log(res.data)
        this.nickname = res.data.name
        this.email = res.data.email
        this.keywords = res.data.keywords
      })
      .catch(() => {
        alert('로그인이 필요한 서비스입니다.')
        this.$store.dispatch("LOGOUT")
        .then(() => {
          this.$router.replace('/')
        })
      })
    },
    methods: {
      withDrawal() {
        axios.delete(`${this.$store.getters.getServer}/user/withdraw`)
        .then(() => {
          alert('회원 탈퇴가 완료되었습니다.')
          this.$router.replace('/')
        })
        .catch(() => {
          alert('오류가 발생했습니다. 다시 시도해주세요.')
        })
      },
    },
  };
</script>
<style>
</style>
