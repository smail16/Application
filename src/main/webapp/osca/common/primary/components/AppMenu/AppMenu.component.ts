import { ref, onMounted, watch } from 'vue';
import { useRouter, useRoute } from 'vue-router';

import { useI18n } from 'vue-i18n';

export default {
    setup() {
        const { t } = useI18n();
        const router = useRouter();
        const route = useRoute();
        const active = ref(0);
        const items = ref([
            {
                label: 'AppMenu.Accueil',
                icon: 'pi pi-fw pi-home',
                route: '/',
            },
            {
                label: 'AppMenu.MesProjets',
                icon: 'pi pi-fw pi-folder',
                route: '/ProjectList',
            },
        ]);

        // Méthodes onMounted et watch fournies par Primevue dans le composant TabMenu

        // Méthode qui garantit que le bon élément de menu est sélectionné au rendu
        onMounted(() => {
            active.value = items.value.findIndex(item => {
                if (item.route && route.path) {
                    return route.path === router.resolve(item.route).path;
                } else {
                    return false;
                }
            });
        });

        // Méthode qui gère les changements de route
        watch(
            route,
            () => {
                active.value = items.value.findIndex(item => route.path === router.resolve(item.route).path);
            },
            { immediate: true },
        );

        return { t, active, items };
    },
};
