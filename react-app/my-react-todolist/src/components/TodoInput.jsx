import React from "react";

const TodoInput = ({todo, onChange, onAdd}) => {

    return (
        <div className="input-group mb-3">
            <input className="form-control" type="text" onChange={onChange} value={todo} />
            <button className="btn btn-primary" onClick={onAdd}>Add</button>
        </div>
    )

}

export default TodoInput;