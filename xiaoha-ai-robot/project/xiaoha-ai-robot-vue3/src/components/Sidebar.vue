<template>
  <!-- 左边栏 -->
  <div
    :class="sidebarOpen ? 'translate-x-0' : '-translate-x-full'"
    class="w-64 bg-[#f9fbff] border-r border-gray-200 fixed left-0 top-0 h-full transition-transform duration-300 ease-in-out z-10 overflow-y-auto"
  >
    左侧栏
  </div>

  <!-- 侧边栏切换按钮 -->
  <button
    :class="sidebarOpen ? 'left-64' : 'left-0'"
    @click="toggleSidebar"
    class="fixed top-4 z-20 bg-white border border-gray-200 rounded-r-lg p-2"
  >
    <!-- 图标 -->
    <SvgIcon
      :name="sidebarOpen ? 'sidebar-open' : 'sidebar-close'"
      :customCss="
        sidebarOpen ? 'w-6 h-6 text-gray-400' : 'w-7 h-7 text-gray-400'
      "
    />
  </button>
</template>

<script setup>
import SvgIcon from "@/components/SvgIcon.vue";

// 定义 props, 对外部暴露配置项
const props = defineProps({
  sidebarOpen: { type: Boolean, required: true }, // 左边栏是否展开
});
// 定义 emits
const emit = defineEmits(["toggle-sidebar"]);

// 切换侧边栏显示/隐藏
const toggleSidebar = () => {
  emit("toggle-sidebar");
};
</script>

<style scoped>
.overflow-y-auto {
  scrollbar-color: rgba(0, 0, 0, 0.2) transparent; /* 自定义滚动条颜色 */
}
</style>
