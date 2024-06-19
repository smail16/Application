import { useReferentielStore } from '@/common/secondary/pinia/ReferentielStore';
import { defineComponent, watchEffect, onMounted, ref } from 'vue';

export default defineComponent({
    setup() {
        // Utilisation du store de référentiel
        const refStore = useReferentielStore();
        const referentiel = refStore.referentiel;
        const expandedKeys = ref({ '1': true });

        /**
         * Tableaux des items du menu à partir des données du store de référentiels sur lequel le panelMenu côté HTML va boucler.
         * @type {Array}
         */
        const menuItems = referentiel
            ? referentiel.rubriques.map(rubrique => ({
                  label: rubrique.titre,
                  icon: 'pi pi-fw',
                  key: rubrique.keyRubriques,
                  items: rubrique.sousRubriques.map(sousRubrique => ({
                      label: sousRubrique.titre,
                      icon: '',
                      command: () =>
                          // Met à jour la sous-rubrique active lors du clic
                          updateRubriques(rubrique, sousRubrique),
                  })),
              }))
            : [];
        function updateRubriques(rubrique, sousRubrique) {
            refStore.setSelectedRubrique(rubrique.titre);
            refStore.setSelectedSousRubrique(sousRubrique.titre);
            refStore.setSelectedSousRubriqueKey(sousRubrique.keySousRubriques);
            refStore.setSelectedRubriquesKey(rubrique.keyRubriques);
        }
        /**
         * Observe les changements de la sous-rubrique sélectionnée
         * @param {string} newValue nouvelle valeur de la sous-rubrique sélectionnée.
         */
        watchEffect(() => {
            refStore.selectedSousRubriques;
        });
        const handleClick = event => {
            const menuItem = event.target.closest('.p-menuitem-link');
            if (menuItem) {
                /* Supprimer la classe active-panel de tous les éléments */
                document.querySelectorAll('.active-panel').forEach(element => {
                    element.classList.remove('active-panel');
                });
                /* Ajouter la classe active-panel au sous-menu */
                menuItem.classList.add('active-panel');
                /*  Ajouter la classe active-panel au menu  */
                const parentPanel = event.target.closest('.p-panelmenu-panel');
                parentPanel.classList.add('active-panel');
            }
        };
        const ActiveMenuItem = () => {
            const menuItemHeaders = document.querySelectorAll('.p-panelmenu-header-action');
            const menuItems = document.querySelectorAll('.p-menuitem-text');

            if (menuItemHeaders.length > 0) {
                menuItemHeaders[0].classList.add('active-panel');
            }

            if (menuItems.length > 0) {
                menuItems[3].classList.add('active-panel');
            }
        };
        onMounted(() => {
            if (referentiel) {
                const firstRubrique = referentiel.rubriques[0];
                const firstSousRubrique = firstRubrique.sousRubriques[0];
                if (firstRubrique && firstSousRubrique) {
                    updateRubriques(firstRubrique, firstSousRubrique);
                }
            }

            setTimeout(() => {
                ActiveMenuItem();
            }, 1);
        });

        return {
            menuItems,
            handleClick,
            ActiveMenuItem,
            expandedKeys,
        };
    },
});
