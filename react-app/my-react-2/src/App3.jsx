import { useState } from 'react';
import './App.css'

const MyInput = () => {
  return (
    <div>
      <input type="text" placeholder='請輸入一些內容' />
    </div>
  );
};

const MyDisplay = () => {
  return (
    <div>
      顯示:
    </div> 
  );
};


function App() {
  const [message, setMessage] = useState(''); // '' 表示 message 的初始值

  const handleChange = (e) => {
    setMessage(e.target.value); // 透過 setMessage 變更 message 變數內容後網頁會自動渲染更新
    console.log('message:', message);
  };

  return (
    <>
      <MyInput />
      <MyDisplay />
    </>
  )
}

export default App
