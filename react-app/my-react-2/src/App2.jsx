import { useState } from 'react';
import './App.css'

function App() {
  const [message, setMessage] = useState(''); // '' 表示 message 的初始值

  const handleChange = (e) => {
    setMessage(e.target.value); // 透過 setMessage 變更 message 變數內容後網頁會自動渲染更新
    console.log('message:', message);
  };

  return (
    <>
      <div>
        <input type="text" placeholder='請輸入一些內容' onChange={handleChange} />
      </div>
      <div>
        顯示: {message}
      </div>
    </>
  )
}

export default App
