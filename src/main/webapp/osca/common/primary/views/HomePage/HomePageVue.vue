<template src="./HomePage.html"></template>

<script lang="ts" src="./HomePage.component.ts"></script>

<style scoped>
.HomePageImage {
    max-width: 100%;
}
.container {
    background-color: #f6f6f6;
    border: 1px solid #dbd5d5;
    display: flex;
    justify-content: space-between;
    padding: 20px;
}

.content {
    display: flex;
    align-items: center;
}

h1 {
    font-size: 4rem;
    margin-left: 2rem;
}

p {
    font-size: 1.5rem;
    margin-left: 2rem;
    margin-bottom: 2rem;
}
.row {
    display: flex;
    flex-wrap: wrap;
    align-items: flex-start;
    justify-content: space-between;
    color: #000;
}

.col {
    font-size: 1.3rem;
    line-height: 28px;
    text-align: left;
    display: flex;
    flex-direction: column;
    margin-bottom: 3rem;
}

.comment-ca-marche h2 {
    margin-top: 5rem;
    margin-bottom: 3rem;
}

.custom-icon {
    font-size: 300%;
    margin-bottom: 1.5rem;
}

.comment-ca-marche {
    font-size: 1.4rem;
}
.router-link {
    text-decoration: none;
}
.button-create {
    float: left;
    margin-left: 2rem;
    margin-top: 1rem;
}
.button-create:hover {
    background: white;
    color: black;
    border: 1px solid #a60726;
}
</style>
