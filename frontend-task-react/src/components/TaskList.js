import React from "react";

export default function TaskList({ tasks, onDelete, onToggle }) {
  return (
    <div className="space-y-2">
      {tasks.map(task => {
        const bgColor =
          task.priority === 'high'
            ? 'bg-red-100'
            : task.priority === 'medium'
            ? 'bg-yellow-100'
            : 'bg-green-100';

        return (
          <div key={task.id} className={`p-4 border rounded ${bgColor}`}>
            <h3 className="text-lg font-bold">{task.title}</h3>
            <p>{task.description}</p>
            <p className="text-sm">Priority: {task.priority} | Status: {task.status}</p>
            <button onClick={() => onToggle(task.id)} className="mr-2 text-green-600">Toggle</button>
            <button onClick={() => onDelete(task.id)} className="text-red-600">Delete</button>
          </div>
        );
      })}
    </div>
  );
}
