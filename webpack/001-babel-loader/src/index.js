import _ from 'lodash';

const element = document.createElement('p');
element.innerHTML = _.join(['Hello,', 'World!'], ' ');
document.body.appendChild(element);

Promise.resolve().finally();
