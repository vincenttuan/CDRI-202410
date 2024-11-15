import './App.css'

// var: 作用範圍 function scope, 可以多次重複宣告
// let: 作用範圍 block scope, 同一個 scope 不可重複宣告
// const: 作用範圍 block scope, 不可重複宣告
function App() {
  let message = '';

  const handleChange = (e) => {
    message = e.target.value; // 變數改變, 但是畫面不會渲染更新
    console.log('message:', message);

    // 直接用 DOM 操作來更新內容, 如此渲染更新
    document.getElementById('displayMessage').textContent = `顯示: ${message}`;
  };

  return (
    <>
      <div>
        <input type="text" placeholder='請輸入一些內容' onChange={handleChange} />
      </div>
      <div id="displayMessage">
        顯示: {message}
      </div>
    </>
  )
}

export default App
