<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryRef" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="数据库名称" prop="dbName">
        <el-input
          v-model="queryParams.dbName"
          placeholder="请输入数据库名称"
          clearable
          @keyup.enter="handleQuery"
        />
      </el-form-item>
      <el-form-item label="数据库类型" prop="dbType">
        <el-select v-model="queryParams.dbType" placeholder="请选择数据库类型" clearable>
          <el-option
            v-for="dict in gen_db_type"
            :key="dict.value"
            :label="dict.label"
            :value="dict.value"
          />
        </el-select>
      </el-form-item>
      <el-form-item label="ip" prop="ip">
        <el-input
          v-model="queryParams.ip"
          placeholder="请输入ip"
          clearable
          @keyup.enter="handleQuery"
        />
      </el-form-item>
      <el-form-item label="端口" prop="port">
        <el-input
          v-model="queryParams.port"
          placeholder="请输入端口"
          clearable
          @keyup.enter="handleQuery"
        />
      </el-form-item>
      <el-form-item>
        <el-button type="primary" icon="Search" @click="handleQuery">搜索</el-button>
        <el-button icon="Refresh" @click="resetQuery">重置</el-button>
      </el-form-item>
    </el-form>

    <el-row :gutter="10" class="mb8">
      <el-col :span="1.5">
        <el-button
          type="primary"
          plain
          icon="Plus"
          @click="handleAdd"
          v-hasPermi="['tool:db:add']"
        >新增</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="success"
          plain
          icon="Edit"
          :disabled="single"
          @click="handleUpdate"
          v-hasPermi="['tool:db:edit']"
        >修改</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="danger"
          plain
          icon="Delete"
          :disabled="multiple"
          @click="handleDelete"
          v-hasPermi="['tool:db:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="Download"
          @click="handleExport"
          v-hasPermi="['tool:db:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar v-model:showSearch="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="dbList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="编号" align="center" prop="dbId" />
      <el-table-column label="数据库名称" align="center" prop="dbName" />
      <el-table-column label="数据库类型" align="center" prop="dbType">
        <template #default="scope">
          <dict-tag :options="gen_db_type" :value="scope.row.dbType"/>
        </template>
      </el-table-column>
      <el-table-column label="ip" align="center" prop="ip" />
      <el-table-column label="端口" align="center" prop="port" />
      <el-table-column label="备注" align="center" prop="remark" />
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template #default="scope">
          <el-button link type="primary" icon="Edit" @click="handleUpdate(scope.row)" v-hasPermi="['tool:db:edit']">修改</el-button>
          <el-button link type="primary" icon="Delete" @click="handleDelete(scope.row)" v-hasPermi="['tool:db:remove']">删除</el-button>
        </template>
      </el-table-column>
    </el-table>
    
    <pagination
      v-show="total>0"
      :total="total"
      v-model:page="queryParams.pageNum"
      v-model:limit="queryParams.pageSize"
      @pagination="getList"
    />

    <!-- 添加或修改代码生成数据库对话框 -->
    <el-dialog :title="title" v-model="open" width="500px" append-to-body>
      <el-form ref="dbRef" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="数据库名称" prop="dbName">
          <el-input v-model="form.dbName" placeholder="请输入数据库名称" />
        </el-form-item>
        <el-form-item label="数据库类型" prop="dbType">
          <el-radio-group v-model="form.dbType">
            <el-radio
              v-for="dict in gen_db_type"
              :key="dict.value"
              :label="dict.value"
            >{{dict.label}}</el-radio>
          </el-radio-group>
        </el-form-item>
        <el-form-item label="ip" prop="ip">
          <el-input v-model="form.ip" placeholder="请输入ip" />
        </el-form-item>
        <el-form-item label="端口" prop="port">
          <el-input v-model="form.port" placeholder="请输入端口" />
        </el-form-item>
        <el-form-item label="用户名" prop="username">
          <el-input v-model="form.username" placeholder="请输入用户名" />
        </el-form-item>
        <el-form-item label="密码" prop="password">
          <el-input v-model="form.password" placeholder="请输入密码" />
        </el-form-item>
        <el-form-item label="备注" prop="remark">
          <el-input v-model="form.remark" type="textarea" placeholder="请输入内容" />
        </el-form-item>
      </el-form>
      <template #footer>
        <div class="dialog-footer">
          <el-button type="primary" @click="submitForm">确 定</el-button>
          <el-button @click="cancel">取 消</el-button>
        </div>
      </template>
    </el-dialog>
  </div>
</template>

<script setup name="Db">
import { listDb, getDb, delDb, addDb, updateDb } from "@/api/tool/db";

const { proxy } = getCurrentInstance();
const { gen_db_type } = proxy.useDict('gen_db_type');

const dbList = ref([]);
const open = ref(false);
const loading = ref(true);
const showSearch = ref(true);
const ids = ref([]);
const single = ref(true);
const multiple = ref(true);
const total = ref(0);
const title = ref("");

const data = reactive({
  form: {},
  queryParams: {
    pageNum: 1,
    pageSize: 10,
    dbName: null,
    dbType: null,
    ip: null,
    port: null,
  },
  rules: {
    dbName: [
      { required: true, message: "数据库名称不能为空", trigger: "blur" }
    ],
    dbType: [
      { required: true, message: "数据库类型不能为空", trigger: "change" }
    ],
    ip: [
      { required: true, message: "ip不能为空", trigger: "blur" }
    ],
    port: [
      { required: true, message: "端口不能为空", trigger: "blur" }
    ],
    username: [
      { required: true, message: "用户名不能为空", trigger: "blur" }
    ],
    password: [
      { required: true, message: "密码不能为空", trigger: "blur" }
    ],
    genType: [
      { required: true, message: "生成代码方式不能为空", trigger: "change" }
    ],
  }
});

const { queryParams, form, rules } = toRefs(data);

/** 查询代码生成数据库列表 */
function getList() {
  loading.value = true;
  listDb(queryParams.value).then(response => {
    dbList.value = response.rows;
    total.value = response.total;
    loading.value = false;
  });
}

// 取消按钮
function cancel() {
  open.value = false;
  reset();
}

// 表单重置
function reset() {
  form.value = {
    dbId: null,
    dbName: null,
    dbType: null,
    ip: null,
    port: null,
    username: null,
    password: null,
    genType: null,
    genPath: null,
    createBy: null,
    createTime: null,
    updateBy: null,
    updateTime: null,
    remark: null
  };
  proxy.resetForm("dbRef");
}

/** 搜索按钮操作 */
function handleQuery() {
  queryParams.value.pageNum = 1;
  getList();
}

/** 重置按钮操作 */
function resetQuery() {
  proxy.resetForm("queryRef");
  handleQuery();
}

// 多选框选中数据
function handleSelectionChange(selection) {
  ids.value = selection.map(item => item.dbId);
  single.value = selection.length != 1;
  multiple.value = !selection.length;
}

/** 新增按钮操作 */
function handleAdd() {
  reset();
  open.value = true;
  title.value = "添加代码生成数据库";
}

/** 修改按钮操作 */
function handleUpdate(row) {
  reset();
  const _dbId = row.dbId || ids.value
  getDb(_dbId).then(response => {
    form.value = response.data;
    open.value = true;
    title.value = "修改代码生成数据库";
  });
}

/** 提交按钮 */
function submitForm() {
  proxy.$refs["dbRef"].validate(valid => {
    if (valid) {
      if (form.value.dbId != null) {
        updateDb(form.value).then(response => {
          proxy.$modal.msgSuccess("修改成功");
          open.value = false;
          getList();
        });
      } else {
        addDb(form.value).then(response => {
          proxy.$modal.msgSuccess("新增成功");
          open.value = false;
          getList();
        });
      }
    }
  });
}

/** 删除按钮操作 */
function handleDelete(row) {
  const _dbIds = row.dbId || ids.value;
  proxy.$modal.confirm('是否确认删除代码生成数据库编号为"' + _dbIds + '"的数据项？').then(function() {
    return delDb(_dbIds);
  }).then(() => {
    getList();
    proxy.$modal.msgSuccess("删除成功");
  }).catch(() => {});
}

/** 导出按钮操作 */
function handleExport() {
  proxy.download('system/db/export', {
    ...queryParams.value
  }, `db_${new Date().getTime()}.xlsx`)
}

getList();
</script>
