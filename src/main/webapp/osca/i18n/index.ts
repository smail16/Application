import { createI18n } from 'vue-i18n';
import texteEN from '@/i18n/en.json';
import texteFR from '@/i18n/fr.json';

export default createI18n({
    locale: 'FR',
    legacy: false,
    messages: {
        FR: texteFR,
        EN: texteEN,
    },
});
