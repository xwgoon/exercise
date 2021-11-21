import settings from "../settings";

const newWebsocketClient = () => {
	let wsUrl = settings.Server.wsBaseUrl;
	wsUrl = /^ws:\/\//.test(wsUrl) ? wsUrl : `ws://${window.location.host}${wsUrl}`;
	wsUrl = `${wsUrl}/copRegs`;
	// eslint-disable-next-line no-undef
	const wsClient = new WebSocket(wsUrl, "echo-protocol");
	return wsClient;
}

export default newWebsocketClient;