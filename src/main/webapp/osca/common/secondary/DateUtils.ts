export default class DateUtils {
    static toFrDateFormat(dateToConvert: string | null) {
        if (!dateToConvert) {
            return null;
        }
        const dateISO = new Date(dateToConvert);
        return dateISO.toLocaleDateString('fr-FR');
    }
}
