import React, { useState, useEffect } from "react";
import {
	Show,
	SimpleShowLayout,
	TextField,
	useTranslate,
	useNotify,
	ReferenceField,
	useRefresh,
	ShowController,
	ShowView,
} from "react-admin";
import {
	Button,
	TextField as MuiTextField,
} from "@material-ui/core";
import { makeStyles } from "@material-ui/styles";
import { Transaction, RestAPI } from "rclink";
import PasswordInputDialog from "./PasswordInputDialog";
import { getKeypair } from "../../dataprovider/keypairsStore";
import StatusField from "./StatusFiled";
import ImageField from "./ImageField";
import settings from "../../settings";
import newWebsocketClient from "../../utils/websocketClient";

const ReviewForm = ({
	permissions, record = {}, action,
}) => {
	const translate = useTranslate();
	const notify = useNotify();
	const [open, setOpen] = useState(false);
	const [notes, setNotes] = useState("");
	const [showProgress, setShowProgress] = useState(false);
	if (permissions === "AUTHORITY_USER" && record.status === "REQUESTED") {
		let buttonLabel = action === "register" ? "registerButtonLabel" : "rejectButtonLabel";
		buttonLabel = translate(`resources.copRegs.fields.${buttonLabel}`);
		let inputLabel = action === "register" ? "registerNotes" : "rejectNotes";
		inputLabel = translate(`resources.copRegs.fields.${inputLabel}`);

		const handleOk = async (password) => {
			setShowProgress(true);
			const chaincodeFunctionArgs = [JSON.stringify({
				hashArray: [record.image.hash],
				notes,
			})];
			const tx = new Transaction({
				type: "CHAINCODE_INVOKE",
				chaincodeName: settings.RepChain.default.chaincodeName,
				chaincodeVersion: settings.RepChain.default.chaincodeVersion,
				chaincodeInvokeParams: {
					chaincodeFunction: `${action[0].toUpperCase()}${action.slice(1)}`,
					chaincodeFunctionArgs,
				},
			});
			// eslint-disable-next-line no-undef
			const creditCode = localStorage.getItem("userId");
			const { prvKeyPEM, pubKeyPEM, certName } = await getKeypair(creditCode)
				.catch((e) => {
					setShowProgress(false);
					notify(e.message, "error");
				});
			let txSignedBuffer;
			try {
				txSignedBuffer = tx.sign({
					prvKey: prvKeyPEM,
					pubKey: pubKeyPEM,
					alg: "ecdsa-with-SHA1",
					pass: password,
					creditCode,
					certName,
				});
			} catch (e) {
				setShowProgress(false);
				notify(e.message, "error");
				return;
			}

			const rest = new RestAPI(settings.RepChain.default.url_api);
			const wsClient = newWebsocketClient();
			wsClient.onerror = ((ev) => {
				console.error("websocket error: ", ev);
				setShowProgress(false);
				setOpen(false);
			});
			wsClient.onmessage = (ev) => {
				const copReg = JSON.parse(ev.data);
				if (copReg.id !== record.id) return;
				setOpen(false);
				setShowProgress(false);
				wsClient.close();
			};
			await rest.sendTransaction(txSignedBuffer)
				.then((r) => {
					if (r.err) throw r.err;
					console.log("TX send feedback:\n", JSON.stringify(r));
				})
				.catch((e) => {
					setShowProgress(false);
					notify(e.message, "error");
				});
		};

		return (
			<div>
				<MuiTextField
					variant="filled"
					margin="normal"
					required
					label={inputLabel}
					style={{ display: "block" }}
					onChange={e => setNotes(e.target.value)}
				/>
				<Button color="primary" variant="contained"
					onClick={() => setOpen(true)}
					style={{ display: "block" }}
				>
					{buttonLabel}
				</Button>
				<PasswordInputDialog
					open={open}
					setOpen={setOpen}
					onPasswordOk={handleOk}
					showProgress={showProgress}
				/>
			</div>
		);
	}
	return null;
};

const useStyles = makeStyles({
	textField: {
		wordBreak: "break-all",
	},
});

export default ({
	permissions, ...props
}) => {
	const classes = useStyles();
	const refresh = useRefresh();

	useEffect(() => {
		const wsClient = newWebsocketClient();
		wsClient.onmessage = (ev) => {
			const copReg = JSON.parse(ev.data);
			if (copReg?.id !== props?.id) return;
			refresh();
		};

		return () => wsClient.close();
	}, [props]);

	return (
		<ShowController {...props}>
			{
				(controllerProps) => (
					<ShowView {...props} {...controllerProps}>
						<SimpleShowLayout {...props} >
							<ImageField source="image.url" width="100%" height="auto" />
							<TextField source="copRegKey" className={classes.textField} />
							<StatusField source="status" />
							<TextField source="image.hash" className={classes.textField} />
							<TextField source="image.hashAlg" />
							<TextField source="image.name" className={classes.textField} />
							<TextField source="image.desc" className={classes.textField} />
							<ReferenceField label="申请登记交易" source="requestTxId" reference="Transaction" linkType="show">
								<TextField source="txid" className={classes.textField} />
							</ReferenceField>
							<TextField source="requestNotes" />
							{
								controllerProps?.record?.status === "REGISTERED"
								&& <ReferenceField label="确认登记交易" source="registerTxId" reference="Transaction" linkType="show">
									<TextField source="txid" className={classes.textField} />
								</ReferenceField>
							}
							{
								controllerProps?.record?.status === "REGISTERED"
								&& <TextField source="registerNotes" />
							}
							<ReviewForm
								permissions={permissions}
								action="register"
							/>
							{
								controllerProps?.record?.status === "REJECTED"
								&& <ReferenceField label="拒绝登记交易" source="rejectTxId" reference="Transaction" linkType="show">
									<TextField source="txid" className={classes.textField} />
								</ReferenceField>
							}
							{
								controllerProps?.record?.status === "REJECTED"
								&& <TextField source="rejectNotes" />
							}
							<ReviewForm
								permissions={permissions}
								action="reject"
							/>
						</SimpleShowLayout>
					</ShowView>
				)
			}
		</ShowController>
	);
};