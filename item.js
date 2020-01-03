'use strict';

class TodoItem extends Component {
	constructor() {
		super();

		this.handleSubmit = this.handleSubmit.bind(this);
		this.handleEdit = this.handleEdit.bind(this);
		this.toggle = this.toggle.bind(this);
		this.handleTextInput = this.handleTextInput.bind(this);
		this.handleKeyDown = this.handleKeyDown.bind(this);
		this.handleDestroy = this.handleDestroy.bind(this);
	}

	handleSubmit() {
		let { onSave, onDestroy, todo } = this.props,
			val = this.state.editText.trim();
		if (val) {
			onSave(todo, val);
			this.setState({ editText: val });
		}
		else {
			onDestroy(todo);
		}
	}

	handleEdit() {
		let { onEdit, todo } = this.props;
		onEdit(todo);
		this.setState({ editText: todo.title });
	}

	toggle(e) {
		let { onToggle, todo } = this.props;
		onToggle(todo);
		e.preventDefault();
	}

	handleTextInput(e) {
		this.setState({ editText: e.target.value });
	}

	handleKeyDown(e) {
		if (e.which===ESCAPE_KEY) {
			let { todo } = this.props;
			this.setState({ editText: todo.title });
			this.props.onCancel(todo);
		}
		else if (e.which===ENTER_KEY) {
			this.handleSubmit();
		}
	}
	
	handleDestroy() {
		this.props.onDestroy(this.props.todo);
	}

	componentDidUpdate() {
		let node = this.base && this.base.querySelector('.edit');
		if (node) node.focus();
	}

	render({ todo.data():{ title, completed }, onToggle, onDestroy, editing }, { editText }) {
		return html`
			<li class=${{ completed, editing }}>
				<div class="view">
					<input
						class="toggle"
						type="checkbox"
						checked=${completed}
						onChange=${this.toggle}
					/>
					<label onDblClick=${this.handleEdit}>${title}</label>
					<button class="destroy" onClick=${this.handleDestroy} />
				</div>
				${ editing && html`
					<input
						class="edit"
						value=${editText}
						onBlur=${this.handleSubmit}
						onInput=${this.handleTextInput}
						onKeyDown=${this.handleKeyDown}
					/>
				` }
			</li>
		`;
	}
}
