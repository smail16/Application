import { AxiosHttp } from '@/http/AxiosHttp';
import { Referentiel } from './Referentiel/Referentiel';
import axiosInstanceBackEnd from '@/http/AxiosInstanceBackEnd';

class ReferentielService {
    private axiosHttp = new AxiosHttp(axiosInstanceBackEnd);

    /**
     * Recherche un référentiel en fonction de sa version.
     *
     * @param versionRef La version du référentiel à rechercher.
     * @return Un objet de type Referentiel correspondant au référentiel trouvé
     */

    async findReferentielByVersion(versionRef: string) {
        return this.axiosHttp.get<Referentiel>(`/referentiel/${versionRef}`);
    }
}
export default ReferentielService;
