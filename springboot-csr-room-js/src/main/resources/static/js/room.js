// 獲取 DOM 元素
const roomList = document.getElementById('roomList');

// 透過 fetch 經由 http://localhost:8081/rest/room 取得遠端資料
const fetchRooms = async () => {
	try {
		const response = await fetch('http://localhost:8081/rest/room');
		const apiResponse = await response.json();
		console.log(apiResponse);
		displayRooms(apiResponse.data);
	} catch(e) {
		console.error('遠端資料存取錯誤:', e);	
	}
};

// 顯示房間列表
const displayRooms = (rooms) => {
	
	rooms.forEach(room => {
		const listItem = document.createElement('li'); // 建立 <li> 標籤元素
		listItem.textContent = `房號: ${room.roomId} 房名: ${room.roomName} - 人數: ${room.roomSize}`;
		// 將 listItem 加入到 roomList 中
		roomList.appendChild(listItem);
	});
};


// 執行 fetchRooms()
fetchRooms();