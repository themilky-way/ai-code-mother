<template>
  <div class="global-header">
    <div class="header-left">
      <img src="@/assets/logo.png" alt="logo" class="logo" />
      <span class="site-title">AI Code Mother</span>
    </div>
    <a-menu mode="horizontal" :selected-keys="[selectedKey]" class="header-menu">
      <a-menu-item v-for="item in menuItems" :key="item.key">
        <router-link :to="item.path">{{ item.label }}</router-link>
      </a-menu-item>
    </a-menu>
    <div class="header-right">
      <a-button type="primary">登录</a-button>
    </div>
  </div>
</template>

<script setup lang="ts">
import { ref, watch, onMounted } from 'vue'
import { useRoute } from 'vue-router'

const props = defineProps({
  menuItems: {
    type: Array,
    required: true,
  },
})

const route = useRoute()
const selectedKey = ref('/')

const updateSelectedKey = () => {
  const found = props.menuItems.find(item => item.path === route.path)
  selectedKey.value = found ? found.key : ''
}

watch(() => route.path, updateSelectedKey)
onMounted(updateSelectedKey)
</script>

<style scoped>
.global-header {
  display: flex;
  align-items: center;
  justify-content: space-between;
  height: 64px;
  padding: 0 24px;
  background: #fff;
  border-bottom: 1px solid #f0f0f0;
}
.header-left {
  display: flex;
  align-items: center;
}
.logo {
  width: 40px;
  height: 40px;
  margin-right: 12px;
}
.site-title {
  font-size: 20px;
  font-weight: bold;
  color: #1677ff;
}
.header-menu {
  flex: 1;
  margin: 0 32px;
  min-width: 200px;
}
.header-right {
  display: flex;
  align-items: center;
}
</style>
