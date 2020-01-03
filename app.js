'use strict';

const { Component, h, render } = window.preact;
const html = window.htm.bind(h);

const ENTER_KEY = 13;
const ESCAPE_KEY = 27;

const FILTERS = {
	all: todo => true,
	active: todo => !todo.get('completed'),
	completed: todo => todo.get('completed')
};

class App extends Component {
	constructor() {
		super();
		this.model = new TodoModel('preact-todos', () => this.setState({}) );

		this.handleRoute = this.handleRoute.bind(this);
		this.handleNewTodoKeyDown = this.handleNewTodoKeyDown.bind(this);
		this.handleNewTodoInput = this.handleNewTodoInput.bind(this);
		this.toggleAll = this.toggleAll.bind(this);
		this.toggle = this.toggle.bind(this);
		this.destroy = this.destroy.bind(this);
		this.edit = this.edit.bind(this);
		this.save = this.save.bind(this);
		this.cancel = this.cancel.bind(this);
		this.clearCompleted = this.clearCompleted.bind(this);

		addEventListener('hashchange', this.handleRoute);
		this.handleRoute();

		firebase.auth().onAuthStateChanged((user) => this.model.store() );
	}

	handleRoute() {
		let nowShowing = String(location.hash||'').split('/').pop();
		if (!FILTERS[nowShowing]) {
			nowShowing = 'all';
		}
		this.setState({ nowShowing });
	}

	handleNewTodoKeyDown(e) {
		if (e.keyCode!==ENTER_KEY) return;
		e.preventDefault();

		let val = this.state.newTodo.trim();
		if (val) {
			this.model.addTodo(val);
			this.setState({ newTodo: '' });
		}
	}

	handleNewTodoInput(e) {
		this.setState({ newTodo: e.target.value });
	}

	toggleAll(event) {
		let checked = event.target.checked;
		this.model.toggleAll(checked);
	}

	toggle(todo) {
		this.model.toggle(todo);
	}

	destroy(todo) {
		this.model.destroy(todo);
	}

	edit(todo) {
		this.setState({ editing: todo.id });
	}

	save(todoToSave, text) {
		this.model.save(todoToSave, text);
		this.setState({ editing: null });
	}

	cancel() {
		this.setState({ editing: null });
	}

	clearCompleted() {
		this.model.clearCompleted();
	}

	render({ }, { nowShowing=ALL_TODOS, newTodo, editing }) {
		let todos = this.model.todos || [],
			shownTodos = todos.filter( FILTERS[nowShowing] ),
			activeTodoCount = todos.reduce( (a, todo) => a + (todo.get('completed') ? 0 : 1), 0),
			completedCount = todos.length - activeTodoCount;

		return html`
			<div>
				<header class="header">
					<h1>todos</h1>
					<input
						class="new-todo"
						placeholder="What needs to be done?"
						value=${newTodo}
						onKeyDown=${this.handleNewTodoKeyDown}
						onInput=${this.handleNewTodoInput}
						autoFocus=${true}
					/>
				</header>

				${ todos.length ? html`
					<section class="main">
						<input
							id="toggle-all"
							class="toggle-all"
							type="checkbox"
							onChange=${this.toggleAll}
							checked=${activeTodoCount === 0}
						/>
						<label for="toggle-all">Mark all as complete</label>
						<ul class="todo-list">
							${ shownTodos.map( todo => html`
								<${TodoItem}
									todo=${todo.data()}
									onToggle=${this.toggle}
									onDestroy=${this.destroy}
									onEdit=${this.edit}
									editing=${editing === todo.id}
									onSave=${this.save}
									onCancel=${this.cancel}
								><//>
							`) }
						</ul>
					</section>
				` : null }

				${ (activeTodoCount || completedCount) ? html`
					<${TodoFooter}
						count=${activeTodoCount}
						completedCount=${completedCount}
						nowShowing=${nowShowing}
						onClearCompleted=${this.clearCompleted}
					><//>
				` : null }
			</div>
		`;
	}
}
