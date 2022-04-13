import { asyncRouterMap, constantRouterMap } from '@/router/index';
import { getRouters } from '@/api/menu'
import Layout from '@/layout'

const permission = {
  state: {
    routers: [],
    addRouters: []
  },
  mutations: {
    SET_ROUTERS: (state, routers) => {
      state.addRouters = routers;
      state.routers = constantRouterMap.concat(routers);
    }
  },
  actions: {
    GenerateRoutes({ commit }, data) {
      return new Promise(resolve => {
        // 向后端请求路由数据
        getRouters().then(res => {
          const accessedRoutes = filterAsyncRouter(res.routerList)
          accessedRoutes.push({ path: '*', redirect: '/404', hidden: true })
          commit('SET_ROUTERS', accessedRoutes)
          resolve(accessedRoutes)
        })
      })
    }
  }
}

// 遍历后台传来的路由字符串，转换为组件对象
function filterAsyncRouter(asyncRouterMap) {
  return asyncRouterMap.filter(route => {
    if (route.component) {
      // Layout组件特殊处理
      if (route.component === 'Layout') {
        route.component = Layout
      } else {
        route.component = loadView(route.component)
      }
    }
    if (route.children != null && route.children && route.children.length) {
      route.children = filterAsyncRouter(route.children)
    }
    return true
  })
}

export const loadView = (view) => { // 路由懒加载
  return (resolve) =>  require([`@/views/${view}`], resolve)
}

export default permission;
