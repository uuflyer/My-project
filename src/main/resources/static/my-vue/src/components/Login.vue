<template>
  <body id="poster">
  <el-form class="login-container" label-position="left" label-width="0px">
    <h3 class="login_title">系统登录</h3>
    <el-form-item>
      <el-input type="text" v-model="loginForm.username" placeholder="请输入用户名/手机号" clearable></el-input>
    </el-form-item>
    <el-form-item>

      <el-input type="text" v-model="loginForm.password" placeholder="请输入密码" show-password></el-input>
    </el-form-item>


    <el-form-item style="width: 100%">
      <el-button type="primary" style="width: 100%;background: #1ab2ff;border: none" v-on:click="login">登录</el-button>
    </el-form-item>
    <a href="Register">没有账号?点击注册</a>
  </el-form>
  </body>
</template>

<script>

  export default {
    name: "Login",
    data() {
      return {
        loginForm: {
          username: '18200358328',
          password: '123456',
        },
        responseResult: [],
      }
    },
    methods: {
      login() {
        var _this = this
        console.log(sessionStorage.getItem('username'))
        this.$axios
          .post('/auth/login', {
            username: this.loginForm.username,
            password: this.loginForm.password,
          })
          .then(successResponse => {
            if (successResponse.data.status === "OK") {
              console.log(successResponse.headers)
              console.log(document.cookie.toString())
              _this.$store.commit('login', successResponse.data.data)
              var path = this.$route.query.redirect
              console.log('query')
              this.$router.replace({path: path === '/' || path === undefined ? '/home' : path})

            } else {
              alert(successResponse.data.msg);
            }
          })
          .catch(() => {
              alert('网络资源错误');
            }
          )
      }
    }
  }
</script>

<style scoped>
  .login-container {
    border-radius: 15px;
    background-clip: padding-box;
    margin: 200px auto;
    width: 350px;
    padding: 35px 35px 15px 35px;
    background: #fff;
    border: 1px solid #eaeaea;
    box-shadow: 0 0 25px #cac6c6;
  }

  .login_title {
    margin: 0px auto 40px auto;
    text-align: center;
    color: #505458;
  }

  #poster {
    background: url("../assets/index.jpg") no-repeat;
    background-position: center top;
    height: 100%;
    width: 100%;
    margin: -8px -9px;
    background-size: cover;
    position: fixed;
    overflow-y: auto;
  }


</style>
