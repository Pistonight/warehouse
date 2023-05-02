import React, { ReactNode } from "react";
import { Dialog, DialogTitle, DialogProps } from "@material-ui/core";

type Props = DialogProps & {
	open: boolean;
	title: string;
	onReset?: () => void;
}

export default class CommonDialog extends React.Component<Props> {
	componentDidUpdate(prevProps: Props): void {
		if (prevProps.open !== this.props.open && this.props.open) {
			this.props.onReset && this.props.onReset();
		}
	}
	render(): ReactNode {
		return (
			<div>
				<Dialog {...this.props}>
					<DialogTitle>{this.props.title}</DialogTitle>
					{this.props.children}
				</Dialog>
			</div>
		);
	}
}
