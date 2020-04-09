<template>
  <el-container>
    <nav-menu>
    </nav-menu>

    <router-view/>
  </el-container>


</template>

<script>
  import NavMenu from "./common/NavMenu";

  export default {
    name: "Home",
    components: {NavMenu}
  }
</script>
