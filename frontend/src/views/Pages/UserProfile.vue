<template>
  <div>
    <div class="header pb-8 pt-5 pt-lg-8 d-flex align-items-center profile-header"
        style="min-height: 600px; background-image: url(img/theme/profile-cover.jpg); background-size: cover; background-position: center top;">
      <b-container fluid>
        <!-- Mask -->
        <span class="mask bg-gradient-success opacity-8"></span>
        <!-- Header container -->
        <b-container fluid class="d-flex align-items-center">
          <b-row >
            <b-col lg="7" md="10">
              <!-- 닉네임 들어갈 부분 -->
              <h1 class="display-2 text-white">닉네임</h1>
              <!-- 한줄 소개 들어갈 부분 -->
              <p class="text-white mt-0 mb-3">
                나에게 힘을 주는 인생에 관한 명언 48가지 : 고개 숙이지 마십시오. ... 고난의 시기에 동요하지 않는 것, ... 사막이 아름다운 것은 ...행복의 한 쪽 문이 닫히면 다른 쪽 문이 열린다. ... 만족할 줄 아는 사람은 진정한 부자이고, ... 성공해서 만족하는 것은 아니다. ... 곧 위에 비교하면 족하지 못하나, ... 그대의 하루 하루를
              </p>
              <div>
                <svg xmlns="http://www.w3.org/2000/svg" width="24" height="24" viewBox="0 0 24 24">
                  <path d="M19 0h-14c-2.761 0-5 2.239-5 5v14c0 2.761 2.239 5 5 5h14c2.762 0 5-2.239 5-5v-14c0-2.761-2.238-5-5-5zm-4.466 19.59c-.405.078-.534-.171-.534-.384v-2.195c0-.747-.262-1.233-.55-1.481 1.782-.198 3.654-.875 3.654-3.947 0-.874-.312-1.588-.823-2.147.082-.202.356-1.016-.079-2.117 0 0-.671-.215-2.198.82-.64-.18-1.324-.267-2.004-.271-.68.003-1.364.091-2.003.269-1.528-1.035-2.2-.82-2.2-.82-.434 1.102-.16 1.915-.077 2.118-.512.56-.824 1.273-.824 2.147 0 3.064 1.867 3.751 3.645 3.954-.229.2-.436.552-.508 1.07-.457.204-1.614.557-2.328-.666 0 0-.423-.768-1.227-.825 0 0-.78-.01-.055.487 0 0 .525.246.889 1.17 0 0 .463 1.428 2.688.944v1.489c0 .211-.129.459-.528.385-3.18-1.057-5.472-4.056-5.472-7.59 0-4.419 3.582-8 8-8s8 3.581 8 8c0 3.533-2.289 6.531-5.466 7.59z"/>
                </svg>
                <b-link href="https://github.com/">
                  https://github.com/
                </b-link>
              </div>
              <br>
              <a href="#!" class="btn btn-info">Edit profile</a>
            </b-col>
          </b-row>
        </b-container>
      </b-container>
    </div>

    <b-container fluid class="mt--6">
      <user-card></user-card>
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
      UserCard
    }
  };
</script>
<style>
</style>
