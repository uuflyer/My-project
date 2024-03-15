<template>
    <div style="position: fixed;left: 200px;width: 100%;height: 100%;background: linear-gradient(135deg, #FFFFFF, #c2e9fb);">
        <div
            style="width: 100%;height: 100%;overflow-y: scroll;">
            <img src="@/components/Recommend/bg.png" />
          <el-button type="text">
            <h2 style="color: #000000; margin-right: 15px;font-size: 30px;">
              用户社交关系
            </h2>
          </el-button>
            <el-row style="padding: auto;">
                <span style="margin-left: 1%;">用户名称：</span>
                <el-input style="width: 200px;padding-bottom: 2%;" v-model="searchName" placeholder="请输入用户名称"></el-input>
                <el-button style="position: relative;" icon="el-icon-search" circle></el-button>
                <el-button type="success" icon="el-icon-plus" style="position:fixed; margin-right: 40px;"
                @click="showAddDilag()">添加</el-button>
            </el-row>
            
            <el-table
                :data="curtPageData"
                :border="true" 
                :header-cell-style="rowclass"
                :cell-style="{borderColor:'#C0C0C0'}"
                style="width: 100%;border: solid 1px black;">
                <el-table-column fixed prop="userName" label="用户名称" width="150">
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
                <el-table-column prop="trustStatus" label="信任状态">
                    <template slot-scope="trustStatusScope">
                        <el-tag v-if="trustStatusScope.row.trustStatus == '1'" type="success">关注</el-tag>
                        <el-tag v-else-if="trustStatusScope.row.trustStatus == '-1'" type="danger">不感兴趣</el-tag>
                        <el-tag v-else type="info">未标记</el-tag>
                    </template>

                </el-table-column>

                <el-table-column fixed="right" label="操作">
                    <template slot-scope="scope">
                        <el-button type="primary" @click="handleEdit(scope.row)" icon="el-icon-edit" circle></el-button>
                        <el-button type="danger" icon="el-icon-delete" circle @click="deleteItem(scope.row)"></el-button>
                    </template>
                </el-table-column>
            </el-table>
            <div class="block" style="margin-top: 15px;">
                <el-pagination align="center" @size-change="handleSizeChange" @current-change="handleCurrentChange"
                    :current-page="currentPage" :page-sizes="[1, 5, 10, 20]" :page-size="pageSize"
                    layout="total, sizes, prev, pager, next, jumper" :total="total">
                </el-pagination>
            </div>
    
        </div>



        <el-dialog center title="编辑用户关系" :append-to-body="true" :visible="showEditDialog" width="800px" height="800px"
            :before-close="handleClose">
            <el-row style="left: 10%;margin-top: 3%;">
                <span style="float: left; height: 35px;line-height: 35px;width: 20%;">用户名称：
                </span>
                <el-input style="float: left;height: 35px;resize:none;width: 60%;left: 35px;" v-model="curtEditRow.userName"
                    disabled></el-input>
            </el-row>
            <el-row style="left: 10%;margin-top: 3%;">
                <span style="float: left; height: 35px;line-height: 35px;width: 20%;">喜欢音乐类型：
                </span>
                <el-input style="float: left;height: 35px;resize:none;width: 60%;left: 35px;"
                    v-model="curtEditRow.interests" disabled></el-input>
            </el-row>
            <el-row style="left: 10%;margin-top: 3%;">
                <span style="float: left; height: 35px;line-height: 35px;width: 20%;">喜欢歌手：
                </span>
                <el-input style="float: left;height: 35px;resize:none;width: 60%;left: 35px;"
                    v-model="curtEditRow.interestSinger" disabled></el-input>
            </el-row>
            <el-row style="left: 10%;margin-top: 3%;">
                <span style="float: left; height: 35px;line-height: 35px;width: 20%;">信任状态：
                </span>
                <el-select clearable v-model="curtEditRow.trustStatus"
                    style="float: left;height: 35px;resize:none;width: 60%;left: 35px;">
                    <el-option v-for="item in options" :key="item.value" :label="item.label" :value="item.value">
                    </el-option></el-select>

            </el-row>
            <span slot="footer" class="dialog-footer" style="margin-top: 100px;">
                <el-button @click="closeEdit()">取 消</el-button>
                <el-button type="primary" @click="save(curtEditRow)">保 存</el-button>
            </span>
        </el-dialog>

        <el-dialog center title="新增用户关系" :append-to-body="true" :visible.sync="showAddDialog" width="60%"
            :before-close="handleCloseAddDialog">
            <el-table
                :data="curtPageNonRelateData"
                :border="true" style="width: 100%;height: 60%;">
                <el-table-column fixed prop="userName" label="用户名称" width="150">
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
                <el-table-column prop="trustStatus" label="信任状态">
                    <template slot-scope="trustStatusScope">
                        <el-select clearable v-model="trustStatusScope.row.trustStatus"
                            style="float: left;height: 35px;resize:none;width: 180px;left: 10px;">
                            <el-option v-for="item in options" :key="item.value" :label="item.label" :value="item.value">
                            </el-option></el-select>
                    </template>

                </el-table-column>

                <el-table-column fixed="right" label="操作">
                    <template slot-scope="scope">
                        <el-tooltip class="item" effect="dark" content="增加关系" placement="top">
                            <el-button type="primary" @click="addRelateShip(scope.row)" icon="el-icon-plus"
                                circle></el-button> </el-tooltip>
                    </template>
                </el-table-column>
            </el-table>
            <div class="block" style="margin-top: 15px;">
                <el-pagination align="center" @size-change="handleAddSizeChange" @current-change="handleAddRelateChange"
                    :current-page="addDialog.currentPage" :page-sizes="[1, 5, 10, 20]" :page-size="addDialog.pageSize"
                    layout="total, sizes, prev, pager, next, jumper" :total="addDialog.total">
                </el-pagination>
            </div>
            <span slot="footer" class="dialog-footer">
                <el-button @click="showAddDialog = false">关 闭</el-button>
            </span>
        </el-dialog>
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
            allNonRelatedData: [],
            curtPageNonRelateData: [],
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
        this.loadUserRlelationShip();
    },
        methods: {

            closeEdit() { 
                showEditDialog = false;
                this.loadUserRlelationShip();
            },
        //每页条数改变时触发 选择一页显示多少行
        handleSizeChange(val) {
            console.log(`每页 ${val} 条`);
            this.currentPage = 1;
            this.pageSize = val;
        },
        handleAddSizeChange(val) { 
            console.log(`每页 ${val} 条`);
            this.addDialog.currentPage = 1;
            this.addDialog.pageSize = val;
            this.curtPageNonRelateData = this.allNonRelatedData ? this.allNonRelatedData.slice((this.addDialog.currentPage - 1) * this.addDialog.pageSize, this.addDialog.currentPage * this.addDialog.pageSize) : [];
        }
        ,
        //当前页改变时触发 跳转其他页
        handleCurrentChange(val) {
            console.log(`当前页: ${val}`);
            this.currentPage = val;
            this.curtPageData = this.tableData ? this.tableData.slice((this.currentPage - 1) * this.pageSize, this.currentPage * this.pageSize) : []
        },
        //当前页改变时触发 跳转其他页
        handleAddRelateChange(val) {
            console.log(`当前页: ${val}`);  
            this.addDialog.currentPage = val;
            this.curtPageNonRelateData = this.allNonRelatedData ? this.allNonRelatedData.slice((this.addDialog.currentPage - 1) * this.addDialog.pageSize, this.addDialog.currentPage * this.addDialog.pageSize) : []
        },

        //编辑当前行数据
        handleEdit(row) {
            console.log('row', row);
            this.$set(this, 'curtEditRow', row);
            this.showEditDialog = true;
        },
        handleClose(done) {
            this.showEditDialog = false;
            this.$set(this, 'curtEditRow', {});
            done();
        },
        handleCloseAddDialog() {
            this.showAddDialog = false;
        },
        showAddDilag() {
            this.loadNonRelateData();
            this.showAddDialog = true;
        },
        loadNonRelateData() {
            this.$axios.post('/api/query_non_related_tableData/',
                { userId: this.$store.state.user.id })
                .then(resp => {
                    this.allNonRelatedData = resp.data.allData;
                    console.log('aaa',this.allNonRelatedData);
                    this.addDialog.total = resp.data.total;
                    this.curtPageNonRelateData = this.allNonRelatedData ? this.allNonRelatedData.slice((this.addDialog.currentPage - 1) * this.addDialog.pageSize, this.addDialog.currentPage * this.addDialog.pageSize) : []
                })
        },
        deleteItem(row) {
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
                        this.loadUserRlelationShip();
                    } else {
                        this.$message.error('删除失败！');
                    }
                })
            let filter = this.tableData.filter((val) => val.name !== row.name);
            this.$set(this, 'tableData', filter);
        },
        addRelateShip(rowData) {
            let param = { primaryUserId: this.$store.state.user.id, friendUserId: rowData.id, trustStatus: rowData.trustStatus };
            this.$axios.post('/api/add_tableData/',
                param)
                .then(resp => {
                    if (resp && resp.data.status === 'success') {
                        this.$message({
                            message: '新增关系成功！',
                            type: 'success'
                        });
                        console.log('rrrttttt', resp);
                        this.loadNonRelateData();
                        this.loadUserRlelationShip();
                    } else {
                        this.$message.error('新增关系失败！');
                    }

                })
        },
        loadUserRlelationShip() {
            let curtUserId = this.$store.state.user.id;
            this.$axios.post('/api/tableData/',
                { userId: curtUserId })
                .then(resp => { 
                    this.tableData = resp.data.allData;
                    if (resp.data.allData) { 
                        this.curtPageData = this.tableData.slice((this.currentPage - 1) * this.pageSize, this.currentPage * this.pageSize);
                        this.total = resp.data.total;
                    }
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
                        this.loadUserRlelationShip();
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