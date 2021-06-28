import Vue from 'vue'
import Vuex from 'vuex'
import goods from "./goods.js"
import user from "./store.js"
import win from "./win.js"
Vue.use(Vuex)

const store = new Vuex.Store({
	  modules:{
		   goods:goods,
		   user:user,
		   win:win
		   
      }
})

export default store
