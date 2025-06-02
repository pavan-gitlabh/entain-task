import {
  ADD_TASK, DELETE_TASK, TOGGLE_TASK, SET_STATUS_FILTER, SET_PRIORITY_FILTER, SET_TASKS
} from "./actions";

const initialState = {
  tasks: [],
  statusFilter: "all",
  priorityFilter: "all"
};

export default function tasksReducer(state = initialState, action) {
  switch (action.type) {
    case SET_TASKS:
      return { ...state, tasks: action.payload };
    case ADD_TASK:
      const newTask = {
        ...action.payload,
        id: Date.now().toString(),
        status: "active",
        createdAt: new Date().toISOString(),
        updatedAt: new Date().toISOString()
      };
      return { ...state, tasks: [...state.tasks, newTask] };
    case DELETE_TASK:
      return { ...state, tasks: state.tasks.filter(t => t.id !== action.payload) };
    case TOGGLE_TASK:
      return {
        ...state,
        tasks: state.tasks.map(t =>
          t.id === action.payload ? { ...t, status: t.status === "active" ? "completed" : "active" } : t
        )
      };
    case SET_STATUS_FILTER:
      return { ...state, statusFilter: action.payload };
    case SET_PRIORITY_FILTER:
      return { ...state, priorityFilter: action.payload };
    default:
      return state;
  }
}
