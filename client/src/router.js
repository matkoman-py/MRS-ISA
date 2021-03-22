import { createRouter, createWebHashHistory } from 'vue-router';
import HelloWorld from "./components/HelloWorld";
import Test from "./components/Test";
import RegistrationPage from "./views/RegistrationPage"

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
    },
    {
        path: '/register',
        name: "RegistrationPage",
        component: RegistrationPage
    }
];

const router = new createRouter({
    history: createWebHashHistory(), 
    routes,
});

export default router;