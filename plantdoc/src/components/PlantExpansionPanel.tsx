import React from "react";
import { Accordion, AccordionDetails, AccordionSummary, Typography } from "@material-ui/core";
import { ExpandMore } from "@material-ui/icons/";
import { If, Then, Else, When } from "react-if";
import { Diagram } from "types";


type Props = Diagram & {
	id: number
}

const PlantExpansionPanel: React.FunctionComponent<Props> = ({ valid, data, url, id }) => {
	let title;
	if (data === undefined || typeof (data.title) === "boolean" || data.title === undefined || data.title === "") {
		title = "Untitled";
	} else {
		title = data.title;
	}
	return (
		<Accordion>
			<AccordionSummary expandIcon={<ExpandMore />}
				aria-controls={`diagram${id}-content`}
				id={`diagram${id}-header`}>
				<If condition={valid}>
					<Then>
						<Typography variant="h6">{title}</Typography>
					</Then>
					<Else>
						<Typography variant="h6">Invalid Diagram</Typography>
					</Else>
				</If>
			</AccordionSummary>
			<When condition={valid}>
				<AccordionDetails>
					<img alt={title} src={url} width="100%" />
				</AccordionDetails>
			</When>
		</Accordion>
	);
};

export default PlantExpansionPanel;