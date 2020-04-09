<template>
  <el-container>
    <el-aside style="width: 200px;margin-top: 20px">
      <side-menu>

      </side-menu>

    </el-aside>
    <el-main>
      <router-view></router-view>
    </el-main>

  </el-container>

</template>

<script>
  import SideMenu from "./sideMenu";
  import EquipmentsGuide from "./EquipmentsGuide";
  import Equip from "./Equip";

  export default {
    name: "EquipmentsIndex",
    components: {EquipmentsGuide, SideMenu, Equip}
  }
</script>

<style scoped>

</style>
