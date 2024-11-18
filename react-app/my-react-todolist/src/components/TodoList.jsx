import React from 'react';
import TodoItem from './TodoItem';

const TodoList = ({ todos, onToggleCompletion, onDelete }) => {
  return (
    <ul className="list-group">
      {todos.map((todo) => (
        <TodoItem
          key={todo.id}
          todo={todo}
          onToggleCompletion={onToggleCompletion}
          onDelete={onDelete}
        />
      ))}
    </ul>
  );
};

export default TodoList;
