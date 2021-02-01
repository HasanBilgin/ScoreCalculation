<template>
  <form id="score-form" v-on:submit.prevent="submit">
    <div class="row">
      <div class="col-12 form-group">
        <label class="col-form-label col-form-label-lg">Adı Soyadı <span class="text-danger">*</span></label>
        <input type="text" v-model.trim="$v.fullName.$model" :class="{'is-invalid': validationStatus($v.fullName)}"
               class="form-control form-control-lg">
        <div v-if="!$v.fullName.required" class="invalid-feedback">Adı Soyadı bilgisi zorunludur.</div>
      </div>
      <div class="col-12 form-group">
        <label class="col-form-label col-form-label-lg">Kimlik Numarası <span class="text-danger">*</span></label>
        <input type="text" v-model.trim="$v.identityNo.$model" :class="{'is-invalid': validationStatus($v.identityNo)}"
               class="form-control form-control-lg" @keypress="onlyNumber" maxlength="11">
        <div v-if="!$v.identityNo.required" class="invalid-feedback">Kimlik numarası zorunludur</div>
        <div v-if="!$v.identityNo.minLength" class="invalid-feedback">Girilen kimlik numarası hatalı</div>
      </div>
      <div class="col-12 form-group">
        <label class="col-form-label col-form-label-lg">Telefon Numarası <span class="text-danger">*</span></label>
        <input type="text" v-model.trim="$v.phoneNumber.$model"
               :class="{'is-invalid': validationStatus($v.phoneNumber)}"
               class="form-control form-control-lg" @keypress="onlyNumber" maxlength="10">
        <div v-if="!$v.phoneNumber.required" class="invalid-feedback">Telefon numarası zorunludur</div>
        <div v-if="!$v.phoneNumber.minLength" class="invalid-feedback">Girilen telefon numarası hatalı</div>
      </div>
      <div class="col-12 form-group">
        <label class="col-form-label col-form-label-lg">Aylık gelir dilimi <span class="text-danger">*</span></label>
        <select v-model.trim="$v.incomeTranche.$model" :class="{'is-invalid': validationStatus($v.incomeTranche)}"
                class="form-control form-control-lg">
          <option disabled value="">Aylık gelir dilimi seçiniz</option>
          <option :value="c.value" :key="c.text" v-for="c in incomeTrancheList">{{ c.text }}</option>
        </select>
        <div v-if="!$v.incomeTranche.required" class="invalid-feedback">Aylık gelir dilimi seçimi zorunludur.</div>
      </div>
      <div class="col-12 form-group">
        <label class="col-form-label col-form-label-lg">İkamet Ettiği İl<span class="text-danger">*</span></label>
        <select v-model.trim="$v.city.$model" :class="{'is-invalid': validationStatus($v.city)}"
                class="form-control form-control-lg">
          <option disabled value="">İkamet ettiği ili seçiniz</option>
          <option :value="c.value" :key="c.text" v-for="c in cityList">{{ c.text }}</option>
        </select>
        <div v-if="!$v.city.required" class="invalid-feedback">İkamet ettiği il seçimi zorunludur.</div>
      </div>
      <div class="col-12 form-group text-center">
        <button class="btn btn-vue btn-lg col-4">Skor Hesapla</button>
      </div>
    </div>
  </form>
</template>
<script>
import {required, between} from 'vuelidate/lib/validators'
import cityList from '@/json/city.json'
import incomeTrancheList from '@/json/incomeTranche.json'
import {post} from '@/common/api-services'

export default {
  name: 'ScoreForm',
  data: function () {
    return {
      fullName: '',
      identityNo: '',
      phoneNumber: '',
      incomeTranche: [],
      city: [],
      cityList: cityList,
      incomeTrancheList: incomeTrancheList,
    }
  },

  validations: {
    fullName: {required},
    identityNo: {required, between: between(10000000000, 99999999999)},
    phoneNumber: {required, between: between(1000000000, 9999999999)},
    incomeTranche: {required},
    city: {required},
  },


  methods: {

    resetData: function () {
      this.fullName = '';
      this.identityNo = '';
      this.phoneNumber = '';
      this.city = [];
      this.incomeTranche = [];
      this.score = 0;
    },

    validationStatus: function (validation) {
      return typeof validation != "undefined" ? validation.$error : false;
    },

    submit: function () {

      this.$v.$touch();
      if (this.$v.$pending || this.$v.$error) return;

      post('insert-score', {
        fullName: this.fullName,
        identityNo: this.identityNo,
        phoneNumber: this.phoneNumber,
        incomeTranche: this.incomeTranche,
        city: this.city
      }).then(response => {
        console.log(response);
        this.score = response.data.score;
        if (response.data.errorMessage !== null)
          this.$alert(response.data.errorMessage, "Hata", "error")
        else
          this.$alert("Skorunuz : " + this.score, "Skor Hesaplama Sistemi", "success")
      })
          .catch(error => {
            console.log(error);
            this.$error("S")
          })
      this.$v.$reset();
      this.resetData();
    },
    onlyNumber($event) {
      let keyCode = ($event.keyCode ? $event.keyCode : $event.which);
      if ((keyCode < 48 || keyCode > 57) && keyCode !== 46) { // 46 is dot
        $event.preventDefault();
      }
    }
  }
}
</script>
<style>
.btn-vue {
  background: #C83E4D;
  color: black;
  font-weight: bold;
}
</style>