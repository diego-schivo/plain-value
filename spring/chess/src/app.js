'use strict';

import client from './client'
import CreateDialog from './create-dialog'
import follow from './follow'
import PieceList from './piece-list'
import React from 'react'
import stompClient from './websocket-listener'
import when from 'when';

const rootPath = '/api';

export default class App extends React.Component {

  constructor(props) {
    super(props);
    this.state = {
      attributes: [],
      page: 1,
      pageSize: 10,
      links: {},
      loggedInPlayer: this.props.loggedInPlayer,
      pieces: []
    };
  }

  render() {
    const createDialog =
        this.state.attributes.length
        ? (<CreateDialog attributes={this.state.attributes} onCreate={this.onCreate} />)
        : null;
    return (
        <>
          {createDialog}
          <PieceList
              attributes={this.state.attributes}
              links={this.state.links}
              loggedInPlayer={this.state.loggedInPlayer}
              onDelete={this.onDelete}
              onNavigate={this.onNavigate}
              onUpdate={this.onUpdate}
              page={this.state.page}
              pageSize={this.state.pageSize}
              pieces={this.state.pieces}
              updatePageSize={this.updatePageSize}
            />
        </>
      );
  }

  componentDidMount() {
    this.loadFromServer(this.state.pageSize);
    stompClient.register([
      {route: '/topic/createPiece', callback: this.refreshAndGoToLastPage},
      {route: '/topic/savePiece', callback: this.refreshCurrentPage},
      {route: '/topic/deletePiece', callback: this.refreshCurrentPage}
    ]);
  }

  loadFromServer(pageSize) {
    follow(client, rootPath, [{rel: 'pieces', params: {size: pageSize}}]).then(response => {
      return client({
        method: 'GET',
        path: response.entity._links.profile.href,
        headers: {'Accept': 'application/schema+json'}
      }).then(schema => {
        Object.keys(schema.entity.properties).forEach(property => {
          if (schema.entity.properties[property].hasOwnProperty('format')) {
            (schema.entity.properties[property].format === 'uri') && delete schema.entity.properties[property];
          }
          else if (schema.entity.properties[property].hasOwnProperty('$ref')) {
            delete schema.entity.properties[property];
          }
        });
        this.schema = schema.entity;
        this.links = response.entity._links;
        return response;
      });
    }).then(response => {
      this.page = response.entity.page;
      return response.entity._embedded.pieces.map(piece =>
          client({
            method: 'GET',
            path: piece._links.self.href
          })
      );
    }).then(promises => {
      return when.all(promises);
    }).then(pieces => {
      this.setState({
        page: this.page,
        pieces: pieces,
        attributes: Object.keys(this.schema.properties),
        pageSize: pageSize,
        links: this.links
      });
    });
  }

  onCreate = newPiece => {
    follow(client, rootPath, ['pieces']).then(response => {
      client({
        method: 'POST',
        path: response.entity._links.self.href,
        entity: newPiece,
        headers: {'Content-Type': 'application/json'}
      })
    });
  }

  onUpdate = (piece, updatedPiece) => {
    if (piece.entity.player.name !== this.state.loggedInPlayer) {
      alert('Unauthorized');
      return;
    }
    updatedPiece['player'] = piece.entity.player;
    client({
      method: 'PUT',
      path: piece.entity._links.self.href,
      entity: updatedPiece,
      headers: {
        'Content-Type': 'application/json',
        'If-Match': piece.headers.Etag
      }
    }).then(response => {
    }, response => {
      if (response.status.code === 403) {
        alert(`DENIED: unauthorized ${piece.entity._links.self.href}`);
      }
      if (response.status.code === 412) {
        alert(`DENIED: stale ${piece.entity._links.self.href}`);
      }
    });
  }

  onDelete = piece => {
    client({method: 'DELETE', path: piece.entity._links.self.href}).then(response => {
    }, response => {
      if (response.status.code === 403) {
        alert(`DENIED: unauthorized ${piece.entity._links.self.href}`);
      }
    });
  }

  onNavigate = navUri => {
    client({
      method: 'GET',
      path: navUri
    }).then(response => {
      this.links = response.entity._links;
      this.page = response.entity.page;
      return response.entity._embedded.pieces.map(piece =>
        client({method: 'GET', path: piece._links.self.href})
      );
    }).then(promises => {
      return when.all(promises);
    }).then(pieces => {
      this.setState({
        page: this.page,
        pieces: pieces,
        attributes: Object.keys(this.schema.properties),
        pageSize: this.state.pageSize,
        links: this.links
      });
    });
  }

  updatePageSize = pageSize => {
    if (pageSize === this.state.pageSize) {
      return;
    }
    this.loadFromServer(pageSize);
  }

  refreshAndGoToLastPage = message => {
    follow(client, rootPath, [{
      rel: 'pieces',
      params: {size: this.state.pageSize}
    }]).then(response => {
      if (response.entity._links.last !== undefined) {
        this.onNavigate(response.entity._links.last.href);
      } else {
        this.onNavigate(response.entity._links.self.href);
      }
    })
  }

  refreshCurrentPage = message => {
    follow(client, rootPath, [{
      rel: 'pieces',
      params: {
        size: this.state.pageSize,
        page: this.state.page.number
      }
    }]).then(response => {
      this.links = response.entity._links;
      this.page = response.entity.page;
      return response.entity._embedded.pieces.map(piece => {
        return client({
          method: 'GET',
          path: piece._links.self.href
        })
      });
    }).then(promises => {
      return when.all(promises);
    }).then(pieces => {
      this.setState({
        page: this.page,
        pieces: pieces,
        attributes: Object.keys(this.schema.properties),
        pageSize: this.state.pageSize,
        links: this.links
      });
    });
  }
}