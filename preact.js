'use strict';

const { Component, h, render } = window.preact;
const html = window.htm.bind(h);


/** Example classful component */
class App extends Component {
	componentDidMount() {
		this.setState({ message:'Hello!' });
	}
	render(props, state) {
		/*
		return (
			h('div', {id:'app'},
				h(Header, { message: state.message }),
				h(Main)
			)
		);
		*/
		return html`<div id="app">
			<${Header} message=${this.state.message}><//>
			<${Main}><//>
		</div>`;
	}
}

/** Components can just be pure functions */
const Header = (props) => {
	return h('header', null,
		h('h1', null, 'App'),
		props.message && h('h2', null, props.message)
	);
};


/** Instead of JSX, use: h(type, props, ...children) */
class Main extends Component {
	render() {
		const items = [1,2,3,4,5].map( (item) => (
			h('li', {id:item}, 'Item '+item)
		));
		return (
			h('main', null,
				h('ul', null, items)
			)
		);
	}
}


render(h(App), document.body);