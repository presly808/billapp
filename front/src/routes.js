// Pages
import page1 from './page1.vue';
import page2 from './page2.vue';
import register from "./register.vue";

export default [
    {
        path: '/page1/',
        component: page1
    },
    {
        path: '/page2/',
        component: page2
    },
    {
        path: "/register/",
        component: register
    }
];