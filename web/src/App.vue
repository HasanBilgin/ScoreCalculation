<template>
  <div id="app" class="h-100">
    <div class="container-fluid h-100">
      <div class="row h-100">
        <div class="col-md-3 vue-bg h-100 d-flex justify-content-center align-items-center">
          <img alt="Vue logo" src="./assets/kocfinans.png" width="256">
        </div>
        <div class="col-md-9 h-100 d-flex justify-content-center align-items-center">
          <div class="col-md-8 rounded px-5 py-4 shadow bg-white text-left">
            <ScoreForm/>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import ScoreForm from './components/ScoreForm.vue'

export default {
  name: 'App',
  components: {
    ScoreForm
  }
}
</script>

<style>
#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
}
.vue-bg {
  background: #C83E4D;
}
</style>
