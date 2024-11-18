import { useEffect, useState } from 'react';
import './App.css';
import TodoList from './components/TodoList';
import TodoInput from './components/TodoInput';
import 'bootstrap/dist/css/bootstrap.min.css'; // 引入 Bootstrap 樣式

function App() {
  const [todos, setTodos] = useState([
    { id: 1, text: '吃早餐', completed: true },
    { id: 2, text: '做運動', completed: true },
    { id: 3, text: '寫程式', completed: true },
    { id: 4, text: 'Debug', completed: false },
  ]);

  
  const [todo, setTodo] = useState('');

  useEffect(() => {
    console.log('抓取 todo list 資料');
    
  }, []);

  const handleAdd = () => {
    if (!todo) return;
    const newId = todos.length > 0 ? Math.max(...todos.map((t) => t.id)) + 1 : 1;
    const newTodo = { id: newId, text: todo, completed: false };
    setTodos([...todos, newTodo]);
    setTodo('');
  };

  const handleChange = (e) => {
    setTodo(e.target.value);
  };

  const toggleCompletion = (id) => {
    setTodos(
      todos.map((todo) =>
        todo.id === id ? { ...todo, completed: !todo.completed } : todo
      )
    );
  };

  return (
    <div className='container mt-5'>
      <h1 className='text-center mb-4'>My Todo List</h1>
      <TodoInput todo={todo} onChange={handleChange} onAdd={handleAdd} />
      <TodoList todos={todos} onToggleCompletion={toggleCompletion} />
    </div>
  );
}

export default App;
