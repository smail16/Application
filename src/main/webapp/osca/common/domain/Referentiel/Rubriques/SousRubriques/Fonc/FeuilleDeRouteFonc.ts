import { CriteresFonc } from './CriteresFonc';
import { PratiquesFonc } from './PratiquesFonc';

export interface FeuilleDeRouteFonc {
    titre: string;
    keyFeuilleDeRouteFonc: string;
    criteresFoncList: CriteresFonc[];
    pratiquesFoncList: PratiquesFonc[];
}
