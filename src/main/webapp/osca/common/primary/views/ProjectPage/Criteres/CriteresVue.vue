<template src="./Criteres.html"></template>

<script lang="ts" src="./Criteres.component.ts"></script>

<style scope>
.header {
    font-weight: 500;
}
.pi-comment {
    font-weight: 500;
    margin-right: 5rem;
}
.p-accordion .p-accordion-tab.p-accordion-tab-active .p-accordion-toggle-icon,
.p-accordion .p-accordion-tab:not(.p-accordion-tab-active) .p-accordion-toggle-icon {
    transform: none !important;
}

.checkbox-label {
    margin-left: 2rem;
}
.checkbox-list {
    margin-bottom: 3rem;
}
.p-checkbox-box.p-highlight {
    background: #a60726;
    border-color: #a60726;
}

.pi-info-circle {
    font-weight: 500;
    margin-right: 5rem;
}

.p-tabview {
    font-size: 0.9rem;
}
.p-tabview .p-tabview-nav li.p-highlight .p-tabview-nav-link {
    color: #a60726;
    background: #fcf4f4;
}

.p-tabview .p-tabview-nav .p-tabview-ink-bar {
    background-color: rgb(241, 199, 199);
}
.tab-view-image {
    width: 100%;
}
.image-description-soustitre {
    font-size: 0.9rem;
    margin-bottom: 1.5rem;
    margin-top: 1rem;
}
.accordion-images {
    margin-bottom: 2rem;
}
.good-practice {
    margin-bottom: 0.9rem;
}

/* Cette règle cache l'icône SVG */
[data-pc-section='uncheckicon'] {
    display: none !important;
}

/* Cette règle ajoute 'NA' au parent de l'élément SVG qui est masqué */
.p-checkbox-box.p-highlight:has(> [data-pc-section='uncheckicon'])::after {
    content: 'N/A';
    font-size: 0.6rem;
    color: white;
    font-weight: bold;
}
.lignecheckboxes {
    margin-bottom: 1rem;
}
</style>
./Criteres.component.js./Criteres.component.js
