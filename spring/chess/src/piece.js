import React from 'react'
import UpdateDialog from './update-dialog'

export default class Piece extends React.Component {

  constructor(props) {
    super(props);
  }

  handleDelete = () => {
    this.props.onDelete(this.props.piece);
  }

  render() {
    return (
      <tr>
        <td>{this.props.piece.entity.name}</td>
        <td>{this.props.piece.entity.player.name}</td>
        <td>
          <UpdateDialog
              attributes={this.props.attributes}
              loggedInPlayer={this.props.loggedInPlayer}
              onUpdate={this.props.onUpdate}
              piece={this.props.piece}
            />
        </td>
        <td>
          <button onClick={this.handleDelete}>Delete</button>
        </td>
      </tr>
    );
  }
}