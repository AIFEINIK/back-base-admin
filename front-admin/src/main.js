import Vue from 'vue'
import App from './App.vue'
import router from './router/router'
import plugins from './plugins' // plugins
import store from './store'
import Element from 'element-ui';
import 'element-ui/lib/theme-chalk/index.css'

Vue.config.productionTip = false

Vue.use(Element)
Vue.use(plugins)

const vm = new Vue({
  router,
  render: h => h(App),
  store,
  created() {
    console.log('app created')
  }
})

vm.$mount('#app')
