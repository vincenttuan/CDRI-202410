import React from 'react';

const TodoItem = ({ todo, onToggleCompletion }) => {
  return (
    <li>
      {todo.id}
      <input
        type="checkbox"
        checked={todo.completed}
        onChange={() => onToggleCompletion(todo.id)}
      />
      {todo.text}
    </li>
  );
};

export default TodoItem;
