<template>
    <div style="position: fixed;left: 0;top: 8%;width: 100%;height: 100%;background: var(--米白);">
        <el-container style="height: 100%;width: 100%;">
            <el-header style="width: 100%;height: 50%;box-shadow: 12px 12px 12px rgba(0,0,0,0.1);border: rgba(0,0,0,0.1);">
                <el-row style="top: 5%;">
                    <el-input style="left: 30%;width: 30%;" v-model="searchName" placeholder="请输入电影名称"></el-input>
                    <el-button style="position: relative;margin-left: 30%;" icon="el-icon-search" circle></el-button>
                </el-row>
                <h2 class="fangsheng">未评分电影列表
                </h2>
                <movieItem :movieItem="item" v-for="(item, index) in unrateMovies" :key="index"></movieItem>
            </el-header>
            <el-main>
                <h2 class="fangsheng">已评分电影列表
                </h2>
                <movieItem :movieItem="item" v-for="(item, index) in ratedMovies" :key="index"></movieItem>
            </el-main>
        </el-container>
    </div>
</template>
<script>
import movieItem from '@/components/common/movieItem'
export default {
    name: "movieRate",
    data() {
        return {
            searchName: ''
            ,
            ratedMovies: [
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
            unrateMovies: [
                {
                    name: '让子弹飞',
                    url: require('@/movies/all/让子弹飞.jpg'),
                    releaseDate: '2010',
                    type: '剧情/喜剧/动作/西部',
                    leader: '姜文',
                    country: '中国大陆 中国香港'
                }, {
                    name: '你的名字。',
                    url: require('@/movies/all/你的名字。.jpg'),
                    releaseDate: '2016',
                    type: '剧情/爱情/动画',
                    leader: '神木隆之介',
                    country: '日本'
                },
                {
                    name: '寻梦环游记',
                    url: require('@/movies/all/寻梦环游记.jpg'),
                    releaseDate: '2017',
                    type: '喜剧/动画/奇幻/音乐',
                    leader: ' 李·昂克里奇 Lee Unkrich',
                    country: '美国'
                },
                {
                    name: '釜山行',
                    url: require('@/movies/all/釜山行.jpg'),
                    releaseDate: '2016',
                    type: '动作/惊悚/灾难',
                    leader: '孔侑 Yoo Gong',
                    country: '韩国'
                },
                {
                    name: '驴得水',
                    url: require('@/movies/all/驴得水.jpg'),
                    releaseDate: '2016',
                    type: '剧情/喜剧',
                    leader: '周申',
                    country: '中国大陆'
                },
                {
                    name: '时空恋旅人',
                    url: require('@/movies/all/时空恋旅人.jpg'),
                    releaseDate: '2013',
                    type: '喜剧/爱情/奇幻',
                    leader: ' 多姆纳尔·格里森 Domhnall Gl...',
                    country: '英国'
                },
                {
                    name: '少年派的奇幻漂流',
                    url: require('@/movies/all/少年派的奇幻漂流.jpg'),
                    releaseDate: '2012',
                    type: '剧情/奇幻/冒险',
                    leader: ' 苏拉·沙玛 Suraj Sharma',
                    country: '美国 中国台湾 英国 加拿大'
                },
                {
                    name: '无敌破坏王',
                    url: require('@/movies/all/无敌破坏王.jpg'),
                    releaseDate: '2012',
                    type: '喜剧/动画/奇幻/冒险',
                    leader: ' 约翰·C·赖利 John C. Reilly',
                    country: '美国'
                },
                {
                    name: '源代码',
                    url: require('@/movies/all/源代码.jpg'),
                    releaseDate: '2011',
                    type: '科幻/悬疑/惊悚',
                    leader: ' 杰克·吉伦哈尔 Jake Gyllenhaal',
                    country: '美国 加拿大'
                },
                {
                    name: '神偷奶爸',
                    url: require('@/movies/all/神偷奶爸.jpg'),
                    releaseDate: '2010',
                    type: '喜剧/动画/冒险',
                    leader: ' 皮艾尔·柯芬 Pierre Coffin',
                    country: '美国 法国'
                },
            ],
            indexBackUrl: require(`@/background/back3.jpg`),
        }
    },
    mounted() {

    },
    methods: {

    }
}
</script>
<style>
.el-header {
    overflow-y: auto;
}
</style>