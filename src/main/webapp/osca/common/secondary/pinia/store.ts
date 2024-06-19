import { defineStore } from 'pinia';
import { Project } from '@/common/domain/Project';
import { OngletMenu } from '@/common/domain/OngletMenu';
import { computed } from 'vue';
import { CriteresDevPage } from '@/common/domain/Page/Rubriques/SousRubriquesPage/Dev/CriteresDevPage';
import { CriteresFoncPage } from '@/common/domain/Page/Rubriques/SousRubriquesPage/Fonc/CriteresFoncPage';
import { CriteresUXPage } from '@/common/domain/Page/Rubriques/SousRubriquesPage/UX/CriteresUXPage';

// interface d'état
interface State {
    activeProject: Project | null;
    projects: Project[];
    progress: number;
    progressUX: number;
    progressDevelop: number;
    progressFunc: number;
    onglets: OngletMenu[];
    ongletActifLabel: string;
    onCreatedPopUp: boolean;
    criteresDevListTotal: CriteresDevPage[];
    criteresFonctionelsListTotal: CriteresFoncPage[];
    criteresUXListTotal: CriteresUXPage[];
    criteresUXList: { name: string; key: string; state: any }[];
    criteresDevList: { name: string; key: string; state: any }[];
    criteresFoncList: { name: string; key: string; state: any }[];
}

export const useProjectStore = defineStore('project', {
    state: (): State => ({
        activeProject: null,
        projects: [],
        progress: 0,
        progressUX: 0,
        progressDevelop: 0,
        progressFunc: 0,
        onglets: [],
        ongletActifLabel: '',
        onCreatedPopUp: false,
        criteresDevListTotal: [],
        criteresFonctionelsListTotal: [],
        criteresUXListTotal: [],
        criteresUXList: [],
        criteresDevList: [],
        criteresFoncList: [],
    }),

    actions: {
        createProject(project: Project) {
            this.activeProject = project;
            this.projects.push(project);
        },
        setOnCreatedPopUp(showMessage: boolean) {
            this.onCreatedPopUp = showMessage;
        },
        addOnglet(onglet: OngletMenu) {
            this.onglets.push(onglet);
        },
        removeOnglet(index: number) {
            if (index >= 0 && index < this.onglets.length) {
                this.onglets.splice(index, 1);
            }
        },
        updateItems(onglets: OngletMenu[]) {
            this.onglets = onglets;
        },
        setProgress(newProgress: number) {
            this.progress = Math.floor(newProgress);
        },
        setCriteresUXList(criteres: { name: string; key: string; state: any }[]) {
            this.criteresUXList = criteres;
        },

        setCriteresDevList(criteres: { name: string; key: string; state: any }[]) {
            this.criteresDevList = criteres;
        },
        setCriteresFoncList(criteres: { name: string; key: string; state: any }[]) {
            this.criteresFoncList = criteres;
        },
        setCriteresDevListTotal(criteres: CriteresDevPage[]) {
            this.criteresDevListTotal = criteres;
        },
        setCriteresFonctionelsListTotal(criteres: CriteresFoncPage[]) {
            this.criteresFonctionelsListTotal = criteres;
        },
        setCriteresUXListTotal(criteres: CriteresUXPage[]) {
            this.criteresUXListTotal = criteres;
        },
        setProgressUX(newProgress: number) {
            this.progressUX = Math.round(newProgress);
        },
        setProgressDevelop(newProgress: number) {
            this.progressDevelop = Math.round(newProgress);
        },
        setProgressFunc(newProgress: number) {
            this.progressFunc = Math.round(newProgress);
        },
        ProjectDetails(projectDetails: Project) {
            this.projects.push(projectDetails);
            this.activeProject = projectDetails;
        },
        setOngletActif(label: string) {
            this.ongletActifLabel = label;
        },
        initializeTabs() {
            const storedTabs = localStorage.getItem('ongletProject');
            if (storedTabs) {
                this.onglets = JSON.parse(storedTabs);
            }
        },
        setActiveProjectDetails(projectDetails: Project) {
            this.activeProject = projectDetails;
        },
    },
    getters: {
        activeProjectDetails(): Project | null {
            return this.activeProject;
        },
    },
});
