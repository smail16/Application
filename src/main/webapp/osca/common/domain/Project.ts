import { Page } from './Page'; // Assurez-vous que le chemin est correct
export interface Project {
    identifiant: string;
    avancementGlobal: number;
    titre: string;
    dateCreation: string;
    dateDerniereModification: string;
    pages: Page[];
}
