<template>
  <div>
    <!-- register -->
    <!-- Header -->
    <div class="header bg-gradient-default py-7 py-lg-8 pt-lg-9">
      <b-container class="container">
        <div class="header-body text-center mb-7">
          <b-row class="justify-content-center">
            <b-col xl="5" lg="6" md="8" class="px-5">
              <b-link href="/#/main">
                <img src="/img/brand/white_roadwiki.png" alt="roadwiki" width="250rem;">
              </b-link>
              <!-- <h1 class="text-white">회원가입</h1> -->
              <!-- <p class="text-lead text-white">Use these awesome forms to login or create new account in your project for
                free.</p> -->
            </b-col>
          </b-row>
        </div>
      </b-container>
      <div class="separator separator-bottom separator-skew zindex-100">
        <svg x="0" y="0" viewBox="0 0 2560 100" preserveAspectRatio="none" version="1.1"
             xmlns="http://www.w3.org/2000/svg">
          <polygon class="fill-default" points="2560 0 2560 100 0 100"></polygon>
        </svg>
      </div>
    </div>
    <!-- Page content -->
    <b-container class="mt--8 pb-5">
      <!-- Table -->
      <b-row class="justify-content-center">
        <b-col lg="6" md="8" >
          <b-card no-body class="bg-secondary border-0">
            <b-card-body class="px-lg-5 py-lg-5">
              <div class="text-center text-muted mb-4">
                <!-- <br> -->
                <!-- <h1>회원가입</h1> -->
              </div>
              <validation-observer v-slot="{handleSubmit}" ref="formValidator">
                <b-form role="form" @submit.prevent="handleSubmit(onSubmit)">
                  <!-- required속성이 true이면 The Name field is required라는 창이 뜸 -->
                  <base-input alternative
                              class="mb-3"
                              prepend-icon="ni ni-hat-3"
                              placeholder="닉네임"
                              name="닉네임"
                              :rules="{required: true}"
                              v-model="name">
                  </base-input>

                  <div class="row">
                    <div class="col-9">
                      <base-input alternative
                                  class="mb-3"
                                  prepend-icon="ni ni-email-83"
                                  placeholder="example123@ssafy.com"
                                  name="이메일"
                                  :rules="{required: true, email: true}"
                                  v-model="email"
                                  v-if="!confirmEmail"
                                  >
                      </base-input>
                      <base-input alternative
                                  prepend-icon="ni ni-email-83"
                                  v-if="confirmEmail"
                                  v-model="email"
                                  placeholder="email"
                                  disabled
                                  >
                      </base-input>
                      <!-- 만약 기존에 계정이 존재하는 이메일이라면 this email is already taken 보여주기 -->
                    </div>
                    <div class="col-3 pl-0">
                      <ModalEmailValidation v-if="isEmailModal" @close="closeModal"/>
                      <b-button v-b-modal.modal-email v-if="!confirmEmail" @click="emailNumSend">인증하기</b-button>
                      <b-button v-if="confirmEmail" disabled>인증완료</b-button>
                    </div>
                  </div>

                  <base-input alternative
                              class="mb-3"
                              prepend-icon="ni ni-lock-circle-open"
                              placeholder="비밀번호"
                              type="password"
                              name="비밀번호"
                              vid="password"
                              :rules="{required: true, min: 8}"
                              v-model="password">
                  </base-input>

                  <base-input alternative
                              class="mb-3"
                              prepend-icon="ni ni-lock-circle-open"
                              placeholder="비밀번호 확인"
                              type="password"
                              name="비밀번호 확인"
                              :rules="{required: true, min: 8, password: password}"
                              v-model="rePassword">
                  </base-input>
                  <hr class="my-4">
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
                    <b-container>
                      <div>1순위 <strong v-if="selected.length > 0">{{ options[selected[0]-1].text }}</strong></div>
                      <div>2순위 <strong v-if="selected.length > 1">{{ options[selected[1]-1].text }}</strong></div>
                      <div>3순위 <strong v-if="selected.length > 2">{{ options[selected[2]-1].text }}</strong></div>
                    </b-container>
                  </div>
                  <hr class="my-4">
                  <b-row class=" my-4">
                    <b-col cols="12">
                      <base-input :rules="{ required: { allowFalse: false } }" name="회원약관 동의" Policy>
                        <b-form-checkbox v-model="agree">
                          <span class="text-muted">
                            <b-link 
                              v-b-modal.modal-scrollable 
                              variant="link" 
                              class="m-0 p-0" 
                              @click="isPolicyModal = true">
                              회원 약관
                            </b-link>
                            에 동의합니다. 
                            <ModalPolicy v-if="isPolicyModal" @close="isPolicyModal = false" @accept="accept"/>
                          </span>
                        </b-form-checkbox>
                      </base-input>
                    </b-col>
                  </b-row>
                  <div class="text-center">
                    <b-button type="submit" variant="primary" class="mt-4" @click="signUp">가입하기</b-button>
                  </div>
                  <div class="text-center">
                    <!-- <b-button v-b-modal.modal-login variant="default" class="mt-4" @click="isLoginModal = true">로그인</b-button> -->
                    <!-- <LoginContent v-if="isLoginModal" @close="isLoginModal = false" /> -->
                    <!-- <LoginContent/> -->
                    <!-- <LogoutContent/> -->
                  </div>
                </b-form>
              </validation-observer>
            </b-card-body>
          </b-card>
        </b-col>
      </b-row>
    </b-container>
  </div>
</template>

<script>
  import ModalEmailValidation from "@/components/Validation/ModalEmailValidation.vue";
  import ModalPolicy from '@/components/Validation/ModalPolicy.vue';
  // import LoginContent from '@/components/Login/LoginContent.vue';
  // import LogoutContent from '@/components/Logout/LogoutContent.vue';

  import { extend } from 'vee-validate';

  extend('password', {
    params: ['target'],
    validate(value, { target }) {
      return value === target;
    },
    message: '비밀번호가 일치하지 않습니다.'
  });

  export default {
    name: 'register',
    components: {
      ModalEmailValidation,
      ModalPolicy,
      // LoginContent,
      // LogoutContent,
    },
    data() {
      return {
        name: '',
        email: '',
        password: '',
        rePassword: '',
        isEmailModal: false,
        isPolicyModal: false,
        confirmEmail: false,
        agree: false,
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
        isLoginModal: false,
      }
    },
    methods: {
      onSubmit() {
        // this will be called only after form is valid. You can do an api call here to register users
      },
      closeModal(e) {
        this.isEmailModal = false
        if (e === true) {
          this.confirmEmail = true
        } else { this.confirmEmail = false}
      },
      accept() {
        this.isPolicyModal = false
        this.agree = true
      },
      emailNumSend() {
        this.isEmailModal = true
        console.log(this.$store.getters.getServer)
        axios.get(`${this.$store.getters.getServer}/email/${this.email}`)
        .then((res) => {
          this.$store.dispatch('SETCODE', res.data['code']);
          this.$store.dispatch('SETEMAIL', res.data['email']);
        });
      },
      signUp() {
        // 인풋이 다 안채워지면 회원가입 버튼이 비활성화되게 로직 추가
        const user = {
          email: this.email,
          name: this.name,
          password: this.password,
          keyword: this.selected,
        }
        if (this.confirmEmail && this.selected.length >= 3) {
          axios.post(`${this.$store.getters.getServer}/user/join`, user)
          .then(() => {
            console.log('잘 가나요오오오오오오오오오오')
            const userinfo = {
              email: this.email,
              password: this.password,
            }
            this.$store.dispatch("LOGIN", userinfo)
            .then(() => {
              this.$router.push('/dashboard')
            })
          })
          // this.$router.replace('/dashboard')
        } else {
          if (!this.confirmEmail) {
            alert('이메일 인증이 완료되지 않았습니다.')
          } else {alert('관심 개발 분야가 선택되지 않았습니다.')}
        }
      },
    },
    watch: {
      password() {
        // isPasswordEqual()
      },
      rePassword() {
        // isPasswordEqual()
      },
    },
    // computed: {
    //   passwordConfirmationRule() {
    //     return () => (this.model.password === this.model.rePassword) || 'Password must match'
    //   },
    // },
    
  };
</script>
<style></style>
