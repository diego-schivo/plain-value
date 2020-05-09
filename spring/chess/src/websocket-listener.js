'use strict';

import SockJS from 'sockjs-client';
import 'stompjs';

export default {
  register: registrations => {
    const socket = SockJS('/chess');
    const stompClient = Stomp.over(socket);
    stompClient.connect({}, () => {
      registrations.forEach(registration => {
        stompClient.subscribe(registration.route, registration.callback);
      });
    });
  }
}
