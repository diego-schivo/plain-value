'use strict';

class TodoModel {
	constructor(key, sub) {
		this.key = key;
		this.onChanges = [sub];
		this.store();
	}

	store() {
		let user = firebase.auth().currentUser;
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
		let batch = firestore.batch();
		this.todos.forEach(todo => batch.update(todo.ref, {completed}));
		batch.commit()
			.then(() => {
				this.todos = this.todos.slice();
				this.inform();
			});
	}

	toggle(todoToToggle) {
		console.log(3, todoToToggle.get('completed'));
		// todoToToggle.ref.update('completed', !todoToToggle.get('completed'))
		todoToToggle.ref.set({ completed: !todoToToggle.get('completed') }, { merge: true })
			.then(() => {
				console.log(4, todoToToggle.get('completed'));
				this.todos = this.todos.slice();
				this.inform();
			});
	}

	destroy(todo) {
		todo.ref.delete()
			.then(() => {
				this.todos = this.todos.filter(t => (t.id !== todo.id));
				this.inform();
			});
	}

	save(todoToSave, title) {
		todoToSave.ref.update('title', title)
			.then(() => {
				this.todos = this.todos.slice();
				this.inform();
			});
	}

	clearCompleted() {
		let batch = firestore.batch();
		let todos = this.todos
			.filter(todo => {
				let completed = todo.get('completed');
				if (completed) {
					batch.delete(todo.ref);
				}
				return !completed;
			});
		batch.commit()
			.then(() => {
				this.todos = todos;
				this.inform();
			});
	}
}
