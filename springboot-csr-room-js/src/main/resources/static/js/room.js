// 獲取 DOM 元素
const roomList = document.getElementById('roomList');

// 透過 fetch 經由 http://localhost:8081/rest/room 取得遠端資料
const fetchRooms = async () => {
	try {
		const response = await fetch('http://localhost:8081/rest/room');
		const apiResponse = await response.json();
		console.log(apiResponse);
	} catch(e) {
		console.error('遠端資料存取錯誤:', e);	
	}
};


// 執行 fetchRooms()
fetchRooms();