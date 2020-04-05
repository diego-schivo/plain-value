(function() {
  'use strict';

  const add = document.querySelector('#todo-add');
  const list = document.querySelector('#todo-list');

  const addItem = todo => {
    const template = document.querySelector('#todo-template');
    const item = template.content.querySelector('.todo-item').cloneNode(true);
    const text = document.createTextNode(todo);

    const remove = item.querySelector('.todo-remove');
    item.insertBefore(text, remove);

    remove.addEventListener('click', () => {
      const index = todos.indexOf(todo);
      todos.splice(index, 1);
      removeItem(index);
    });

    list.appendChild(item);
  }

  const removeItem = index => {
    const item = list.querySelectorAll('.todo-item').item(index);
    list.removeChild(item);
  }

  add.addEventListener('keyup', (event) => {
    if (event.key !== 'Enter') {
      return;
    }
    const todo = add.value.trim();
    if (!todo || todos.indexOf(todo) >= 0) {
      return;
    }
    todos.push(todo);
    add.value = '';
    addItem(todo);
  });

  const todos = [
    'foo',
    'bar',
    'baz'
  ];

  todos.forEach(current => {
    addItem(current);
  });
})();
