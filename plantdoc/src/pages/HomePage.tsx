import React, { ReactNode } from "react";
import HeaderBar from "components/HeaderBar";
import { TextField, Button } from "@material-ui/core";
import LargeIconPageContent from "components/LargeIconPageContent";
import { RouterPropsWithAnyParam } from "types";
import { processDocumentLinkOrId } from "functions/documents";

type InternalProps = {
	processDocumentLinkOrId: (input: string) => string
}

type Props = RouterPropsWithAnyParam & InternalProps;

type State = {
	docId: string
	error: boolean
}

export class HomePage extends React.Component<Props, State> {

	state = {
		docId: "",
		error: false
	}

	setDocId(docId: string): void {
		this.setState({ docId, error: false });
	}

	openDocument(): void {
		if (!this.state.docId) {
			this.setState({ error: true });
			return;
		}
		const document = this.props.processDocumentLinkOrId(this.state.docId);
		if (document) {
			this.props.history.push(`/docs/${document}`);
		}

	}

	render(): ReactNode {
		return (
			<div>
				<HeaderBar title="Home" isHome />
				<LargeIconPageContent icon={<img src="logo256.png" />} title="Start Collaborating On Your Diagrams Now!">
					<div>
						<TextField
							className="half-width"
							margin="normal"
							autoFocus
							label="Google Doc Link/Id"
							value={this.state.docId}
							onChange={(e) => this.setDocId(e.target.value)}
							error={this.state.error}
							required
							helperText={this.state.error ? "Invalid Document Link or Id" : ""}
						/>
					</div>
					<div className="margin-top-small">
						<Button variant="contained" color="primary" onClick={() => this.openDocument()}>Open Document</Button>
					</div>
				</LargeIconPageContent>


			</div>
		);
	}
}

import { injectProps } from "functions/props";

export default injectProps<RouterPropsWithAnyParam, InternalProps>(HomePage, {
	processDocumentLinkOrId
});