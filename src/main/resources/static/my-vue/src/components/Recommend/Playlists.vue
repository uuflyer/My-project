<template>
  <ul>
    <li v-for='item in lists' :key='item.id' style='margin-bottom: 10px'>
      <PlayCard :data='item'></PlayCard>
    </li>
  </ul>
</template>

<script>
import PlayCard from '@/components/PlayCard'

export default {
  name: 'Playlists',
  components:{PlayCard},
  data() {
    return {
      lists: [],
    }
  },
  mounted() {
    this.getRecommendMusic();
    },
    methods: {
    changeMusiclist(list) {
      this.$bus.$emit("changeMusiclist", list);
    },
    getRecommendMusic() { 
      this.$axios.post('/api/recommend/', {userId:this.$store.state.user.id,pageSize:10,offset:0})
        .then(resp => {
          console.log('recommend',resp);
          if (resp) {
            this.lists = resp.data;
            this.lists.forEach(element => {
              element.url = require('@/music/音乐数据集图片/' + element.url);
            });
          }
        }
        )
    }
  },
}
</script>

<style scoped lang='less'>
ul {
  display: flex;
  flex-flow: row wrap;
  justify-content: space-between;
}
</style>