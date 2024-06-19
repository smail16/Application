import { AxiosHttp } from '@/http/AxiosHttp';
import { Project } from './Project';
import axiosInstanceBackEnd from '@/http/AxiosInstanceBackEnd';
import { Page } from './Page';
/**
 * Service pour effectuer des opérations liées aux projets.
 */

class ProjectServices {
    // Instance de AxiosHttp utilisée pour les requêtes HTTP liées aux projets.
    private axiosHttp = new AxiosHttp(axiosInstanceBackEnd);

    /**
     * Crée un nouveau projet avec le titre spécifié.
     */
    async createProject(titre: string) {
        const projectData = { titre };
        return this.axiosHttp.post('/projects', projectData);
    }

    /**
     * La liste des projets
     */
    async findAllProjects() {
        return this.axiosHttp.get<Project[]>('/projects');
    }
    /**
     * GetByIdentifiant
     */
    async findProjectByIdentifiant(identifiant: string) {
        return this.axiosHttp.get<Project>(`/projects/${identifiant}`);
    }

    async updateProject(identifiant: string, projectData: Project, pages: Page[]) {
        const data = {
            ...projectData,
            pages: pages.map(page => ({ titrePage: page.titrePage, rubriquesPage: page.rubriquesPages })),
        };

        return this.axiosHttp.put(`/projects/${identifiant}`, data);
    }

    async updateAvancement(identifiant: string, avancementUpdated: number) {
        return this.axiosHttp.put(`/projects/avancement/${identifiant}?avancementGlobal=${avancementUpdated}`);
    }

    async deleteProject(identifiant: string) {
        return this.axiosHttp.delete<void>(`/projects/${identifiant}`);
    }
}

export default ProjectServices;
