import React, { ReactNode } from "react";
import axios from "axios";
import { RouteComponentProps } from "react-router-dom";
import { CircularProgress, Tooltip, IconButton, Typography, Button } from "@material-ui/core";
import { ErrorOutlineOutlined, RefreshOutlined } from "@material-ui/icons";
import { If, Then, Else, When } from "react-if";
import AuthorizeDialog from "components/dialogs/AuthorizeDialog";
import HeaderBar from "components/HeaderBar";
import PlantExpansionPanel from "components/PlantExpansionPanel";
import { Document } from "types";
import parseDiagramData from "functions/diagram";

type Props = RouteComponentProps<{ docId: string }> & {
	authorized: boolean,
	token: string | null,
	setToken: (token: string | null) => void
}

type State = {
	loading: boolean,
	error: boolean,
	document?: Document
}

class DocsPage extends React.Component<Props, State> {

	state: State = {
		loading: false,
		error: false,
		document: undefined
	}
	componentDidMount(): void {
		this.updateDocument();
	}
	componentDidUpdate(prevProps: Props): void {
		if (this.props.match.params.docId !== prevProps.match.params.docId) {
			this.updateDocument();
		}

	}
	updateDocument(): void {
		const docId = this.props.match.params.docId;
		if (!docId) {
			this.props.history.push("/");
			return;
		}
		//redirectDocIdStore.store(docId);
		if (this.props.authorized) {
			this.setState({
				loading: true,
				error: false,
				document: undefined
			});
			this.loadDocument();
		}
	}
	loadDocument = async (): Promise<void> => {

		try {
			const response = await axios.get(`/docs/${this.props.match.params.docId}`, { headers: { authorization: `bearer ${this.props.token}` } });

			if (response.status === 200) {
				const document = {
					title: response.data.title,
					documentId: response.data.documentId,
					diagrams: response.data.rawData.map(parseDiagramData)
				};
				this.setState({ document, loading: false, error: false });
			} else {
				console.log(response);
				this.setState({ error: true, loading: false, document: undefined });
			}
		} catch (err) {
			if (err.response && err.response.status === 401) {
				this.props.setToken(null);
			}
			console.log(err);
			this.setState({ error: true, loading: false, document: undefined });
		}
	};

	returnHome(): void {
		this.props.history.push("/");
	}


	render(): ReactNode {
		return (
			<div>
				<HeaderBar title={this.state.loading ? "Loading Title..." : this.state.error ? "Error" : this.state.document ? this.state.document.title : "Unknown Document"} >
					<Tooltip title="Refresh">
						<IconButton edge="start" color="inherit" onClick={() => this.loadDocument()}>
							<RefreshOutlined className="header-icon" />
						</IconButton>
					</Tooltip>
				</HeaderBar>
				<AuthorizeDialog open={!this.props.authorized} onClose={() => this.props.history.push("/")} />
				<If condition={this.state.loading}>
					<Then>
						<LargeIconPageContent icon={<CircularProgress size="20rem" thickness={5} />} title="Loading Your Diagrams" />

					</Then>
					<Else>
						<If condition={this.state.error}>
							<Then>
								<LargeIconPageContent icon={<ErrorOutlineOutlined className="huge-icon color-danger" />} title="Error">
									<Typography>We cannot load your document</Typography>
									<Typography>Make sure {this.props.match.params.docId} is the correct document id</Typography>
									<div className="margin-top-small">
										<Button variant="contained" color="primary" onClick={() => this.updateDocument()}>Retry</Button>
									</div>
									<div className="margin-top-small">
										<Button variant="contained" onClick={() => this.returnHome()}>return to home</Button>
									</div>

								</LargeIconPageContent>
							</Then>
							<Else>
								<When condition={!!this.state.document}>
									{() =>
										<If condition={this.state.document?.diagrams.filter(diagram => diagram.valid).length === 0}>
											<Then>
												No Diagram
											</Then>
											<Else>
												<div className="page">
													{this.state.document?.diagrams.map((diagram, i) => (
														<PlantExpansionPanel key={i} id={i} {...diagram} />
													))}
												</div>
											</Else>
										</If>
									}
								</When>
							</Else>
						</If>
					</Else>
				</If>
			</div>
		);
	}
}

import { State as TokenStoreState, getToken, setToken } from "store/slices/TokenSlice";

import { connect } from "react-redux";
import LargeIconPageContent from "components/LargeIconPageContent";

type ReduxStoreState = TokenStoreState;

const connectToRedux = connect((state: ReduxStoreState) => ({
	//Mapping state to props
	authorized: getToken(state) !== null,
	token: getToken(state)
}), {
	//Mapping action creators to prop functions
	setToken,
});

export default connectToRedux(DocsPage);