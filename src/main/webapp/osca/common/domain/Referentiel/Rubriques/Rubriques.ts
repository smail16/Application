import { SousRubriques } from './SousRubriques/SousRubriques';

export interface Rubriques {
    titre: string;
    keyRubriques: string;
    sousRubriques: SousRubriques[];
}
