<template>
<div>
  <div class="left-bottom">
    <img src="../bg.png" alt=""/>
    <el-button type="text">
      <h2 style="color: #000000; margin-right: 15px">榜单</h2>
    </el-button>
  </div>
  <hr color="#C20C0C" style="margin-bottom: 20px" />
  <div class="left-bottom-content">
    <ul>
      <li>
        <img src="https://p1.music.126.net/pcYHpMkdC69VVvWiynNklA==/109951166952713766.jpg?param=100y100"  alt=""/>
        <div>
          <router-link to="/toplist">飙升榜</router-link>
          <a title="播放">
            <el-button type="text" icon="el-icon-video-play" @click="changeMusiclist(soarList)"></el-button>
          </a>
          <a href="" title="收藏">
            <el-button type="text" icon="el-icon-folder-add"></el-button>
          </a>
        </div>
      </li>
      <li v-for="(item, index) in soarList" :key="item.id">
        <span>{{ index + 1 }}</span><a href="">{{ item.name }}</a>
      </li>
      <li><router-link to="/toplist">查看全部></router-link></li>
    </ul>
    <ul>
      <li>
        <img src="http://p4.music.126.net/wVmyNS6b_0Nn-y6AX8UbpQ==/109951166952686384.jpg?param=100y100" />
        <div>
          <a>新歌榜</a>
          <a title="播放">
            <el-button type="text" icon="el-icon-video-play" @click="changeMusiclist(newSongList)"></el-button>
          </a>
          <a href="" title="收藏">
            <el-button type="text" icon="el-icon-folder-add"></el-button>
          </a>
        </div>
      </li>
      <li v-for="(item, index) in newSongList" :key="item.id">
        <span>{{ index + 1 }}</span><a href="">{{ item.name }}</a>
      </li>
      <li><router-link to="/toplist?id=3779629">查看全部></router-link></li>
    </ul>
    <ul>
      <li>
        <img src="http://p3.music.126.net/iFZ_nw2V86IFk90dc50kdQ==/109951166961388699.jpg?param=100y100" />
        <div>
          <a href="#">原创榜</a>
          <a href="" title="播放">
            <el-button type="text" icon="el-icon-video-play" @click="changeMusiclist(originalList)"></el-button>
          </a>
          <a href="" title="收藏">
            <el-button type="text" icon="el-icon-folder-add"></el-button>
          </a>
        </div>
      </li>
      <li v-for="(item, index) in originalList" :key="item.id">
        <span>{{ index + 1 }}</span><a href="">{{ item.name }}</a>
      </li>
      <li><router-link to="/toplist?id=2884035">查看全部></router-link></li>
    </ul>
  </div>
</div>
</template>

<script>
export default {
  name: "Hotlist",
  data() {
    return {
      soarList: [],
      newSongList: [],
      originalList: []
    };
  },
  mounted() {
    // 榜单
    this.$API.topListDetail().then((res) => {
      if (res.status === 200) {
        //飙升榜id
        let soarId =
            res.data.list.find((item) => item.name === "飙升榜").id + "";
        //新歌榜id
        let newSongId =
            res.data.list.find((item) => item.name === "新歌榜").id + "";
        //原创榜id
        let originalListId =
            res.data.list.find((item) => item.name === "原创榜").id + "";

        // 获取榜单数据
        this.$API.listDetail(soarId).then((res) => {
          this.soarList = res.data.playlist.tracks.splice(0, 10);
        });
        this.$API.listDetail(newSongId).then((res) => {
          this.newSongList = res.data.playlist.tracks.splice(0, 10);
        });
        this.$API.listDetail(originalListId).then((res) => {
          this.originalList = res.data.playlist.tracks.splice(0, 10);
        });
      }
    });
  },
}
</script>

<style scoped lang="less">
.left-bottom {
  margin-top: 20px;
}

.left-bottom-content {
  height: 480px;
  display: flex;
  flex-flow: row nowrap;

  ul {
    width: calc(730px / 3);
    background-color: #f5f5f5;
    border: 1px solid #d3d3d3;
    display: flex;
    flex-flow: column nowrap;

    li:first-child {
      height: 140px;
      padding: 20px;
      display: flex;

      img {
        margin-right: 10px;
      }

      div {
        a:first-child {
          display: block;
          margin-top: 15px;
          font-size: 18px;
          font-weight: bolder;
        }

        button {
          font-size: 25px;
        }
      }
    }

    li:nth-child(2n) {
      background-color: #e8e8e8;
    }

    li:nth-child(-n + 4) {
      span {
        color: #c20c0c;
      }
    }

    li {
      padding: 5px 0px 5px 20px;
      height: 32px;
      overflow: hidden;
      text-overflow: ellipsis;
      white-space: nowrap;
      font-size: 12px;

      span {
        display: inline-block;
        width: 30px;
      }
    }

    a:hover {
      border-bottom: #c20c0c 1px solid;
    }
  }
}
</style>