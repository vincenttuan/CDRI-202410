import { useState } from 'react';
import './App.css'
import MyInput from './components/MyInput';
import MyDisplay from './components/MyDisplay';

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
