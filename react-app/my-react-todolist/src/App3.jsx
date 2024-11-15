import { useState } from 'react'
import './App.css'

function App() {
  
  const [todos, setTodos] = useState([
    '吃早餐', '做運動', '寫程式', 'Debug'
  ]);

  const handleClick = (e) => {
    setTodos(todos.concat('繼續寫'));
  };
  
  return (
    <>
      <h1>My Todo List</h1>
      <div>
        <input type="text" />
        <button onClick={handleClick}>Add</button>
      </div>
      <ul>
        {todos.map((todo, index) => (<li key={index}>{todo}</li>))}
      </ul>
    </>
  )
}

export default App
