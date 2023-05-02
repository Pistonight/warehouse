import React, { ReactNode } from "react";
import { RouterPropsWithAnyParam } from "types";
import { AppBar, Toolbar, IconButton, Typography, Tooltip } from "@material-ui/core";
import { When } from "react-if";
import { InsertDriveFileOutlined, SettingsOutlined, AccountCircleOutlined, HomeOutlined } from "@material-ui/icons";
import AuthorizeDialog from "components/dialogs/AuthorizeDialog";
import OpenDocumentDialog from "components/dialogs/OpenDocumentDialog";


type Props = RouterPropsWithAnyParam & {
	title: string;
	isHome?: boolean;
}

type State = {
	openAuthorizeDialog: boolean;
	openOpenDocumentDialog: boolean
}

class HeaderBar extends React.Component<Props, State>{
	constructor(props: Props) {
		super(props);
		this.state = {
			openAuthorizeDialog: false,
			openOpenDocumentDialog: false
		};
	}

	setOpenDocument(openDocument: string | undefined): void {
		this.setState({ openOpenDocumentDialog: false });
		if (openDocument) {
			this.props.history.push(`/docs/${openDocument}`);
		}
	}

	openHomePage(): void {
		this.props.history.push("/");
	}

	render(): ReactNode {
		return (
			<header id="header">
				<AppBar position="static">
					<Toolbar>
						<When condition={!this.props.isHome}>
							<Tooltip title="Home">
								<IconButton edge="start" color="inherit" onClick={() => this.openHomePage()}>
									<HomeOutlined className="header-icon" />
								</IconButton>
							</Tooltip>
						</When>
						<Tooltip title="Open Document">
							<IconButton edge="start" color="inherit" onClick={() => this.setState({ openOpenDocumentDialog: true })}>
								<InsertDriveFileOutlined className="header-icon" />
							</IconButton>
						</Tooltip>

						<Typography variant="h5">
							{this.props.title}
						</Typography>

						<div className="toolbar-align-right">
							{this.props.children}
							<Tooltip title="Authorization">
								<IconButton edge="start" color="inherit" onClick={() => this.setState({ openAuthorizeDialog: true })}>
									<AccountCircleOutlined className="header-icon" />
								</IconButton>
							</Tooltip>
							<Tooltip title="Settings">
								<IconButton edge="start" color="inherit">
									<SettingsOutlined className="header-icon" />
								</IconButton>
							</Tooltip>
						</div>

					</Toolbar>
				</AppBar >
				<OpenDocumentDialog open={this.state.openOpenDocumentDialog} onClose={this.setOpenDocument.bind(this)} />

				<AuthorizeDialog open={this.state.openAuthorizeDialog} onClose={() => this.setState({ openAuthorizeDialog: false })} />
			</header>

		);
	}
}

import { withRouter } from "react-router-dom";

export default withRouter(HeaderBar);