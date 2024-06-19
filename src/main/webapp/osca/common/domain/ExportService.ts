import axios from 'axios';

/**
 * Service pour l'exportation des projets.
 */
class ExportService {
    /**
     * Exporter un projet en téléchargeant un fichier CSV.
     *
     * @param identifiant L'identifiant du projet à exporter.
     * @returns Une promesse qui se résout lorsque le téléchargement est terminé.
     */
    async exportProject(identifiant: string) {
        const response = await axios.get(`/api/projects/${identifiant}/export/csv`, {
            responseType: 'blob',
        });

        const contentDisposition = response.headers['content-disposition'];
        const filename = contentDisposition.split('filename=')[1];

        const url = window.URL.createObjectURL(new Blob([response.data]));
        const link = document.createElement('a');
        link.href = url;
        link.setAttribute('download', filename);
        document.body.appendChild(link);
        link.click();
    }
}

export default new ExportService();
