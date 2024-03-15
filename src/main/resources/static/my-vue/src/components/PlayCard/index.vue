<template>
  <el-card :body-style="{ padding: '0px' }" class="playcard" >
    <el-image :src="`${data.url || data.coverImgUrl}?param=150y140`" style='width: 160px;height: 140px;'
      class="image" lazy />
    <div style="padding: 6px">
      <div class="bottom">
        <span><i class="el-icon-headset" style="color: #c20c0c"></i>{{ playCount }}</span>
        <el-button type="text" icon="el-icon-video-play" @click.stop="changeMusiclist"></el-button>
      </div>
      <a class="title"><strong>{{ data.name }}</strong></a>
      <a class="title">歌手：{{ data.singer }}</a>
    </div>
  </el-card>
</template>

<script>
export default {
  name: "PlayCard",
  props: ["data"],
  methods: {
    toDetailPage() {
      const { id, name } = this.data;
      this.$router.push({
        name: "playlistdetail",
        query: { id, title: name },
      });
    },
    changeMusiclist() {
      this.$API.playlistDetail(this.data.id).then(res => {
        if (res.data.code === 200) {
          this.$bus.$emit("changeMusiclist", res.data.playlist.tracks);
        }
      })
    }
  },
  computed: {
    playCount() {
      if (this.data.playCount > 10000) {
        return Math.floor(this.data.playCount / 10000) + "万";
      }
      return this.data.playCount;
    }
  },
};
</script>

<style scoped>
.title {
  margin-top: 5px;
  overflow: hidden;
  text-overflow: ellipsis;
  display: -webkit-box;
  -webkit-line-clamp: 2;
  -webkit-box-orient: vertical;
  font-family: "Microsoft YaHei UI";
  font-size: 14px;
}

.playcard {
  width: 150px;
  height: 215px;
  cursor: pointer;
}

.bottom {
  margin: -10px 0;
  line-height: 12px;
  display: flex;
  align-items: center;
  font-size: 13px;
  justify-content: space-between;
}

.image {
  width: 100%;
  display: block;
  overflow: hidden;
  transition: all 0.6s;
}

span {
  border-bottom: #c20c0c 1px solid;
}

img:hover {
  transform: scale(1.1);
}
</style>