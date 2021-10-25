import Vue from 'vue'
import App from './App'

import store from './store'
import appConfig from './https/appConfig'

import community from './pages/community/index.vue'
Vue.component('community',community)

import find from './pages/find/index.vue'
Vue.component('find',find)

import home from './pages/home/index.vue'
Vue.component('home',home)

import message from './pages/message/index.vue'
Vue.component('message',message)

import my from './pages/my/index.vue'
Vue.component('my',my)


import cuCustom from './colorui/components/cu-custom.vue'
Vue.component('cu-custom',cuCustom)


Vue.config.productionTip = false

Vue.prototype.$store = store
Vue.prototype.appBaseUrl = appConfig.baseUrl


Vue.prototype.$backgroundAudioData = {
	playing: false,
	playTime: 0,
	formatedPlayTime: '00:00:00'
}
Vue.prototype.$adpid = "1111111111"

App.mpType = 'app'

const app = new Vue({
	store,
	...App
})
app.$mount()
