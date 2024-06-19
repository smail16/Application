import { computed, defineComponent, onBeforeMount, ref, watchEffect } from 'vue';
import DashBoardVue from '../../views/ProjectPage/DashBoard/DashBoardVue.vue';
import ProjectPageVue from '../../views/ProjectPage/ProjectPageVue.vue';
import ProgressBar from '@/common/primary/components/ProgressBar/ProgressBarVue.vue';
import DialogueCreationOnglet from '../DialogueOnglet/DialogueCreationOngletVue.vue';
import { useI18n } from 'vue-i18n';
import { useProjectStore } from '@/common/secondary/pinia/store';
import { watch } from 'vue';
import CustomToast from '../CustomToast/CustomToastVue.vue';
import ReferentielService from '@/common/domain/ReferentielService';
import { useReferentielStore } from '@/common/secondary/pinia/ReferentielStore';
import PageService from '@/common/domain/PageService';
import ProjectService from '@/common/domain/ProjectServices';
import { Page } from '@/common/domain/Page';
import DialogueDeletePageVue from '../DialogueDeletePage/DialogueDeletePageVue.vue';
import { useRoute } from 'vue-router';
import { Project } from '@/common/domain/Project';

export default defineComponent({
    components: {
        DashBoardVue,
        ProjectPageVue,
        ProgressBar,
        DialogueCreationOnglet,
        DialogueDeletePageVue,
        CustomToast,
    },
    setup() {
        // Charge le référentiel dans le local storage
        onBeforeMount(async () => {
            await loadReferentiel('4.2');
        });
        const customToast = ref();
        const active = ref<{ key: string; label: string; component: string } | null>(null);
        const visible = ref(false);
        const show = ref(false);
        const { t } = useI18n();
        const store = useProjectStore();
        const referentielStore = useReferentielStore();
        const referentielService = new ReferentielService();
        const pageService = new PageService();
        const projectService = new ProjectService();
        const activeProject = computed(() => {
            return store.activeProjectDetails;
        });

        const activeIndex = ref(0);
        const route = useRoute();
        const isLoading = ref(true);
        async function loadReferentiel(versionRef: string) {
            const response = await referentielService.findReferentielByVersion(versionRef);
            referentielStore.setReferentiel(response.data);
        }

        const activeProjectTitle = computed(() => {
            if (isLoading.value) {
                return '';
            } else {
                return activeProject.value ? activeProject.value.titre : t('ProjectPage.projetactif');
            }
        });

        const ongletPermanent = [
            { key: 'dashboard', label: 'Dashboard', component: 'DashBoardVue' },
            { key: 'criteresGlobaux', label: 'Crit. globaux', component: 'DashBoardVue' },
        ];

        const items = ref([...ongletPermanent, ...store.onglets]);

        let pageCount = 1;
        //initialise les onglets avec ceux existants dans le store
        store.initializeTabs();

        //affiche le bon onglet on click avec le bon composant
        const onTabClick = key => {
            active.value = items.value.find(item => item.key === key) || null;
            if (active.value !== null && active.value.label !== undefined) {
                store.setOngletActif(active.value?.label);
            }
        };

        // Ouvrire le dialogue sans créer de nouvel onglet
        const ajouterNouvelOnglet = () => {
            visible.value = true;
        };

        //observateur réactif pour écouter les changements sur `store.activeProjectDetails`.
        watch(
            () => store.activeProjectDetails,
            newProject => {
                if (newProject) {
                    items.value = [
                        ...ongletPermanent,
                        ...newProject.pages.map((page, index) => ({
                            key: `page${index + 1}`,
                            label: page.titrePage,
                            component: 'ProjectPageVue',
                        })),
                    ];
                }
            },
            { immediate: true },
        );
        watchEffect(async () => {
            isLoading.value = true;
            if (route && route.params) {
                // Vérifie si route et route.params sont définis
                const identifiant = route.params.identifiant;
                // Utilisez `identifiant` ici
                const response = await projectService.findProjectByIdentifiant(identifiant as string);

                // Transforme la réponse du service en un objet de type Projet, compatible avec ce qu'on a dans le store
                const projectDetails: Project = response.data;

                // Met à jour le store
                store.ProjectDetails(projectDetails);

                store.setProgress(projectDetails.avancementGlobal);
            }
            isLoading.value = false;
        });
        // Créer et ajouter l'onglet après la confirmation dans le dialogue
        const confirmerAjoutOnglet = nouvelOngletTitle => {
            const nouvelOnglet = {
                key: `page${pageCount}`,
                label: nouvelOngletTitle || `Page ${pageCount}`,
                component: 'ProjectPageVue',
            };

            // push le nouvel onglet crée dans la le store
            store.addOnglet(nouvelOnglet);
            // push le nouvel onglet crée dans la liste d'onglet existantes
            items.value.push(nouvelOnglet);
            //set la valeur active sur le nouvel onglet pour l'afficher à la création

            active.value = nouvelOnglet;
            pageCount++;
            visible.value = false;
        };
        const handleTitleChange = async newTitle => {
            // Vérifie si un projet actif est sélectionné
            if (!store.activeProjectDetails) {
                alert('Aucun projet actif sélectionné');
                return;
            }

            try {
                // Récupère les pages existantes du projet actif
                const existingPages = store.activeProjectDetails.pages || [];

                // Vérifie l'unicité du titre de la nouvelle page
                const isUnique = !existingPages.some(page => page.titrePage === newTitle);

                // Si le titre n'est pas unique, affiche une erreur et quitte la fonction
                if (!isUnique) {
                    customToast.value.showError('Le titre de la page est déjà existant');
                    return;
                }
                if (items.value.length >= 32) {
                    customToast.value.showError('Le nombre maximum de page est atteint. Nombre de pages maximum: 30');
                    return;
                }
                // Appelle le service pour créer une nouvelle page avec le nouveau titre
                const responsePage = await pageService.createPage(store.activeProjectDetails.identifiant, newTitle, '4.2');

                // Extrait les données de la nouvelle page depuis la réponse Axios
                const newPageProject = responsePage.data as Page;

                // Convertit la nouvelle page au format Page
                const newPageTest: Page = {
                    titrePage: newPageProject.titrePage,
                    rubriquesPages: newPageProject.rubriquesPages || [],
                };

                // Ajoute la nouvelle page aux pages existantes du projet
                const updatedPages = [...existingPages, newPageTest];

                // Met à jour l'état local du projet avec les nouvelles pages
                store.setActiveProjectDetails({ ...store.activeProjectDetails, pages: updatedPages });
                if (activeProject.value?.identifiant) {
                    projectService.updateAvancement(activeProject.value?.identifiant, store.progress);
                }

                // Met à jour la liste des onglets avec les nouvelles pages
                items.value = [
                    ...ongletPermanent,
                    ...updatedPages.map((page, index) => ({
                        key: `page${index + 1}`,
                        label: page.titrePage,
                        component: 'ProjectPageVue',
                    })),
                ];
            } catch (error) {
                // En cas d'erreur lors de l'ajout de la nouvelle page, affiche une erreur dans la console
                console.error("Erreur lors de l'ajout d'une nouvelle page : ", error);
            }
        };

        /**
         * Gère la suppression d'une page
         * - Supprime l'onglet correspondant de la liste des items (ce qui va supprimer l'onglet du menu)
         * - Supprime la page du projet côté bdd
         * - Définit la page précédente comme active.
         * - Met à jour les détails du projet actif dans le store: les changements sont observés dans un watcher dans WrapperCriteres qui va recalculer l'avancement
         * - Si le nombre d'items est réduit à 2 (les deux onglets permanent: dashboard et crit. Globaux), réinitialise le progrès du projet à 0
         *
         * @async
         * @function handleDelete
         * @param {Boolean} deleteConfirm - Indique si la suppression est confirmée, récupéré depuis le composant enfant: DialogueDeletePage
         */
        const handleDelete = async deleteConfirm => {
            if (deleteConfirm) {
                const identifiant = activeProject.value?.identifiant;
                const ongletActifLabel = store.ongletActifLabel;
                const index = items.value.findIndex(item => item === active.value);

                if (index >= 2) {
                    // Récupérer l'index de la page précédente (avant celle supprimée)
                    const newIndex = index > 0 ? index - 1 : 0;

                    // Suppression de l'onglet de la liste des items
                    items.value.splice(index, 1);

                    //définir la nouvelle page active
                    activeIndex.value = newIndex;

                    if (identifiant && ongletActifLabel) {
                        // Suppression de l'onglet côté serveur
                        try {
                            await pageService.deletePage(identifiant, ongletActifLabel);

                            // Définir la page précédente comme active
                            active.value = items.value[newIndex];
                            store.setOngletActif(active.value?.label);
                            store.initializeTabs();

                            // Récupération des détails du projet mis à jour
                            const updatedProject = await projectService.findProjectByIdentifiant(identifiant);
                            store.setActiveProjectDetails(updatedProject.data);

                            // Mettre à jour activeIndex avec le nouvel index
                            activeIndex.value = newIndex;

                            // Si le nombre d'onglets restants est égal à 2, réinitialise le progrès du projet
                            if (items.value.length === 2) {
                                store.setProgress(0);
                                projectService.updateAvancement(identifiant, 0);
                                activeIndex.value = 1;
                            }
                        } catch (error) {
                            console.error('Erreur lors de la suppression de la page :', error);
                        }
                    }
                }
            }
        };
        // fonction watchEffect: observe les changements de la variable activeIndex et met à jour l'élément actif
        watchEffect(() => {
            const index = activeIndex.value;
            if (index >= 0 && index < items.value.length) {
                active.value = items.value[index];
            }
        });

        return {
            active,
            ajouterNouvelOnglet,
            items,
            onTabClick,
            confirmerAjoutOnglet,
            t,
            activeProjectTitle,
            visible,
            handleTitleChange,
            customToast,
            show,
            handleDelete,
            activeIndex,
        };
    },

    created() {
        // Initialiser active avec l'onglet "Dashboard" lors de la création du composant
        this.active = this.items.find(item => item.key === 'dashboard') || null;
    },
});
