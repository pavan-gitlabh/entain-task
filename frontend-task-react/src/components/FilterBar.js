import React from "react";
import { useDispatch } from "react-redux";
import { setStatusFilter, setPriorityFilter } from "../store/actions";

export default function FilterBar() {
  const dispatch = useDispatch();

  return (
    <div className="flex space-x-4 mb-4">
      <select onChange={e => dispatch(setStatusFilter(e.target.value))} className="p-2 border rounded">
        <option value="all">All</option>
        <option value="active">Active</option>
        <option value="completed">Completed</option>
      </select>
      <select onChange={e => dispatch(setPriorityFilter(e.target.value))} className="p-2 border rounded">
        <option value="all">All</option>
        <option value="high">High</option>
        <option value="medium">Medium</option>
        <option value="low">Low</option>
      </select>
    </div>
  );
}
