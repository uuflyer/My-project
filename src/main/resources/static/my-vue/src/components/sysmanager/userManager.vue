<template>
    <div style="position: fixed;left: 200px;width: 100%;height: 100%;background: linear-gradient(135deg, #FFFFFF, #c2e9fb);overflow-y: scroll;">
        <div
            style="width: 100%;height: 100%;overflow-y: scroll;">
            <img src="@/components/Recommend/bg.png" />
          <el-button type="text">
            <h2 style="color: #000000; margin-right: 15px;font-size: 30px;">
              用户信息管理
            </h2>
          </el-button>
            <el-row style="padding: auto;">
                <span style="margin-left: 1%;">用户名称：</span>
                <el-input style="width: 200px;padding-bottom: 2%;" v-model="searchName" placeholder="请输入用户名称"></el-input>
                <el-button style="position: relative;" @click="searchUser()" icon="el-icon-search" circle></el-button>
            </el-row>
            
            <el-table
                :data="this.tableData ? this.tableData.slice((currentPage - 1) * pageSize, currentPage * pageSize) : []"
                :border="true" 
                :header-cell-style="rowclass"
                :cell-style="{borderColor:'#C0C0C0'}"
                style="width: 100%;border: solid 1px black;overflow-y: scroll;">
                <el-table-column fixed prop="userName" label="用户名称" width="150">
                    <template slot-scope="userNameScope" v-if="userNameScope.row.userName">
                        <el-tag style="margin-left: 2%;margin-top: 2%;"
                             :key="userNameScope.row.userName">{{
                                userNameScope.row.userName }}</el-tag>
                    </template>
                </el-table-column>
                <el-table-column prop="interestsSongType" label="喜欢歌曲类型">
                    <template slot-scope="interestScope" v-if="interestScope.row.interestsSongType">
                        <el-tag style="margin-left: 2%;margin-top: 2%;"
                            v-for="item in interestScope.row.interestsSongType.split(',')" :key="item">{{
                                item }}</el-tag>
                    </template>
                </el-table-column>
                <el-table-column prop="interestSinger" label="喜欢歌手">
                    <template slot-scope="interestSingerScope" v-if="interestSingerScope.row.interestSinger">
                        <el-tag style="margin-left: 2%;margin-top: 2%;"
                            v-for="item in interestSingerScope.row.interestSinger.split(',')" :key="item">{{
                                item }}</el-tag>
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
                    :current-page="currentPage" :page-sizes="[1, 5, 10, 15]" :page-size="pageSize"
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
            showEditDialog: false,
            showAddDialog: false,
            nonRelatedData: [],
            addDialog: {
                currentPage: 1, // 当前页码
                total: 20, // 总条数
                pageSize: 5, // 每页的数据条数

            }
            ,
            options: [{
                label: '关注',
                value: 1,
            },
            {
                label: '不感兴趣',
                value: -1,
            },
            {
                label: '未标记',
                value: 0,
            }
            ]
            ,
            tableData: [],
            curtPageData: [],
        }
    },
    mounted() {
        this.loadAllUserInfo();
    },
    methods: {
        //每页条数改变时触发 选择一页显示多少行
        handleSizeChange(val) {
            console.log(`每页 ${val} 条`);
            this.currentPage = 1;
            this.pageSize = val;
            // this.curtPageData = 
        },
        //当前页改变时触发 跳转其他页
        handleCurrentChange(val) {
            console.log(`当前页: ${val}`);
            this.currentPage = val;
            // this.curtPageData = this.tableData ? this.tableData.slice((currentPage - 1) * pageSize, currentPage * pageSize) : []
        },
        searchUser() { 
            this.$axios.post('/api/loadSearchUser/', {userName: this.searchName})
                .then(resp => {
                    this.tableData = resp.data.allData; 
                    console.log('loadAllUser',resp.data);
                    this.curtPageData = this.tableData.slice((this.currentPage - 1) * this.pageSize, this.currentPage * this.pageSize);
                    this.total = resp.data.total;
                })


        },

        deleteItem(row) {

            this.$confirm('是否确认删除当前用户?', '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(() =>{
            this.$axios.post('/api/delUser/',
                { userId: row.id })
                .then(resp => {
                    if (resp && resp.data.status === 'success') {
                        this.$message({
                            message: '删除成功！',
                            type: 'success'
                        });
                        console.log('delete', resp);
                        this.loadAllUserInfo();
                    } else {
                        this.$message.error('删除失败！');
                    }
                })
            })
        },
        loadAllUserInfo() {
            this.$axios.post('/api/loadAllUser/')
                .then(resp => {
                    this.tableData = resp.data.allData; 
                    console.log('loadAllUser',resp.data);
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
                        this.loadAllUserInfo();
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