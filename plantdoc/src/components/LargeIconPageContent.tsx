import React, { ReactNode } from "react";
import { Typography } from "@material-ui/core";

type Props = {
	icon: ReactNode
	title: string
}

const LargeIconPageContent: React.FunctionComponent<Props> = ({ icon, title, children }) => {
	return (
		<div className="page">
			<div className="align-center large-icon-page-padding">
				<span>{icon}</span>
			</div>
			<div className="align-center">
				<Typography variant="h4">{title}</Typography>
			</div>
			<div className="align-center margin-top">
				{children}
			</div>
		</div>);
};

export default LargeIconPageContent;