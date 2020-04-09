<template>
  <div>
    <el-card shadow="hover" class="userCard">
      <div>
        <h3><i class="el-icon-postcard"></i> 基本信息</h3>
        <el-row class="title1">
          <span>用户名:</span>
          <span class="rowData">{{this.username}}</span>
        </el-row>
        <div class="title1">
          <span>用户ID:</span>
          <span class="rowData">{{this.userId}}</span>
        </div>

        <div class="title1">
          <span>用户类型:</span>
          <span class="rowData">{{this.userType}}</span>
        </div>

      </div>
      <div>
        <h3 class="appInfo"><i class="el-icon-s-help"></i>应用信息</h3>
        <div class="title3">
          <span>应用网址:</span>
          <el-input class="input" v-model="editUserForm.IpAddress"></el-input>
        </div>
      </div>
      <div>
        <h3 class="appInfo"><i class="el-icon-office-building"></i>联系信息</h3>
        <div class="title3">
          <span>联系地址:</span>
          <el-input class="input" v-model="editUserForm.HomeAddress"></el-input>
        </div>
        <div class="title3">
          <span>邮箱地址:</span>
          <el-input class="input" v-model="editUserForm.email"></el-input>
        </div>
      </div>
      <div class="SaveButton">
        <el-button type="primary" @click="saveInfo">保存</el-button>
      </div>
    </el-card>
  </div>
</template>

<script>
  export default {
    name: "BaseInfo",
    data() {
      return {
        username: sessionStorage.getItem("username"),
        userId: sessionStorage.getItem("userId"),
        userType: sessionStorage.getItem("userType"),
        editUserForm: {
          IpAddress: '',
          HomeAddress: '',
          tel: '',
          email: ''
        }
      }
    },
    methods: {
      saveInfo() {
        this.$axios.post('/userInfo', this.editUserForm)
          .then(res => {
            if (res.data.status === 'OK') {
              this.$notify({
                title: '成功',
                message: '保存成功',
              })
              this.getUserInfo()
            }
          })
          .catch(function (err) {
            console.log(err)
          })
      },
      getUserInfo() {
        this.$axios.get('/userInfo')
          .then(res => {
            console.log(res.data.data)
            if (res.data.status === 'OK') {
              this.editUserForm.email = res.data.data.mail;
              this.editUserForm.HomeAddress = res.data.data.homeAddress;
              this.editUserForm.IpAddress = res.data.data.ipAddress;
              this.editUserForm.tel = res.data.data.tel;
            }
          })

      }
    },
    mounted() {
      this.getUserInfo()
    }
  }
</script>

<style scoped>
  h3 {
    position: fixed;
    top: 15%;
    left: 30%;
  }

  .userCard {
    position: fixed;
    top: 90px;
    width: 70%;
    height: 85%;
    left: 24%;
    overflow-y: auto;
  }


  .title1 {
    position: relative;
    left: -200px;
    margin: 30px;
  }


  .title3 {
    position: relative;
    left: -100px;
    margin: 20px;
  }

  .appInfo {
    position: relative;
    left: -38%;
    margin-top: 5%;
  }


  .input {
    position: relative;
    left: 40px;
    width: 300px;
  }

  .rowData {
    position: relative;
    left: 60px;
  }

  .SaveButton {
    position: relative;
    margin: 50px;
  }
</style>
