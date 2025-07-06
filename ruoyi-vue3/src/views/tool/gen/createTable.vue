<template>
  <!-- 创建表 -->
  <el-dialog title="创建表" v-model="visible" width="800px" top="5vh" append-to-body>
    <el-form ref="formRef" :model="form" :rules="rules" label-width="100px">
      <el-form-item label="数据源" prop="dbId">
        <el-select
          v-model="form.dbId"
          placeholder="请选择数据源"
          clearable
          filterable
          style="width: 100%"
        >
          <el-option
            v-for="item in dataSourceList"
            :key="item.dbId"
            :label="item.dbName"
            :value="item.dbId"
          />
        </el-select>
      </el-form-item>
      <el-form-item label="建表语句" prop="sqlContent">
        <span class="form-tips">支持多个建表语句</span>
        <el-input 
          type="textarea" 
          :rows="10" 
          placeholder="请输入建表语句" 
          v-model="form.sqlContent"
        ></el-input>
      </el-form-item>
    </el-form>
    <template #footer>
      <div class="dialog-footer">
        <el-button type="primary" @click="handleCreateTable">确 定</el-button>
        <el-button @click="visible = false">取 消</el-button>
      </div>
    </template>
  </el-dialog>
</template>

<script setup>
import { createTable } from "@/api/tool/gen";
import { listDb } from "@/api/tool/db";

const dataSourceList = ref([]);
const visible = ref(false);
const formRef = ref(null);
const { proxy } = getCurrentInstance();
const emit = defineEmits(["ok"]);

// 表单对象
const form = ref({
  dbId: "",
  sqlContent: ""
});

// 表单校验规则
const rules = {
  dbId: [
    { required: true, message: "请选择数据源", trigger: "blur" }
  ],
  sqlContent: [
    { required: true, message: "请输入建表语句", trigger: "blur" }
  ]
};

/** 显示弹框 */
function show() {
  visible.value = true;
  // 重置表单
  if (formRef.value) {
    formRef.value.resetFields();
  }
}

/** 导入按钮操作 */
function handleCreateTable() {
  formRef.value.validate(valid => {
    if (valid) {
      createTable(form.value).then(res => {
        proxy.$modal.msgSuccess(res.msg);
        if (res.code === 200) {
          visible.value = false;
          emit("ok");
        }
      });
    }
  });
}

onMounted(() => {
  getDataSourceList();
});

/** 获取数据源列表 */
function getDataSourceList() {
  listDb().then(res => {
    dataSourceList.value = res.rows || res.data || [];
  });
}

defineExpose({
  show,
});
</script>

<style scoped>
.form-tips {
  font-size: 12px;
  color: #909399;
  margin-left: 5px;
}
</style>
