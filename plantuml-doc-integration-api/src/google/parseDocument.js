const truncateDiagram = (diagramString) => {
	//make string from @diagram to @enduml
	const startIndex = diagramString.indexOf("@startuml");
	const endIndex = diagramString.indexOf("@enduml");
	if (startIndex < 0 || endIndex < 0) {
		return "";
	}
	return diagramString.substring(0, endIndex);
}
const findDiagrams = (content) => {
	const diagramContents = content.split("@diagram");
	const diagramStrings = [];
	diagramContents.filter(c => c).forEach(diagramContent => {
		const truncatedDiagramString = truncateDiagram("@diagram" + diagramContent);
		if (truncatedDiagramString) {
			diagramStrings.push(truncatedDiagramString);
		}
	});
	return diagramStrings;
}

const parseElement = (element) => {
	if (!element.textRun || !element.textRun.content) {
		return "";
	}
	return element.textRun.content;
}

const parseContent = (content) => {
	if (!content || !content.paragraph || !content.paragraph.elements) {
		return "";
	}
	const elements = content.paragraph.elements.map(parseElement);
	return elements.join("");
}
const parseBody = (body) => {
	if (!body.content) {
		return [];
	} else {
		const { content } = body;
		const paragraphs = content.map(parseContent);
		return findDiagrams(paragraphs.join(""));
	}
};

export default function parseDocument(documentData) {
	const { title, body, documentId } = documentData;
	return { title, documentId, rawData: parseBody(body) };
};
