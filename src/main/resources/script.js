document.addEventListener('DOMContentLoaded', () => {
    const taskForm = document.getElementById('task-form');
    const taskList = document.getElementById('task-list');

    let tasks = loadTasks();

    function createButton(className, text, onClick) {
        const button = document.createElement('button');
        button.className = className;
        button.textContent = text;
        button.addEventListener('click', onClick);
        return button;
    }

    function renderTasks() {
        taskList.innerHTML = '';
        tasks.forEach((task, index) => {
            const li = document.createElement('li');
            li.className = task.completed ? 'completed' : '';
            li.innerHTML = `<span>${task.title}: ${task.description}</span>`;

            const buttonsContainer = document.createElement('div');
            buttonsContainer.appendChild(createButton('complete', task.completed ? 'Undo' : 'Complete', () => toggleCompleteTask(index)));
            buttonsContainer.appendChild(createButton('edit', 'Edit', () => editTask(index)));
            buttonsContainer.appendChild(createButton('delete', 'Delete', () => deleteTask(index)));

            li.appendChild(buttonsContainer);
            taskList.appendChild(li);
        });
    }

    function addTask(title, description) {
        tasks.push({ title, description, completed: false });
        saveTasks();
        renderTasks();
    }

    function deleteTask(index) {
        tasks.splice(index, 1);
        saveTasks();
        renderTasks();
    }

    function toggleCompleteTask(index) {
        tasks[index].completed = !tasks[index].completed;
        saveTasks();
        renderTasks();
    }

    function editTask(index) {
        const newTitle = prompt('Enter new task title:', tasks[index].title);
        const newDescription = prompt('Enter new task description:', tasks[index].description);
        if (newTitle !== null && newDescription !== null) {
            tasks[index].title = newTitle;
            tasks[index].description = newDescription;
            saveTasks();
            renderTasks();
        }
    }

    function saveTasks() {
        localStorage.setItem('tasks', JSON.stringify(tasks));
    }

    function loadTasks() {
        return JSON.parse(localStorage.getItem('tasks')) || [];
    }

    taskForm.addEventListener('submit', (e) => {
        e.preventDefault();
        const title = document.getElementById('task-title').value;
        const description = document.getElementById('task-desc').value;
        addTask(title, description);
        taskForm.reset();
    });

    renderTasks();
});
