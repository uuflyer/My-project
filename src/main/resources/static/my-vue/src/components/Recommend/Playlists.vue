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