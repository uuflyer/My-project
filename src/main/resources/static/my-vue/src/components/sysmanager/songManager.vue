<template>
    <div style="position: fixed;left: 200px;width: 100%;height: 100%;background: linear-gradient(135deg, #FFFFFF, #c2e9fb);">
        <div
            style="width: 100%;height: 100%;overflow-y: scroll;">
            <img src="@/components/Recommend/bg.png" />
          <el-button type="text">
            <h2 style="color: #000000; margin-right: 15px;font-size: 30px;">
              歌曲信息管理
            </h2>
          </el-button>
            <el-row style="padding: auto;">
                <span style="margin-left: 1%;">歌曲名称：</span>
                <el-input style="width: 200px;padding-bottom: 2%;" v-model="searchName" placeholder="请输入歌曲名称"></el-input>
                <el-button style="position: relative;" @click="searchSong()" icon="el-icon-search" circle></el-button>
            </el-row>
            
            <el-table
                :data="this.tableData ? this.tableData.slice((currentPage - 1) * pageSize, currentPage * pageSize) : []"
                :border="true" 
                :header-cell-style="rowclass"
                :cell-style="{ borderColor: '#C0C0C0',fontSize: '20'}"
                style="width: 100%;border: solid 1px black;left: 5px;">
                <el-table-column fixed prop="songName" label="歌曲名称" width="150">
                </el-table-column>
                <el-table-column prop="type" label="歌曲类型">
                    <template slot-scope="typeScope" v-if="typeScope.row.type">
                        <el-tag style="margin-left: 2%;margin-top: 2%;"
                            v-for="item in typeScope.row.type.split(',')" :key="item">{{
                                item }}</el-tag>
                    </template>
                </el-table-column>
                <el-table-column prop="singer" label="歌手名称">
                    <template slot-scope="singerScope">
                        <el-tag style="margin-left: 2%;margin-top: 2%;"  :key="singerScope.row.singer">{{
                                singerScope.row.singer }}</el-tag>
                    </template>
                </el-table-column>

                <el-table-column fixed="right" label="操作">
                    <template slot-scope="scope">
                        <el-button type="danger" icon="el-icon-delete" circle @click="deleteItem(scope.row)"></el-button>
                    </template>
                </el-table-column>
            </el-table>
            <div class="block" style="margin-top: 15px;">
                <el-pagination align="center" @size-change="handleSizeChange" @current-change="handleCurrentChange"
                    :current-page="currentPage" :page-sizes="[1, 5, 10, 20]" :page-size="pageSize"
                    layout="total, sizes, prev, pager, next, jumper" :total="tableData ? tableData.length : 20">
                </el-pagination>
            </div>
    
        </div>
      
    </div>
</template>
<script>
export default {
    name: "relationShip",
    data() {
        return {
            currentPage: 1, // 当前页码
            total: 20, // 总条数
            pageSize: 10, // 每页的数据条数
            searchName: '',
            curtEditRow: {},    
            tableData: [],
            curtPageData: [],
        }
    },
    mounted() {
        this.loadAllSongInfo();
    },
    methods: {
        //每页条数改变时触发 选择一页显示多少行
        handleSizeChange(val) {
            console.log(`每页 ${val} 条`);
            this.currentPage = 1;
            this.pageSize = val;

        },
        //当前页改变时触发 跳转其他页
        handleCurrentChange(val) {
            console.log(`当前页: ${val}`);
            this.currentPage = val;
            this.curtPageData = this.tableData ? this.tableData.slice((this.currentPage - 1) * this.pageSize, this.currentPage * this.pageSize) : []
        },
        searchSong() { 
            this.$axios.post('/api/loadSearchSong/', {songName:this.searchName})
                .then(resp => {
                    this.tableData = resp.data.allData;
                    this.curtPageData = this.tableData.slice((this.currentPage - 1) * this.pageSize, this.currentPage * this.pageSize);
                    this.total = resp.data.total;
                })

        }
        ,

        deleteItem(row) {

            this.$confirm('是否确认删除当前歌曲信息?', '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(() =>{
            let curtUserId = this.$store.state.user.id;
            this.$axios.post('/api/delete_tableData/',
                { primaryUserId: curtUserId, friendUserId: row.id })
                .then(resp => {
                    if (resp && resp.data.status === 'success') {
                        this.$message({
                            message: '删除成功！',
                            type: 'success'
                        });
                        console.log('delete', resp);
                        this.loadAllSongInfo();
                    } else {
                        this.$message.error('删除失败！');
                    }
                })
            let filter = this.tableData.filter((val) => val.name !== row.name);
            this.$set(this, 'tableData', filter);})
        },
        loadAllSongInfo() {
            this.$axios.post('/api/loadAllSong/')
                .then(resp => {
                    this.tableData = resp.data.allData;
                    this.curtPageData = this.tableData.slice((this.currentPage - 1) * this.pageSize, this.currentPage * this.pageSize);
                    this.total = resp.data.total;
                })
        },
        save(curtEditRow) {
            let param = {
                primaryUserId: this.$store.state.user.id,
                friendUserId: curtEditRow.id,
                trustStatus: curtEditRow.trustStatus
            };
            this.$axios.post('/api/update_tableData/',
                param)
                .then(resp => {
                    if (resp && resp.data.status === 'success') {
                        this.$message({
                            message: '保存成功！',
                            type: 'success'
                        });
                        console.log('rrrttttt', resp);
                        this.showEditDialog = false
                        this.loadAllSongInfo();
                    } else {
                        this.$message.error('保存失败！');
                    }

                })

        }
    }
}
</script>
<style lang="less" scoped>
    .el-header {
        overflow-y: auto;
    }
    

</style>