<template src="@/common/primary/views/CreateProjectPage/CreateProject.html"></template>

<script lang="ts" src="@/common/primary/views/CreateProjectPage/CreateProject.component"></script>

<style scoped>
h1 {
    font-size: 2rem;
    font-family: sans-serif;
    color: #a60726;
}
.container {
    padding: 2rem;
}

label {
    font-weight: bold;
    font-family: sans-serif;
}

.custom-button-end {
    color: white;
    border: none;
    border-radius: 0;
    width: 7rem;
    display: inline-block;
    margin-bottom: 12rem;
}
.custom-button-end:hover {
    background: white;
    color: black;
    border: 1px solid #a60726;
}
.custom-button-add {
    background: white;
    color: black;
    border-radius: 0;
    border: 1px solid black;
    justify-content: center;
    width: 6rem;
}
.p-inputtext.p-focus,
.p-inputtext:focus,
.p-inputtext.p-filled {
    border-color: darkred;
    box-shadow: none;
}
.p-float-label > label {
    left: 1rem;
    color: rgba(0, 0, 0, 0.6);
    transition-duration: 0, 5s;
}
.p-float-label input:focus + label {
    color: black;
}
.p-float-label .p-inputwrapper-focus ~ label {
    color: black;
}
</style>
