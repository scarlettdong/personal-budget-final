<template>
  <div>

  </div>
</template>

<script>
export default {
  name: 'YudaoGit',
  data() {
    return {
      url: 'https://github.com/YunaiV/ruoyi-vue-pro'
    }
  },
  methods: {
    goto() {
      window.open(this.url)
    }
  }
}
</script>
