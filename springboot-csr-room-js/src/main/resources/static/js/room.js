// 獲取 DOM 元素 - 查詢房間
const roomList = document.getElementById('roomList');
// 獲取 DOM 元素 - 新增房間
const roomIdInput = document.getElementById('roomId');
const roomNameInput = document.getElementById('roomName');
const roomSizeInput = document.getElementById('roomSize');
const addResultText = document.getElementById('addResult');
// 獲取 DOM 元素 - 修改房間(Modal)
const editModal = document.getElementById('editModal');
const editRoomIdInput = document.getElementById('editRoomId');
const editRoomNameInput = document.getElementById('editRoomName');
const editRoomSizeInput = document.getElementById('editRoomSize');


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
	
	roomList.innerHTML = ''; // 清空 roomList 資料
	
	rooms.forEach(room => {
		const listItem = document.createElement('li'); // 建立 <li> 標籤元素
		listItem.textContent = `房號: ${room.roomId} 房名: ${room.roomName} - 人數: ${room.roomSize} `;
		
		// 在 listItem 中多加入刪除元素(按鈕)
		const deleteButton = document.createElement('button');
		deleteButton.textContent = '刪除';
		deleteButton.onclick = () => deleteRoom(room.roomId);
		listItem.appendChild(deleteButton);
		
		// 在 listItem 中多加入修改元素(按鈕)
		const updateButton = document.createElement('button');
		updateButton.textContent = '修改';
		updateButton.onclick = () => openModal(room.roomId, room.roomName, room.roomSize);
		listItem.appendChild(updateButton), 
				
		// 將 listItem 加入到 roomList 中
		roomList.appendChild(listItem);
	});
};

// 打開 Modal 小視窗
const openModal = (roomId, roomName, roomSize) => {
	editRoomIdInput.value = roomId;
	editRoomNameInput.value = roomName;
	editRoomSizeInput.value = roomSize;
	editModal.style.display = 'flex';
};

// 關閉 Modal 小視窗
const closeModal = () => {
	editModal.style.display = 'none';
};

// 修改確認
const confirmEdit = async () => {
	try {
		// 將資料轉換為 json 物件
		const roomDto = {
			roomName: editRoomNameInput.value,
			roomSize: editRoomSizeInput.value
		};
		
		const roomId = editRoomIdInput.value;
		const response = await fetch(`http://localhost:8081/rest/room/${roomId}`, {
			method: 'PUT',
			headers: {
				'Content-Type': 'application/json'
			},
			body: JSON.stringify(roomDto) // 轉 json string 後送出
			
		});
		
		const apiResponse = await response.json();
		console.log('apiResponse:', JSON.stringify(apiResponse));
		
		if(response.ok) {
			fetchRooms(); // 修改成功之後重新加載房間列表
		} else {
			addResultText.textContent = apiResponse.message;
		}
	} catch(e) {
		addResultText.textContent = e;
	} finally {
		// 關閉 Modal
		closeModal();
	}
	
};

// 刪除房間
const deleteRoom = async (roomId) => {
	try {
		const response = await fetch(`http://localhost:8081/rest/room/${roomId}`, {
			method: 'DELETE'
		});
		
		const apiResponse = await response.json();
		console.log('apiResponse:', JSON.stringify(apiResponse));
		
		if(response.ok) {
			fetchRooms(); // 刪除成功之後重新加載房間列表
		} else {
			addResultText.textContent = apiResponse.message;
		}
	} catch(e) {
		addResultText.textContent = e;
	}	
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
		addResultText.textContent = e;
	}
		
	
};



// 執行 fetchRooms()
fetchRooms();














