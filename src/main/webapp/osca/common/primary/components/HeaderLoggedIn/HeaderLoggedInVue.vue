<template src="@/common/primary/components/HeaderLoggedIn/HeaderLoggedIn.html"></template>

<script lang="ts" src="@/common/primary/components/HeaderLoggedIn/HeaderLoggedIn.component"></script>

<style>
.titre-header {
    margin-left: 5rem;
}

.osca {
    margin-top: 2rem;
    margin-bottom: 0rem;
    font-size: 2rem;
}

.sous-titre {
    float: left;
    margin-top: 0.5rem;
    font-size: 1rem;
    font-weight: normal;
    white-space: nowrap;
}

.dropdown {
    float: right;
}

.button-container {
    display: flex;
    width: 100%;
}

.avatar-loggedIn {
    background-color: white;
    color: #a60726;
    border: 2px solid #a60726;
    padding: 3px;
    height: 3.3rem;
    width: 3.3rem;
}
.nom-loggedIn {
    white-space: nowrap;
    font-weight: 500;
}
</style>
