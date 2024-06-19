import { CriteresUX } from './CriteresUX';
import { PratiquesUX } from './PratiquesUX';

export interface FeuilleDeRouteUX {
    titre: string;
    keyFeuilleDeRouteUX: string;
    criteresUXList: CriteresUX[];
    pratiquesUXList: PratiquesUX[];
}
