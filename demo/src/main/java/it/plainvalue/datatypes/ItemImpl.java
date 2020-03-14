package it.plainvalue.datatypes;

class ItemImpl implements Item {

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
