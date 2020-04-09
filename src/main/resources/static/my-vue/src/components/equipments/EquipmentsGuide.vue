<template>
  <el-tabs type="border-card" class="mainbody">
    <el-tab-pane label="设备接入" class="maintitle">
      <el-collapse>
        <h3>HTTP 协议</h3>
        <p>HTTP协议（HyperText Transfer
          Protocol，超文本传输协议）是互联网上应用最为广泛的一种网络协议。它用于传送WWW方式的数据，关于HTTP协议的详细内容请参考RFC2616。平台支持http及https两种接入方式。</p>
        <el-collapse-item title="HTTP协议详细内容" class="maindata" name="1">
          <h3 class="head">产品创建</h3>
          <span>产品创建是设备接入的第一步，根据不同的协议创建产品，支持创建产品、修改产品名称和产品类别等操作。如果不熟悉产品创建的详细步骤。</span>
          <h3 class="head">服务定义</h3>
          <span>设备的服务是用来描述一款设备是什么、能做什么以及如何控制该设备的文件。一个服务文件的内容主要包括设备的服务信息，这一部分定义了设备的业务数据，包括设备上报的上行数据和厂商服务器下发给设备的下行数据。</span>
          <h3 class="head">设备添加</h3>
          <span>在已创建的产品中，可以添加自己的终端设备，同时支持终端设备状态查看、认证信息、删除等操作。</span>
          <h3 class="head">线下设备开发</h3>
          <span>HTTP(S)协议线下设备开发包括SDK下载和通信模组两部分。</span>
          <h3 class="head">设备与平台对接</h3>
          <span>HTTP(S)协议的设备与平台对接时，分为设备注册登录、数据上报、事件上报、订阅推送等步骤。</span>
        </el-collapse-item>
        <h3>TCP 协议</h3>
        <p>支持基于TCP协议的业务数据透传，设备登录平台并完成认证以后，可以通过透传的方式进行上下行的数据传输。TCP透传的高灵活性决定了它不受约束，适用于任何TCP承载的用户私有协议</p>
        <el-collapse-item title="TCP协议详细内容" name="2">
          <h3 class="head">产品创建</h3>
          <span>产品创建是设备接入的第一步，根据不同的协议创建产品，支持创建产品、修改产品名称和产品类别等操作。</span>
          <h3 class="head">设备添加</h3>
          <span>在已创建的产品中，可以添加自己的终端设备，同时支持终端设备状态查看、认证信息、删除等操作。</span>
          <h3 class="head">线下设备开发</h3>
          <span>TCP协议线下设备开发包括SDK下载和通信模组两部分</span>
          <h3 class="head">设备与平台对接</h3>
          <span>TCP协议的设备与平台对接时，分为设备注册登录、数据上报、指令下发等步骤。</span>
        </el-collapse-item>
      </el-collapse>

      <el-button type="primary" plain @click="jump" style="position: relative;top: 20px">开始设备接入</el-button>

    </el-tab-pane>

    <el-tab-pane label="开发联调" class="maintitle">
      <h3>开发联调概述</h3>
      <p>开发者能直接调用API进行线下应用开发，其中请求数据的API格式为'/api/data'类型，并需要携带用户的ID以及密码进行验证才能正确获取自己设备的数据;也可以免费使用或者获取一些公用的设备数据</p>
    </el-tab-pane>
  </el-tabs>
</template>

<script>
  export default {
    name: "EquipmentsGuide",
    data() {
      return {}
    },
    methods: {
      jump() {
        this.$router.push({path: '/equipments/equip'})
      }
    }

  }
</script>

<style scoped>
  .mainbody {
    position: fixed;
    top: 10%;
    width: 70%;
    height: 85%;
    left: 24%;
    overflow-y: auto;
  }



  p {
    text-indent: 20px;
  }

  .head {
    position: relative;
    left: -43%;
  }
</style>
