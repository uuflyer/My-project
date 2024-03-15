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
                <el-button type="success" icon="el-icon-plus" style="position:fixed; margin-right: 40px;"
                @click="showAddDilag()">添加</el-button>
            </el-row>
            
            <el-table
                :data="this.tableData ? this.tableData.slice((this.currentPage - 1) * this.pageSize, this.currentPage * this.pageSize) : []"
                :border="true" 
                :header-cell-style="rowclass"
                :cell-style="{borderColor:'#C0C0C0'}"
                style="width: 100%;border: solid 1px black;left: 5px;">
                <el-table-column fixed prop="name" label="歌曲名称" width="150">
                </el-table-column>
                <el-table-column prop="type" label="歌曲类型" >
                    <template slot-scope="typeScope" v-if="typeScope.row.type">
                        <el-tag style="margin-left: 2%;margin-top: 2%;"
                            v-for="item in typeScope.row.type.split(',')" :key="item">{{
                                item }}</el-tag>
                    </template>
                </el-table-column>
                <el-table-column prop="singer" label="歌手名称">
                    <template slot-scope="singerScope" v-if="singerScope.row.singer">
                        <el-tag style="margin-left: 2%;margin-top: 2%;"
                            v-for="item in singerScope.row.singer.split(',')" :key="item">{{
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
                <el-image :src="`${curtEditRow.url}?param=150y140`" style='margin-left: 35%;width: 230px;height: 250px;'
      class="image" lazy />
                <el-row style="left: 10%;margin-top: 3%;">
                    <span style="float: left; height: 35px;line-height: 35px;width: 20%;">歌曲名称：
                    </span>
                    <el-input style="float: left;height: 35px;resize:none;width: 60%;" v-model="curtEditRow.name"
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
                    <el-button type="primary" @click="updateRate(curtEditRow)">保 存</el-button>
                </span>
            </el-dialog>

        <el-dialog center title="新增歌曲评分" :append-to-body="true" :visible.sync="showAddDialog" width="60%"
            :before-close="handleCloseAddDialog">
            <el-table
                :data="noRateSong.slice((this.addDialog.currentPage - 1) * this.addDialog.pageSize, this.addDialog.currentPage * this.addDialog.pageSize)"
                :border="true" style="width: 100%;height: 60%;">
                <el-table-column fixed prop="name" label="歌曲名称" width="150">
                </el-table-column>
                <el-table-column prop="type" label="歌曲类型" fixed width="200">
                    <template slot-scope="typeScope">
                        <el-tag style="margin-left: 2%;margin-top: 2%;"
                            v-for="item in typeScope.row.type.split(',')" :key="item">{{
                                item }}</el-tag>
                    </template>
                </el-table-column>
                <el-table-column prop="singer" label="歌手名称" fixed width="180">
                    <template slot-scope="typeScope">
                        <el-tag style="margin-left: 2%;margin-top: 2%;"
                            v-for="item in typeScope.row.singer.split(',')" :key="item">{{
                                item }}</el-tag>
                    </template>
                </el-table-column>
                <el-table-column prop="rate" label="歌曲评分">
                    <template slot-scope="rateScope">
                        <el-rate 
                            show-score
                            allow-half
                            v-model="rateScope.row.rate"
                            score-template="{value}"
                            text-color="#ff9900"
                            :colors=colors>
                            </el-rate>
                    </template>
                </el-table-column>

                <el-table-column fixed="right" label="操作">
                    <template slot-scope="scope">
                        <el-tooltip class="item" effect="dark" content="增加歌曲评分" placement="top">
                            <el-button type="primary" @click="addRateSong(scope.row)" icon="el-icon-plus"
                                circle></el-button> </el-tooltip>
                    </template>
                </el-table-column>
            </el-table>
            <div class="block" style="margin-top: 15px;">
                <el-pagination align="center" @size-change="handleAddSizeChange" @current-change="handleAddRelateChange"
                    :current-page="addDialog.currentPage" :page-sizes="[1, 5, 10, 20]" :page-size="addDialog.pageSize"
                    layout="total, sizes, prev, pager, next, jumper" :total="noRateSong.length">
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
            noRateSong: [],
            addDialog: {
                currentPage: 1, // 当前页码
                total: 20, // 总条数
                pageSize: 5, // 每页的数据条数
                curtPageData: [],

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
            tableData: [],
            curtPageData: [],
        }
    },
    mounted() {
        this.loadAllRatedSongInfo();
    },
    methods: {
        //每页条数改变时触发 选择一页显示多少行
        handleSizeChange(val) {
            console.log(`每页 ${val} 条`);
            this.currentPage = 1;
            this.pageSize = val;
           this.curtPageData = this.tableData ? this.tableData.slice((this.currentPage - 1) * this.pageSize, this.currentPage * this.pageSize) : []
        },
        //当前页改变时触发 跳转其他页
        handleCurrentChange(val) {
            console.log(`当前页: ${val}`);
            this.currentPage = val;
            this.curtPageData =  this.tableData ? this.tableData.slice((this.currentPage - 1) * this.pageSize, this.currentPage * this.pageSize) : []
        },

        //每页条数改变时触发 选择一页显示多少行
        handleAddSizeChange(val) {
            console.log(`每页 ${val} 条`);
            this.addDialog.currentPage = 1;
            this.addDialog.pageSize = val;
            this.addDialog.curtPageData = this.noRateSong ? this.noRateSong.slice((this.addDialog.currentPage - 1) * this.addDialog.pageSize, this.addDialog.currentPage * this.addDialog.pageSize) : []
        },
        //当前页改变时触发 跳转其他页
        handleAddRelateChange(val) {
            console.log(`当前页: ${val}`);
            this.addDialog.currentPage = val;
            this.addDialog.curtPageData = this.noRateSong ? this.noRateSong.slice((this.addDialog.currentPage - 1) * this.addDialog.pageSize, this.addDialog.currentPage * this.addDialog.pageSize) : []
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
            this.loadNoRateSongInfo();
            this.showAddDialog = true;
        },
        loadNoRateSongInfo() {
            this.$axios.post('/api/unratedMusic/',
                { userId: this.$store.state.user.id,pageSize: this.addDialog.pageSize,offset:(this.addDialog.currentPage - 1)*this.addDialog.pageSize })
                .then(resp => {
                    this.noRateSong = resp.data.allData;
                    this.addDialog.total = resp.data.total;
                })
        },
        deleteItem(row) {

            this.$confirm('是否确认删除当前歌曲评分信息?', '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(() =>{
            let curtUserId = this.$store.state.user.id;
            this.$axios.post('/api/delMusicRate/',
                { userId: curtUserId, rateId: row.rateId })
                .then(resp => {
                    if (resp && resp.data.status === 'success') {
                        this.$message({
                            message: '删除成功！',
                            type: 'success'
                        });
                        console.log('delete', resp);
                        this.loadAllRatedSongInfo();
                    } else {
                        this.$message.error('删除失败！');
                    }
                })
            let filter = this.tableData.filter((val) => val.name !== row.name);
            this.$set(this, 'tableData', filter);})
        },
        addRateSong(rowData) {
            let param = { userId: this.$store.state.user.id, songId: rowData.id, rate: rowData.rate };
            this.$axios.post('/api/saveMusicRate/',
                param)
                .then(resp => {
                    if (resp && resp.data.status === 'success') {
                        this.$message({
                            message: '新增歌曲评分成功！',
                            type: 'success'
                        });
                        console.log('rrrttttt', resp);
                        this.loadNoRateSongInfo();
                        this.loadAllRatedSongInfo();
                    } else {
                        this.$message.error('新增歌曲评分失败！');
                    }

                })
        },
        loadAllRatedSongInfo() {
            let curtUserId = this.$store.state.user.id;
            this.$axios.post('/api/ratedMusic/',
                { userId: curtUserId,pageSize:this.pageSize,offset:(this.currentPage - 1) * this.pageSize })
                .then(resp => {
                    this.tableData = resp.data.allData;
                    if (this.tableData) { 
                        this.tableData.forEach(element => {
                            element.url = require('@/music/音乐数据集图片/'+element.url)
                        });
                        console.log('allRatedMusic',this.tableData);
                    }
                    this.curtPageData = this.tableData.slice((this.currentPage - 1) * this.pageSize, this.currentPage * this.pageSize);
                    this.total = resp.data.total;
                })
        },
        updateRate(curtEditRow) {
            let param = {
                userId: this.$store.state.user.id,
                rateId: curtEditRow.rateId,
                rate: curtEditRow.rate
            };
            this.$axios.post('/api/updateMusicRate/',
                param)
                .then(resp => {
                    if (resp && resp.data.status === 'success') {
                        this.$message({
                            message: '保存成功！',
                            type: 'success'
                        });
                        this.showEditDialog = false
                        this.loadAllRatedSongInfo();
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