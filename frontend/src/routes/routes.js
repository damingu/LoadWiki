import DashboardLayout from '@/views/Layout/DashboardLayout.vue';
import AuthLayout from '@/views/Pages/AuthLayout.vue';
import NothingMain from '@/views/NothingMain.vue';

import NotFound from '@/views/NotFoundPage.vue';

const routes = [
  {
    path: '/main',
    name: 'main',
    component: NothingMain,
  },
  {
    path: '/',
    redirect: 'main',
    component: DashboardLayout,
    children: [
      {
        path: '/dashboard',
        redirect: '/godiagram',
        name: 'dashboard',
        // route level code-splitting
        // this generates a separate chunk (about.[hash].js) for this route
        // which is lazy-loaded when the route is visited.
        component: () => import(/* webpackChunkName: "demo" */ '../views/Dashboard.vue')
      },
      {
        path: '/icons',
        name: 'icons',
        component: () => import(/* webpackChunkName: "demo" */ '../views/Icons.vue')
      },
      {
        path: '/profile',
        name: 'profile',
        component: () => import(/* webpackChunkName: "demo" */ '../views/Pages/UserProfile.vue')
      },
      {
        path: '/profile-update',
        name: 'profile-update',
        component: () => import(/* webpackChunkName: "demo" */ '../views/Pages/ProfileUpdate.vue')
      },
              
      {
        path: '/maps',
        name: 'maps',
        component: () => import(/* webpackChunkName: "demo" */ '../views/GoogleMaps.vue')
      },
      {
        path: '/detail-contents',
        name: 'Detail Content',
        component: () => import(/* webpackChunkName: "demo" */ '../components/Board/DetailContent.vue')
      },
      {
        path: '/godiagram',
        name: 'godiagram',
        component: () => import(/* webpackChunkName: "demo" */ '../views/useGoDiagramWorkflow.vue')
      },
      {
        path: '/roadmap',
        name: 'roadmap',
        component: () => import('../views/Roadmap/RoadMap.vue'),
        props: true
      },
      {
        path: '/tmp_board',
        name: 'tmp_board',
        component: () => import('../views/Board/Board.vue')
      },
      {
        path: '/officialRoadmap',
        name: 'officialRoadmap',
        component: () => import('../views/officialRoadmap.vue'),
        props: true
      },
    ]
  },
  {
    path: '/',
    // redirect: 'login',
    component: AuthLayout,
    children: [
      {
        path: '/login',
        name: 'login',
        component: () => import(/* webpackChunkName: "demo" */ '../views/Pages/Login.vue')
      },
      {
        path: '/register',
        name: 'register',
        component: () => import(/* webpackChunkName: "demo" */ '../views/Pages/Register.vue')
      },
      { path: '*', component: NotFound },
    ]
  },
];

export default routes;