<template>
    <!-- 一部电影 -->
    <div class="movie-item">
        <!--     v-on:mouseover="isHover = true" 绑定鼠标的悬停和离开-->
        <div v-on:mouseover="isHover = true" v-on:mouseleave="isHover = false">
            <!-- 点击海报，进入该电影详细信息 -->
            <a href="javascript: void(0)" @click="dialogDisplay = true">
                <img class="poster" :src="movieItem.url">
            </a>
            <!--     显示电影下面的电影名字 -->
            <div class="title-style">
                <el-rate v-if="movieItem.rate > 0" v-model="movieItem.rate" disabled show-score text-color="#ff9900"
                    score-template="{value}">
                </el-rate>
                <a href="javascript:void(0)" @click="dialogDisplay = true">{{ movieItem.name }}</a>
            </div>
            <!-- 鼠标划过时的效果 -->
            <div class="movie-item-hover" v-if="isHover">
                <!-- 海报 -->
                <a href="javascript: void(0)" @click="dialogDisplay = true">
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
                        <div class="title-hover" v-if="movieItem.leader">
                            <span class="name-tags">导演: </span>{{ movieItem.leader }}
                        </div>
                        <div class="title-hover" v-if="movieItem.country">
                            <span class="name-tags">国家: </span>{{ movieItem.country }}
                        </div>
                    </div>
                </a>
            </div>
        </div>

        <el-dialog center title="电影详情" :append-to-body="true" :visible="dialogDisplay" width="60%"
            :before-close="handleClose">
            <el-row>
                <el-col :span="12">
                    <img :src="movieItem.url">
                </el-col>
                <el-col :span="12">
                    <div class="movie-info">
                        <span class="name-tags"><b>电影名称：{{ movieItem.name }}</b></span>
                    </div>
                    <div class="movie-info">
                        <span class="name-tags">类型: </span>{{ movieItem.type }}
                    </div>
                    <div class="movie-info">
                        <span class="name-tags">上映时间: </span>{{ movieItem.releaseDate }}
                    </div>
                    <div class="movie-info" v-if="movieItem.leader">
                        <span class="name-tags">导演: </span>{{ movieItem.leader }}
                    </div>
                    <div class="movie-info" v-if="movieItem.country">
                        <span class="name-tags">国家: </span>{{ movieItem.country }}
                    </div>
                    <div class="movie-info">
                        <span class="name-tags">
                            评分：
                        </span>
                        <el-rate allow-half v-model="movieItem.rate" show-score text-color="#ff9900"
                            score-template="{value}"></el-rate>
                    </div>


                </el-col>
            </el-row>
            <span slot="footer" class="dialog-footer">
                <el-button @click="dialogDisplay = false">取 消</el-button>
                <el-button type="primary" @click="dialogDisplay = false">保 存</el-button>
            </span>
        </el-dialog>
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
            dialogDisplay: false,
            isHover: false,
            movieInfoUrl: ''
        }
    },
    created() {
        //解析上映的时间
        this.movieItem.releaseDate = moment(this.movieItem.releaseDate).format('YYYY-MM-DD')
    },
    //监听器
    watch: {
        'movieItem'() {

            // 监听 movieItem 属性的数据变化
            // 作用 : 只要 movieItem 的值发生变化,这个方法就会被调用

            this.movieItem.url = this.movieItem.url
            this.movieItem.name = this.movieItem.name
            this.movieItem.rate = this.movieItem.rate
            //  格式化时间
            this.movieItem.releaseDate = moment(this.movieItem.releaseDate).format('YYYY-MM-DD')

        }
    },

    methods: {
        handleClose(done) {
            done();
            this.dialogDisplay = false;

        }

    }
}
</script>
  
<style scoped>
.movie-item {
    margin-top: 30px;
    margin-left: 30px;
    display: inline-block;
    vertical-align: baseline;
    position: relative;
}

.poster {
    width: 160px;
    height: 220px;
}

.title-style {
    width: 160px;
    white-space: nowrap;
    overflow: auto;
    text-overflow: ellipsis;
    text-align: center;
    font-size: 16px;
    color: #333;
    margin-top: 5px;
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
    height: 330px;
    z-index: 10;
    top: -40px;
    left: -29px;
    overflow: auto;
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

.movie-info:first-child {
    font-size: 30px;
    line-height: 30px;
    margin-top: 10%;
}

.movie-info {
    font-size: 20px;
    line-height: 20px;
    margin-top: 10%;
    width: 100%;
    overflow: hidden;
    text-overflow: ellipsis;
    white-space: nowrap;
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
  
  