<template>
    <!-- 一部电影 -->
    <div class="movie-item">
        <!--     v-on:mouseover="isHover = true" 绑定鼠标的悬停和离开-->
        <div v-on:mouseover="isHover = true" v-on:mouseleave="isHover = false">
            <!-- 点击海报，进入该电影详细信息 -->
            <a :href="movieInfoUrl">
                <!--      this.global.base  :const base = 'http://127.0.0.1:8181/'(后端的接口)
          movieItem.moviePoster:  /images/movie/2020/12/15/4425e22bc1264164a2fbdd01d56cd939.jpg   -->
                <img class="poster" :src="movieItem.url">
            </a>
            <!-- 鼠标划过时的效果 -->
            <div class="movie-item-hover" v-if="isHover">
                <!-- 海报 -->
                <a :href="movieInfoUrl">
                    <img class="poster-hover" :src="movieItem.url">
                    <!-- 鼠标划过时显示的信息 -->
                    <div class="movie-hover-info">
                        <div class="title-hover">
                            <span class="name-tags">{{ movieItem.name }}</span>
                        </div>
                        <div class="title-hover">
                            <span class="name-tags">类型: </span>{{ movieItem.type }}
                        </div>
                        <div class="title-hover">
                            <span class="name-tags">上映时间: </span>{{ movieItem.releaseDate }}
                        </div>
                    </div>
                </a>
            </div>
        </div>
        <!--     显示电影下面的电影名字 -->
        <div class="title-style">
            <a href="/movieInfo">{{ movieItem.name }}</a>
        </div>
    </div>
</template>
  
<script>
import moment from 'moment'
export default {
    name: "movie-item",
    props: {
        movieItem: Object
    },
    data() {
        return {
            isHover: false,
            movieInfoUrl: ''
        }
    },
    created() {
        //解析上映的时间
        this.movieItem.releaseDate = moment(this.movieItem.releaseDate).format('YYYY-MM-DD')
        //类型之间加/
        this.movieItem.movieCategoryList = this.movieItem.movieCategoryList.map((obj, index) => {
            return obj.movieCategoryName;
        }).join("/")
        console.log(this.movieItem.movieCategoryList)
        //赋值目标链接
        this.movieInfoUrl = '/movieInfo/' + this.movieItem.movieId
    },

    //监听器
    watch: {
        'movieItem'() {

            // 监听 movieItem 属性的数据变化
            // 作用 : 只要 movieItem 的值发生变化,这个方法就会被调用

            this.movieItem.url = this.movieItem.url
            this.movieItem.name = this.movieItem.name
            //  格式化时间
            this.movieItem.releaseDate = moment(this.movieItem.releaseDate).format('YYYY-MM-DD')
            //  在每个类型之间，添加/,为了美观
            this.movieItem.movieCategoryList = this.movieItem.movieCategoryList.map((obj, index) => {
                return obj.movieCategoryName;
            }).join("/")

            //赋值目标链接，用于跳转到这个电影的详细信息
            this.movieInfoUrl = '/movieInfo/' + this.movieItem.movieId
        }
    }
}
</script>
  
<style scoped>
.movie-item {
    margin-top: 30px;
    margin-left: 30px;
    display: inline-block;
    vertical-align: top;
    position: relative;
}

.poster {
    width: 160px;
    height: 220px;
}

.title-style {
    width: 160px;
    white-space: nowrap;
    overflow: hidden;
    text-overflow: ellipsis;
    text-align: center;
    font-size: 16px;
    color: #333;
    margin-top: 10px;
}

.score {
    color: #ffb400;
    font-size: 24px;
    font-style: italic;
}

a {
    text-decoration: none;
    color: #333;
}

.movie-item-hover {
    position: absolute;
    width: 218px;
    height: 300px;
    z-index: 10;
    top: -40px;
    left: -29px;
    overflow: hidden;
    background: #fff;
    box-shadow: 0 0 16px #fff, 0 0 6px rgba(0, 0, 0, 0.2);
}

.poster-hover {
    width: 218px;
    height: 300px;
}

.movie-hover-info {
    padding: 16px;
    height: 150px;
    box-sizing: border-box;
    position: absolute;
    z-index: 11;
    top: 150px;
    background: #fff;
    width: 218px;
    color: #999;
}

.title-hover:first-child {
    font-size: 20px;
    line-height: 20px;
    margin-top: 6px;
}

.title-hover {
    font-size: 16px;
    line-height: 16px;
    margin-top: 12px;
    width: 100%;
    overflow: hidden;
    text-overflow: ellipsis;
    white-space: nowrap;
}

.name-tags {
    color: #333;
}
</style>
  
  