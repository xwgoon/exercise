// In createRealtimeSaga.js
import realtimeSaga from 'ra-realtime';

import { ApolloClient } from 'apollo-client';
import { InMemoryCache } from 'apollo-cache-inmemory';
import { WebSocketLink } from 'apollo-link-ws';
import { SubscriptionClient } from 'subscriptions-transport-ws';
import gql from 'graphql-tag';
import settings from  './settings';


const GRAPHQL_ENDPOINT = settings.Prisma.url_subscribe;
const wsClient = new SubscriptionClient(
    GRAPHQL_ENDPOINT, { reconnect: true }
);
const link = new WebSocketLink(wsClient);

const apolloClient = new ApolloClient({
    link,
    cache: new InMemoryCache()
});



const observeRequest = dataProvider => (type, resource, params) => {
    // Filtering so that only posts are updated in real time
    var s_gql = null;
    if (resource === 'NetPeer') {
        s_gql = gql`
        subscription netPeer {
            netPeer {
            mutation
            node {
                id
                nodename
                seedip
                status
            }
            }
        }
        `
    }else if (resource === 'Network') {
        s_gql = gql`
        subscription network {
        network {
            mutation
            node {
                id
                syncHeight
            }
        }
    }
    `
    }

    if (s_gql == null)
        return;

    // Use your apollo client methods here or sockets or whatever else including the following very naive polling mechanism
    return {
        subscribe(observer) {
            //todo 用graphql 订阅替换

            apolloClient.subscribe({
                query: s_gql,
                variables: {}
            }).subscribe({
                next(data) {
                    // Notify your application with the new arrived data
                    //console.log(data);
                    dataProvider(type, resource, params)
                        .then(results => observer.next(results)) // New data received, notify the observer
                        .catch(error => observer.error(error));
                }
            });

            const subscription = {
                unsubscribe() {
                    // Clean up after ourselves
                    //clearInterval(intervalId);
                    // Notify the saga that we cleaned up everything
                    //observer.complete();
                }
            };

            return subscription;
        },
    };
};

export default dataProvider => realtimeSaga(observeRequest(dataProvider));