<template>
  <div>
    <div style="width: 70%; background-color: #fff;margin-left: 210px;">
      <img src="./bg.png" />
          <el-button type="text">
            <h2 style="color: #000000; margin-right: 15px">
              热门歌曲
            </h2>
          </el-button>
      <el-carousel :interval="4000" type="card" height="290px" arrow="always" class="el-carousel"
        indicator-position="none">
        <el-carousel-item v-for="(banner, index) in banners" :key="index">
          <img style="height: 100%;width: 90%;" :src="banner.imageUrl" />
        </el-carousel-item>
      </el-carousel>
    </div>
    <div class="main">
      <div class="left">
        <img src="./bg.png" />
          <el-button type="text">
            <h2 style="color: #000000; margin-right: 15px">
              个性化推荐
            </h2>
          </el-button>
        <hr color="#C20C0C" style="margin-bottom: 20px" />
        <Playlists></Playlists>
        <!-- 新碟上架 -->
        <!-- <NewestAlbum :newestAlbum="newestAlbum" /> -->
        <!-- 榜单 -->
        <Hotlist />
      </div>

      <Right />
    </div>
  </div>
</template>

<script>
import Playlists from "./Playlists";
import Right from "./right";
import NewestAlbum from "./NewestAlbum/index.vue";
import Hotlist from "./Hotlist/index.vue";

export default {
  name: "Recommend",
  components: {NewestAlbum, Playlists, Right, Hotlist },
  data() {
    return {
      banners: [{imageUrl:require("@/music/1.jpg")},{imageUrl:require('@/music/2.jpg')},
      {imageUrl:require('@/music/3.jpg')},{imageUrl:require('@/music/4.jpg')},
      {imageUrl:require('@/music/5.jpg')}],
      swiperOptions: {
        initialSlide: 0,
        slidesPerView: 5,
        slidesPerGroup: 5,
        loop: true,
        navigation: {
          nextEl: ".swiper-button-next",
          prevEl: ".swiper-button-prev",
        },
      },
      topAlbum: [],
      newestAlbum: [],
    };
  },
  mounted() {
    // 获取轮播图数据
    // this.$API.getBanner().then((res) => {
    //   if (res.status === 200) {
    //     this.banners = res.data.banners;
    //   }
    // });

    // // 新碟上架
    // this.$API.albumNewest().then((res) => {
    //   if (res.data.code === 200) {
    //     this.newestAlbum = res.data.albums.splice(0,10);
    //   }
    // });

  },
  methods: {
    changeMusiclist(list) {
      this.$bus.$emit("changeMusiclist", list);
    },
  },
};
</script>

<style scoped lang='less'>
.el-carousel {
  width: 1200px;
  margin: 0 auto;
}

.main {
  width: 100%;
  margin: 0 auto;
  margin-left: 210px;
  display: flex;
  background-color: #fff;

  .left {
    width: 65%;
    padding-right: 5%;
    border: 1px solid #f5f5f5;

    .el-icon-s-help {
      color: #c20c0c;
    }

    .left-top {
      display: flex;
      align-items: center;

      ul {
        display: flex;
        flex-flow: row nowrap;

        li {
          margin-right: 15px;
        }
      }
    }
  }
}
</style>
