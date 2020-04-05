const element = document.createElement('p');
element.innerHTML = 'Hello, World!';
document.body.appendChild(element);

Promise.resolve().finally();
