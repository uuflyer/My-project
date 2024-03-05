<template>
  <div class='right'>
    <div class='loginInfo' v-if="!isLogin">
      <span>登录网抑云音乐，可以享受无限收藏的乐趣，并且无限同步到手机</span>
      <br>
      <el-button type='primary' round style='margin-top: 10px' @click='dialogFormVisible = true'>用户登录</el-button>
      <Login :dialogFormVisible.sync='dialogFormVisible' />
    </div>
    <div class="loginInfo2" v-if="isLogin">

      <div class="userInfo">
        <!--  头像  -->
        <div class='avatar'>
          <img :src="`${userAvatarUrl}?param=80y80`"/>
        </div>
        <!--  用户名  -->
        <div style="margin-left: 10px;display: flex;flex-flow: column nowrap;">
          <strong>{{ userNickname }}</strong>
          <el-button plain round size="mini" style="margin-top: 5px;width: 50px">Lv{{ userLevel }}</el-button>
          <el-button size="small" type="primary" style="width: 100px;margin-top: 5px">签 到</el-button>
        </div>
      </div>
      <!--   用户动态关注粉丝数   -->
      <div class="somgUserCount">
        <div><strong>{{ userEventCount }}</strong>
          <p>动态</p></div>
        <div><strong>{{ follows }}</strong>
          <p>关注</p></div>
        <div><strong>{{ followeds }}</strong>
          <p>粉丝</p></div>
      </div>
    </div>

    <div class='singer-list'>
      <div class='title'>
        <a href=''>热门歌手</a>
        <hr>
      </div>
      <el-card shadow='always' class='artist-card' :body-style="{ padding: '6px' }" v-for='artist in artistsList'
               :key='artist.id'>
        <div>
          <img :src='`${artist.img1v1Url}?param=68y68`'/>
          <div>
            <span>{{ artist.name }}</span>
            <br/>
            <a @click="toArtist(artist.id, artist.name)">进入歌手主页</a>
          </div>
        </div>
      </el-card>
    </div>
    <div class='dj-list'>
      <div class='title' style='margin-bottom: 10px'>
        <a href=''>热门主播</a>
        <hr>
      </div>
      <ul>
        <li v-for='dj in djList' :key="dj.id">
          <img :src='`${dj.avatarUrl}?param=40y40`'>
          <span>{{ dj.nickName }}</span>
        </li>
      </ul>
    </div>
  </div>
</template>

<script>
import {mapState, mapGetters} from "vuex";

export default {
  name: 'right',
  data() {
    return {
      artistsList: [],
      djList: [],
      dialogFormVisible: false,
    }
  },
  mounted() {
    this.getTopArtists()
    this.getTopDj()
  },
  methods: {
    async getTopArtists() {
      let result = await this.$API.topArtists(5)
      this.artistsList = result.data.artists
    },
    async getTopDj() {
      let result = await this.$API.djToplist(5)
      this.djList = result.data.data.list
    },
    toArtist(id, name) {
      this.$router.push({
        path: '/artist',
        query: {id, title: name}
      })
    }
  },
  computed: {
    ...mapState(["isLogin"]),
    ...mapGetters(["userAvatarUrl", 'followeds', 'follows', 'userEventCount', "userNickname", "userLevel"])
  }
}
</script>

<style scoped lang='less'>
.right {
  width: 250px;
  border: 1px solid #f5f5f5;

  .loginInfo {
    height: 140px;
    padding: 16px;
    background-image: linear-gradient(120deg, #fdfbfb 0%, #ebedee 100%);
    box-shadow: #cccccc 1px 1px 1px;
    text-align: center;
  }

  .loginInfo2 {
    display: flex;
    flex-flow: column nowrap;
    padding: 20px;
    background-image: linear-gradient(120deg, #fdfbfb 0%, #ebedee 100%);
    box-shadow: #cccccc 1px 1px 1px;

    .userInfo {
      display: flex;
      flex-flow: row nowrap;

      strong {
        font-size: 16px;
        overflow: hidden;
        text-overflow: ellipsis;
        white-space: nowrap;
      }

      .avatar {
        width: 86px;
        height: 86px;
        padding: 2px;
        border: 1px solid #c7c7c7;
      }
    }

    .somgUserCount {
      display: flex;
      flex-flow: row nowrap;
      justify-content: space-between;
      color: #666666;
      margin-top: 15px;

      div {
        padding: 8px;

        p {
          font-size: 12px;
        }

        &:hover {
          color: #0099ff;
        }
      }
    }
  }

  .singer-list {
    padding: 15px;

    .title {
      margin-bottom: 10px;
    }

    .artist-card {
      margin-bottom: 16px;
      cursor: pointer;

      div {
        display: flex;
        flex-flow: row nowrap;
        font-size: 13px;

        span {
          width: 45px;
          height: 30px;
          margin-left: 10px;
        }

        a {
          margin-top: 50px;
        }
      }
    }
  }

  .dj-list {
    padding: 15px;

    ul {
      li {
        display: flex;
        align-items: center;
        margin-bottom: 10px;
        cursor: pointer;
      }
    }
  }

  a:hover {
    border-bottom: #C20C0C 1px solid;
  }
}
</style>