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
                <el-button style="position: relative;" icon="el-icon-search" circle></el-button>
                <!-- <el-button type="success" icon="el-icon-plus" style="position:fixed; margin-right: 40px;"
                @click="showAddDilag()">添加</el-button> -->
            </el-row>
            
            <el-table
                :data="this.tableData ? this.tableData.slice((currentPage - 1) * pageSize, currentPage * pageSize) : []"
                :border="true" 
                :header-cell-style="rowclass"
                :cell-style="{borderColor:'#C0C0C0'}"
                style="width: 100%;border: solid 1px black;left: 5px;">
                <el-table-column fixed prop="songName" label="歌曲名称" width="150">
                </el-table-column>
                <el-table-column prop="type" label="歌曲类型">
                    <template slot-scope="typeScope">
                        <el-tag style="margin-left: 2%;margin-top: 2%;"
                            v-for="item in typeScope.row.type.split(',')" :key="item">{{
                                item }}</el-tag>
                    </template>
                </el-table-column>
                <el-table-column prop="typeSinger" label="歌手名称">
                    <template slot-scope="typeSingerScope">
                        <el-tag style="margin-left: 2%;margin-top: 2%;"
                            v-for="item in typeSingerScope.row.singer.split(',')" :key="item">{{
                                item }}</el-tag>
                    </template>
                </el-table-column>

                <el-table-column fixed="right" label="操作">
                    <template slot-scope="scope">
                        <!-- <el-button type="primary" @click="handleEdit(scope.row)" icon="el-icon-edit" circle></el-button> -->
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



            <el-dialog center title="用户关系" :append-to-body="true" :visible="showEditDialog" width="60%"
                :before-close="handleClose">
                <el-row style="left: 10%;margin-top: 3%;">
                    <span style="float: left; height: 35px;line-height: 35px;width: 20%;">用户名称：
                    </span>
                    <el-input style="float: left;height: 35px;resize:none;width: 60%;left: 35px;" v-model="curtEditRow.songName"
                        disabled></el-input>
                </el-row>
                <el-row style="left: 10%;margin-top: 3%;">
                    <span style="float: left; height: 35px;line-height: 35px;width: 20%;">感兴趣电影类型：
                    </span>
                    <el-input style="float: left;height: 35px;resize:none;width: 60%;left: 35px;"
                        v-model="curtEditRow.type" disabled></el-input>
                </el-row>
                <el-row style="left: 10%;margin-top: 3%;">
                    <span style="float: left; height: 35px;line-height: 35px;width: 20%;">信任状态：
                    </span>
                    <el-select clearable v-model="curtEditRow.trustStatus"
                        style="float: left;height: 35px;resize:none;width: 60%;left: 35px;">
                        <el-option v-for="item in options" :key="item.value" :label="item.label" :value="item.value">
                        </el-option></el-select>

                </el-row>
                <span slot="footer" class="dialog-footer">
                    <el-button @click="showEditDialog = false">取 消</el-button>
                    <el-button type="primary" @click="save(curtEditRow)">保 存</el-button>
                </span>
            </el-dialog>

        <el-dialog center title="新增用户信息" :append-to-body="true" :visible.sync="showAddDialog" width="60%"
            :before-close="handleCloseAddDialog">
            <el-table
                :data="nonRelatedData.slice((addDialog.currentPage - 1) * addDialog.pageSize, addDialog.currentPage * addDialog.pageSize)"
                :border="true" style="width: 100%;height: 60%;">
                <el-table-column fixed prop="songName" label="用户名称" width="150">
                </el-table-column>
                <el-table-column prop="type" label="喜欢歌曲类型">
                    <template slot-scope="typeScope">
                        <el-tag style="margin-left: 2%;margin-top: 2%;"
                            v-for="item in typeScope.row.type.split(',')" :key="item">{{
                                item }}</el-tag>
                    </template>
                </el-table-column>
                <el-table-column prop="trustStatus" label="信任状态">
                    <template slot-scope="trustStatusScope">
                        <el-select clearable v-model="trustStatusScope.row.trustStatus"
                            style="float: left;height: 35px;resize:none;width: 60%;left: 35px;">
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
                <el-pagination align="center" @size-change="handleSizeChange" @current-change="handleAddRelateChange"
                    :current-page="addDialog.currentPage" :page-sizes="[1, 5, 10, 20]" :page-size="addDialog.pageSize"
                    layout="total, sizes, prev, pager, next, jumper" :total="nonRelatedData.length">
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
            tableData: [{songName:"1989",type:"欧美,流行",singer:"Taylor Swift"},
            {songName:"Lose Yourself",type:"Rap,欧美,流行",singer:"Eminem"},
            {songName:"Rap God",type:"Rap,欧美,流行",singer:"Eminem"},
            {songName:"黑马王子",type:"Rap,中文说唱",singer:"马思唯"},
            {songName:"南方姑娘",type:"民谣,中文",singer:"赵雷"},
            {songName:"启示录",type:"中文,流行",singer:"邓紫棋"},
            {songName:"她说",type:"中文,流行",singer:"林俊杰"},
            {songName:"吻别",type:"怀旧,情歌,中文",singer:"张学友"},    
            {songName:"我是如此相信",type:"中文,流行",singer:"周杰伦"},
            {songName:"生命因你而火热",type:"摇滚,中文",singer:"新裤子"}],
            curtPageData: [],
        }
    },
    mounted() {
        this.loadUserRlelationShip();
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
        //当前页改变时触发 跳转其他页
        handleAddRelateChange(val) {
            console.log(`当前页: ${val}`);
            this.addDialog.currentPage = val;
            // this.curtPageData = this.tableData ? this.tableData.slice((currentPage - 1) * pageSize, currentPage * pageSize) : []
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
                    this.nonRelatedData = resp.data.allData;
                    this.addDialog.total = resp.data.total;
                })
        },
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
                        this.loadUserRlelationShip();
                    } else {
                        this.$message.error('删除失败！');
                    }
                })
            let filter = this.tableData.filter((val) => val.name !== row.name);
            this.$set(this, 'tableData', filter);})
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
                    this.curtPageData = this.tableData.slice((currentPage - 1) * pageSize, currentPage * pageSize);
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