'use strict';

const e = React.createElement;

class MyComponent extends React.Component {
  constructor(props) {
    super(props);
    this.state = { message: 'Hello, World!' };
  }

  render() {
    if (this.state.clicked) {
      return 'Hi there and greetings!';
    }

    return [
      e('p',
        null,
        this.state.message),
      e('button',
        { onClick: () => this.setState({ message: 'Hi there and greetings!' }) },
        'Change message')
    ];
  }
}

const myContainer = document.querySelector('#my-container');
ReactDOM.render(e(MyComponent), myContainer);
