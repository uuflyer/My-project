<template>
  <div id='topbar'>
    <div class='top-nav1'>
      <!-- logo -->
      <div class='top-logo'>
        <i class='el-icon-headset' style='margin-right: 10px'></i>
        <span>白嫖云音乐</span>
      </div>
      <!-- 导航栏 -->
      <ul>
        <li @click="routerTo('/', '1')">
          <a>
            <span>发现音乐</span>
            <sub class='cor' v-show='sub === "1"'></sub>
          </a>
        </li>
        <li @click="routerTo('/mymusic', '2')">
          <a>
            <span>我的音乐</span>
            <sub class='cor' v-show='sub === "2"'></sub>
          </a>
        </li>
        <li>
          <a href='#' class=''>
            <span>关注</span>
            <sub class='cor' v-show='sub === "3"'></sub>
          </a>
        </li>
      </ul>

      <!-- 搜索框-->
      <el-autocomplete popper-class="my-autocomplete" placeholder='音乐/视频/电台/用户' prefix-icon='el-icon-search'
        :trigger-on-focus='false' v-model='searchVal' :fetch-suggestions="querySearch" :debounce="500">
        <template v-slot="{ item }">
          <div class="searchSuggest">
            <div class="type">{{ item.type }}</div>
            <div class="data">
              <p v-for="i in item.data" :key="i.id" @click="handleSearchSelect(item.type,i)">
                <span style="color:#C20C0C;">{{ i.name }}</span>
                <span v-if="i.artist">-{{ i.artist.name }}</span>
                <span v-if="i.artists">-{{ i.artists[0].name }}</span>
              </p>
            </div>
          </div>
        </template>
      </el-autocomplete>

      <!--登录/登录头像-->
      <div class='login'>
        <template v-if="isLogin">
          <el-dropdown trigger="hover" placement="bottom">
            <img :src="`${userAvatarUrl}?param=30y30`" alt="">
            <el-dropdown-menu slot="dropdown">
              <el-dropdown-item>我的主页</el-dropdown-item>
              <el-dropdown-item @click="logout">退出</el-dropdown-item>
            </el-dropdown-menu>
          </el-dropdown>
        </template>
        <template v-if="!isLogin">
          <el-button type='text' @click='dialogFormVisible = true'>登录</el-button>
          <Login :dialogFormVisible.sync='dialogFormVisible' />
        </template>
      </div>
    </div>

    <div class='top-nav2' :class='{ "shrinkNav": $route.meta.pickUpNav }'>
      <el-menu :default-active='activeIndex' class='el-menu-demo' mode='horizontal' background-color='#C20C0C'
        text-color='#fff' active-text-color='#FFFFFF' router @select="handlerMenuSelect">
        <el-menu-item index='/recommend'>推荐</el-menu-item>
        <el-menu-item index='/toplist'>排行榜</el-menu-item>
        <el-menu-item index='/playlist'>歌单</el-menu-item>
        <el-menu-item index='/djradio'>主播电台</el-menu-item>
        <el-menu-item index='/singer'>歌手</el-menu-item>
        <el-menu-item index='/newalbum'>新碟上架</el-menu-item>
      </el-menu>
    </div>
  </div>
</template>

<script>
import { mapState,mapGetters } from 'vuex'
export default {
  name: 'Topbar',
  data() {
    return {
      dialogFormVisible: false, //控制显示登录dialog
      activeIndex: sessionStorage.getItem("topMenuIndex") || '/',
      searchVal: '',
      sub: sessionStorage.getItem('curTopbarNav') || 1,
    }
  },
  methods: {
    routerTo(path, sub) {
      // 路由跳转并更改小三角sub显示
      window.sessionStorage.setItem('curTopbarNav', sub)
      this.sub = sub
      this.$router.push(path)
    },
    handlerMenuSelect(index) {
      this.activeIndex = index;
      window.sessionStorage.setItem("topMenuIndex", index);
    },
    async querySearch(queryString, cb) {
      let ts = Date.now();
      let result = await this.$API.searchSuggest(queryString,ts);
      if (result.data.code === 200) {
        let arr = this.formatSearchResult(result.data.result);
        cb(arr);
      }
    },
    // 格式化搜索结果
    formatSearchResult(result) {
      let arr = [];
      // 单曲
      if (result.songs) {
        arr.push({
          type: '单曲',
          data: result.songs
        })
      }
      // 歌手
      if (result.artists) {
        arr.push({
          type: '歌手',
          data: result.artists
        })
      }
      // 专辑
      if (result.albums) {
        arr.push({
          type: '专辑',
          data: result.albums
        })
      }
      // 歌单
      if (result.playlists) {
        arr.push({
          type: '歌单',
          data: result.playlists
        })
      }
      return arr;
    },
    // 选择搜索结果后跳转到对应页面
    handleSearchSelect(type,data){    
      // title:data.name 用于改变页面标题
      let path = '';
      let query={};
      if (type === "歌手"){
        path='/artist';
        query.id=data.id;
        query.title=data.name;
      }

      if (type === "专辑"){
        path='/albumdetail';
        query.id=data.id;
        query.title=data.name;
      }

      if (type === "歌单"){
        path='/playlistdetail';
        query.id=data.id;
        query.title=data.name;
      }

      if (type === "单曲"){
        path='/songdetail';
        query.ids=data.id; // 歌曲传ids过去 api里获取歌曲信息需要ids
        query.title=data.name;
      }

      this.$router.push({
        path,
        query
      })
    },

    logout(){
      this.$store.dispatch('userLogout');
    }
  },
  computed: {
    ...mapState(['isLogin']),
    ...mapGetters(['userAvatarUrl']),
  }
}
</script>

<style scoped lang='less'>
#topbar {
  width: 100%;
  background-color: #242424;
  display: flex;
  flex-flow: column;
  justify-content: center;
  align-items: center;

  .top-nav1 {
    height: 70px;
    width: 980px;
    color: white;
    display: flex;
    align-items: center;
    overflow: hidden;

    .top-logo {
      font-family: "Helvetica Neue", Helvetica, "PingFang SC",
        "Hiragino Sans GB", "Microsoft YaHei", "微软雅黑", Arial, sans-serif;
      font-size: 28px;
      margin-right: 25px;
    }

    ul {
      display: flex;
      flex-flow: row nowrap;
      height: 100%;

      li {
        margin: 0 20px 0 0;
        width: 100px;
        height: 100%;
        color: #ccc;
        text-align: center;
        cursor: pointer;

        a {
          position: relative;

          span {
            line-height: 70px;
          }

          .cor {
            width: 12px;
            height: 6px;
            background: url(../../assets/topbar.png) -226px 0px;
            position: absolute;
            top: 38px;
            left: 50%;
          }
        }
      }

      li:hover {
        color: white;
        background-color: #000000;
      }
    }

    .login {
      margin-left: 20px;
      color: #606266;
      img{
        vertical-align: middle;
        border-radius: 50%;
        width: 30px;
        height: 30px;
      }
    }
  }

  .top-nav2 {
    width: 100%;
    display: flex;
    flex-flow: nowrap;
    justify-content: center;
    background-color: #C20C0C;

  }

  .shrinkNav {
    height: 5px;
    overflow: hidden;
  }
}

.my-autocomplete {
  li {

    .searchSuggest {
      width: 100%;
      display: flex;
      flex-flow: row nowrap;
      justify-content: start;


      .type {
        font-size: 12px;
        color: #b4b4b4;
        border-right: 1px #e6e6e6 solid;
        padding-right: 10px;
      }

      .data {
        overflow: hidden;
        text-overflow: ellipsis;
        white-space: nowrap;
        width: 100%;
        border-top: 1px solid #e6e6e6;

        p {
          padding-left: 5px;
          display: block;
        }

        p:hover {
          background-color: #cacaca;
        }
      }
    }

  }
}
</style>
