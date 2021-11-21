/*
 * Copyright  2018 Linkel Technology Co., Ltd, Beijing
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BA SIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */
import { GET_LIST, GET_MANY, GET_MANY_REFERENCE, DELETE } from 'react-admin';
import { QUERY_TYPES } from 'ra-data-graphql';
import { TypeKind } from 'graphql';

import { encodeQuery, encodeMutation } from './utils/graphqlify';
import getFinalType from './utils/getFinalType';
import isList from './utils/isList';
import isRequired from './utils/isRequired';

export const buildFields = introspectionResults => fields => {
  return fields.reduce((acc, field) => {
    const type = getFinalType(field.type);

    if (type.name.startsWith('_')) {
      return acc;
    }

    if (type.kind !== TypeKind.OBJECT) {
      return { ...acc, [field.name]: {} };
    }

    const linkedResource = introspectionResults.resources.find(r => r.type.name === type.name);

    if (linkedResource) {
      return { ...acc, [field.name]: { fields: { id: {} } } };
    }

    const linkedType = introspectionResults.types.find(t => t.name === type.name);

    if (linkedType) {
      return {
        ...acc,
        [field.name]: {
          fields: buildFields(introspectionResults)(linkedType.fields)
        }
      };
    }

    // NOTE: We might have to handle linked types which are not resources but will have to be careful about
    // ending with endless circular dependencies
    return acc;
  }, {});
};

export const getArgType = arg => {
  const type = getFinalType(arg.type);
  const required = isRequired(arg.type);
  const list = isList(arg.type);

  return `${list ? '[' : ''}${type.name}${list ? '!]' : ''}${required ? '!' : ''}`;
};

export const buildArgs = (query, variables) => {
  if (query.args.length === 0) {
    return {};
  }

  const validVariables = Object.keys(variables).filter(k => typeof variables[k] !== 'undefined');
  let args = query.args
    .filter(a => validVariables.includes(a.name))
    .reduce((acc, arg) => ({ ...acc, [`${arg.name}`]: `$${arg.name}` }), {});

  return args;
};

export const buildApolloArgs = (query, variables) => {
  if (query.args.length === 0) {
    return {};
  }

  const validVariables = Object.keys(variables).filter(k => typeof variables[k] !== 'undefined');

  let args = query.args.filter(a => validVariables.includes(a.name)).reduce((acc, arg) => {
    return { ...acc, [`$${arg.name}`]: getArgType(arg) };
  }, {});

  return args;
};

export default introspectionResults => (resource, aorFetchType, queryType, variables) => {
  const apolloArgs = buildApolloArgs(queryType, variables);
  const args = buildArgs(queryType, variables);
  const fields = buildFields(introspectionResults)(resource.type.fields);

  if (
    aorFetchType === GET_LIST ||
    aorFetchType === GET_MANY ||
    aorFetchType === GET_MANY_REFERENCE
  ) {
    const result = encodeQuery(queryType.name, {
      params: apolloArgs,
      fields: {
        items: {
          field: queryType.name,
          params: args,
          fields
        },
        total: {
          field: `${queryType.name}Connection`,
          params: {...args, first: undefined, skip: undefined},
          fields: {
            aggregate: {
              fields: { count: {} }
            }
          }
        }
      }
    });

    return result;
  }

  if (aorFetchType === DELETE) {
    return encodeMutation(queryType.name, {
      params: apolloArgs,
      fields: {
        data: {
          field: queryType.name,
          params: args,
          fields: { id: {} }
        }
      }
    });
  }

  const query = {
    params: apolloArgs,
    fields: {
      data: {
        field: queryType.name,
        params: args,
        fields
      }
    }
  };

  return QUERY_TYPES.includes(aorFetchType)
    ? encodeQuery(queryType.name, query)
    : encodeMutation(queryType.name, query);
};
