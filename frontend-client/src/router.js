import Vue from 'vue';
import VueRouter from 'vue-router';
import HelloWorld from "./components/HelloWorld";
import Test from "./components/Test";

Vue.use(VueRouter)

const routes = [
    {
        path: '/',
        name: "HelloWorld",
        component: HelloWorld
    },
    {
        path: '/test',
        name: "Test",
        component: Test
    }
];

const router = new VueRouter({ 
    routes,
});

export default router;