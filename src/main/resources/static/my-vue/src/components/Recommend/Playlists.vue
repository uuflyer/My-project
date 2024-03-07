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
      lists: [{id: 1,picUrl: require('@/music/all/1989.jpg'),name:"1989",singerName:"Taylor swift"},
      {id: 2,picUrl: require('@/music/all/lose yourself.jpg'),name:"lose yourself",singerName:"Eminem"},
      {id: 3,picUrl: require('@/music/all/rap god.jpg'),name:"rap god",singerName:"Eminem"},
      {id:4,picUrl: require('@/music/all/黑马王子.jpg'),name:"黑马王子",singerName:"马思唯"},
      {id: 5,picUrl: require('@/music/all/南方姑娘.jpg'),name:"南方姑娘",singerName:"赵雷"},
      {id: 6,picUrl: require('@/music/all/启示录.jpg'),name:"启示录",singerName:"邓紫棋"},
      {id: 7,picUrl: require('@/music/all/生命因你而火热.jpg'),name:"生命因你而火热",singerName:"新裤子"},
      {id:8,picUrl: require('@/music/all/她说.jpg'),name:"她说",singerName:"林俊杰"},
      {id: 9,picUrl: require('@/music/all/吻别.jpg'),name:"吻别",singerName:"张学友"},
      {id:10,picUrl: require('@/music/all/我是如此相信.jpg'),name:"我是如此相信",singerName:"周杰伦"}],
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