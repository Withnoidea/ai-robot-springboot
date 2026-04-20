<template>
  <div class="h-screen flex overflow-hidden overflow-x-hidden">
    <!-- 左边栏 -->
    <Sidebar :sidebarOpen="sidebarOpen" @toggle-sidebar="toggleSidebar" />

    <!-- 主内容区域 -->
    <div :class="sidebarOpen ? 'ml-64' : 'ml-0'" class="bg-green-200 flex-1">
      主内容区域
    </div>
  </div>
</template>

<script setup>
import { ref } from "vue";
import Sidebar from "@/components/Sidebar.vue";

// 左边栏状态，true 表示默认展开
const sidebarOpen = ref(true);

// 切换侧边栏显示/隐藏
const toggleSidebar = () => {
  sidebarOpen.value = !sidebarOpen.value;
};
</script>
