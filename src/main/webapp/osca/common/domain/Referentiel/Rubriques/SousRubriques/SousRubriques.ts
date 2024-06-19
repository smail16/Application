import { FeuilleDeRouteFonc } from './Fonc/FeuilleDeRouteFonc';
import { FeuilleDeRouteDev } from './Dev/FeuilleDeRouteDev';
import { FeuilleDeRouteUX } from './UX/FeuilleDeRouteUX';

export interface SousRubriques {
    titre: string;
    keySousRubriques: string;
    sousRubriqueUx: FeuilleDeRouteUX[];
    sousRubriqueDeveloppement: FeuilleDeRouteDev[];
    sousRubriqueFonctionnelle: FeuilleDeRouteFonc[];
}
