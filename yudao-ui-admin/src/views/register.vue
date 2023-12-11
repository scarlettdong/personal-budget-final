<template>
  <div class="register">
    <el-form ref="registerForm" :model="registerForm" :rules="registerRules" class="register-form">
      <h3 class="title">Budget Tracker</h3>
      <el-form-item prop="username">
        <el-input v-model="registerForm.username" type="text" auto-complete="off" placeholder="account">
          <svg-icon slot="prefix" icon-class="user" class="el-input__icon input-icon" />
        </el-input>
      </el-form-item>
      <el-form-item prop="password">
        <el-input
          v-model="registerForm.password"
          type="password"
          auto-complete="off"
          placeholder="password"
          @keyup.enter.native="handleRegister"
        >
          <svg-icon slot="prefix" icon-class="password" class="el-input__icon input-icon" />
        </el-input>
      </el-form-item>
      <el-form-item prop="confirmPassword">
        <el-input
          v-model="registerForm.confirmPassword"
          type="password"
          auto-complete="off"
          placeholder="confirm your password"
          @keyup.enter.native="handleRegister"
        >
          <svg-icon slot="prefix" icon-class="password" class="el-input__icon input-icon" />
        </el-input>
      </el-form-item>
      <el-form-item style="width:100%;">
        <el-button
          :loading="loading"
          size="medium"
          type="primary"
          style="width:100%;"
          @click.native.prevent="getCode"
        >
          <span v-if="!loading">registration</span>
          <span v-else>registered...</span>
        </el-button>
        <div style="float: right;">
          <router-link class="link-type" :to="'/login'">log in with an existing account</router-link>
        </div>
      </el-form-item>
    </el-form>
    <!-- 图形验证码 -->
    <Verify ref="verify" :captcha-type="'blockPuzzle'" :img-size="{width:'400px',height:'200px'}"
            @success="handleRegister" />

    <!--  底部  -->
    <div class="el-register-footer">
    </div>
  </div>
</template>

<script>
import { getCodeImg, register } from "@/api/login";
import {
  addUser
} from "@/api/system/user";
import Verify from '@/components/Verifition/Verify';
export default {
  name: "Register",
  components: {
    Verify
  },
  data() {
    const equalToPassword = (rule, value, callback) => {
      if (this.registerForm.password !== value) {
        callback(new Error("the password entered twice is inconsistent"));
      } else {
        callback();
      }
    };
    return {
      captchaEnable: true,
      codeUrl: "",
      registerForm: {
        username: "",
        password: "",
        confirmPassword: "",
        code: "",
        uuid: ""
      },
      registerRules: {
        username: [
          { required: true, trigger: "blur", message: "please enter your account number" },
          { min: 2, max: 20, message: 'user accounts must be between 2 and 20 in length', trigger: 'blur' }
        ],
        password: [
          { required: true, trigger: "blur", message: "please enter your password" },
          { min: 5, max: 20, message: 'user passwords must be between 5 and 20 in length', trigger: 'blur' }
        ],
        confirmPassword: [
          { required: true, trigger: "blur", message: "please enter your password again" },
          { required: true, validator: equalToPassword, trigger: "blur" }
        ]
      },
      loading: false,
      captchaEnabled: true
    };
  },
  created() {
  },
  methods: {
    getCode() {
      // 情况一，未开启：则直接登录
      if (!this.captchaEnable) {
        this.handleRegister({})
        return;
      }

      // 情况二，已开启：则展示验证码；只有完成验证码的情况，才进行登录
      // 弹出验证码
      this.$refs.verify.show()
    },
    handleRegister() {
      register(this.registerForm).then(response => {
        this.$modal.msgSuccess("registration is successful");
      }).then(() => {
        this.$router.push("/login");
      });
    }
  }
};
</script>

<style lang="scss" scoped>
.register {
  display: flex;
  justify-content: center;
  align-items: center;
  height: 100%;
  background-image: url("../assets/images/login-background.jpg");
  background-size: cover;
}
.title {
  margin: 0px auto 30px auto;
  text-align: center;
  color: #707070;
}

.register-form {
  border-radius: 6px;
  background: #ffffff;
  width: 400px;
  padding: 25px 25px 5px 25px;
  .el-input {
    height: 38px;
    input {
      height: 38px;
    }
  }
  .input-icon {
    height: 39px;
    width: 14px;
    margin-left: 2px;
  }
}
.register-tip {
  font-size: 13px;
  text-align: center;
  color: #bfbfbf;
}
.register-code {
  width: 33%;
  height: 38px;
  float: right;
  img {
    cursor: pointer;
    vertical-align: middle;
  }
}
.el-register-footer {
  height: 40px;
  line-height: 40px;
  position: fixed;
  bottom: 0;
  width: 100%;
  text-align: center;
  color: #fff;
  font-family: Arial;
  font-size: 12px;
  letter-spacing: 1px;
}
.register-code-img {
  height: 38px;
}
</style>
