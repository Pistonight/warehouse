import React, { ReactNode } from "react";
import HeaderBar from "components/HeaderBar";
import { Button } from "@material-ui/core";
import LargeIconPageContent from "components/LargeIconPageContent";
import { SentimentVeryDissatisfiedOutlined } from "@material-ui/icons";
import { RouterPropsWithAnyParam } from "types";


export default class NotFoundPage extends React.Component<RouterPropsWithAnyParam> {
	returnHome(): void {
		this.props.history.push("/");
	}

	render(): ReactNode {
		return (
			<div>
				<HeaderBar title="404 Not Found" isHome />
				<LargeIconPageContent icon={<SentimentVeryDissatisfiedOutlined className="huge-icon color-fade" />} title="The page you are looking for does not exist">

					<Button variant="contained" color="primary" onClick={() => this.returnHome()}>return to home</Button>
				</LargeIconPageContent>


			</div>
		);
	}
}

