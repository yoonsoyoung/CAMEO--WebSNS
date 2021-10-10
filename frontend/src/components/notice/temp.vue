<template>
    <div class="table-grid">
    <div class="table-btn-background">
      <div class="table-searchbar-background">
        <input v-model="keyWord" type="text" @keyup.enter="onSearch" id="searchBar">
        <button @click="onSearch" id="searchBtn">검색</button>
      </div>
      <div class="" >
        <button @click="onCreate" id="createBtn" >등록</button>
      </div>
    </div>
    <div>
      <table>
        <colgroup>
          <col width="6%"/>
          <col width="*"/>
          <col width="10%"/>
          <col width="15%"/>
        </colgroup>
        <tr>
          <th>No.</th>
          <th>제목</th>
          <th>작성자</th>
          <th>작성일</th>
        </tr>
        <tr
          v-for="(row, idx) in this.list.slice(this.num-this.pagination, this.num)"
          :key="idx"
        >
          <td id="item">{{row.notice_id}}</td>
          <td id="item"><a :href="`notice/read/${row.notice_id}`" id="noticeTitle">{{row.notice_title}}</a></td>
          <td id="item">admin</td>
          <td id="item">{{row.notice_date}}</td>
        </tr>
        <tr
          v-if="list.length==0"
        >
          <td colspan="4">데이터가 없습니다.</td>
        </tr>
      </table>
      <div class="pagination-btn-group">
        <button @click="prevPage"  id="prevBtn">이전</button>
        <button @click="nextPage" disabled id="nextBtn">다음</button>
      </div>
    </div>
  </div>
</template>

<script>
export default {

}
</script>

<style>

</style>