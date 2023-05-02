import React, { ReactNode } from "react";
import DialogBase from "./DialogBase";
import { Button, DialogActions } from "@material-ui/core";
import InputDialogContent from "components/dialogs/InputDialogContent";
import { processDocumentLinkOrId } from "functions/documents";

type ExternalProps = {
	onClose: (document: string | undefined) => void;
	open: boolean;
}

type InternalProps = {
	processInput: (input: string) => string;
}

type Props = ExternalProps & InternalProps

type State = {
	input: string;
	error: string;
}

export class OpenDocumentDialog extends React.Component<Props, State>{
	constructor(props: Props) {
		super(props);
		this.state = {
			input: "",
			error: ""
		};
	}

	reset(): void {
		this.setState({ input: "", error: "" });
	}

	onChangeInput(input: string): void {
		this.setState({ input, error: "" });
	}

	onClickClose(proceed: boolean): void {
		if (!proceed) {
			this.props.onClose(undefined);
		} else {
			if (this.validateInput(this.state.input)) {
				this.props.onClose(this.props.processInput(this.state.input));
			}
		}
	}

	validateInput(input: string): boolean {
		if (!input) {
			this.setState({ error: "This is required" });
			return false;
		}
		return true;
	}

	render(): ReactNode {
		return (
			<DialogBase fullWidth open={this.props.open} title="Open from Google Docs" onReset={() => this.reset()}>
				<InputDialogContent label="Document Link/Id" value={this.state.input} onChange={(v) => this.onChangeInput(v)} error={this.state.error}>
					Please enter the document link or id
				</InputDialogContent>
				<DialogActions>
					<Button color="primary" onClick={() => this.onClickClose(false)}>Cancel</Button>
					<Button variant="contained" color="primary" onClick={() => this.onClickClose(true)}>Open</Button>
				</DialogActions>
			</DialogBase>
		);
	}
}

import { injectProps } from "functions/props";

export default injectProps<ExternalProps, InternalProps>(OpenDocumentDialog, {
	processInput: processDocumentLinkOrId
});