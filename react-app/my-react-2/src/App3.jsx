import { useState } from 'react';
import './App.css'

const MyInput = ({setMessage}) => {
  
  const handleChange = (e) => {
    setMessage(e.target.value); // 透過 setMessage 變更 message 變數內容後網頁會自動渲染更新
    console.log('e.target.value:', e.target.value);
  };

  return (
    <div>
      <input type="text" placeholder='請輸入一些內容' onChange={handleChange} />
    </div>
  );
};

const MyDisplay = ({message}) => {
  return (
    <div>
      顯示: {message}
    </div> 
  );
};

function App() {
  const [message, setMessage] = useState(''); // '' 表示 message 的初始值

  return (
    <>
      <MyInput setMessage={setMessage} />
      <MyDisplay message={message} />
    </>
  )
}

export default App
