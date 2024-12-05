let webSocket;
const url = 'ws://localhost:8080/channel/chat';
const connectButton = document.getElementById('connectButton');
const closeButton = document.getElementById('closeButton');
const messageInput = document.getElementById('messageInput');
const sendButton = document.getElementById('sendButton');
const targetInput = document.getElementById('targetInput');
const sendTargetButton = document.getElementById('sendTargetButton');
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
		targetInput.disabled = false;
		sendTargetButton.disabled = false;
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
		targetInput.disabled = true;
		sendTargetButton.disabled = true;	
	};
	
	webSocket.onerror = (event) => {
		addLog(`WebSocket 發生錯誤: ${event}`)	
	};			
};

// 指定對象-發送按鈕
sendTargetButton.onclick = () => {
	const message = messageInput.value;
	const target = targetInput.value; // sessionId
	if(!message) {
		addLog('請輸入訊息');
	}
	if(!target) {
		addLog('請輸入對象的 session id');
	}
	
	// 構建 JSON 格式的消息
    const payload = {
        type: "message", // 消息類型
        target: target,   // 接收目標: "all" 或指定 sessionId
        message: message // 消息內容
    };
	
	const jsonString = JSON.stringify(payload);
	// 傳送訊息
	webSocket.send(jsonString);
	
	addLog(`發送訊息: ${message} 私訊: ${target}`)
	// 清空輸入框
	messageInput.value = "";
};

// 廣播-發送按鈕
sendButton.onclick = () => {
	const message = messageInput.value;
	if(!message) {
		addLog('請輸入訊息');
	}
	
	// 構建 JSON 格式的消息
    const payload = {
        type: "message", // 消息類型
        target: "all",   // 接收目標: "all" 或指定 sessionId
        message: message // 消息內容
    };
	
	const jsonString = JSON.stringify(payload);
	// 傳送訊息
	webSocket.send(jsonString);
	
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














