<template>
  <div>
    <div class="left-mid">
      <img src="../bg.png"/>
      <el-button type="text">
        <h2 style="color: #000000; margin-right: 15px">
          新碟上架
        </h2>
      </el-button>
    </div>
    <hr color="#C20C0C" style="margin-bottom: 20px"/>
    <div class="albums">
      <div v-for="album in newestAlbum" :key="album.id">
        <Record :album="album"></Record>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: "NewestAlbum",
  props: {
    newestAlbum: {
      type: Array,
      default: () => [],
    },
  },
}
</script>

<style scoped lang="less">
.left-mid {
  margin-top: 20px;
}

.albums {
  height: 300px;
  padding: 10px;
  background: #f5f5f5;
  border: 1px solid #d3d3d3;
  display: flex;
  flex-flow: wrap;

  div {
    flex: 20%;
  }
}
</style>