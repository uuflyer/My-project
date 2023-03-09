<template>
    <div style="position: fixed;left: 0;top: 8%;width: 100%;height: 100%;background: var(--米白);">

        <div style="width: 100%;height: 100%;box-shadow: 12px 12px 12px rgba(0,0,0,0.1);border: rgba(0,0,0,0.1);">
            <el-row style="top: 5%;">
                <el-input style="left: 30%;width: 30%;" v-model="searchName" placeholder="请输入用户名称"></el-input>
                <el-button style="position: relative;margin-left: 30%;" icon="el-icon-search" circle></el-button>
            </el-row>
            <h2>用户社交列表
            </h2>
            <el-button type="success" icon="el-icon-plus" style="margin: 1% 1% 1% 1%;">新增用户关系</el-button>
            <el-table :data="tableData.slice((currentPage - 1) * pageSize, currentPage * pageSize)" :border="true"
                style="width: 100%">
                <el-table-column fixed prop="name" label="用户名称" width="150">
                </el-table-column>
                <el-table-column prop="sex" label="性别">
                    <template slot-scope="sexScope">
                        <el-tag v-if="sexScope.row.sex == '1'">男</el-tag>
                        <el-tag v-else type="warning">女</el-tag>
                    </template>
                </el-table-column>
                <el-table-column prop="interests" label="感兴趣电影类型">
                    <template slot-scope="interestScope">
                        <el-tag style="margin-left: 2%;margin-top: 2%;" v-for="item in interestScope.row.interests"
                            :key="item.type">{{
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
                    layout="total, sizes, prev, pager, next, jumper" :total="tableData.length">
                </el-pagination>
            </div>
        </div>



        <el-dialog center title="用户关系" :append-to-body="true" :visible="showEditDialog" width="60%"
            :before-close="handleClose">
            <el-row style="left: 10%;margin-top: 3%;">
                <span style="float: left; height: 35px;line-height: 35px;width: 20%;">用户名称：
                </span>
                <el-input style="float: left;height: 35px;resize:none;width: 60%;left: 35px;" v-model="curtEditRow.name"
                    disabled></el-input>
            </el-row>
            <el-row style="left: 10%;margin-top: 3%;">
                <span style="float: left; height: 35px;line-height: 35px;width: 20%;">性别：
                </span>
                <el-input style="float: left;height: 35px;resize:none;width: 60%;left: 35px;" v-model="curtEditRow.sex"
                    disabled></el-input>
            </el-row>
            <el-row style="left: 10%;margin-top: 3%;">
                <span style="float: left; height: 35px;line-height: 35px;width: 20%;">感兴趣电影类型：
                </span>
                <el-input style="float: left;height: 35px;resize:none;width: 60%;left: 35px;"
                    v-model="curtEditRow.interests" disabled></el-input>
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
                <el-button type="primary" @click="showEditDialog = false">保 存</el-button>
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
            options: [{
                label: '关注',
                value: '1',
            },
            {
                label: '不感兴趣',
                value: '-1',
            },
            {
                label: '未标记',
                value: '0',
            }
            ]
            ,
            tableData: [
                {
                    name: '测试用户1',
                    sex: '1',
                    interests: ['喜剧', '科幻', '惊悚', '冒险'],
                    trustStatus: '1',
                }, {
                    name: '测试用户2',
                    sex: '0',
                    interests: ['爱情', '犯罪', '悬疑', '灾难'],
                    trustStatus: '-1',
                }
                , {
                    name: '测试用户3',
                    sex: '1',
                    interests: ['动画', '传记', '家庭', '奇幻'],
                    trustStatus: '1',
                }
                , {
                    name: '测试用户4',
                    sex: '1',
                    interests: ['战争', '剧情', '犯罪', '推理'],
                    trustStatus: '0',
                }
                , {
                    name: '测试用户5',
                    sex: '0',
                    interests: ['暴力', '历史', '战争', '音乐'],
                    trustStatus: '-1',
                }
                , {
                    name: '三体',
                    sex: '0',
                    interests: ['喜剧', '悬疑', '科幻', '惊悚', '冒险'],
                    trustStatus: '-1',
                }
                , {
                    name: '智子',
                    sex: '0',
                    interests: ['爱情', '剧情',],
                    trustStatus: '-1',
                }
                , {
                    name: '太阳',
                    sex: '0',
                    interests: ['奇幻', '纪录片', '惊悚'],
                    trustStatus: '0',
                }
            ],
        }
    },
    mounted() {

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
        deleteItem(row) {
            let filter = this.tableData.filter((val) => val.name !== row.name);
            this.$set(this, 'tableData', filter);
        }
    }
}
</script>
<style>
.el-header {
    overflow-y: auto;
}
</style>