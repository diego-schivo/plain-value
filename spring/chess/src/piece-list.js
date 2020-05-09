import Piece from './piece'
import React from 'react'

export default class PieceList extends React.Component {

  constructor(props) {
    super(props);
    this.pageSize = React.createRef();
  }

  handleInput = e => {
    e.preventDefault();
    const pageSize = this.pageSize.current.value;
    if (/^[0-9]+$/.test(pageSize)) {
      this.props.updatePageSize(pageSize);
    } else {
      this.pageSize.current.value = pageSize.substring(0, pageSize.length - 1);
    }
  }

  handleNavFirst = e => {
    e.preventDefault();
    this.props.onNavigate(this.props.links.first.href);
  }

  handleNavPrev = e => {
    e.preventDefault();
    this.props.onNavigate(this.props.links.prev.href);
  }

  handleNavNext = e => {
    e.preventDefault();
    this.props.onNavigate(this.props.links.next.href);
  }

  handleNavLast = e => {
    e.preventDefault();
    this.props.onNavigate(this.props.links.last.href);
  }

  render() {
    const pageInfo = this.props.page.hasOwnProperty('number') ?
      <h3>Pieces - page {this.props.page.number + 1} of {this.props.page.totalPages}</h3> : null;

    const pieces = this.props.pieces.map(piece =>
      <Piece
          attributes={this.props.attributes}
          key={piece.entity._links.self.href}
          loggedInPlayer={this.props.loggedInPlayer}
          onDelete={this.props.onDelete}
          onUpdate={this.props.onUpdate}
          piece={piece}
        />
    );

    const navLinks = [];
    if ('first' in this.props.links) {
      navLinks.push(<button key="first" onClick={this.handleNavFirst}>&lt;&lt;</button>);
    }
    if ('prev' in this.props.links) {
      navLinks.push(<button key="prev" onClick={this.handleNavPrev}>&lt;</button>);
    }
    if ('next' in this.props.links) {
      navLinks.push(<button key="next" onClick={this.handleNavNext}>&gt;</button>);
    }
    if ('last' in this.props.links) {
      navLinks.push(<button key="last" onClick={this.handleNavLast}>&gt;&gt;</button>);
    }

    return (
      <div>
        {pageInfo}
        <input ref={this.pageSize} defaultValue={this.props.pageSize} onInput={this.handleInput}/>
        <table>
          <tbody>
            <tr>
              <th>Name</th>
              <th>Player</th>
              <th></th>
              <th></th>
            </tr>
            {pieces}
          </tbody>
        </table>
        <div>
          {navLinks}
        </div>
      </div>
    );
  }
}