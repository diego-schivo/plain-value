import React from 'react'

export default class UpdateDialog extends React.Component {

  constructor(props) {
    super(props);
    props.attributes.forEach(attribute => {
      this[attribute] = React.createRef();
    });
  }

  handleSubmit = e => {
    e.preventDefault();
    const updatedPiece = {};
    this.props.attributes.forEach(attribute => {
      updatedPiece[attribute] = this[attribute].current.value.trim();
    });
    this.props.onUpdate(this.props.piece, updatedPiece);
    window.location = '#';
  }

  render() {
    const dialogId = `updatePiece-${this.props.piece.entity._links.self.href}`;
    if (this.props.piece.entity.player.name !== this.props.loggedInPlayer) {
      return (
          <div>
            <a>Not your piece</a>
          </div>
        );
    }
    const inputs = this.props.attributes.map(attribute =>
        <p key={this.props.piece.entity[attribute]}>
          <input type="text" placeholder={attribute}
              defaultValue={this.props.piece.entity[attribute]}
              ref={this[attribute]} className="field"/>
        </p>
      );
    return (
        <div>
          <a href={'#' + dialogId}>Update</a>
          <div id={dialogId} className="modalDialog">
            <div>
              <a href="#" title="Close" className="close">X</a>
              <h2>Update a piece</h2>
              <form>
                {inputs}
                <button onClick={this.handleSubmit}>Update</button>
              </form>
            </div>
          </div>
        </div>
      );
  }

}