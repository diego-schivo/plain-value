'use strict';

class TodoModel {
	constructor(key, sub) {
		this.key = key;
		this.onChanges = [sub];
		this.store();
	}

	store() {
		var user = firebase.auth().currentUser;
		if (user) {
			firestore.collection(this.key).where("author_uid", "==", user.uid).get()
				.then(querySnapshot => {
					this.todos = querySnapshot.docs;
					this.inform();
				})
				.catch(function(error) {
					console.log("Error getting documents: ", error);
				});
		} else {
			this.todos = [];
			this.inform();
		}
	}

	inform() {
		this.onChanges.forEach( cb => cb() );
	}

	addTodo(title) {
		firestore.collection(this.key)
			.add({
				author_uid: firebase.auth().currentUser.uid,
				title,
				completed: false
			}).then(ref => ref.get())
			.then(doc => {
				this.todos = this.todos.concat(doc);
				this.inform();
			})
			.catch(function(error) {
				console.log("Error adding document: ", error);
			});
	}

	toggleAll(completed) {
		this.todos.forEach(todo => todo.ref.set({ completed }, { merge: true })
			.then(() => {
				this.todos = this.todos.slice();
				this.inform();
			}));
	}

	toggle(todoToToggle) {
		todoToToggle.ref.set({ completed: !todoToToggle.get('completed') }, { merge: true })
			.then(() => {
				this.todos = this.todos.slice();
				this.inform();
			});
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
