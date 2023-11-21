<template>
  <div class="app-container">

    <!-- Search toolbar -->
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="Budget category" prop="category">
        <el-input v-model="queryParams.category" placeholder="Please enter budget category" clearable @keyup.enter.native="handleQuery"/>
      </el-form-item>
      <el-form-item label="Budget amount" prop="amount">
        <el-input v-model="queryParams.amount" placeholder="Please enter budget amount" clearable @keyup.enter.native="handleQuery"/>
      </el-form-item>
      <el-form-item label="Spent amount" prop="spentAmount">
        <el-input v-model="queryParams.spentAmount" placeholder="Please enter budget amount" clearable @keyup.enter.native="handleQuery"/>
      </el-form-item>
      <el-form-item label="Budget date" prop="budgetDate">
        <el-date-picker v-model="queryParams.budgetDate" style="width: 240px" value-format="yyyy-MM-dd HH:mm:ss" type="daterange"
                        range-separator="-" start-placeholder="Start date" end-placeholder="End date" :default-time="['00:00:00', '23:59:59']" />
      </el-form-item>
      <el-form-item>
        <el-button type="primary" icon="el-icon-search" @click="handleQuery">Search</el-button>
        <el-button icon="el-icon-refresh" @click="resetQuery">Reset</el-button>
      </el-form-item>
    </el-form>

    <!-- Operation toolbar -->
    <el-row :gutter="10" class="mb8">
      <el-col :span="1.5">
        <el-button type="primary" plain icon="el-icon-plus" size="mini" @click="handleAdd"
                   v-hasPermi="['budget:user-budget:create']">Create</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button type="warning" plain icon="el-icon-download" size="mini" @click="handleExport" :loading="exportLoading"
                   v-hasPermi="['budget:user-budget:export']">Export</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <!-- List -->
    <el-table v-loading="loading" :data="list">
      <el-table-column label="Budget category" align="center" prop="category" />
      <el-table-column label="Budget amount" align="center" prop="amount" />
      <el-table-column label="Spent amount" align="center" prop="spentAmount" />
      <el-table-column label="Budget date" align="center" prop="budgetDate" :formatter="formatDate"/>
      <el-table-column label="Operation" align="center" class-name="small-padding fixed-width">
        <template v-slot="scope">
          <el-button size="mini" type="text" icon="el-icon-edit" @click="handleUpdate(scope.row)"
                     v-hasPermi="['budget:user-budget:update']">Expense</el-button>
          <el-button size="mini" type="text" icon="el-icon-edit" @click="handleUpdate(scope.row)"
                     v-hasPermi="['budget:user-budget:update']">Modify</el-button>
          <el-button size="mini" type="text" icon="el-icon-delete" @click="handleDelete(scope.row)"
                     v-hasPermi="['budget:user-budget:delete']">Delete</el-button>
        </template>
      </el-table-column>
    </el-table>

    <!-- Pagination component -->
    <pagination v-show="total > 0" :total="total" :page.sync="queryParams.pageNo" :limit.sync="queryParams.pageSize"
                @pagination="getList"/>

    <!-- Dialog box (add/modify) -->
    <el-dialog :title="title" :visible.sync="open" width="500px" v-dialogDrag append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">

        <el-form-item label="Budget category" prop="category">
          <el-input v-model="form.category" placeholder="Please enter budget category" />
        </el-form-item>
        <el-form-item label="Budget amount" prop="amount">
          <el-input v-model="form.amount" placeholder="Please enter budget amount" />
        </el-form-item>
<!--        <el-form-item label="Spent amount" prop="spentAmount">-->
<!--          <el-input v-model="form.spentAmount" placeholder="Please enter spent amount" />-->
<!--        </el-form-item>-->
        <el-form-item label="Budget date" prop="budgetDate">
          <el-date-picker clearable v-model="form.budgetDate" type="date" value-format="yyyy-MM-dd" placeholder="Select budget date" />
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitForm">Confirm</el-button>
        <el-button @click="cancel">Cancel</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import { createUserBudget, updateUserBudget, deleteUserBudget, getUserBudget, getUserBudgetPage, exportUserBudgetExcel } from "@/api/budget/userBudget";

export default {
  name: "UserBudget",
  components: {
  },
  data() {
    return {
      // 遮罩层
      loading: true,
      // 导出遮罩层
      exportLoading: false,
      // 显示搜索条件
      showSearch: true,
      // 总条数
      total: 0,
      // 预算列表
      list: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNo: 1,
        pageSize: 10,
        category: null,
        amount: null,
        spentAmount: null,
        budgetDate: [],
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
      }
    };
  },
  created() {
    this.getList();
  },
  methods: {
    formatDate(row, column) {
      // 获取单元格数据
      let data = row[column.property]
      if(data == null) {
        return null
      }
      let dt = new Date(data)
      return dt.getFullYear() + '-' + (dt.getMonth() + 1)
    },
    /** 查询列表 */
    getList() {
      this.loading = true;
      // 执行查询
      getUserBudgetPage(this.queryParams).then(response => {
        this.list = response.data.list;
        this.total = response.data.total;
        this.loading = false;
      });
    },
    /** 取消按钮 */
    cancel() {
      this.open = false;
      this.reset();
    },
    /** 表单重置 */
    reset() {
      this.form = {
        id: undefined,
        category: undefined,
        amount: undefined,
        spentAmount: undefined,
        budgetDate: undefined,
      };
      this.resetForm("form");
    },
    /** 搜索按钮操作 */
    handleQuery() {
      this.queryParams.pageNo = 1;
      this.getList();
    },
    /** 重置按钮操作 */
    resetQuery() {
      this.resetForm("queryForm");
      this.handleQuery();
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加预算";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const id = row.id;
      getUserBudget(id).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改预算";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (!valid) {
          return;
        }
        // 修改的提交
        if (this.form.id != null) {
          updateUserBudget(this.form).then(response => {
            this.$modal.msgSuccess("修改成功");
            this.open = false;
            this.getList();
          });
          return;
        }
        // 添加的提交
        console.log(this.form)
        createUserBudget(this.form).then(response => {
          this.$modal.msgSuccess("新增成功");
          this.open = false;
          this.getList();
        });
      });
    },
    /** 删除按钮操作 */
    handleDelete(row) {
      const id = row.id;
      this.$modal.confirm('是否确认删除预算编号为"' + id + '"的数据项?').then(function() {
          return deleteUserBudget(id);
        }).then(() => {
          this.getList();
          this.$modal.msgSuccess("删除成功");
        }).catch(() => {});
    },
    /** 导出按钮操作 */
    handleExport() {
      // 处理查询参数
      let params = {...this.queryParams};
      params.pageNo = undefined;
      params.pageSize = undefined;
      this.$modal.confirm('是否确认导出所有预算数据项?').then(() => {
          this.exportLoading = true;
          return exportUserBudgetExcel(params);
        }).then(response => {
          this.$download.excel(response, '预算.xls');
          this.exportLoading = false;
        }).catch(() => {});
    }
  }
};
</script>
