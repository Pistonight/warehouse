export function processDocumentLinkOrId(input: string): string {
	const prefix = "https://docs.google.com/document/d/";
	if (input.startsWith(prefix)) {
		input = input.substring(prefix.length);
		const slash = input.indexOf("/");
		if (slash > 0) {
			input = input.substring(0, slash);
		}
	}
	return input;
}