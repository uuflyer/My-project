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
      lists: [{id: 1,picUrl: require('@/music/1.jpg'),name:"新歌AAA"},
      {id: 2,picUrl: require('@/music/2.jpg'),name:"新歌BBB"},
      {id: 3,picUrl: require('@/music/3.jpg'),name:"最新的歌"},
      {id:4,picUrl: require('@/music/4.jpg'),name:"最新的歌322"},
      {id: 5,picUrl: require('@/music/1.jpg'),name:"新歌AAA"},
      {id: 6,picUrl: require('@/music/2.jpg'),name:"新歌BBB"},
      {id: 7,picUrl: require('@/music/3.jpg'),name:"最新的歌"},
      {id:8,picUrl: require('@/music/4.jpg'),name:"最新的歌322"},
      {id: 9,picUrl: require('@/music/3.jpg'),name:"最新的歌"},
      {id:10,picUrl: require('@/music/4.jpg'),name:"最新的歌322"}],
    }
  },
  mounted() {
    this.$API.getRecommendPlaylist(8).then(res => {
      this.lists = res.data.result
    })
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