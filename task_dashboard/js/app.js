document.addEventListener("DOMContentLoaded", () => {
    const taskForm = document.getElementById("taskForm");
    const statusFilter = document.getElementById("statusFilter");
    const priorityFilter = document.getElementById("priorityFilter");
    const darkModeToggle = document.getElementById("darkModeToggle");

    renderTasks();

    taskForm.addEventListener("submit", (e) => {
        e.preventDefault();
        const title = document.getElementById("title").value;
        const description = document.getElementById("description").value;
        const priority = document.getElementById("priority").value;
        if (title && description && priority) {
            addTask(title, description, priority);
            taskForm.reset();
        }
    });

    statusFilter.addEventListener("change", renderTasks);
    priorityFilter.addEventListener("change", renderTasks);

    darkModeToggle.addEventListener("click", () => {
        document.body.classList.toggle("dark-mode");
    });
});
