<template>
    <div id="goTopBtnRoot">
        <button @click="goTop" class="top-btn shadow-sm"><i class="top-btn-icon fas fa-angle-up"></i></button>
    </div>
</template>

<script>
export default {
    name: 'GoTopBtn',
    methods: {
        goTop() {
            window.scrollTo(0, 0);
        }
    },
}
</script>

<style scoped>
#goTopBtnRoot {
    position: fixed;
    bottom: 50px;
    right: 40px;
}
.top-btn {
    width: 30px;
    height: 30px;
    border-radius: 100%;
    border: none;
    background-color: rgba(212, 212, 212, 0.5);
    text-align: center;
    z-index: 80;
}
.top-btn-icon {
    font-size: 1.4em;
    color: #365959;
}
</style>