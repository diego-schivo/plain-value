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
					this.todos = querySnapshot.docs.map(doc => { ...doc.data(), ref: doc.ref });
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
		let data =
			{
				author_uid: firebase.auth().currentUser.uid,
				title,
				completed: false
			};
		firestore.collection(this.key)
			.add(data)
			.then(ref => {
				this.todos = this.todos.concat({ ...data, ref });
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
		todoToToggle.ref.update('completed', !todoToToggle.completed)
			.then(() => {
				this.todos = this.todos
					.map(todo => (
						(todo !== todoToToggle) ? todo : ({ ...todo, completed: !todo.completed })
					));
				this.inform();
			});
	}

	destroy(todoToDestroy) {
		todoToDestroy.ref.delete()
			.then(() => {
				this.todos = this.todos
					.filter(todo => (todo !== todoToDestroy));
				this.inform();
			});
	}

	save(todoToSave, title) {
		todoToSave.ref.update('title', title)
			.then(() => {
				this.todos = this.todos
					.map(todo => (
						(todo !== todoToSave) ? todo : ({ ...todo, title })
					));
				this.inform();
			});
	}

	clearCompleted() {
		let batch = firestore.batch();
		let todos = this.todos
			.filter(todo => {
				if (todo.completed) {
					batch.delete(todo.ref);
				}
				return !todo.completed;
			});
		batch.commit()
			.then(() => {
				this.todos = todos;
				this.inform();
			});
	}
}
