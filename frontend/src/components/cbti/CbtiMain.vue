<template>
    <div id="content">
        <div class="content-main-img">
            <img src="../../assets/images/CBTI/CAMEO_default.png" alt="캐릭터">
        </div>
        <div class="content-desc-box">
            <span class="content-text">
                <p>나의 메뉴 취향과 카페 취향을 알아보세요!</p>
                <span class="content-text-left">
                    C- 5미의 특색, 커피가 좋아<br>
                    N- 커피보단 다른 게 좋아 <br>
                    <hr class="content-text-hr">
                    H- 몸도 마음도 따뜻~하게 뜨거운 음료가 좋아<br>
                    C- 시원하게 들이킬 수 있는 시원한 음료가 좋아 <br>
                    <hr class="content-text-hr">
                    T- 나는야 맛슐랭! 음식을 파는 데 맛이 중요하지<br>
                    V- 분위기에 취한다 인생샷 각! 뷰가 중요하지 <br>
                    <hr class="content-text-hr">
                    S- 달달하고 상큼한 맛은 기분을 몽글몽글하게 해<br>
                    U- 할매입맛이라니? 부담없이 즐기는 무던함이지 <br>
                </span>
                <span class="mt-3">
                    나는 과연 어떤 타입인지 귀여운 메오와 추천 메뉴를 확인해보세요~😉
                </span>
                <span class="content-text-sm">
                    (본 테스트에는 과학적 근거가 없으며 조금 다르더라도 재미로 즐겨주세요👉👈)
                </span>
            </span>
        </div>
        <div class="content-btn-box mt-4 my-4">
            <button class="btn btn-goCbti" @click="goQuestion">시작하기</button>
        </div>
    </div>
</template>

<script>
export default {
    name: 'CbtiMain',
    methods: {
        goQuestion() {
            this.$emit("menuCheck", "qna");
        }
    },
    data() {
        return {
        }
    },
}
</script>

<style scoped>
@import url("https://fonts.googleapis.com/css2?family=Fredoka+One&family=Lobster&family=Poor+Story&family=Jua&family=Noto+Sans+KR:wght@300;400&display=swap");
* {
    font-family: "Noto Sans KR";
    font-weight: 400;
}
a {
	text-decoration: none;
	color: black;
}
ul {
	padding: 0;
	margin: 0;
}
li {
	list-style: none;
}
input {
    border: 0;
    background: none;
}
input:focus {
    outline: none;
}
/* button {
    border: none;
    background: none;
} */
#content {
    display: flex;
    flex-direction: column;
    flex-wrap: nowrap;
    align-content: center;
    justify-content: center;
    align-items: center;
    width: 80%;
}
.content-main-img {
    width: 50%;
    height: 450px;
    background-color: #f1f1f1;
    border: 1px solid #ececec;
    border-radius: 10px;
    margin: 0 auto;
    
}
.content-main-img > img {
    width: 100%;
    height: 100%;
}
.content-desc-box {
    min-height: 200px;
    margin-top: 40px;
}
.content-text {
    font-size: 1.5em;
    text-align: center;
    display: flex;
    flex-direction: column;
    flex-wrap: nowrap;
    align-content: center;
    align-items: center;
    justify-content: center;
}
.content-text p:first-child {
    font-size: 1.1em;
}
.content-text-left {
    font-family: 'Poor Story';
    text-align: left;
}
.content-text-hr {
    margin: 2px 0;
}
.content-text-sm {
    font-size: 0.7em;
}
.btn-goCbti {
    width: 200px;
    height: 50px;
    border: 1px solid #365959;
    color: #365959;
    font-size: 1.1em;
}
.btn-goCbti:hover {
    background-color: #365959;
    color: white;
}
/* 모바일 버전 */
@media (max-width: 479px) {
    .content-main-img {
        width: 80%;
        height: 220px;
    }
    .content-text {
        font-size: 1.0em;
    }
    .content-text p:first-child {
        font-size: 1.0em;
    }
}
</style>