<template>
    <div style="position: fixed;left: 0;top: 8%;width: 100%;height: 100%;">
        <el-container style="height: 100%;width: 100%;">
  <el-header style="width: 100%;height: 60%;">
    <h2 class="fangsheng">热门电影
    </h2>
    <el-carousel id="el-carousel" :interval="4000" :height="imageHeight" type="card" style="width: 100%" >
    <el-carousel-item  v-for="(item,index) in hotImages" :key="index">
      <el-image
      style="width:80%;height: 100%;"
      :src="item.url"
      fit="fit"
      ></el-image>
    </el-carousel-item>
  </el-carousel>
</el-header>
  <el-main >
    <h3 class="fangsheng">推荐电影
    </h3>
    Main</el-main>
</el-container>
    </div>
</template>

<script>
    export default {
        name: "AppIndex",
        data(){
            return{
                imageHeight:{
                    type: String,
                    defalut: '500px',
                }
                ,
                hotImages:[
                    {
                        id: 0,url:require('@/movies/hot/八百.jpg'),
                    },
                    {
                        id: 1,url:require('@/movies/hot/独行月球.jpg'),
                    },
                    {
                        id: 2,url:require('@/movies/hot/钢铁侠3.jpg'),
                    },
                    {
                        id: 3,url:require('@/movies/hot/误杀.jpg'),
                    }
                    ,
                    {
                        id: 4,url:require('@/movies/hot/星际穿越.jpg'),
                    }
                ],
                indexBackUrl: require(`@/background/back3.jpg`),
            }
        },
        mounted() {
      this.setSize1();
      const that = this;
      //监听浏览器窗口大小改变
      window.addEventListener('resize', function() {
        var width = window.innerWidth || document.documentElement.clientWidth || document.body.clientWidth;
        that.screenWidth = width;
        that.setSize();
      }, false);
    },
    methods: {
      setSize1: function() {
        var width = window.innerWidth || document.documentElement.clientWidth || document.body.clientWidth;
        this.screenWidth = width;
        //图片                高 / 宽  500 / 1920
        this.bannerHeight = 500 / 1920 * this.screenWidth - 50
        document.getElementById('el-carousel').style.height = this.bannerHeight + 'px';
      },
      setSize: function() {
        this.bannerHeight = 500 / 1920 * this.screenWidth - 50
        document.getElementById('el-carousel').style.height = this.bannerHeight + 'px';
      },
    }
    }
</script>

<style scoped>
.el-carousel >>> .el-carousel__container {
    height: 90% !important;
  }
 
  img {
    display: inline-block;
    height: auto;
    max-width: 100%;
  }
  .fangsheng{
    /* 要把阴影与大小配合好，一般来说大小都是偏大时采用 */
    font-family: "Arial Rounded MT Bold", "Helvetica Rounded", Arial, sans-serif;
    text-transform: uppercase;/* 全开大写 */
    font-size: 25px;
    color: #5e3c1a;
    text-shadow: 0 8px 9px #744d3a, 0px -2px 1px #c7c0c0;
    font-weight: bold;
    letter-spacing: 4px;
}

</style>
