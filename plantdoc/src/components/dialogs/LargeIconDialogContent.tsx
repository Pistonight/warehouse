import React from "react";
import clsx from "clsx";
import { DialogContent, Typography, DialogContentText } from "@material-ui/core";

type Props = {
	iconClass?: string | Array<string>;
	icon: React.ReactNode;
	caption: React.ReactNode;
}

const LargeIconDialogContent: React.FunctionComponent<Props> = ({ iconClass, icon, caption, children }) => {
	return (
		<DialogContent>
			<div className={clsx("align-center", iconClass)}>
				{icon}
				<Typography variant="h5">{caption}</Typography>
			</div>
			<DialogContentText>
				{children}
			</DialogContentText>
		</DialogContent>
	);
};

export default LargeIconDialogContent;