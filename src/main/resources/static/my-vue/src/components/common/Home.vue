<template>
    <div id='app'>
   
      <AppMain />
    </div>
  </template>
  
  <script>
//   import Topbar from '../topbar/Topbar'
  import AppMain from './AppMain'
  
  export default {
    name: 'App',
    components: {
      AppMain,
     
    },
  }
  </script>
  
  <style scoped>
  #app {
    background-color: #f5f5f5;
    display: flex;
    flex-flow: column;
    justify-content: center;
    align-items: center;
  }
  
  </style>
  
  