<template>
  <div>
      <div class="u-search-container" style="display: flex;">
          <div class="u-search-div">
              <label class="u-search-standard">검색 기준</label>
              <select v-model="searchUser.key" name="standard" class="search-standard-u">
                  <option value="none">없음</option>
                  <option value="id">아이디</option>
                  <option value="name">이름</option>
              </select>
          </div>
          <div class="u-search-div">
              <label>검색 내용 </label>
              <input class="search-standard-u" type="text" v-model="searchUser.word" />
          </div>
          <div class="u-search-div">
              <label>정렬 기준 </label>
              <select v-model="searchUser.orderBy" class="search-standard-u">
                  <option value='none'>없음</option>
                  <option value="id">아이디</option>
                  <option value="name">이름</option>
              </select>
          </div>
          <div class="u-search-div">
              <label>정렬 방향 </label>
              <select v-model="searchUser.orderByDir" class="search-standard-u">
                  <option value="asc">오름차순</option>
                  <option value="desc">내림차순</option>
              </select>
          </div>
          <div class="search-div">
              <button @click="searchUserList" class="custom-btn-search btn-6-s"><span>검색</span></button>
          </div>
          
      </div>
  </div>
</template>

<script setup>
import { ref, onMounted, defineProps, defineEmits } from 'vue'
import { useUserStore } from '@/stores/user'

const props = defineProps(['showSearchResults']);
const emit = defineEmits();

const searchUser = ref({
  key: 'none',
  word: '',
  orderBy: 'none',
  orderByDir: 'asc'
});

const store = useUserStore();

const searchUserList = async () => {
  console.log('Searching users with:', searchUser.value);
  await store.searchUserList(searchUser.value);
  // Emit the event to notify the parent component
  emit('search-results-updated', store.userList.length > 0);
};

</script>

<style scoped>

.u-search-div{
  margin: 0 5px;
  text-align: center;
}

.search-standard-u{
  border: 1px solid #C4C4C4;
  box-sizing: border-box;
  border-radius: 10px;
  padding: 5px;
  font-style: normal;
  font-weight: 400;
  font-size: 14px;
  line-height: 16px;
}

.search-standard-u:focus{
  border: 1px solid #FEE58D;
  box-sizing: border-box;
  border-radius: 10px;
  outline: 3px solid #f5eac1;
  border-radius: 10px;
}

.custom-btn-search {
width: 70px;
height: 28px;
padding: 10px 25px;
border: 2px solid #FEE58D;
font-weight: 500;
background: transparent;
cursor: pointer;
transition: all 0.3s ease;
position: relative;
display: inline-block;
justify-content: center;
}

/* 6 */
.btn-6-s {
 background: #989898;
color: white;
line-height: 30px;
padding: 0;
border: none;
border-radius: 50px;
}
.btn-6-s span {
position: relative;
display: block;
width: 100%;
height: 100%;
border-radius: 50px;
}
.btn-6-s:before,
.btn-6:after {
position: absolute;
content: "";
height: 0%;
width: 2px;
background: white;
border-radius: 50px;
}
.btn-6-s:before {
right: 0;
top: 0;
transition: all 500ms ease;
border-radius: 50px;
}
.btn-6-s:after {
left: 0;
bottom: 0;
transition: all 500ms ease;
border-radius: 50px;
}
.btn-6-s:hover{
color: black;
background: transparent;
border-radius: 50px;
}
.btn-6-s:hover:before {
transition: all 500ms ease;
height: 100%;
border-radius: 50px;
}
.btn-6-s:hover:after {
transition: all 500ms ease;
height: 100%;
border-radius: 50px;
}
.btn-6-s span:before,
.btn-6-s span:after {
position: absolute;
content: "";
background: #FEE58D;
border-radius: 50px;
}
.btn-6-s span:before {
left: 0;
top: 0;
width: 0%;
height: 2px;
transition: all 500ms ease;
border-radius: 50px;
}
.btn-6-s span:after {
right: 0;
bottom: 0;
width: 0%;
height: 2px;
transition: all 500ms ease;
border-radius: 50px;
}
.btn-6-s span:hover:before {
width: 100%;
}
.btn-6-s span:hover:after {
width: 100%;
}
</style>