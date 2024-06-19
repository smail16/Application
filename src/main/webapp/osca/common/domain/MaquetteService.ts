import { AxiosHttp } from '@/http/AxiosHttp';
import axiosInstanceBackEnd from '@/http/AxiosInstanceBackEnd';
import { Maquette } from './Page/Rubriques/SousRubriquesPage/Maquette';

class MaquetteService {
    private axiosHttp = new AxiosHttp(axiosInstanceBackEnd);
    // Méthode pour ajouter une maquette
    async addMaquette(file: File, projectId: string, keySousRubrique: string, titrePage: string) {
        // Création d'un objet FormData pour envoyer des données binaires
        const maquetteData = new FormData();
        // Ajout des données de la maquette à l'objet FormData
        maquetteData.append('file', file);
        maquetteData.append('keySousRubrique', keySousRubrique);
        maquetteData.append('projectId', projectId);
        maquetteData.append('titrePage', titrePage);

        return this.axiosHttp.post('/maquette', maquetteData);
    }
    // Méthode pour récupérer une maquette par son identifiant
    async getMaquetteByIdentifiant(identifiant: string) {
        return this.axiosHttp.get<Maquette>(`/maquette/${identifiant}`);
    }
    // Méthode pour récupérer une maquette par l'identifiant du projet, la clé de la sous-rubrique et le titre de la page
    async getMaquetteByIdentifiantProjetAndKeySousRubriqueAndTitrePage(projectId: string, keySousRubrique: string, titrePage: string) {
        return this.axiosHttp.get<Maquette[]>(`/maquettes/${projectId}?keySousRubrique=${keySousRubrique}&titrePage=${titrePage}`);
    }
    // Méthode pour supprimer une maquette par son identifiant
    async deleteMaquette(identifiant: string) {
        return this.axiosHttp.delete(`/maquette/${identifiant}`);
    }
}
export default MaquetteService;
