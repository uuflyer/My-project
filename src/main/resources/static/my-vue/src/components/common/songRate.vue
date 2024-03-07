<template>
    <div style="position: fixed;left: 200px;width: 100%;height: 100%;background: linear-gradient(135deg, #FFFFFF, #c2e9fb);">
        <div
            style="width: 100%;height: 100%;overflow-y: scroll;">
            <img src="@/components/Recommend/bg.png" />
          <el-button type="text">
            <h2 style="color: #000000; margin-right: 15px;font-size: 30px;">
              歌曲评分
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
                <el-table-column prop="rate" label="歌曲评分">
                    <template slot-scope="rateScope">
                        <el-rate 
                        disabled
                        show-score
                            v-model="rateScope.row.rate"
                            score-template="{value}"
                            text-color="#ff9900"
                            :colors=colors>
                            </el-rate>
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
                    layout="total, sizes, prev, pager, next, jumper" :total="tableData ? tableData.length : 20">
                </el-pagination>
            </div>
    
        </div>



            <el-dialog center title="歌曲评分编辑" :append-to-body="true" :visible="showEditDialog" width="800px"
                :before-close="handleClose">
                <el-image :src="`${curtEditRow.picUrl}?param=150y140`" style='margin-left: 35%;width: 230px;height: 250px;'
      class="image" lazy />
                <el-row style="left: 10%;margin-top: 3%;">
                    <span style="float: left; height: 35px;line-height: 35px;width: 20%;">歌曲名称：
                    </span>
                    <el-input style="float: left;height: 35px;resize:none;width: 60%;" v-model="curtEditRow.songName"
                        disabled></el-input>
                </el-row>
                <el-row style="left: 10%;margin-top: 3%;">
                    <span style="float: left; height: 35px;line-height: 35px;width: 20%;">歌曲类型：
                    </span>
                    <el-input style="float: left;height: 35px;resize:none;width: 60%;"
                        v-model="curtEditRow.type" disabled></el-input>
                </el-row>
                <el-row style="left: 10%;margin-top: 3%;">
                    <span style="float: left; height: 35px;line-height: 35px;width: 20%;">歌曲评分：
                    </span>
                    <el-rate 
                    style="margin-top: 10px;"
                        show-score
                        allow-half
                            v-model="curtEditRow.rate"
                            score-template="{value}"
                            text-color="#ff9900"
                            :colors=colors>
                            </el-rate>

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

            },
            colors: ['#99A9BF', '#F7BA2A', '#FF9900'],
            
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
            tableData: [{songName:"1989",type:"欧美,流行",picUrl: require('@/music/all/1989.jpg'),singer:"Taylor Swift",rate: 3.7},
            {songName:"Lose Yourself",picUrl: require('@/music/all/lose yourself.jpg'),type:"Rap,欧美,流行",singer:"Eminem",rate: 2.5},
            {songName:"Rap God",type:"Rap,欧美,流行",picUrl: require('@/music/all/rap god.jpg'),singer:"Eminem",rate: 4.8},
            {songName:"黑马王子",type:"Rap,中文说唱",picUrl: require('@/music/all/黑马王子.jpg'),singer:"马思唯",rate: 4.3},
            {songName:"南方姑娘",type:"民谣,中文",picUrl: require('@/music/all/南方姑娘.jpg'),singer:"赵雷",rate: 3},
            {songName:"启示录",type:"中文,流行",picUrl: require('@/music/all/启示录.jpg'),singer:"邓紫棋",rate: 3},
            {songName:"她说",type:"中文,流行",picUrl: require('@/music/all/她说.jpg'),singer:"林俊杰",rate: 4.1},
            {songName:"吻别",type:"怀旧,情歌,中文",picUrl: require('@/music/all/吻别.jpg'),singer:"张学友",rate: 3},    
            {songName:"我是如此相信",type:"中文,流行",picUrl: require('@/music/all/我是如此相信.jpg'),singer:"周杰伦",rate: 3.6},
            {songName:"生命因你而火热",type:"摇滚,中文",picUrl: require('@/music/all/生命因你而火热.jpg'),singer:"新裤子",rate: 4}],
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