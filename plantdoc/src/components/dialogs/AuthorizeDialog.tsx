import React, { ReactNode } from "react";
import DialogBase from "components/dialogs/DialogBase";
import { Button, CircularProgress, DialogActions } from "@material-ui/core";
import { VpnKeyOutlined, LockOutlined, ErrorOutlineOutlined } from "@material-ui/icons";
import { If, Then, Else } from "react-if";
import LargeIconDialogContent from "components/dialogs/LargeIconDialogContent";
import { RouteComponentProps } from "react-router-dom";

type Props = RouteComponentProps<Record<string, string | undefined>> & {
	onClose: () => void;
	setToken: (token: string | null) => void;
	setRedirectDocId: (docId: string | null) => void;
	open: boolean;
	authorized: boolean;
}

type State = {
	loading: boolean;
	fail: boolean;
}

class AuthorizeDialog extends React.Component<Props, State> {
	constructor(props: Props) {
		super(props);
		this.state = {
			loading: false,
			fail: false,
		};
	}

	reset(): void {
		this.setState({
			loading: false,
			fail: false,
		});
	}

	unauthorize = (): void => {
		this.props.setToken(null);
		this.props.setRedirectDocId(null);
		this.props.onClose();
	}

	authorize = async (): Promise<void> => {
		this.setState({
			loading: true,
			fail: false
		}, () => {
			this.props.history.push("/auth");
		});
	};

	render(): ReactNode {
		return (
			<DialogBase open={this.props.open} onClose={() => this.props.onClose()} onReset={() => this.reset()} title="Authorization">
				<If condition={this.state.loading}>
					<Then>
						<If condition={this.state.fail}>
							<Then>
								<LargeIconDialogContent iconClass="color-danger" icon={<ErrorOutlineOutlined className="large-icon" />} caption="Failed to connect">
									An error occured when connecting to the server.
								</LargeIconDialogContent>
								<DialogActions>
									<Button color="primary" onClick={() => this.props.onClose()}>Cancel</Button>
									<Button variant="contained" color="primary" onClick={() => this.authorize()}>Retry</Button>
								</DialogActions>
							</Then>
							<Else>
								<LargeIconDialogContent icon={<CircularProgress size="5rem" />} caption="Please wait...">
									You will be redirected to Google
								</LargeIconDialogContent>
							</Else>
						</If>
					</Then>
					<Else>
						<If condition={this.props.authorized}>
							<Then>
								<LargeIconDialogContent iconClass="color-safe" icon={<VpnKeyOutlined className="large-icon" />} caption="Authorized">
									You have authorized PlantUML Doc Integration to read your documents on Google Docs.
									If you wish to remove your authorization token, please click <b>Unauthorize</b> below.<br /><br />
									This does not remove permissions for PlantUML Doc Integration. You will need to do that in your Google account settings.
								</LargeIconDialogContent>
							</Then>
							<Else>
								<LargeIconDialogContent iconClass="color-danger" icon={<LockOutlined className="large-icon" />} caption="Unauthorized">
									You need to authorize PlantUML Doc Integration to read your documents on Google Docs in order to use it.
									We do not keep your data. We simply parse it and draw diagrams from it.<br /><br />
									You will be automatically asked to authorize this app when you open a document. If you want to authorize it now, click the <b>Authorize</b> button below.
								</LargeIconDialogContent>
							</Else>
						</If>
						<DialogActions>
							<Button color="primary" onClick={() => this.props.onClose()}>Cancel</Button>
							<If condition={this.props.authorized}>
								<Then>
									<Button variant="contained" color="primary" onClick={() => this.unauthorize()} >Unauthorize</Button>
								</Then>
								<Else>
									<Button variant="contained" color="primary" onClick={() => this.authorize()}> Authorize</Button>
								</Else>
							</If>
						</DialogActions>
					</Else>
				</If>
			</DialogBase >
		);
	}
}

import { State as TokenStoreState, getToken, setToken } from "store/slices/TokenSlice";
import { setRedirectDocId } from "store/slices/RedirectDocIdSlice";
import { connect } from "react-redux";
import { withRouter } from "react-router-dom";

type ReduxStoreState = TokenStoreState;

const connectToRedux = connect((state: ReduxStoreState) => ({
	//Mapping state to props
	authorized: getToken(state) !== null
}), {
	//Mapping action creators to prop functions
	setToken,
	setRedirectDocId
});

export default withRouter(connectToRedux(AuthorizeDialog));

