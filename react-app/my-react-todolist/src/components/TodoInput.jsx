import React from "react";

const TodoInput = ({todo, onChange, onAdd}) => {

    return (
        <div>
            <input type="text" onChange={onChange} value={todo} />
            <button onClick={onAdd}>Add</button>
        </div>
    )

}

export default TodoInput;