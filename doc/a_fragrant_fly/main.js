import Vue from 'vue'
import App from './App'
import config from './config/index.js'
import store from './store'

Vue.config.productionTip = false
Vue.prototype.$store = store
Vue.prototype.$config = config
App.mpType = 'app'
// import pageHead from './components/template/page-head.vue'
// import pageFoot from './components/template/page-foot.vue'

// 实时通讯连接状态
import statusWebsocket from "@/components/template/status/websocket.vue"

// 底部自定义导航组件
import NavTabBar from "@/components/template/nav/tabBar.vue"
// 首页底部导航
import mainTabBar from "@/components/public/mainTabBar.vue";

// 弹出输入选择组件
import popupInput from "@/components/template/popup/input.vue"
// 顶部自定义导航组件
import tabNav from "@/components/template/nav/tabNav.vue"
// 城市选择组件
import cityPicker from "@/components/picker/cityPicker.vue"

// 顶部自定义导航 按钮覆盖
import ycFab from "@/components/template/yc/fab.vue"
Vue.component('yc-fab',ycFab)
Vue.component('status-websocket',statusWebsocket);
Vue.component('tab-nav', tabNav)
Vue.component('nav-tab-bar', NavTabBar)
Vue.component('main-tab-bar', mainTabBar)
Vue.component('popup-input', popupInput)
Vue.component('city-picker',cityPicker)
// Vue.component('page-head', pageHead)
// Vue.component('page-foot', pageFoot)
const app = new Vue({
    store,
    ...App
})
app.$mount()
