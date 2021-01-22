<template>
  <div>
    <!-- Header -->
    <div class="header bg-gradient-success py-7 py-lg-8 pt-lg-9">
      <b-container class="container">
        <div class="header-body text-center mb-7">
          <b-row class="justify-content-center">
            <b-col xl="5" lg="6" md="8" class="px-5">
              <h1 class="text-white">Create an account</h1>
              <p class="text-lead text-white">Use these awesome forms to login or create new account in your project for
                free.</p>
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
            <!-- 소셜 로그인 부분 -->
            <!-- <b-card-header class="bg-transparent pb-5">
              <div class="text-muted text-center mt-2 mb-4"><small>Sign up with</small></div>
              <div class="text-center">
                <a href="#" class="btn btn-neutral btn-icon mr-4">
                  <span class="btn-inner--icon"><img src="img/icons/common/github.svg"></span>
                  <span class="btn-inner--text">Github</span>
                </a>
                <a href="#" class="btn btn-neutral btn-icon">
                  <span class="btn-inner--icon"><img src="img/icons/common/google.svg"></span>
                  <span class="btn-inner--text">Google</span>
                </a>
              </div>
            </b-card-header> -->
            <b-card-body class="px-lg-5 py-lg-5">
              <div class="text-center text-muted mb-4">
                <small>Or sign up with credentials</small>
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
                              v-model="model.name">
                  </base-input>

                  <div class="row">
                    <div class="col-9">
                      <base-input alternative
                                  class="mb-3"
                                  prepend-icon="ni ni-email-83"
                                  placeholder="example123@ssafy.com"
                                  name="이메일"
                                  :rules="{required: true, email: true}"
                                  v-model="model.email">
                      </base-input>
                    </div>
                    <div class="col-3 pl-0">
                      <b-button v-b-modal.modal-email>인증하기</b-button>
                      <ModalEmailValidation/>
                      <!-- <b-modal id="modal-1" title="BootstrapVue">
                        <p class="my-4">Hello from modal!</p>
                      </b-modal> -->
                    </div>
                  </div>

                  <base-input alternative
                              class="mb-3"
                              prepend-icon="ni ni-lock-circle-open"
                              placeholder="비밀번호"
                              type="password"
                              name="비밀번호"
                              :rules="{required: true, min: 8}"
                              v-model="model.password">
                  </base-input>

                  <base-input alternative
                              class="mb-3"
                              prepend-icon="ni ni-lock-circle-open"
                              placeholder="비밀번호 확인"
                              type="password"
                              name="비밀번호"
                              :rules="{required: true, min: 8, passwordConfirmationRule}"
                              v-model="model.rePassword">
                  </base-input>
                  <div class="text-muted font-italic"><small>password strength: <span
                    class="text-success font-weight-700">strong</span></small></div>
                  <b-row class=" my-4">
                    <b-col cols="12">
                      <base-input :rules="{ required: { allowFalse: false } }" name=Privacy Policy>
                        <b-form-checkbox v-model="model.agree">
                          <span class="text-muted">
                            I agree with the 
                            <!-- b-button에 먹혀있는 hover효과 빼기 -->
                            <b-button v-b-modal.modal-scrollable variant="link" class="m-0 p-0">
                              Privacy Policy
                            </b-button>
                            <ModalPolicy/>
                          </span>
                        </b-form-checkbox>
                      </base-input>
                    </b-col>
                  </b-row>
                  <div class="text-center">
                    <b-button type="submit" variant="primary" class="mt-4">Create account</b-button>
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

  export default {
    name: 'register',
    components: {
      ModalEmailValidation,
      ModalPolicy,
    },
    data() {
      return {
        model: {
          name: '',
          email: '',
          password: '',
          agree: false,
          // rePassword: '',
        }
      }
    },
    methods: {
      onSubmit() {
        // this will be called only after form is valid. You can do an api call here to register users
      }
    },
    // computed: {
    //   passwordConfirmationRule() {
    //     return () => (this.model.password === this.model.rePassword) || 'Password must match'
    //   },
    // },
    
  };
</script>
<style></style>
