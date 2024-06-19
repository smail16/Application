import { useI18n } from 'vue-i18n';

//méthodes appelées @onclick par les icones
export default {
    setup() {
        const { t } = useI18n();
        return { t };
    },
    methods: {
        openXLink() {
            window.open('https://twitter.com/SopraSteria');
        },
        openInstagramLink() {
            window.open('https://www.instagram.com/soprasteria_fr/');
        },
        openFacebookLink() {
            window.open('https://www.facebook.com/SopraSteria.in/');
        },
        openYouTubeLink() {
            window.open('https://www.youtube.com/channel/UC-7O40XLuOQW967KGfSEzRw');
        },
        openLinkedInLink() {
            window.open('https://www.linkedin.com/company/soprasteria/');
        },
    },
};
