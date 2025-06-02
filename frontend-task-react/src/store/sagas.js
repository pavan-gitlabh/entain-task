import { put, takeEvery } from "redux-saga/effects";
import { FETCH_TASKS, setTasks } from "./actions";

function* fetchTasksSaga() {
  const data = localStorage.getItem("tasks");
  const tasks = data ? JSON.parse(data) : [];
  yield put(setTasks(tasks));
}

export default function* rootSaga() {
  yield takeEvery(FETCH_TASKS, fetchTasksSaga);
}
