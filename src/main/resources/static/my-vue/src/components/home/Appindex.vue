<template>
    <div style="position: fixed;left: 0;top: 8%;width: 100%;height: 100%;background: var(--米白);">
        <el-container style="height: 100%;width: 100%;">
            <el-header style="width: 100%;height: 50%;box-shadow: 12px 12px 12px rgba(0,0,0,0.1);border: rgba(0,0,0,0.1);">
                <h2>热门电影
                </h2>
                <el-carousel id="mycarousel" :interval="4000" type="card" style="width: 100%">
                    <el-carousel-item v-for="(item, index) in hotImages" :key="index">
                        <el-image style="width:60%;height: 100%;" :src="item.url" fit="fit"></el-image>
                    </el-carousel-item>
                </el-carousel>
            </el-header>

            <el-main>
                <h2>推荐电影
                </h2>
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
            hotImages: [
                {
                    id: 0, url: require('@/movies/hot/八百.jpg'),
                },
                {
                    id: 1, url: require('@/movies/hot/独行月球.jpg'),
                },
                {
                    id: 2, url: require('@/movies/hot/钢铁侠3.jpg'),
                },
                {
                    id: 3, url: require('@/movies/hot/误杀.jpg'),
                }
                ,
                {
                    id: 4, url: require('@/movies/hot/星际穿越.jpg'),
                },
                {
                    id: 5, url: require('@/movies/hot/影.jpg'),
                }
            ],
            recommendImages: [
                {
                    name: '我不是药神',
                    url: require('@/movies/all/我不是药神.jpg'),
                    releaseDate: '2018',
                    type: '剧情/喜剧',
                    rate: 3.7
                }, {
                    name: '绿皮书',
                    url: require('@/movies/all/绿皮书.jpg'),
                    releaseDate: '2018',
                    type: '剧情/喜剧/传记',
                    rate: 4.1
                },
                {
                    name: '头号玩家',
                    url: require('@/movies/all/头号玩家.jpg'),
                    releaseDate: '2018',
                    type: '动作/科幻/冒险',
                    rate: 3.9
                },
                {
                    name: '疯狂动物城',
                    url: require('@/movies/all/疯狂动物城.jpg'),
                    releaseDate: '2016',
                    type: '喜剧/动画/冒险',
                    rate: 3.3
                },
                {
                    name: '看不见的客人',
                    url: require('@/movies/all/看不见的客人.jpg'),
                    releaseDate: '2016',
                    type: '剧情/犯罪/悬疑/惊悚',
                    rate: 4.2
                },
                {
                    name: '星际穿越',
                    url: require('@/movies/all/星际穿越.jpg'),
                    releaseDate: '2014',
                    type: '剧情/科幻/冒险',
                    rate: 4.4
                },
                {
                    name: '爆裂鼓手',
                    url: require('@/movies/all/爆裂鼓手.jpg'),
                    releaseDate: '2014',
                    type: '剧情/音乐',
                    rate: 3.2
                },
                {
                    name: '爱在午夜降临前',
                    url: require('@/movies/all/爱在午夜降临前.jpg'),
                    releaseDate: '2013',
                    type: '剧情/爱情',
                    rate: 3.1
                },
                {
                    name: '蝙蝠侠：黑暗骑士崛起',
                    url: require('@/movies/all/蝙蝠侠：黑暗骑士崛起.jpg'),
                    releaseDate: '2012',
                    type: '剧情/动作/科幻/犯罪/惊悚',
                    rate: 4.2
                },
                {
                    name: '盗梦空间',
                    url: require('@/movies/all/盗梦空间.jpg'),
                    releaseDate: '2010',
                    type: '剧情/科幻/悬疑/冒险',
                    rate: 4.4
                },
            ],
            indexBackUrl: require(`@/background/back3.jpg`),
        }
    },
    mounted() {
        this.setSize1();
        const that = this;
        //监听浏览器窗口大小改变
        window.addEventListener('resize', function () {
            var width = window.innerWidth || document.documentElement.clientWidth || document.body.clientWidth;
            that.screenWidth = width;
            that.setSize();
        }, false);
    },
    methods: {
        setSize1: function () {
            var width = window.innerWidth || document.documentElement.clientWidth || document.body.clientWidth;
            this.screenWidth = width;
            //图片                高 / 宽  500 / 1920
            this.bannerHeight = 500 / 1920 * this.screenWidth - 50
            document.getElementById('mycarousel').style.height = this.bannerHeight + 'px';
        },
        setSize: function () {
            this.bannerHeight = 500 / 1920 * this.screenWidth - 50
            document.getElementById('mycarousel').style.height = this.bannerHeight + 'px';
        },
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
