import React from "react";

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

export default MyInput;
