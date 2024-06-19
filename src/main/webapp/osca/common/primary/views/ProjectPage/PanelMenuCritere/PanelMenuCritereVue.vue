<template src="./PanelMenuCritere.html"></template>

<script lang="ts" src="./PanelMenuCritere.component.ts"></script>

<style lang="scss">
.p-panelmenu {
    max-width: 23rem;
    font-size: 1rem;
    margin-bottom: 5rem;
}

.Rubrique {
    font-size: 1.5rem;
}

.p-menuitem-text {
    font-weight: 500;
    align-items: left;
}
.p-panelmenu .p-menuitem-link {
    padding-left: 4rem;
}
.p-menuitem-link.active-panel .p-menuitem-text {
    color: var(--color);
}
.p-menuitem-link.active-panel {
    background-color: var(--surface-a);
}
.p-panelmenu-panel.active-panel .p-panelmenu-header-action {
    color: var(--color);
}
.p-panelmenu-panel.active-panel .p-panelmenu-header-content {
    background-color: var(--bg-color);
}
.p-menuitem-link.active-panel {
    color: var(--color);
}
.p-panelmenu-header-action.active-panel {
    color: var(--color);
    background-color: var(--bg-color);
}
.p-menuitem-text.active-panel {
    color: var(--color);
}
</style>