<template>
  <div>
    <div class="table-btn-background">
      <b-form-input
        style="width:30%; border:none; background-color: #C4C4C4;"
        id="filter-input"
        v-model="keyWord"
        type="search"
      ></b-form-input>
      <button style="width:10%; height:100%;" @click="onCreate" id="createBtn" >등록</button>
    </div>
    <b-table 
      id="inquiryTable"
      :items="inquiryList" 
      :fields="fields" 
      responsive="sm"
      :filter=keyWord
      class="text-center"
      :per-page="perPage"
      :current-page="currentPage"
    >
      <template #cell(#)="data">
        <div >{{data.index+1}}</div>
      </template>
      <template #cell(제목)="data" >
       <div class="d-flex justify-content-start"><a :href="`inquiry/read/${data.item.inquiry_id}`">{{data.item["inquiry_title"]}}</a></div> 
      </template>
      <template #cell(작성자)="data">
        <div>{{data.item["inquiry_user_id"]}}</div>
      </template>
      <template #cell(작성일)="data">
        <div>{{data.item["inquiry_date"]}}</div>
      </template>
    </b-table>
    <b-pagination
      small
      id="inquiryTablePagination"
      v-model="currentPage"
      :total-rows="rows"
      :per-page="perPage"
      prev-text="Prev"
      next-text="Next"
      aria-controls="inquiryTable"
      align="center"
    >
    
      <template #first-text><span @click="goToTableTop" class="text-dark">First</span></template>
      <template #prev-text><span  @click="goToTableTop" v-elseclass="text-dark">Prev</span></template>
      <template #next-text><span @click="goToTableTop" class="text-dark">Next</span></template>
      <template #last-text><span @click="goToTableTop" class="text-dark">Last</span></template>
      <template #page="{ page }">
        <b @click="goToTableTop" class="text-dark" >{{ page }}</b>
      </template>
    </b-pagination>

  </div>
</template>

<script>
import http from "@/util/http-common.js";
export default {
  name : 'InquiryTable',
  data () {
    return {
      perPage: 10,
      currentPage: 1,
      keyWord : '',
      fields : ['#', '제목', '작성자', '작성일'],
      inquiryList : [],
    }
  },
  created() {
    http.get('inquiry/list')
    .then((res)=>{
      console.log(res)
      this.inquiryList = res.data.reverse()
    })
  },
  watch :  {
  },
  computed: {
    rows(){
      return this.inquiryList.length;
    }
  },
  methods : {
    onCreate() {
      this.$router.push('/inquiry/create')
    },
    goToTableTop() {
      window.scrollTo({top : 150})
    }

  },
}
</script>

<style scoped>
  #inquiryTablePagination{
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