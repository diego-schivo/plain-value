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
		return html`
			<div id="app">
				<${Header} message=${state.message}><//>
				<${Main}><//>
			</div>
		`;
	}
}

/** Components can just be pure functions */
const Header = (props) => {
	/*
	return h('header', null,
		h('h1', null, 'App'),
		props.message && h('h2', null, props.message)
	);
	*/
	return html`
		<header>
			<h1>App</h1>
			<h2>${props.message}</h2>
		</header>
	`;
};


/** Instead of JSX, use: h(type, props, ...children) */
class Main extends Component {
	render() {
		const items = [1,2,3,4,5].map( (item) => (
			// h('li', {id:item}, 'Item '+item)
			html`<li id="${item}">Item ${item}>/li>`
		));
		/*
		return (
			h('main', null,
				h('ul', null, items)
			)
		);
		*/
		return html`
			<main>
				${html`<ul>${items}</ul>`}
			</main>
		`;
	}
}


render(h(App), document.body);