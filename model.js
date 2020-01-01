'use strict';

class TodoModel {
	constructor(key, sub) {
		this.key = key;
		this.todos = store(key) || [];
		this.onChanges = [sub];

		firestore.collection(key).get()
			.then(querySnapshot => {
				querySnapshot.forEach(doc => {
					console.log(doc.id, " => ", doc.data());
				});
			})
			.catch(function(error) {
				console.log("Error getting documents: ", error);
			});
	}

	inform() {
		store(this.key, this.todos);
		this.onChanges.forEach( cb => cb() );
	}

	addTodo(title) {
		this.todos = this.todos.concat({
			id: uuid(),
			title,
			completed: false
		});
		this.inform();
	}

	toggleAll(completed) {
		this.todos = this.todos.map(
			todo => ({ ...todo, completed })
		);
		this.inform();
	}

	toggle(todoToToggle) {
		this.todos = this.todos.map( todo => (
			todo !== todoToToggle ? todo : ({ ...todo, completed: !todo.completed })
		) );
		this.inform();
	}

	destroy(todo) {
		this.todos = this.todos.filter( t => t !== todo );
		this.inform();
	}

	save(todoToSave, title) {
		this.todos = this.todos.map( todo => (
			todo !== todoToSave ? todo : ({ ...todo, title })
		));
		this.inform();
	}

	clearCompleted() {
		this.todos = this.todos.filter( todo => !todo.completed );
		this.inform();
	}
}
