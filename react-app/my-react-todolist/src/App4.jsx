import { useState } from 'react'
import './App.css'

function App() {
  
  const [todos, setTodos] = useState([
    {id: 1, text: '吃早餐', completed: true},
    {id: 2, text: '做運動', completed: false},
    {id: 3, text: '寫程式', completed: true},
    {id: 4, text: 'Debug', completed: false},    
  ]);

  const [todo, setTodo] = useState('');

  const handleClick = (e) => {
    if(!todo) return;
    // 取得 id 值 = 目前最大 id + 1
    const newId = todos.length > 0 ? Math.max(...todos.map((t) => t.id)) + 1 : 1;

    const newTodo = {
      id: newId, text: todo, completed: false
    };

    setTodos([...todos, newTodo]);
    setTodo(''); // 將 todo 清空
  };

  const handleChange = (e) => {
    setTodo(e.target.value);
  };
  
  const toggleCompletion = (id) => {
      setTodos(
        todos.map((todo) => todo.id === id ? {...todo, completed: !todo.completed} : todo)
      );      
  };

  return (
    <>
      <h1>My Todo List</h1>
      <div>
        <input type="text" onChange={handleChange} value={todo} />
        <button onClick={handleClick}>Add</button>
      </div>
      <ul>
        {todos.map((todo) => (
          <li key={todo.id}>
            {todo.id}
            <input type="checkbox" onChange={() => toggleCompletion(todo.id)} checked={todo.completed} />
            {todo.text}
          </li>
          ))}
      </ul>
    </>
  )
}

export default App
