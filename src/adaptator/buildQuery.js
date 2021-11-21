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
import gql from 'graphql-tag';
import buildVariables from './buildVariables';
import buildGqlQuery from './buildGqlQuery';
import getResponseParser from './getResponseParser';

export const buildQueryFactory = (
  buildVariablesImpl,
  buildGqlQueryImpl,
  getResponseParserImpl
) => introspectionResults => {
  const knownResources = introspectionResults.resources.map(r => r.type.name);

  return (aorFetchType, resourceName, params) => {
    const resource = introspectionResults.resources.find(r => r.type.name === resourceName);

    if (!resource) {
      throw new Error(
        `Unknown resource ${resourceName}. Make sure it has been declared on your server side schema. Known resources are ${knownResources.join(
          ', '
        )}`
      );
    }

    const queryType = resource[aorFetchType];

    if (!queryType) {
      throw new Error(
        `No query or mutation matching aor fetch type ${aorFetchType} could be found for resource ${
          resource.type.name
        }`
      );
    }

    const variables = buildVariablesImpl(introspectionResults)(
      resource,
      aorFetchType,
      params,
      queryType
    );
    const query = buildGqlQueryImpl(introspectionResults)(
      resource,
      aorFetchType,
      queryType,
      variables
    );
    const parseResponse = getResponseParserImpl(introspectionResults)(
      aorFetchType,
      resource,
      queryType
    );

    return {
      query: gql`
        ${query}
      `,
      variables,
      parseResponse
    };
  };
};

export default buildQueryFactory(
  buildVariables,
  buildGqlQuery,
  getResponseParser
);
