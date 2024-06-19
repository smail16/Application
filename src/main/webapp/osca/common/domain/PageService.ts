import { AxiosHttp } from '@/http/AxiosHttp';
import axiosInstanceBackEnd from '@/http/AxiosInstanceBackEnd';
import { Page } from './Page';

class PageService {
    private axiosHttp = new AxiosHttp(axiosInstanceBackEnd);
    /**
     * Crée une nouvelle page
     */
    async createPage(identifiantProject: string, titrePage: string, version: string) {
        const pageData = { identifiantProject, titrePage, version };
        return this.axiosHttp.post('/pages', pageData);
    }
    async deletePage(identifiantProject: string, titrePage: string) {
        return this.axiosHttp.delete(`/pages?identifiant=${identifiantProject}&titrePage=${titrePage}`);
    }

    /**
     * Update une page
     */
    async updatePage(
        identifiant: string,
        titrePage: string,
        updatedPage: Page,
        avancementGlobal: number,
        avancementUX: number,
        avancementDev: number,
        avancementFonc: number,
        keySousRubrique: string,
    ) {
        const data = {
            ...updatedPage,
            avancementGlobal: avancementGlobal,
        };
        const response = await this.axiosHttp.put(
            `/pages?identifiant=${identifiant}&titrePage=${titrePage}&avancementGlobal=${avancementGlobal}&keySousRubrique=${keySousRubrique}&avancementUX=${avancementUX}&avancementDev=${avancementDev}&avancementFonc=${avancementFonc}`,
            data,
        );
        return response.data as Page;
    }
}
export default PageService;
