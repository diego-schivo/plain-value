'use strict';

import defaultRequest from 'rest/interceptor/defaultRequest';
import errorCode from 'rest/interceptor/errorCode';
import hal from 'rest/mime/type/application/hal';
import mime from 'rest/interceptor/mime';
import registry from 'rest/mime/registry';
import rest from 'rest';
import template from 'rest/interceptor/template';
import uriListConverter from './uriListConverter';

const childRegistry = registry.child();

childRegistry.register('text/uri-list', uriListConverter);
childRegistry.register('application/hal+json', hal);

export default rest
  .wrap(mime, { registry: childRegistry })
  .wrap(template)
  .wrap(errorCode)
  .wrap(defaultRequest, { headers: { 'Accept': 'application/hal+json' }});
