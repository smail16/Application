import { CriteresDev } from './CriteresDev';
import { PratiquesDev } from './PratiquesDev';

export interface FeuilleDeRouteDev {
    titre: string;
    keyFeuilleDeRouteDev: string;
    criteresDevList: CriteresDev[];
    pratiquesDevList: PratiquesDev[];
}
