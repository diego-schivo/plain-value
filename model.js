'use strict';

class TodoModel {
	constructor(key, sub) {
		this.key = key;
		firestore.collection(key).get()
			.then(querySnapshot => {
				this.todos = querySnapshot.docs;
			})
			.catch(function(error) {
				console.log("Error getting documents: ", error);
			});
		this.onChanges = [sub];
	}

	inform() {
		this.onChanges.forEach( cb => cb() );
	}

	addTodo(title) {
		firestore.collection(this.key)
			.add({
				title,
				completed: false
			}).then(doc => {
				this.todos = this.todos.concat(doc);
			})
			.catch(function(error) {
				console.log("Error adding document: ", error);
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
