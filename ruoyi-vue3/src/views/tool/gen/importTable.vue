<template>
  <!-- 导入表 -->
  <el-dialog title="导入表" v-model="visible" width="800px" top="5vh" append-to-body>
    <el-form :model="queryParams" ref="queryRef" :inline="true">
      <el-form-item label="数据源" prop="dbId">
        <el-select
          v-model="queryParams.dbId"
          placeholder="请选择数据源"
          clearable
          filterable
          style="width: 180px"
          @change="handleDataSourceChange"
        >
          <el-option
            v-for="item in dataSourceList"
            :key="item.dbId"
            :label="item.dbName"
            :value="item.dbId"
          />
        </el-select>
      </el-form-item>
      <el-form-item label="表名称" prop="tableName">
        <el-input
          v-model="queryParams.tableName"
          placeholder="请输入表名称"
          clearable
          style="width: 180px"
          @keyup.enter="handleQuery"
        />
      </el-form-item>
      <el-form-item label="表描述" prop="tableComment">
        <el-input
          v-model="queryParams.tableComment"
          placeholder="请输入表描述"
          clearable
          style="width: 180px"
          @keyup.enter="handleQuery"
        />
      </el-form-item>
      <el-form-item>
        <el-button type="primary" icon="Search" @click="handleQuery">搜索</el-button>
        <el-button icon="Refresh" @click="resetQuery">重置</el-button>
      </el-form-item>
    </el-form>
    <el-row>
      <el-table @row-click="clickRow" ref="table" :data="dbTableList" @selection-change="handleSelectionChange" height="260px">
        <el-table-column type="selection" width="55"></el-table-column>
        <el-table-column prop="tableName" label="表名称" :show-overflow-tooltip="true"></el-table-column>
        <el-table-column prop="tableComment" label="表描述" :show-overflow-tooltip="true"></el-table-column>
        <el-table-column prop="createTime" label="创建时间"></el-table-column>
        <el-table-column prop="updateTime" label="更新时间"></el-table-column>
      </el-table>
      <pagination
        v-show="total>0"
        :total="total"
        v-model:page="queryParams.pageNum"
        v-model:limit="queryParams.pageSize"
        @pagination="getList"
      />
    </el-row>
    <template #footer>
      <div class="dialog-footer">
        <el-button type="primary" @click="handleImportTable">确 定</el-button>
        <el-button @click="visible = false">取 消</el-button>
      </div>
    </template>
  </el-dialog>
</template>

<script setup>
import { listDbTable, importTable } from "@/api/tool/gen";
import { listDb } from "@/api/tool/db";

const total = ref(0);
const visible = ref(false);
const tables = ref([]);
const dbTableList = ref([]);
const dataSourceList = ref([]);
const { proxy } = getCurrentInstance();

const queryParams = reactive({
  pageNum: 1,
  pageSize: 10,
  tableName: undefined,
  tableComment: undefined,
  dbId: undefined
});

const emit = defineEmits(["ok"]);

/** 查询参数列表 */
function show() {
  getDataSourceList();
  visible.value = true;
}

/** 获取数据源列表 */
function getDataSourceList() {
  listDb().then(res => {
    dataSourceList.value = res.rows || res.data || [];
  });
}

/** 数据源变化处理 */
function handleDataSourceChange() {
  // 清空表格数据和查询条件
  dbTableList.value = [];
  total.value = 0;
  queryParams.tableName = undefined;
  queryParams.tableComment = undefined;
  queryParams.pageNum = 1;
  
  // 如果选择了数据源，则查询表数据
  if (queryParams.dbId) {
    getList();
  }
}

/** 单击选择行 */
function clickRow(row) {
  proxy.$refs.table.toggleRowSelection(row);
}

/** 多选框选中数据 */
function handleSelectionChange(selection) {
  tables.value = selection.map(item => item.tableName);
}

/** 查询表数据 */
function getList() {
  if (!queryParams.dbId) {
    proxy.$modal.msgWarning("请先选择数据源");
    return;
  }
  listDbTable(queryParams).then(res => {
    dbTableList.value = res.rows;
    total.value = res.total;
  });
}

/** 搜索按钮操作 */
function handleQuery() {
  if (!queryParams.dbId) {
    proxy.$modal.msgWarning("请先选择数据源");
    return;
  }
  queryParams.pageNum = 1;
  getList();
}

/** 重置按钮操作 */
function resetQuery() {
  proxy.resetForm("queryRef");
  dbTableList.value = [];
  total.value = 0;
}

/** 导入按钮操作 */
function handleImportTable() {
  const tableNames = tables.value.join(",");
  if (tableNames == "") {
    proxy.$modal.msgError("请选择要导入的表");
    return;
  }
  importTable({ tables: tableNames }).then(res => {
    proxy.$modal.msgSuccess(res.msg);
    if (res.code === 200) {
      visible.value = false;
      emit("ok");
    }
  });
}

defineExpose({
  show,
});
</script>
