<template>
    <div style="position: fixed;left: 0;top: 8%;width: 100%;height: 100%;background: var(--米白);">
        <el-container style="height: 100%;width: 100%;">
            <el-header style="width: 100%;height: 50%;box-shadow: 12px 12px 12px rgba(0,0,0,0.1);border: rgba(0,0,0,0.1);">
                <h2>热门歌曲
                </h2>
                <el-carousel id="mycarousel" :interval="4000" type="card" style="left: 15%;width: 70%;height: 80%;">
                    <el-carousel-item v-for="(item, index) in hotImages" :key="index">
                        <el-image style="width:40%;height: 100%;" :src="require('@/movies/allInfo/' + item.url)"
                            fit="fit"></el-image>
                    </el-carousel-item>
                </el-carousel>
            </el-header>

            <el-main>
                <el-row style="top: 5%;">
                    <el-col :span="12">
                        <h2>推荐歌曲
                        </h2>
                    </el-col>
                    <el-col :span="12"><el-button icon="el-icon-refresh" type="success"
                            style="margin-left: 75%;width: 20%;border: none"
                            v-on:click="refreshRecommend">刷新</el-button></el-col>

                </el-row>
                <movieItem :movieItem="item" v-for="(item, index) in recommendImages" :key="index"></movieItem>
            </el-main>
        </el-container>
    </div>
</template>

<script>
import 'chinese-gradient'
import movieItem from '@/components/common/movieItem'
export default {
    name: "AppIndex",
    data() {
        return {
            screenWidth: 50,
            bannerHeight: 100,

            hotImages: [],
            recommendImages: [],
            indexBackUrl: require(`@/background/back3.jpg`),
        }
    },
    mounted() {
        this.getHotMovieInfo();
        this.getRecommend();
    },
    created() {
    },
    methods: {
        getHotMovieInfo: function () {
            this.$axios.post('/api/Hot/')
                .then((resp) => {
                    console.log('awwww', resp);
                    if (resp) {
                        let hotInfo = resp.data;
                        for (let index = 0; index < hotInfo.length; index++) {
                            const curtItem = hotInfo[index];
                            curtItem['id'] = index;
                        }
                        console.log('hotInfo', hotInfo);
                        this.$set(this, 'hotImages', hotInfo);
                    }
                    console.log('hotImages', this.hotImages);
                })

        },
        refreshRecommend: function () {
            let param = {
                userId: this.$store.state.user.id,
            };
            this.$axios.post('/api/refreshRecommend/', param)
                .then((resp) => {
                    if (resp) {
                        let recommend = resp.data;
                        this.$set(this, 'recommendImages', recommend);
                    }
                    console.log('recommendImages', this.recommendImages);
                })

        },
        getRecommend: function () {
            let param = {
                userId: this.$store.state.user.id,
            };
            this.$axios.post('/api/recommend/', param)
                .then((resp) => {
                    console.log('awwww', resp);
                    if (resp) {
                        let recommend = resp.data;
                        console.log('recommend', recommend);
                        this.$set(this, 'recommendImages', recommend);
                    }
                    console.log('recommendImages', this.recommendImages);
                })

        }
    }
}
</script>

<style scoped>
.el-carousel>>>.el-carousel__container {
    height: 80% !important;
}


.fangsheng {
    /* 要把阴影与大小配合好，一般来说大小都是偏大时采用 */
    font-family: "Arial Rounded MT Bold", "Helvetica Rounded", Arial, sans-serif;
    text-transform: uppercase;
    /* 全开大写 */
    font-size: 25px;
    color: #638abd;
    text-shadow: 0 8px 9px #5d6b6d, 0px -2px 1px #c7c0c0;
    font-weight: bold;
    letter-spacing: 4px;
}


@keyframes opacityChange {
    50% {
        opacity: .5;
    }

    100% {
        opacity: 1;
    }
}

@keyframes rotate {
    100% {
        transform: rotate(1turn);
    }
}

.el-header {
    overflow-y: auto;
}



div.item:nth-child(9) div::after {
    animation: opacityChange 5s infinite linear;
}
</style>
