<template>
  <div>
    <div class="header pb-8 pt-5 pt-lg-8 d-flex align-items-center profile-header"
        style="min-height: 600px; background-image: url(img/theme/profile-cover.jpg); background-size: cover; background-position: center top;">
      <b-container class="align-items-center">
        <b-row class="justify-content-end"><BackgroundImg/></b-row>
      </b-container>
    </div>

    <b-container fluid class="mt--6">
      <b-card no-body class="card-profile" alt="Image placeholder" img-top>
        <b-row class="justify-content-start">
          <b-col lg="3" class="order-lg-2">
            <b-container class="card-profile-image">
              <b-row>
                <b-img src="img/theme/team-4.jpg" rounded="circle" />
              </b-row>
              <b-row class="justify-content-end">
                <ProfileImg/>
              </b-row>
            </b-container>
          </b-col>
        </b-row>

        <b-card-header class="text-center border-0 pt-8 pt-md-4 pb-0 pb-md-4 mb-4">
          <h1 class="display-1">
            <!-- email.com -->
          </h1>
        </b-card-header>
        <b-card-body class="pt-0">
          <b-row>
            <b-col>
              <div class="card-profile-stats d-flex justify-content-center mt-md-5">
              </div>
            </b-col>
          </b-row>
          <b-container>
            <!-- <hr class="my-4"> -->
            <b-row class="mb-3">
              <b-col cols="3" class="text-center" align-self="center">
                <h2>
                  <!-- <i class="ni ni-hat-3 mr-2"></i> -->
                  닉네임
                </h2>
              </b-col>
              <b-col>
                <b-form-input value="nike"></b-form-input>
              </b-col>
            </b-row>
            <b-row class="mb-3">
              <b-col cols="3" class="text-center" align-self="center">
                <h2>
                  <!-- <i class="ni ni-hat-3 mr-2"></i> -->
                  전공
                </h2>
              </b-col>
              <b-col>
                <b-form-input value="기계공학"></b-form-input>
              </b-col>
            </b-row>
            <b-row class="mb-3">
              <b-col cols="3" class="text-center" align-self="center">
                <h2>
                  <!-- <i class="ni ni-hat-3 mr-2"></i> -->
                  블로그
                </h2>
              </b-col>
              <b-col>
                <b-form-input value="https://github.com/Jo-Myounghee"></b-form-input>
              </b-col>
            </b-row>
            <b-row class="mb-3">
              <b-col cols="3" class="text-center" align-self="center">
                <h2>
                  <!-- <i class="ni ni-hat-3 mr-2"></i> -->
                  한 줄 소개
                </h2>
              </b-col>
              <b-col>
                <b-form-textarea 
                    value="
                      나에게 힘을 주는 인생에 관한 명언 48가지 : 고개 숙이지 마십시오. ... 고난의 시기에 동요하지 않는 것, ... 사막이 아름다운 것은 ...행복의 한 쪽 문이 닫히면 다른 쪽 문이 열린다. ... 만족할 줄 아는 사람은 진정한 부자이고, ... 성공해서 만족하는 것은 아니다. ... 곧 위에 비교하면 족하지 못하나, ... 그대의 하루 하루를
                    "
                    rows="5"
                >
                </b-form-textarea>
              </b-col>
            </b-row>
            <b-row class="mb-3">
              <b-col cols="3" class="text-center" align-self="center">
                <h2>
                  <!-- <i class="ni ni-hat-3 mr-2"></i> -->
                  관심 개발 분야
                </h2>
              </b-col>
              <b-col align-self="center">
                <b-badge variant="warning" class="mr-3 h3">
                   Python
                </b-badge>
                <b-badge variant="success" class="mr-3 h3">
                   Django
                </b-badge>
                <b-badge variant="primary" class="mr-3 h3">
                   Python
                </b-badge>
              </b-col>
              <FlavourContent class="mr-3" align-self="center"/>
            </b-row>

            <hr class="my-4">
            <b-row class="justify-content-end">
              <b-button variant="warning" class="mt-4" @click="withDrawal" size="sm">회원탈퇴</b-button>
            </b-row>
            <b-row class="justify-content-center">
              <b-button variant="primary" class="mt-4" @click="withDrawal" size="lg">정보수정</b-button>
            </b-row>

          </b-container>
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
  import LoginContent from '@/components/Login/LoginContent.vue';
  import FlavourContent from '@/components/Profileupdate/FlavourContent.vue';
  import ProfileImg from '@/components/Profileupdate/ProfileImg.vue';
  import BackgroundImg from '@/components/Profileupdate/BackgroundImg.vue';


  export default {
    components: {
      // EditProfileForm,
      UserCard,
      LoginContent,
      FlavourContent,
      ProfileImg,
      BackgroundImg,
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
