import HomePageVue from '@/common/primary/views/HomePage/HomePageVue.vue';
import { createRouter, createWebHistory } from 'vue-router';
import CreateProjectVue from '@/common/primary/views/CreateProjectPage/CreateProjectVue.vue';
import ProjectList from '@/common/primary/views/ProjetListPage/ProjectListVue.vue';
import { MenuProjectVue } from '@/common/primary/components/MenuProject';

const routes = [
    {
        path: '/',
        name: 'HomePage',
        component: HomePageVue,
    },
    {
        path: '/CreateProject',
        name: 'CreateProject',
        component: CreateProjectVue,
    },
    {
        path: '/ProjectList',
        name: 'ProjectList',
        component: ProjectList,
    },
    {
        path: '/Project/:identifiant',
        name: 'Project',
        component: MenuProjectVue,
    },
];

const router = createRouter({
    history: createWebHistory(),
    routes,
});

export default router;
