// 獲取 DOM 元素 - 查詢房間
const roomList = document.getElementById('roomList');
// 獲取 DOM 元素 - 新增房間
const roomIdInput = document.getElementById('roomId');
const roomNameInput = document.getElementById('roomName');
const roomSizeInput = document.getElementById('roomSize');
const addResultText = document.getElementById('addResult');

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

// 新增房間
const addRoom = async () => {
	const roomId = roomIdInput.value;
	const roomName = roomNameInput.value;
	const roomSize = roomSizeInput.value;
	
	// 檢查資料
	if(!roomId || !roomName || !roomSize) {
		addResultText.textContent = '請輸入 id, name 與 size';
		return;
	}
	
	// 遠端新增程序
	try {
		// 將資料轉 json 物件
		const roomDto = {
			roomId: roomId,
			roomName: roomName,
			roomSize: roomSize
		};
		
		const response = await fetch('http://localhost:8081/rest/room', {
			method: 'POST',
			headers: {
				'Content-Type': 'application/json'
			},
			body: JSON.stringify(roomDto) // 轉 json string 後送出
		});
		
		const apiResponse = await response.json();
		addResultText.textContent = apiResponse.message;
		
		if(response.ok) {
			// 重新查詢房間列表資料
			fetchRooms(); 
			// 清空新增房間的表單欄位
			roomIdInput.value = '';
			roomNameInput.value = '';
			roomSizeInput.value = '';	
		}
		
	} catch(e) {
		console.error('遠端資料存取錯誤:', e);
	}
		
	
};



// 執行 fetchRooms()
fetchRooms();














