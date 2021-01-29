import Vue from 'vue';
import VueRouter from 'vue-router';
import routes from './routes';
import store from '@/store';

Vue.use(VueRouter);

// configure router
const router = new VueRouter({
  routes, // short for routes: routes
  linkActiveClass: 'active',
  
  scrollBehavior: (to, from ,savedPosition) => {
    if (savedPosition) {
      return savedPosition;
    }
    if (to.hash) {
      return { selector: to.hash };
    }
    return { x: 0, y: 0 };
  }
});

export default router;

router.beforeEach((to, from, next) => {
  console.log(to.name)
  console.log(store.getters.getAccessToken)
  if (to.name === 'main' && store.getters.getAccessToken !== null) {
    next({name:'dashboard'})
  } else {
    if ((to.name !== 'main' && to.name !== 'register') && (store.getters.getAccessToken === null)) {
      next({name:'main'})
      alert('로그인이 필요한 서비스입니다.')}
    else next()
    }
  })