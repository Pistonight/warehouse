import React, { ReactNode } from "react";
import axios from "axios";
import { CircularProgress, Button, DialogActions } from "@material-ui/core";
import { DoneOutlineOutlined, ErrorOutlineOutlined } from "@material-ui/icons";
import { If, Then, Else, When } from "react-if";
import LargeIconDialogContent from "components/dialogs/LargeIconDialogContent";
import DialogBase from "components/dialogs/DialogBase";
import HeaderBar from "components/HeaderBar";
import queryString from "query-string";
import { RouterPropsWithAnyParam } from "types";

type Props = RouterPropsWithAnyParam & {
	authorized: boolean,
	redirectDocId: string | null,
	setToken: (token: string | null) => void
}

type State = {
	loading: boolean;
	success: boolean;
	hasCode: boolean;
}

class AuthPage extends React.Component<Props, State> {
	constructor(props: Props) {
		super(props);
		this.state = {
			loading: true,
			success: false,
			hasCode: false,
		};
	}

	componentDidMount(): void {
		if (this.props.authorized) {
			//Already authorized
			this.props.history.push("/");
		} else {
			const query = queryString.parse(this.props.location.search);
			const code = query.code;
			this.setState({
				hasCode: typeof code === "string"
			});
			if (typeof code !== "string") {
				//Redirect to google auth
				this.redirectToAuthUrl();
			} else {
				//const code: string = query.code;
				this.verifyCode(code);
			}
		}
	}

	verifyCode = async (code: string): Promise<void> => {
		let success = false;
		try {
			const response = await axios.get("/auth/token", { params: { code } });
			if (response.status === 200) {
				success = true;
				this.props.setToken(response.data.token);
			}
		} catch (err) {
			console.log(err);
		}
		this.setState({
			success, loading: false
		});
	};

	redirectToAuthUrl = async (): Promise<boolean> => {
		let success = true;
		try {
			const response = await axios.get("/auth/new");
			if (response.status === 200) {
				window.location.href = response.data.authUrl;
			} else {
				success = false;
			}
		} catch (err) {
			console.log(err);
			success = false;
		}
		return success;
	}

	retryAuthorize = (): void => {
		this.setState({
			loading: true,
			success: false
		}, this.redirectToAuthUrl);
	}

	redirectHome = (): void => {
		this.props.history.push("/");
	}
	redirectDocs = (): void => {
		if (this.props.redirectDocId !== null) {
			this.props.history.push(`/docs/${this.props.redirectDocId}`);
		} else {
			this.props.history.push("/");
		}
	}
	render(): ReactNode {
		return (
			<div>
				<HeaderBar title="Authorization" />
				<DialogBase open={true} onClose={() => this.redirectHome()} title="Authorization">
					<If condition={this.state.loading}>
						<Then>
							<If condition={!this.state.hasCode}>
								<Then>
									<LargeIconDialogContent icon={<CircularProgress size="5rem" />} caption="Please wait...">
										You will be redirected to Google
									</LargeIconDialogContent>
								</Then>
								<Else>
									<LargeIconDialogContent icon={<CircularProgress size="5rem" />} caption="Just A Moment...">
										We are getting your response from Google
									</LargeIconDialogContent>
								</Else>
							</If>
						</Then>
						<Else>
							<If condition={this.state.success}>
								<Then>
									<LargeIconDialogContent iconClass="color-safe" icon={<DoneOutlineOutlined className="large-icon" />} caption="Success!">
										All Done! You can start viewing your diagrams now.
										<When condition={this.props.redirectDocId !== null}>
											<br />Click <b>Proceed</b> to view the diagrams in your document
										</When>
									</LargeIconDialogContent>
									<DialogActions>
										<If condition={this.props.redirectDocId !== null}>
											<Then>
												<Button color="primary" onClick={() => this.redirectHome()}>Back</Button>
												<Button variant="contained" color="primary" onClick={() => this.redirectDocs()}>Proceed</Button>
											</Then>
											<Else>
												<Button variant="contained" color="primary" onClick={() => this.redirectHome()}>Done</Button>
											</Else>
										</If>
									</DialogActions>
								</Then>
								<Else>
									<LargeIconDialogContent iconClass="color-danger" icon={<ErrorOutlineOutlined className="large-icon" />} caption="Failed to authorize">
										An error occured. Click <b>Retry</b> to try again.
									</LargeIconDialogContent>
									<DialogActions>
										<Button color="primary" onClick={() => this.redirectHome()}>Back</Button>
										<Button variant="contained" color="primary" onClick={() => this.retryAuthorize()}>Retry</Button>
									</DialogActions>
								</Else>
							</If>
						</Else>
					</If>
				</DialogBase>
			</div>
		);
	}
}

import { State as TokenStoreState, getToken, setToken } from "store/slices/TokenSlice";
import { State as RedirectDocIdStoreState, getRedirectDocId } from "store/slices/RedirectDocIdSlice";
import { connect } from "react-redux";

type ReduxStoreState = TokenStoreState & RedirectDocIdStoreState;

const connectToRedux = connect((state: ReduxStoreState) => ({
	//Mapping state to props
	authorized: getToken(state) !== null,
	redirectDocId: getRedirectDocId(state)
}), {
	//Mapping action creators to prop functions
	setToken,
});

export default connectToRedux(AuthPage);