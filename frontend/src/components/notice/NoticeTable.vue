<template>
  <div>
    <div class="table-btn-background">
      <b-form-input
        style="width:30%; border:none; background-color: #C4C4C4;"
        id="filter-input"
        v-model="keyWord"
        type="search"
      ></b-form-input>
      <button v-if="this.loginUser==='admin'" style="width:10%; height:100%;" @click="onCreate" id="createBtn" >등록</button>
    </div>
    <b-table 
      id="noticeTable"
      :items="List" 
      :fields="fields" 
      responsive="sm"
      :filter="keyWord"
      class="text-center"
      :per-page="perPage"
      :current-page="currentPage"
    >
      <template #cell(#)="data">
        <div >{{data.index + 1}}</div>
      </template>
      <template #cell(제목)="data" >
       <div class="d-flex justify-content-start"><a :href="`notice/read/${data.item.notice_id}`">{{data.item["notice_title"]}}</a></div> 
      </template>
      <template #cell(작성자)="">
        <div>admin</div>
      </template>
      <template #cell(작성일)="data">
        <div>{{data.item["notice_date"]}}</div>
      </template>
    </b-table>
    <b-pagination
      small
      
      id="noticeTablePagination"
      v-model="currentPage"
      :total-rows="rows"
      :per-page="perPage"
      aria-controls="noticeTable"
      prev-text="Prev"
      next-text="Next"
      align="center"
      
    > 
      <template #first-text><span @click="goToTableTop" class="text-dark">First</span></template>
      <template #prev-text><span @click="goToTableTop" class="text-dark">Prev</span></template>
      <template #next-text><span @click="goToTableTop" class="text-dark">Next</span></template>
      <template #last-text><span  @click="goToTableTop" class="text-dark">Last</span></template>
      <template #page="{ page }">
        <b  @click="goToTableTop" class="text-dark" >{{ page }}</b>
      </template>
    </b-pagination>
  </div>
</template>

<script>
import http from "@/util/http-common.js";

export default {
  name : 'Table',
  data () {
    return {
      loginUser : localStorage.getItem("user_id"),
      perPage: 10,
      currentPage: 1,
      keyWord : '',
      fields : ['#', '제목', '작성자', '작성일'],
      noticeList : [],
      List : [],
    }
  },
  created() {
    http.get('/notice/list')
    .then((res)=>{
      console.log(res)
      this.noticeList = res.data.reverse()
      this.List = this.noticeList
    })
  },
  watch :  {
    num : function(v) {
      this.checkNum();
    },
  },
  computed: {
    rows(){
      return this.noticeList.length;
    }
  },
  methods : {
    onCreate() {
      this.$router.push('/notice/write')
    },
    goToTableTop() {
      window.scrollTo({top : 150})
    }

  },

}


</script>

<style scoped>
#noticeTablePagination{
  margin-top: 10%;
}
  a {
      text-decoration: none;
      color: black;
  }
  a:hover{
    color: black;
  }
  .table-grid{
    position: relative;
  }
  .table-btn-background {
    display: flex;
    justify-content: space-between;
    align-items: center;
    margin-bottom: 3%;
  }
  .table-searchbar-background{
    display: flex;
  }
  #searchBar{
    width: 100%;
    font-size: 1rem;
  }
  #filter-input{
    all: none;
  }
  #searchBtn{
    width: 25%;
    background-color: #C4C4C4;
    margin-left: 10%;
    font-size: 1rem;
    height: 100%;
    border-radius: 5px;
    border: none;
  }
  .create-btn-background {
    display: flex;
    justify-content: flex-end;
  }
  #createBtn{
    width: 100%;
    padding: 0;
    background-color: #DCC19E;
    margin-left: 1%;
    font-size: 1rem;
    height: 100%;
    border-radius: 5px;
    border: none;
    cursor: pointer;
  }
  /* @media screan : (max-width:768px;) {
    
  } */
</style>