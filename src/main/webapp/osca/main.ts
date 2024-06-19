import { createApp } from 'vue';
import { AppVue } from './common/primary/app';
import router from './router/router';
import { createPinia } from 'pinia';
import piniaPluginPersistedstate from 'pinia-plugin-persistedstate';
import PrimeVue from 'primevue/config';
import i18n from '@/i18n/index';
import 'primevue/resources/themes/md-light-indigo/theme.css';
import 'primevue/resources/primevue.min.css';
import 'primeicons/primeicons.css';
import 'primeflex/primeflex.css';
import '../content/_variables.scss';

/* eslint-disable vue/multi-word-component-names */
// import primevue
/* eslint-disable-next-line vue/no-reserved-component-names */
import ButtonPrime from 'primevue/button';
import Toolbar from 'primevue/toolbar';
import MegaMenu from 'primevue/megamenu';
import Menubar from 'primevue/menubar';
import SplitButton from 'primevue/splitbutton';
import ToastService from 'primevue/toastservice';
import TabMenu from 'primevue/tabmenu';
/* eslint-disable-next-line vue/no-reserved-component-names */
import Image from 'primevue/image';
import AutoComplete from 'primevue/autocomplete';
import InputText from 'primevue/inputtext';
import Dropdown from 'primevue/dropdown';
import DataTable from 'primevue/datatable';
import Column from 'primevue/column';
import ColumnGroup from 'primevue/columngroup';
import Row from 'primevue/row';
import ProgressBar from 'primevue/progressbar';
import Accordion from 'primevue/accordion';
import AccordionTab from 'primevue/accordiontab';
import FileUpload from 'primevue/fileupload';
import PanelMenu from 'primevue/panelmenu';
import TabView from 'primevue/tabview';
import TabPanel from 'primevue/tabpanel';
import Checkbox from 'primevue/checkbox';
/* eslint-disable-next-line vue/no-reserved-component-names */
import DialogPrime from 'primevue/dialog';
import Avatar from 'primevue/avatar';
import AvatarGroup from 'primevue/avatargroup';
import Toast from 'primevue/toast';
import Message from 'primevue/message';
import TriStateCheckbox from 'primevue/tristatecheckbox';
import Knob from 'primevue/knob';
import Breadcrumb from 'primevue/breadcrumb';
import ImagePrime from 'primevue/image';
import Badge from 'primevue/badge';

// jhipster-needle-main-ts-import

const app = createApp(AppVue);
app.use(PrimeVue);
app.use(router);
app.use(ToastService);
app.use(i18n);
const pinia = createPinia();
pinia.use(piniaPluginPersistedstate);
app.use(pinia);
app.component('ButtonPrime', ButtonPrime);
app.component('Toast', Toast);
app.component('Toolbar', Toolbar);
app.component('MegaMenu', MegaMenu);
app.component('Menubar', Menubar);
app.component('SplitButton', SplitButton);
app.component('TabMenu', TabMenu);
app.component('Image', Image);
app.component('AutoComplete', AutoComplete);
app.component('InputText', InputText);
app.component('Dropdown', Dropdown);
app.component('DataTable', DataTable);
app.component('Column', Column);
app.component('ColumnGroup', ColumnGroup);
app.component('Row', Row);
app.component('ProgressBar', ProgressBar);
app.component('Accordion', Accordion);
app.component('AccordionTab', AccordionTab);
app.component('FileUpload', FileUpload);
app.component('PanelMenu', PanelMenu);
app.component('TabView', TabView);
app.component('TabPanel', TabPanel);
app.component('Checkbox', Checkbox);
app.component('DialogPrime', DialogPrime);
app.component('Avatar', Avatar);
app.component('AvatarGroup', AvatarGroup);
app.component('Message', Message);
app.component('TriStateCheckbox', TriStateCheckbox);
app.component('Knob', Knob);
app.component('Breadcrumb', Breadcrumb);
app.component('ImagePrime', ImagePrime);
app.component('Badge', Badge);

// jhipster-needle-main-ts-provider
app.mount('#app');
