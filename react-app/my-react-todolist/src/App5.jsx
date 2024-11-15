import { useState } from 'react';
import './App.css';
import TodoList from './components/TodoList';

function App() {
  const [todos, setTodos] = useState([
    { id: 1, text: '吃早餐', completed: true },
    { id: 2, text: '做運動', completed: true },
    { id: 3, text: '寫程式', completed: true },
    { id: 4, text: 'Debug', completed: false },
  ]);

  const [todo, setTodo] = useState('');

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
    <div>
      <h1>My Todo List</h1>
      <div>
        <input type="text" onChange={handleChange} value={todo} />
        <button onClick={handleAdd}>Add</button>
      </div>
      <TodoList todos={todos} onToggleCompletion={toggleCompletion} />
    </div>
  );
}

export default App;
