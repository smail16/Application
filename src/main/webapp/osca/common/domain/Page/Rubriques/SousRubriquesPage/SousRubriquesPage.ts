import { FeuilleDeRouteDevPage } from './Dev/FeuilleDeRouteDev';
import { FeuilleDeRouteFoncPage } from './Fonc/FeuilleDeRouteFoncPage';
import { FeuilleDeRouteUXPage } from './UX/FeuilleDeRouteUXPage';
import { Maquette } from './Maquette';

export interface SousRubriquesPage {
    keySousRubriquesPage: string;
    feuilleDeRouteDevPage: FeuilleDeRouteDevPage[];
    feuilleDeRouteUXPage: FeuilleDeRouteUXPage[];
    feuilleDeRouteFoncPage: FeuilleDeRouteFoncPage[];
    maquette: Maquette[];
}
