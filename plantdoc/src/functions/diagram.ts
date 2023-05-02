/*
 * Parser for diagrams that parses raw string into Diagram structures
 */
import { Diagram } from "types";
import { pngUrl, svgUrl } from "functions/plantuml/url";

type DiagramParserConfig = {
	addTitle?: boolean
	format?: "svg" | "png"
}

export default function parseDiagramData(rawData: string, config: DiagramParserConfig): Diagram {
	// Load config
	const {
		addTitle = false,
		format = "svg"
	} = config;

	//Separate data string and uml string
	const index = rawData.indexOf("@startuml");
	if (index < 0) {
		return { valid: false };
	}
	const dataString = rawData.substring(0, index);
	let umlString = rawData.substring(index + "@startuml".length);

	//Parse data string
	const dataArray = dataString.split("\n");
	const dataObject: Record<string, string | boolean> = {};
	dataArray.forEach(data => {
		data = data.trim();
		if (data.startsWith("@")) {
			const spaceIndex = data.indexOf(" ");
			if (spaceIndex < 0) {
				const name = data.substring(1);
				dataObject[name] = true;
			} else {
				const name = data.substring(1, spaceIndex).trim();
				const value = data.substring(spaceIndex).trim();
				dataObject[name] = value;
			}
		}
	});

	//Process configs
	if (addTitle && dataObject.title) {
		umlString = "TITLE " + dataObject.title + "\n" + umlString;
	}

	const url = format === "svg" ? svgUrl(umlString) : pngUrl(umlString);

	return {
		valid: true,
		data: dataObject,
		url
	};
}