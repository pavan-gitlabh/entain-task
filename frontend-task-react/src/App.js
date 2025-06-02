import React, { useEffect } from "react";
import { useDispatch, useSelector } from "react-redux";
import { addTask, deleteTask, toggleTaskStatus, fetchTasks } from "./store/actions";
import TaskForm from "./components/TaskForm";
import TaskList from "./components/TaskList";
import FilterBar from "./components/FilterBar";

export default function App() {
  const dispatch = useDispatch();
  const { tasks, statusFilter, priorityFilter } = useSelector(state => state.tasks);

  useEffect(() => {
    dispatch(fetchTasks());
  }, [dispatch]);

  const filteredTasks = tasks.filter(task => {
    return (statusFilter === "all" || task.status === statusFilter) &&
           (priorityFilter === "all" || task.priority === priorityFilter);
  });

  return (
    <div className="p-4 max-w-3xl mx-auto">
      <header className="flex justify-between items-center mb-4">
        <h1 className="text-2xl font-bold">Task Dashboard</h1>
      </header>
      <TaskForm onAdd={task => dispatch(addTask(task))} />
      <FilterBar />
      <TaskList
        tasks={filteredTasks}
        onDelete={id => dispatch(deleteTask(id))}
        onToggle={id => dispatch(toggleTaskStatus(id))}
      />
    </div>
  );
}
