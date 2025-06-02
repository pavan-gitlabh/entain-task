export const ADD_TASK = "ADD_TASK";
export const DELETE_TASK = "DELETE_TASK";
export const TOGGLE_TASK = "TOGGLE_TASK";
export const SET_STATUS_FILTER = "SET_STATUS_FILTER";
export const SET_PRIORITY_FILTER = "SET_PRIORITY_FILTER";
export const FETCH_TASKS = "FETCH_TASKS";
export const SET_TASKS = "SET_TASKS";

export const addTask = task => ({ type: ADD_TASK, payload: task });
export const deleteTask = id => ({ type: DELETE_TASK, payload: id });
export const toggleTaskStatus = id => ({ type: TOGGLE_TASK, payload: id });
export const setStatusFilter = status => ({ type: SET_STATUS_FILTER, payload: status });
export const setPriorityFilter = priority => ({ type: SET_PRIORITY_FILTER, payload: priority });
export const fetchTasks = () => ({ type: FETCH_TASKS });
export const setTasks = tasks => ({ type: SET_TASKS, payload: tasks });
