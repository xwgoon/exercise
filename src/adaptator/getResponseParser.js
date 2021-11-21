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
import { TypeKind } from 'graphql';
import { GET_LIST, GET_MANY, GET_MANY_REFERENCE } from 'react-admin';
import getFinalType from './utils/getFinalType';

const sanitizeResource = (introspectionResults, resource) => data => {
  return Object.keys(data).reduce((acc, key) => {
    if (key.startsWith('_')) {
      return acc;
    }

    const field = resource.type.fields.find(f => f.name === key);
    const type = getFinalType(field.type);

    if (type.kind !== TypeKind.OBJECT) {
      return { ...acc, [field.name]: data[field.name] };
    }

    // FIXME: We might have to handle linked types which are not resources but will have to be careful about endless circular dependencies
    const linkedResource = introspectionResults.resources.find(r => r.type.name === type.name);

    if (linkedResource) {
      const linkedResourceData = data[field.name];

      if (Array.isArray(linkedResourceData)) {
        return {
          ...acc,
          [field.name]: data[field.name].map(
            sanitizeResource(introspectionResults, linkedResource)
          ),
          [`${field.name}Ids`]: data[field.name].map(d => d.id)
        };
      }

      return {
        ...acc,
        [`${field.name}.id`]: linkedResourceData ? data[field.name].id : undefined,
        [field.name]: linkedResourceData
          ? sanitizeResource(introspectionResults, linkedResource)(data[field.name])
          : undefined
      };
    }

    return { ...acc, [field.name]: data[field.name] };
  }, {});
};

export default introspectionResults => (aorFetchType, resource) => response => {
  const sanitize = sanitizeResource(introspectionResults, resource);
  const data = response.data;

  if (
    aorFetchType === GET_LIST ||
    aorFetchType === GET_MANY ||
    aorFetchType === GET_MANY_REFERENCE
  ) {
    return {
      data: response.data.items.map(sanitize),
      total: response.data.total.aggregate.count
    };
  }

  return { data: sanitize(data.data) };
};
