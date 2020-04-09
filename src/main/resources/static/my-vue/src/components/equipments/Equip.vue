<template>
  <div>
    <div style="position: fixed;top: 16%;left: 25%;font-size: 25px">
      <i class="el-icon-s-platform">
        <span>设备信息</span>
      </i>
      <el-row>

        <div class="delete">
          <el-button type="danger" class="el-icon-delete"
                     @click="centerDialogVisible=true" circle></el-button>
        </div>
        <div class="add">
          <el-button type="primary" class="el-icon-plus" circle @click="addFormVisible=true"></el-button>
        </div>

      </el-row>
    </div>

    <div>
      <el-table
        :data="equipData "
        ref="multipleTable"
        border
        v-loading="loading"
        highlight-current-row
        @selection-change="handleSelectionChange"
        style="position:fixed;width: 70%;left: 25%;top: 25%">

        <el-table-column
          type="selection"
          v-show="false"
        ></el-table-column>
        <el-table-column
          prop="eid"
          label="设备ID"
        >
        </el-table-column>
        <el-table-column
          prop="protocol"
          label="协议类型"
        >
        </el-table-column>
        <el-table-column
          prop="category"
          label="设备分类">
        </el-table-column>

        <el-table-column
          prop="description"
          label="设备描述">
        </el-table-column>


        <el-table-column
          prop="ename"
          label="设备名称">
        </el-table-column>

        <el-table-column
          prop="creatAt"
          sortable
          label="创建时间"></el-table-column>

        <el-table-column
          prop="updateAt"
          sortable
          label="更新时间">
        </el-table-column>
        <el-table-column
          label="操作">
          <template scope="scope">
            <el-button type="primary" icon="el-icon-edit" @click="handleEdit(scope.$index,scope.row)"
                       circle></el-button>
          </template>

        </el-table-column>
      </el-table>
    </div>


    <el-pagination
      @size-change="handleSizeChange"
      @current-change="handleCurrentChange"
      :current-page.sync="this.currentPage "
      :page-size="this.pagesize"
      layout="prev, pager, next, jumper"
      :total="this.total"
      style="position: fixed;top: 90%;left: 50%"
    >
    </el-pagination>


    <el-dialog
      title="提示"
      :visible.sync="centerDialogVisible"
      width="45%"
      top="25%"
      center>
      <span>确定删除选中设备</span>
      <span slot="footer" class="dialog-footer">
    <el-button @click="centerDialogVisible = false">取 消</el-button>
    <el-button type="primary" @click=" dialogFormDelete">确 定</el-button>
  </span>
    </el-dialog>


    <el-dialog title="添加设备信息" :visible.sync="addFormVisible">
      <el-form :model="addfrom">
        <el-form-item label="设备名称:" :label-width="formLableWidth">
          <el-input v-model="addfrom.ename" autocomplete="off" clearable class="formInput"></el-input>
        </el-form-item>
        <el-form-item label="连接协议:" :label-width="formLableWidth">
          <el-select v-model="addfrom.protocol" placeholder="请选择连接协议" class="formInput">
            <el-option label="HTTP" value="HTTP"></el-option>
            <el-option label="直接连接" value="直接连接"></el-option>
            <el-option label="TCP/IP" value="TCP/IP"></el-option>
          </el-select>
        </el-form-item>

        <el-form-item label="设备分类:" :label-width="formLableWidth">
          <el-select v-model="addfrom.category" placeholder="设备分类" class="formInput">
            <el-option label="智慧城市" value="智慧城市"></el-option>
            <el-option label="智慧水利" value="智慧水利"></el-option>
            <el-option label="智慧农业" value="智慧农业"></el-option>
            <el-option label="智能生活" value="智能生活"></el-option>
            <el-option label="智慧天气" value="智慧天气"></el-option>
          </el-select>
        </el-form-item>

        <el-form-item label="设备描述:" :label-width="formLableWidth">
          <el-input type="textarea" v-model="addfrom.description" placeholder="请输入设备描述" autosize
                    class="formInputDes"></el-input>
        </el-form-item>
      </el-form>
      <div slot="footer" class="formButton">
        <el-button @click="addFormVisible = false" type="danger">取 消</el-button>
        <el-button @click="addEquip(addfrom)" type="success submit">添 加</el-button>
      </div>

    </el-dialog>

    <el-dialog title="设备编辑" :visible.sync="editFormVisible" :close-on-click-modal="false">
      <el-form :model="editForm" label-width="120px">


        <el-form-item label="设备名称:">
          <el-input v-model="editForm.ename" class="formInput"></el-input>
        </el-form-item>

        <el-form-item label="设备ID:">
          <el-input v-model="editForm.eid" :disabled="true" class="formInput"></el-input>
        </el-form-item>
        <el-form-item label="用户ID:">
          <el-input v-model="editForm.userId" :disabled="true" class="formInput"></el-input>
        </el-form-item>

        <el-form-item label="协议类型:">
          <el-select v-model="editForm.protocol" class="formInput">
            <el-option label="HTTP" value="HTTP"></el-option>
            <el-option label="直接连接" value="直接连接"></el-option>
            <el-option label="TCP/IP" value="TCP/IP"></el-option>
          </el-select>
        </el-form-item>

        <el-form-item label="设备分类:" :label-width="formLableWidth">
          <el-select v-model="editForm.category" class="formInput">
            <el-option label="智慧城市" value="智慧城市"></el-option>
            <el-option label="智慧水利" value="智慧水利"></el-option>
            <el-option label="智慧农业" value="智慧农业"></el-option>
            <el-option label="智能生活" value="智能生活"></el-option>
            <el-option label="智慧天气" value="智慧天气"></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="设备描述:">
          <el-input type="textarea" v-model="editForm.description" autosize
                    class="formInputDes"></el-input>
        </el-form-item>


        <el-form-item label="创建时间:">
          <el-input v-model="editForm.creatAt" :disabled="true" class="formInput"></el-input>
        </el-form-item>


      </el-form>
      <div slot="footer" class="updateButton">
        <el-button type="danger" @click="editFormVisible=false">取 消</el-button>
        <el-button type="primary" @click="updateEquip(editForm)">修 改</el-button>
      </div>
    </el-dialog>
  </div>


</template>

<script>


  export default {
    name: "Equip",
    data() {
      return {
        pagesize: 6,
        total: 12,
        currentPage: 1,
        equipData: [],
        multipleTable: [],
        centerDialogVisible: false,
        addFormVisible: false,
        editFormVisible: false,
        loading: true,
        addfrom: {
          ename: null,
          userId: null,
          category: null,
          description: null,
          protocol: null
        },

        editForm: {
          eid: null,
          ename: null,
          category: null,
          protocol: null,
          userId: null,
          description: null,
          updateAt: null,
          creatAt: null
        },
        formLableWidth: '120px'
      }
    },
    methods: {

      async getData() {
        this.$axios.get('user/equip', {
          params: {
            page: this.currentPage,
            userId: sessionStorage.getItem("userId")
          }
        })
          .then(resp => {
            if (resp.data.status === "OK") {
              console.log(resp)
              this.total = resp.data.totalEquipNumber
              this.equipData = resp.data.data
              this.loading = false

            }

          })
      }

      ,

      handleSizeChange(val) {
        console.log(`每页 ${val} 条`);
      },
      handleEdit: function (index, row) {
        this.editFormVisible = true;
        this.editForm = Object.assign({}, row)

        console.log(this.editForm)
      }
      ,
      handleCurrentChange(val) {
        this.currentPage = val;
        console.log(`当前是第${val}页`)
        this.getData()
      },
      handleSelectionChange(val) {
        this.multipleTable = val
        console.log(val)

        var ids = this.multipleTable.map(item => item.eid).join()

        console.log(ids)

      },
      dialogFormDelete() {
        this.centerDialogVisible = false;
        var ids = this.multipleTable.map(item => item.eid).join();
        console.log(ids);
        if (ids.length === 0) {
          this.$notify({
            title: '警告',
            message: '请选择要删除的设备',
            type: 'warning'
          })
        } else {

          this.$axios.post('user/equip/delete', {
            eids: ids
          })
            .then(res => {
              if (res.data.status === 'OK') {
                this.$notify({
                  title: '成功',
                  message: '删除成功',
                  type: 'success'
                });
                this.getData()
              }
            })
            .catch(function (err) {

              console.log(err)
            });
        }
      },
      addEquip(formName) {
        this.$axios.post('user/equip', {
          ename: formName.ename,
          category: formName.category,
          protocol: formName.protocol,
          description: formName.description
        })
          .then(res => {
            if (res.data.status === 'OK') {
              this.addFormVisible = false;
              this.$notify({
                title: '成功',
                message: '添加成功',
                type: 'success'
              });
              this.getData();
            } else {
              this.$notify.error({
                title: '失败',
                message: res.data.msg,
              })
            }
          })
          .catch(function (err) {
            console.log(err);
            console.log(res.data.msg)
            this.$notify({
              title: '失败',
              message: '添加失败',
            })
          })

      },

      updateEquip(FormName) {
        var url = "user/equip/" + FormName.eid
        console.log(url)
        this.$axios.patch(url, FormName)
          .then(res => {
            if (res.data.status === 'OK') {
              this.editFormVisible = false;
              this.$notify({
                title: '成功',
                message: '修改成功',
                type: 'success'
              });
              this.getData();
            }
          })
          .catch(function (err) {
            console.log(err)
            this.$notify({
              title: '失败',
              message: res.msg
            })
          })
      }
    },

    mounted() {
      this.getData();
    }

  }
</script>

<style scoped>


  .dialog-footer {
    position: absolute;
    top: 75%;
    left: 50%;
  }

  .delete {
    position: fixed;
    right: 20%;
    top: 19%;
  }

  .add {
    position: fixed;
    right: 12%;
    top: 19%;
  }

  .formInput {
    left: -100px;
    width: 300px
  }

  .formInputDes {
    left: -25px;
    width: 450px;
  }

  .formButton {
    position: relative;
    left: -240px;
  }

  .updateButton {
    position: relative;
    left: -40%;

  }

</style>
