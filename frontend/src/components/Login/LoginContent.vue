<template>
  <div>
    <b-button @click="modalShow = !modalShow">로그인</b-button>

    <b-modal v-model="modalShow" hide-footer>
      <base-input alternative
              class="mb-3"
              name="Email"
              prepend-icon="ni ni-email-83"
              placeholder="Email"
      >
      </base-input>

      <base-input alternative
                  class="mb-3"
                  name="Password"
                  prepend-icon="ni ni-lock-circle-open"
                  type="password"
                  placeholder="Password"
      >
      </base-input>
      <div class="text-center">
        <base-button type="primary" native-type="submit" class="my-4">Sign in</base-button>
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
    }
  }
}
</script>

<style>

</style>