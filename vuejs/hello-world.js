'use strict';

const app = new Vue({
  el: '#app',
  data: {
    message: 'Hello, World!'
  },
  methods: {
    changeMessage: function() {
      this.message = 'Hi there and greetings!';
    }
  }
});
