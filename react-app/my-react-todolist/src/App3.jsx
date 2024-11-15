import { useState } from 'react'
import './App.css'

function App() {
  
  const [todos, setTodos] = useState([
    '吃早餐', '做運動', '寫程式', 'Debug'
  ]);

  const [todo, setTodo] = useState('');

  const handleClick = (e) => {
    if(!todo) return;
    //setTodos(todos.concat(todo));
    setTodos([...todos, todo]);
    setTodo(''); // 將 todo 清空
  };

  const handleChange = (e) => {
    setTodo(e.target.value);
  };
  
  return (
    <>
      <h1>My Todo List</h1>
      <div>
        <input type="text" onChange={handleChange} value={todo} />
        <button onClick={handleClick}>Add</button>
      </div>
      <ul>
        {todos.map((todo, index) => (<li key={index}>{todo}</li>))}
      </ul>
    </>
  )
}

export default App
