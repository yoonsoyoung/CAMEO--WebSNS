<template>
  <div id="listItemRoot" @click="giveAddress">
    <table class="info-table">
      <tr>
        <td class="cafe-name" colspan="2"><a :href="cafeUrl">{{ cafeName }}</a></td>
      </tr>
      <tr>
        <td class="icon"><i class="fas fa-map-marker-alt"></i></td>
        <td class="cafe-addr">{{ cafeAddr }}</td>
      </tr>
    </table>
  </div>
</template>

<script>

export default {
    name: 'ListItem',
    props: {
      cafeName : String,
      cafeAddr: String,
      cafeUrl : String,
    },
    methods : {
      giveAddress(){
        this.$emit('giveAddress', this.cafeAddr);
      }
    }
}
</script>

<style scoped>
@import url("https://fonts.googleapis.com/css2?family=Fredoka+One&family=Lobster&family=Jua&family=Noto+Sans+KR:wght@300;400&display=swap");
* {
    font-family: "Noto Sans KR";
    font-weight: 400;
}
a {
  text-decoration: none;
  color: black;
}
#listItemRoot {
    display: flex;
    flex-direction: column;
    flex-wrap: nowrap;
    align-content: flex-start;
    justify-content: center;
    align-items: flex-start;
    width: 100%;
    height: 90px;
}
#listItemRoot:hover {
    background-color: rgba(217, 198, 176, 0.5);
}
.info-table {
    margin: 5px 20px;
    width: 90%;
}
.cafe-name {
    font-size: 1.1em;
    font-weight: 600;
}
.icon {
    text-align: center;
    font-size: 0.8em;
}
</style>