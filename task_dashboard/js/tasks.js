function renderTasks() {
    const list = document.getElementById("task-list");
    const statusFilter = document.getElementById("statusFilter").value;
    const priorityFilter = document.getElementById("priorityFilter").value;
    const tasks = getTasks().filter(task => {
        return (statusFilter === "all" || task.status === statusFilter) &&
               (priorityFilter === "all" || task.priority === priorityFilter);
    });

    list.innerHTML = "";
    tasks.forEach(task => {
        const div = document.createElement("div");
        div.className = `task ${task.priority}`;
        div.innerHTML = `
            <h3>${task.title}</h3>
            <p>${task.description}</p>
            <p>Priority: ${task.priority} | Status: ${task.status}</p>
            <button onclick="toggleTaskStatus('${task.id}')">✓</button>
            <button onclick="deleteTask('${task.id}')">🗑️</button>
        `;
        list.appendChild(div);
    });
}

function addTask(title, description, priority) {
    const tasks = getTasks();
    const newTask = {
        id: Date.now().toString(),
        title,
        description,
        priority,
        status: "active",
        createdAt: new Date().toISOString(),
        updatedAt: new Date().toISOString()
    };
    tasks.push(newTask);
    saveTasks(tasks);
    renderTasks();
}

function toggleTaskStatus(id) {
    const tasks = getTasks();
    const task = tasks.find(t => t.id === id);
    if (task) {
        task.status = task.status === "active" ? "completed" : "active";
        task.updatedAt = new Date().toISOString();
        saveTasks(tasks);
        renderTasks();
    }
}

function deleteTask(id) {
    let tasks = getTasks();
    tasks = tasks.filter(t => t.id !== id);
    saveTasks(tasks);
    renderTasks();
}
