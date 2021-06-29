
const routes = [
  {
    path: '/',
    component: () => import('layouts/MainLayout.vue'),
    children: [
      { path: '', component: () => import('pages/Index.vue') }
    ]
  },
  {
    path: '/auth',
    component: () => import('layouts/AuthLayout.vue'),
    children: [
      { path: '', component: () => import('pages/Auth.vue') }
    ]
  },
  {
    path: '/buyer',
    component: () => import('layouts/BuyerLayout.vue'),
    children: [
      { path: 'search', component: () => import('pages/Search.vue') },
      { path: 'search/property/:id', component: () => import('pages/Property.vue') }
    ]
  },
  {
    path: '/seller',
    component: () => import('layouts/SellerLayout.vue'),
    children: [
      { path: 'home', component: () => import('pages/SellerHomePage.vue') },
      { path: 'seller/property/:id', component: () => import('pages/SellerProperty.vue') }
    ]
  },
  {
    path: '/admin',
    component: () => import('layouts/AdminLayout.vue'),
    children: [
      { path: 'home', component: () => import('pages/AdminHomePage.vue') }
    ]
  },

  // Always leave this as last one,
  // but you can also remove it
  {
    path: '*',
    component: () => import('pages/Error404.vue')
  }
]

export default routes
