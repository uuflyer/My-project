<template>
    <div style="position: fixed;left: 0;top: 8%;width: 100%;height: 100%;background: var(--米白);">
        <el-container style="height: 100%;width: 100%;">
            <el-header style="width: 100%;height: 50%;box-shadow: 12px 12px 12px rgba(0,0,0,0.1);border: rgba(0,0,0,0.1);">
                <el-row style="top: 5%;">
                    <el-input style="left: 30%;width: 30%;" v-model="searchName" placeholder="请输入电影名称"></el-input>
                    <el-button style="position: relative;margin-left: 30%;" icon="el-icon-search" circle
                        @click="serarch(searchName)"></el-button>
                </el-row>
                <h2 class="fangsheng">未评分电影列表
                </h2>
                <ul class="infinite-list" v-infinite-scroll="reloadUnrated" infinite-scroll-distance="50">
                    <movieItem :movieItem="item" @refreshMovieInfo="refreshMovieInfo" v-for="(item, index) in unrateMovies"
                        :key="index"></movieItem>
                </ul>

            </el-header>
            <el-main>
                <h2 class="fangsheng">已评分电影列表
                </h2>
                <ul class="infinite-list" v-infinite-scroll="reloadRate" infinite-scroll-distance="50"
                    style="overflow:auto">
                    <movieItem :movieItem="item" v-for="(item, index) in ratedMovies" :key="index"></movieItem>
                </ul>

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
            pageSize: 24,
            ratedMoviesOffset: 0,
            unratedMoviesOffset: 0,
            searchName: '',
            allRate: [],
            allUnrate: [],
            ratedMovies: [
            ],
            unrateMovies: [
            ],
            indexBackUrl: require(`@/background/back3.jpg`),
        }
    },
    mounted() {
        // this.getAllUnrateMovies();
        // this.getAllRatedMovies();
        // this.unrateMovies.forEach(element => {
        //     this.allUnrate.push(element);
        // });
        // this.ratedMovies.forEach(element => {
        //     this.allRate.push(element);
        // });

    },
    methods: {
        reloadRate() {

            console.log('curtOffset', this.ratedMoviesOffset);
            this.$axios.post('/api/ratedMovie/',
                { userId: this.$store.state.user.id, pageSize: this.pageSize, offset: this.ratedMoviesOffset })
                .then(resp => {
                    console.log('rated', resp);
                    this.ratedMovies = this.ratedMovies.concat(resp.data);
                    console.log('newRate', this.ratedMovies);
                    this.ratedMoviesOffset += this.pageSize;
                })
        }
        ,
        reloadUnrated() {
            this.unratedMoviesOffset = this.unratedMoviesOffset + this.pageSize;

            this.$axios.post('/api/unratedMovie/',
                { userId: this.$store.state.user.id, pageSize: this.pageSize, offset: this.unratedMoviesOffset })
                .then(resp => {
                    console.log('unrated', resp);
                    this.unrateMovies = this.unrateMovies.concat(resp.data);
                    console.log('unrated', this.unrateMovies);

                })
        },
        refreshMovieInfo() {
            this.getAllUnrateMovies();
            this.getAllRatedMovies();
        },
        serarch(name) {
            if (name) {
                let unrate = this.unrateMovies.filter((item) => item.name.includes(name));
                this.$set(this, 'unrateMovies', unrate);
                let rate = this.ratedMovies.filter((item) => item.name.includes(name));
                this.$set(this, 'ratedMovies', rate);
            } else {
                this.$set(this, 'unrateMovies', allUnrate);
                this.$set(this, 'ratedMovies', allRate);
            }

        },
        getAllUnrateMovies() {
            console.log('loginUser', this.$store.state.user.id);
            this.$axios.post('/api/unratedMovie/',
                { userId: this.$store.state.user.id, pageSize: this.pageSize, offset: 0 })
                .then(resp => {
                    console.log('unrate', resp);
                    this.unratedMoviesOffset = this.pageSize;
                    this.unrateMovies = resp.data;
                })

        },
        getAllRatedMovies() {
            console.log('loginUser', this.$store.state.user.id);
            this.$axios.post('/api/ratedMovie/',
                { userId: this.$store.state.user.id, pageSize: this.pageSize, offset: 0 })
                .then(resp => {
                    console.log('rated', resp);
                    this.ratedMoviesOffset = this.pageSize;
                    this.ratedMovies = resp.data;
                })
        }

    }
}
</script>
<style>
.el-header {
    overflow-y: scroll;
}

.el-main {
    overflow-y: scroll;
}
</style>