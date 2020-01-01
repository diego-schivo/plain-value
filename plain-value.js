'use strict';

const { Component, h, render } = window.preact;
const html = window.htm.bind(h);


class App extends Component {
	componentDidMount() {
		this.setState({ message:'Hello!' });
		console.log(1, this);

		var docRef = firestore.collection("users").doc("iN50PPzc8PkmAkWSG8X4");
		docRef.get().then(function(doc) {
			 console.log(2, this);
			 if (doc.exists) {
				  console.log("Document data:", doc.data());
			 } else {
				  // doc.data() will be undefined in this case
				  console.log("No such document!");
			 }
		}).catch(function(error) {
			 console.log("Error getting document:", error);
		});
	}
	render(props, state) {
		return html`
			<div id="app">
				<${Header} message=${state.message}><//>
				<${Main}><//>
			</div>
		`;
	}
}

const Header = (props) => {
	return html`
		<header>
			<h1>App</h1>
			<h2>${props.message}</h2>
		</header>
	`;
};


class Main extends Component {
	render() {
		const items = [1,2,3,4,5].map( (item) => (
			// h('li', {id:item}, 'Item '+item)
			html`<li id="${item}">Item ${item}</li>`
		));
		return html`
			<main>
				${html`<ul>${items}</ul>`}
			</main>
		`;
	}
}


render(h(App), document.body);