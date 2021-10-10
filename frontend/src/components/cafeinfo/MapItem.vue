<template>
	<div id="mapRoot">
		<div id="map"></div>
		<div class="btn-wrap">
			<button id="myLocationBtn" @click="myLocation" class="my-location-btn shadow-sm"><i class="location-icon fas fa-map-marker-alt"></i></button>
		</div>
		<div class="modal-wrap" v-if="this.modalActive">
			<info-modal
				:mapData="mapData"
				:modalActive="modalActive"
				@modalfalse="modalstatus"
			/>
		</div>
	</div>
</template>

<script>
import API_KEY from "../../util/ApiKey.js";
import InfoModal from '@/components/cafeinfo/InfoModal.vue'

export default {
	name: "MapItem",
	components: {
		InfoModal,
	},
	watch:	{
		mapData :function(v){
			this.initMap();
		}
	},
	methods: {
		modalstatus(e){
			this.modalActive = e
		},
		initMap() {
			var mapContainer = document.getElementById('map'), // 지도를 표시할 div 
					mapOption = {
						center: new kakao.maps.LatLng(35.1595454, 126.8526012), // 지도의 중심좌표
						level: 3 // 지도의 확대 레벨
					};  
			// 지도를 생성합니다    
			var map = new kakao.maps.Map(mapContainer, mapOption); 
			// 주소-좌표 변환 객체를 생성합니다
			var geocoder = new kakao.maps.services.Geocoder();
			// 주소로 좌표를 검색합니다
			geocoder.addressSearch(this.mapData, function(result, status) {
			// 정상적으로 검색이 완료됐으면 
			if (status === kakao.maps.services.Status.OK) {
					var coords = new kakao.maps.LatLng(result[0].y, result[0].x);
					// 결과값으로 받은 위치를 마커로 표시합니다
					var marker = new kakao.maps.Marker({
							map: map,
							position: coords
					});
					// 인포윈도우로 장소에 대한 설명을 표시합니다
					var infowindow = new kakao.maps.InfoWindow({
							// content: '<div style="width:150px;text-align:center;padding:6px 0;"></div>'
					});
					// infowindow.open(map, marker);
					marker.setMap(map);
					kakao.maps.event.addListener(marker, 'click', function() {
						// 마커 위에 인포윈도우를 표시합니다
						this.modalActive = !this.modalActive
						console.log(this.modalActive)
					});
					// 지도의 중심을 결과값으로 받은 위치로 이동시킵니다
					map.setCenter(coords);
				} 
			});    
		},
			
		addScript() {
			const script = document.createElement("script");
			var mykey = API_KEY;
			script.onload = () => kakao.maps.load(this.initMap);
			script.src = "//dapi.kakao.com/v2/maps/sdk.js?autoload=false&appkey=" + mykey +"&libraries=services,clusterer,drawing";
			// console.log(mykey);
			document.head.appendChild(script);
		},
    //현재 내위치
		myLocation() {
			// HTML5의 geolocation으로 사용할 수 있는지 확인합니다 
			if (navigator.geolocation) {
				console.log("this is myLocation : ");
				console.log(this);
			const vm = this;
			//GeoLocation을 이용해서 접속 위치를 얻어옵니다
			navigator.geolocation.getCurrentPosition(function(position) {
			var lat = position.coords.latitude, // 위도
					lon = position.coords.longitude; // 경도
			console.log(lat + ", " + lon);
			var locPosition = new kakao.maps.LatLng(lat, lon), // 마커가 표시될 위치를 geolocation으로 얻어온 좌표로 생성합니다
					message = '<div style="padding:5px;">여기에 계신가요?!</div>'; // 인포윈도우에 표시될 내용입니다
							
			// 마커와 인포윈도우를 표시합니다
			// console.log("this is getGurrentPosition : " + this);
			vm.displayMarker(locPosition, message);					
			});
			} else { // HTML5의 GeoLocation을 사용할 수 없을때 마커 표시 위치와 인포윈도우 내용을 설정합니다
				var locPosition = new kakao.maps.LatLng(33.450701, 126.570667),    
						message = 'geolocation을 사용할수 없어요..'
				this.displayMarker(locPosition, message);
			}
			},
		// 지도에 마커와 인포윈도우를 표시하는 함수입니다
		displayMarker(locPosition, message) {
			// 마커를 생성합니다
			var marker = new kakao.maps.Marker({  
					map: this.map, 
					position: locPosition
			}); 
			var iwContent = message, // 인포윈도우에 표시할 내용
					iwRemoveable = true;
			// 인포윈도우를 생성합니다
			var infowindow = new kakao.maps.InfoWindow({
					content : iwContent,
					removable : iwRemoveable
			});
			// 인포윈도우를 마커위에 표시합니다 
			infowindow.open(this.map, marker);
			// 지도 중심좌표를 접속위치로 변경합니다
			this.map.setCenter(locPosition);      
		},
	},

	mounted() {
		// this.initMap();
		window.kakao && window.kakao.maps ? this.initMap() : this.addScript();
	},
	data() {
		return {
			map: {},
			modalActive: false,
		};
	},
	props :{
		mapData : String,
	}
};
</script>

<style scoped>
#mapRoot {
	width: 100%;
	height: 100%;
}
#map {
	width: 100%;
	height: 100%;
}
.menu-box {
	z-index: 10;
	width: 400px;
	height: 100%;
	position: absolute;
	top: 0;
	left: 0;
	background-color: rgba(255, 255, 255, 0.8);
}
.btn-wrap {
    position: absolute;
    bottom: 40px;
    right: 40px;
    z-index: 10;
}
.my-location-btn {
    width: 40px;
    height: 40px;
    border-radius: 50px;
    border: none;
    background-color: rgba(255, 255, 255, 0.8);
}
.location-icon {
    color: #365959;
    font-size: 1.2em;
}
.modal-wrap {
	position: fixed;
	top: 50%;
	left: 50%;
	transform: translate(-20%, -40%);
	z-index: 10;
}
/* 카카오 샘플 */
#menu_wrap {
	position: absolute;
	top: 0;
	left: 0;
	bottom: 0;
	width: 250px;
	margin: 10px 0 30px 10px;
	padding: 5px;
	overflow-y: auto;
	background: rgba(255, 255, 255, 0.7);
	z-index: 1;
	font-size: 12px;
	border-radius: 10px;
}
.bg_white {
	background: #fff;
}
#menu_wrap hr {
	display: block;
	height: 1px;
	border: 0;
	border-top: 2px solid #5f5f5f;
	margin: 3px 0;
}
#menu_wrap .option {
	text-align: center;
    position: relative;
    top: 200px;
}
#menu_wrap .option p {
	margin: 10px 0;
}
#menu_wrap .option button {
	margin-left: 5px;
}
#placesList li {
	list-style: none;
}
#placesList .item {
	position: relative;
	border-bottom: 1px solid #888;
	overflow: hidden;
	cursor: pointer;
	min-height: 65px;
}
#placesList .item span {
	display: block;
	margin-top: 4px;
}
#placesList .item h5,
#placesList .item .info {
	text-overflow: ellipsis;
	overflow: hidden;
	white-space: nowrap;
}
#placesList .item .info {
	padding: 10px 0 10px 55px;
}
#placesList .info .gray {
	color: #8a8a8a;
}
#placesList .info .jibun {
	padding-left: 26px;
	background: url(https://t1.daumcdn.net/localimg/localimages/07/mapapidoc/places_jibun.png)
		no-repeat;
}
#placesList .info .tel {
	color: #009900;
}
#placesList .item .markerbg {
	float: left;
	position: absolute;
	width: 36px;
	height: 37px;
	margin: 10px 0 0 10px;
	background: url(https://t1.daumcdn.net/localimg/localimages/07/mapapidoc/marker_number_blue.png)
		no-repeat;
}
#placesList .item .marker_1 {
	background-position: 0 -10px;
}
#placesList .item .marker_2 {
	background-position: 0 -56px;
}
#placesList .item .marker_3 {
	background-position: 0 -102px;
}
#placesList .item .marker_4 {
	background-position: 0 -148px;
}
#placesList .item .marker_5 {
	background-position: 0 -194px;
}
#placesList .item .marker_6 {
	background-position: 0 -240px;
}
#placesList .item .marker_7 {
	background-position: 0 -286px;
}
#placesList .item .marker_8 {
	background-position: 0 -332px;
}
#placesList .item .marker_9 {
	background-position: 0 -378px;
}
#placesList .item .marker_10 {
	background-position: 0 -423px;
}
#placesList .item .marker_11 {
	background-position: 0 -470px;
}
#placesList .item .marker_12 {
	background-position: 0 -516px;
}
#placesList .item .marker_13 {
	background-position: 0 -562px;
}
#placesList .item .marker_14 {
	background-position: 0 -608px;
}
#placesList .item .marker_15 {
	background-position: 0 -654px;
}
#pagination {
	margin: 10px auto;
	text-align: center;
}
#pagination a {
	display: inline-block;
	margin-right: 10px;
}
#pagination .on {
	font-weight: bold;
	cursor: default;
	color: #777;
}

/* 모바일 */
@media (max-width: 479px) {
	.modal-wrap {
		width: 300px;
		transform: translate(-50%, -40%);
	}
}
</style>
