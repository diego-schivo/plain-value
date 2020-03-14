package it.plainvalue.datatypes.impl;

import it.plainvalue.datatypes.Item;

public class ItemImpl implements Item {

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
