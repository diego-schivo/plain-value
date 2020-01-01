'use strict';

const { Component, h, render } = window.preact;
const html = window.htm.bind(h);

const ENTER_KEY = 13;
const ESCAPE_KEY = 27;

const FILTERS = {
	all: todo => true,
	active: todo => !todo.completed,
	completed: todo => todo.completed
};
