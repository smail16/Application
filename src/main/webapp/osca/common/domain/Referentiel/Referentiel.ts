import { Rubriques } from './Rubriques/Rubriques';

export interface Referentiel {
    version: string;
    rubriques: Rubriques[];
}
