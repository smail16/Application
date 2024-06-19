<template src="@/common/primary/views/ProjetListPage/ProjectList.html"></template>

<script lang="ts" src="@/common/primary/views/ProjetListPage/ProjectList.component"></script>

<style>
.tableau {
    margin-top: 1rem;
    margin-bottom: 22rem;
    font-family: sans-serif;
    background-color: #ffffff;
}
.container-project {
    margin-left: auto;
    display: flex;
    justify-content: space-between;
    align-items: center;
    padding: 1rem;
}

.router-link {
    text-decoration: none;
}

.p-datatable .p-datatable-thead > tr > th {
    background-color: #fff4f4;
}
.tableau th.text-center {
    text-align: center !important;
}

.p-column-title {
    color: #676767;
    font-weight: bold;
    text-align: center;
}

.create:hover {
    background-color: white;
    color: black;
    border: 1px solid #a60726;
}

.buttons-container {
    display: flex;
    align-items: center;
    gap: 0.8rem;
}
button.custom-button-delete {
    background: white;
    color: red;
    border-radius: 1;
    border: 0.1rem solid red;
}
button.custom-button-delete:hover {
    background: black;
    color: white;
    border: black;
}
button.custom-button-modify {
    background: white;
    color: black;
    border-radius: 1;
    border: 0.1rem solid black;
}

button.custom-button-modify:hover {
    background: black;
    color: white;
    border: black;
}
button.custom-button-share {
    background: white;
    color: black;
    border-radius: 1;
    border: 0.1rem solid black;
}
button.custom-button-share:hover {
    background: black;
    color: white;
    border: black;
}
</style>
