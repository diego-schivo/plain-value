package it.plainvalue.datatypes;

class Todo {

	interface ModifiableItem extends Item {

		void setValue(Object value);
	}

	static class ItemImpl implements ModifiableItem {

		Object value;

		@Override
		public Object getValue() {
			return value;
		}

		@Override
		public void setValue(Object value) {
			this.value = value;
		}
	}
}
