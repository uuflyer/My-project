<template>
  <div>
    <el-container>
    <nav-menu>
    </nav-menu>

  </el-container>
  <router-view/>
  </div>
 


</template>

<script>
  import NavMenu from "./common/NavMenu";

  export default {
    name: "Home",
    components: {NavMenu}
  }
</script>
