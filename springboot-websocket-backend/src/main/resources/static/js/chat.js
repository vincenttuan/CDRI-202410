let webSocket;
const url = 'ws://localhost:8080/channel/chat';
const connectButton = document.getElementById('connectButton');
const closeButton = document.getElementById('closeButton');
const messageInput = document.getElementById('messageInput');
const sendButton = document.getElementById('sendButton');
const log = document.getElementById('log');

// 更新對話紀錄
const addLog = (message) => {
	log.textContent += `${message}\n`; 
};

// 連接按鈕
connectButton.onclick = () => {
	webSocket = new WebSocket(url);
	
	webSocket.onopen = () => {
		addLog("WebSocket 連接成功");
		sendButton.disabled = false;
		closeButton.disabled = false;
		connectButton.disabled = true;
		messageInput.disabled = false;
	};
	
	webSocket.onmessage = (event) => {
		addLog(`收到消息: ${event.data}`);	
	};
	
	webSocket.onclose = (event) => {
		addLog(`WebSocket 已斷開: code=${event.code} reason=${event.reason}`);
		sendButton.disabled = true;
		closeButton.disabled = true;
		connectButton.disabled = false;
		messageInput.disabled = true;		
	};
	
	webSocket.onerror = (event) => {
		addLog(`WebSocket 發生錯誤: ${event}`)	
	};			
};

// 發送按鈕
sendButton.onclick = () => {
	const message = messageInput.value;
	if(!message) {
		addLog('請輸入訊息');
	}
	// 傳送訊息
	webSocket.send(message);
	addLog(`發送訊息: ${message}`)
	// 清空輸入框
	messageInput.value = "";
};

// 斷開按鈕
closeButton.onclick = () => {
	if(webSocket) { // 是否在 open 狀態
		webSocket.close();
		addLog('主動關閉 WebSocket 連接');
	}
};














