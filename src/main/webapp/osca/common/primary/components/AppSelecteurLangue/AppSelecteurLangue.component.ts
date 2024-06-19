import { useI18n } from 'vue-i18n';

export default {
    setup() {
        const { locale, availableLocales } = useI18n();
        return {
            locale,
            availableLocales,
        };
    },
};
