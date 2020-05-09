'use strict';

import React from 'react'

export default class CreateDialog extends React.Component {

  constructor(props) {
    super(props);
    props.attributes.forEach(attribute => {
      this[attribute] = React.createRef();
    });
  }

  render() {
    const inputs = this.props.attributes.map(attribute =>
      <p key={attribute}>
        <input ref={this[attribute]} className="field" placeholder={attribute} />
      </p>
    );
    return (
      <>
        <a href="#createPiece">Create</a>
        <div id="createPiece" className="modalDialog">
          <div>
            <a href="#" title="Close" className="close">X</a>
            <h2>Create new piece</h2>
            <form>
              {inputs}
              <button onClick={this.handleSubmit}>Create</button>
            </form>
          </div>
        </div>
      </>
    );
  }

  handleSubmit = e => {
    e.preventDefault();
    const newPiece = this.props.attributes.reduce((piece, attribute) => {
      piece[attribute] = this[attribute].current.value.trim();
      return piece;
    }, {});
    this.props.onCreate(newPiece);
    this.props.attributes.forEach(attribute => {
      this[attribute].current.value = '';
    });
    window.location = "#";
  }
}