<template>
  <SlideYUpTransition :duration="animationDuration">
    <b-modal class="modal fade"
      id="modal-email"
      ref="email-modal"
      :size="size"
      :modal-class="[{'modal-mini': type === 'mini'}, ...modalClasses]"
      @mousedown.self="closeModal"
      tabindex="-1"
      role="dialog"
      centered
      @close="closeModal"
      @hide="closeModal"
      :header-class="headerClasses"
      :footer-class="footerClasses"
      :content-class="[gradient ? `bg-gradient-${gradient}` : '', ...modalContentClasses]"
      :body-class="bodyClasses"
      :aria-hidden="!show">
        <!-- :hide-header="!$slots.header" -->

      <template v-slot:modal-header>
        <!-- <slot name="header"></slot> -->
        <!-- <h5>모달 타이틀</h5> -->
        <slot name="close-button">
          <button type="button"
                  class="close"
                  v-if="showClose"
                  @click="closeModal"
                  data-dismiss="modal"
                  aria-label="Close">
            <span :aria-hidden="!show">×</span>
          </button>
        </slot>
      </template>

      <email-content @confirmSuccess="confirmSuccess" @confirmFail="confirmFail"/>

      <template v-slot:modal-footer>
        <!-- disabled속성이랑 인증확인되었는지 여부랑 v-bind해주기 -->
        <base-button type="primary" @click="closeModal" :disabled="!isConfirm">인증완료</base-button>
        <!-- <slot name="footer">여기는 푸터인가</slot>  -->
      </template>

    </b-modal>
  </SlideYUpTransition>
</template>
<script>
  import { SlideYUpTransition } from "vue2-transitions";
  import EmailContent from './EmailContent.vue';

  export default {
    name: "modal",
    components: {
      SlideYUpTransition,
      EmailContent,
    },
    data: () => {
      return {
        isConfirm: false,
      }
    },
    props: {
      show: Boolean,
      showClose: {
        type: Boolean,
        default: true
      },
      type: {
        type: String,
        default: "",
        validator(value) {
          let acceptedValues = ["", "notice", "mini"];
          return acceptedValues.indexOf(value) !== -1;
        },
        description: 'Modal type (notice|mini|"") '
      },
      modalClasses: {
        type: [Object, String],
        description: "Modal dialog css classes"
      },
      size: {
        type: String,
        description: 'Modal size',
        validator(value) {
          let acceptedValues = ["", "sm", "lg"];
          return acceptedValues.indexOf(value) !== -1;
        },
      },
      modalContentClasses: {
        type: [Object, String],
        description: "Modal dialog content css classes"
      },
      gradient: {
        type: String,
        description: "Modal gradient type (danger, primary etc)"
      },
      headerClasses: {
        type: [Object, String],
        description: "Modal Header css classes"
      },
      bodyClasses: {
        type: [Object, String],
        description: "Modal Body css classes"
      },
      footerClasses: {
        type: [Object, String],
        description: "Modal Footer css classes"
      },
      animationDuration: {
        type: Number,
        default: 500,
        description: "Modal transition duration"
      }
    },
    methods: {
      closeModal() {
        this.$emit("update:show", false);
        this.$emit("close", this.isConfirm);
      },
      confirmSuccess() {
        this.isConfirm = true
      },
      confirmFail() {
        this.isConfirm = false
      },
    },
    watch: {
      show(val) {
        if (val) {
          this.$refs['email-modal'].show();
        } else {
          this.$refs['email-modal'].hide();
        }
      }
    }
  };
</script>
<style>
  .modal-backdrop {
    background-color: rgba(0, 0, 0, 0.6) !important;
  }
</style>
