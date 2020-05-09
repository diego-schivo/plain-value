import App from './app'
import React from 'react'
import ReactDOM from 'react-dom'

ReactDOM.render(
  <React.StrictMode>
    <App loggedInPlayer={document.getElementById('player-name').innerHTML} />
  </React.StrictMode>,
  document.getElementById('root')
);
