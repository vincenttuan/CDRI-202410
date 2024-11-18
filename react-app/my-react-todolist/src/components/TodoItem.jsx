import React from 'react';

const TodoItem = ({ todo, onToggleCompletion, onDelete }) => {
  return (
    <li
      className={`list-group-item d-flex justify-content-between align-items-center ${
        todo.completed ? 'list-group-item-success' : ''
      }`}
    >
      {todo.id}

      <span
        style={{ textDecoration: todo.completed ? 'line-through' : 'none' }}
        onClick={() => onToggle(todo.id)}
      >
        {todo.text}
      </span>
      <div>
        <input
          className="me-2"
          type="checkbox"
          checked={todo.completed}
          onChange={() => onToggleCompletion(todo.id)}
        />
        <button className='btn btn-danger btn-sm' onClick={() => onDelete(todo.id)}>X</button>
      </div>
    </li>
  );
};

export default TodoItem;
