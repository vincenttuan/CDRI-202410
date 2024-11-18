import { useEffect, useState } from 'react';
import './App.css';
import TodoList from './components/TodoList';
import TodoInput from './components/TodoInput';
import 'bootstrap/dist/css/bootstrap.min.css'; // 引入 Bootstrap 樣式
import { fetchTodos, addTodo, updateTodo, deleteTodo } from './services/todoService';

function App() {
  const [todos, setTodos] = useState([]);
  const [todo, setTodo] = useState('');

  useEffect(() => {
    console.log('抓取 todo list 資料');
    // 獲取所有待辦事項
    fetchTodos()
      .then(setTodos)
      .catch((error) => console.error('error:', error));
  }, []);

  // 新增待辦事項
  const handleAdd = async () => {
    if (!todo) return;
    const newTodo = { text: todo, completed: false };

    try {
      const addedTodo = await addTodo(newTodo);
      setTodos([...todos, addedTodo])
      setTodo('');
    } catch (error) {
      console.error('Error add todo:', error);
    }
    
  };

  const handleChange = (e) => {
    setTodo(e.target.value);
  };

  // 更新待辦事項
  const toggleCompletion = async (id) => {
    try {
      const updatedTodo = todos.find((todo) => todo.id === id);
      if (!updatedTodo) return;
      
      updatedTodo.completed = !updatedTodo.completed;
      await updateTodo(updatedTodo);
      setTodos([...todos]);
    } catch (error) {
      console.error('Error updating todo:', error);
    }
  };

  // 刪除待辦事項
  const handleDelete = (id) => {
    deleteTodo(id)
      .then(() => setTodos(todos.filter((todo) => todo.id !== id)))
      .catch((error) => console.error('error:', error));
  };

  return (
    <div className='container mt-5'>
      <h1 className='text-center mb-4'>My Todo List</h1>
      <TodoInput todo={todo} onChange={handleChange} onAdd={handleAdd} />
      <TodoList todos={todos} onToggleCompletion={toggleCompletion} onDelete={handleDelete} />
    </div>
  );
}

export default App;
