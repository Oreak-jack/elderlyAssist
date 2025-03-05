<!--<template>-->
<!--  &lt;!&ndash;  头部    &ndash;&gt;-->
<!--  <header>-->
<!--    <HeaderNav v-if="false"></HeaderNav>-->
<!--  </header>-->
<!--  &lt;!&ndash;  侧边栏（看情况加吧）   &ndash;&gt;-->
<!--  <div >-->

<!--  </div>-->
<!--  &lt;!&ndash;  中心展示区域    &ndash;&gt;-->
<!--  <main>-->
<!--    <router-view></router-view>-->
<!--  </main>-->

<!--</template>-->

<template>
  <div>
    <HeaderNav @setLoginOrRegisterOption = "setLoginOrRegisterOption" />
    <main v-if="!loginOrRegisterOption" class="content">
      <HeroSection />
      <OverviewSection />
      <FeaturesSection />
      <UsersSection />
      <VisionSection />
      <AdvantageSection />
    </main>
    <main v-else>
      <router-view></router-view>
    </main>
    <FooterSection />
  </div>
</template>

<script setup>
import HeaderNav from "@/views/index/HeaderNav.vue";
import HeroSection from "@/views/index/HeroSection.vue";
import OverviewSection from "@/views/index/OverviewSection.vue";
import FeaturesSection from "@/views/index/FeaturesSection.vue";
import UsersSection from "@/views/index/UsersSection.vue";
import VisionSection from "@/views/index/VisionSection.vue";
import AdvantageSection from "@/views/index/AdvantageSection.vue";
import FooterSection from "@/views/index/FooterSection.vue";
import {ref} from "vue";

const loginOrRegisterOption = ref()
function setLoginOrRegisterOption(option){
  loginOrRegisterOption.value = option
  console.log('loginOrRegisterOption 的值:',loginOrRegisterOption.value)
}
</script>

<style>
:root {
  --primary-color: #6db33f;
  --text-color: #333;
  --bg-light: #f1f1f1;
}

* {
  margin: 0;
  padding: 0;
  box-sizing: border-box;
}

body {
  font-family: -apple-system, BlinkMacSystemFont, "Segoe UI", Roboto, "Helvetica Neue", Arial, sans-serif;
  line-height: 1.6;
  color: var(--text-color);
}

.content {
  max-width: 1200px;
  margin: 0 auto;
  padding: 4rem 1rem;
}

.section {
  margin-bottom: 4rem;
}

.section h2 {
  color: var(--primary-color);
  margin-bottom: 1.5rem;
  font-size: 2rem;
}

@media (max-width: 768px) {
  :root {
    --section-padding: 3rem 1.5rem;
  }

  h2 {
    font-size: 2rem;
  }
}
</style>
