'use strict'

class Model {
	
  constructor() {
    this.tasks = [
      'foo',
      'bar',
      'baz'
    ]
  }
	
  create(task, callback) {
    if (!task || task.trim() === '') {
      callback && callback(false)
    }
    const index = this.tasks.indexOf(task)
    if (index >= 0) {
      callback && callback(false)
    }
    this.tasks.push(task)
    callback && callback(true)
  }
	
  read(callback) {
    const tasksCopy = [...this.tasks]
    callback && callback(tasksCopy)
  }
	
  delete(task, callback) {
    const index = this.tasks.indexOf(task)
    if (index < 0) {
      callback && callback(false)
    }
    this.tasks.splice(index, 1)
    callback && callback(true)
  }
}
	
class View {
	
  constructor(element) {
    this.addTaskField = element.querySelector('.add-task-field')
    this.taskList = element.querySelector('.task-list')
    this.taskTemplate = element.querySelector('.task-template')
	
    this.commands = {
	
      fillTaskList: tasks => {
        this.taskList.innerHTML = ''
        tasks.forEach(task => {
          this.commands.addTaskToList(task)
        })
      },
  
      addTaskToList: task => {
        const taskEl = this.taskTemplate.content.querySelector('.task').cloneNode(true)
        taskEl.querySelector('.task-name').textContent = task
        this.taskList.appendChild(taskEl)
      },
  
      removeTaskFromList: task => {
        const tasks = this.taskList.querySelectorAll('.task')
        const taskEl = Array.prototype.find.call(tasks, element => {
          return element.querySelector('.task-name').textContent === task
        })
        this.taskList.removeChild(taskEl)
      },
  
      clearAddTaskField: task => {
        this.addTaskField.value = ''
      }
    }
	
    this.events = {
	
      addTask: handler => {
        this.addTaskField.addEventListener('change', () => {
          handler(this.addTaskField.value)
        })
      },
	
      removeTask: handler => {
        this.taskList.addEventListener('click', event => {
          const buttons = this.taskList.querySelectorAll('.remove-task-button')
          if (Array.prototype.indexOf.call(buttons, event.target) < 0) {
            return
          }
          handler(event.target.closest('.task').querySelector('.task-name').textContent)
        })
      }
    }
  }
	
  render(command, parameter) {
    this.commands[command](parameter);
  }
	
  bind(event, handler) {
    this.events[event](handler);
  }
}
	
class Controller {
	
  constructor(model, view) {
    this.model = model
    this.view = view
	
    this.model.read(tasks => this.view.render('fillTaskList', tasks))
	
    this.view.bind('addTask', task => this.addTask(task))
    this.view.bind('removeTask', task => this.removeTask(task))
  }
	
  addTask(task) {
    this.model.create(task, success => {
      if (!success) {
        return
      }
      this.view.render('clearAddTaskField')
      this.view.render('addTaskToList', task)
    })
  }
	
  removeTask(task) {
    this.model.delete(task, success => {
      if (!success) {
        return
      }
      this.view.render('removeTaskFromList', task)
    })
  }
}
	
class TodoList {
	
  constructor(element) {
    this.model = new Model()
    this.view = new View(element)
    this.controller = new Controller(this.model, this.view)
  }
}

window.addEventListener('load', () => {
  document.querySelectorAll('.todo-list').forEach(element => {
    new TodoList(element)
  })
})
