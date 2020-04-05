'use strict';

const app = new Vue({
  el: '#app',
  data: {
    todos: [
      'foo',
      'bar',
      'baz'
    ],
    newTodo: ''
  },
  methods: {
    addTodo: function() {
      const value = this.newTodo.trim();
      if (!value || this.todos.indexOf(value) >= 0) {
        return;
      }
      this.todos.push(value);
      this.newTodo = '';
    },
    removeTodo: function(todo) {
      this.todos.splice(this.todos.indexOf(todo), 1);
    }
  }
});
