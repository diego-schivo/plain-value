const unique = require('uniq');

const data = [1, 2, 2, 3, 4, 5, 5, 5, 6];

const element = document.createElement('p');
element.innerHTML = 'Hello, World! ' + JSON.stringify(unique(data));
document.body.appendChild(element);
