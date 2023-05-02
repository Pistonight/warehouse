/*
 * Util functions for editing plant uml code
 */
export function setDiagramTitle(umlString: string, title: string): string {
	const titleIndex = umlString.indexOf("TITLE");
	if (titleIndex === -1) {
		return "TITLE " + title + "\n" + umlString;
	}
	let newLineIndex = umlString.indexOf("\n", titleIndex);
	if (newLineIndex === -1) {
		newLineIndex = umlString.length;
	}
	return umlString.substring(0, titleIndex) + "TITLE " + title + umlString.substring(newLineIndex);
}