"use strict";

require("core-js/modules/es7.promise.finally");

const element = document.createElement('p');
element.innerHTML = 'Hello, World!';
document.body.appendChild(element);
Promise.resolve().finally();