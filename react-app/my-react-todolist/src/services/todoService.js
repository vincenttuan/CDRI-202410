/**
 * WEB API TodoList Rest CRUD
 * ------------------------------------------------------------
 * GET    "http://localhost:8080/todolist/"     獲取所有待辦事項
 * POST   "http://localhost:8080/todolist/"     新增待辦事項
 * PUT    "http://localhost:8080/todolist/{id}" 更新待辦事項
 * DELETE "http://localhost:8080/todolist/{id}" 刪除待辦事項
 * ------------------------------------------------------------
 * */
const BASE_URL = 'http://localhost:8080/todolist';

// 獲取所有待辦事項
export const fetchTodos = async() => {
    const response = await fetch(BASE_URL);
    const result = await response.json();
    if (result.status === 200) {
        return result.data; // 返回資料
    }
    throw new Error(result.message);
};

// 新增待辦事項
export const addTodo = async(todo) => {
    const response = await fetch(BASE_URL, {
        method: 'POST',
        headers: {
            'Content-type': 'application/json'
        },
        body: JSON.stringify(todo),
    });
    const result = await response.json();
    if (result.status === 200) {
        return result.data; // 返回資料 json 給 then(json) 接收
    }
    throw new Error(result.message);
};

// 更新待辦事項
export const updateTodo = async(updateTodo) => {
    const response = await fetch(`${BASE_URL}/${updateTodo.id}`, {
        method: 'PUT',
        headers: {
            'Content-type': 'application/json'
        },
        body: JSON.stringify(updateTodo),
    });
    const result = await response.json();
    if (result.status === 200) {
        return result.data; // 返回資料
    }
    throw new Error(result.message);
};

// 刪除待辦事項
export const deleteTodo = async(id) => {
    const response = await fetch(`${BASE_URL}/${id}`, {
        method: 'DELETE',
    });
    const result = await response.json();
    if (result.status === 200) {
        return true; // 返回 true
    }
    throw new Error(result.message);
};