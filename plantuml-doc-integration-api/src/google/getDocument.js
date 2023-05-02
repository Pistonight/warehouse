import googleapis from 'googleapis';
import parseDocument from './parseDocument.js';
const { google } = googleapis;

export default async function getDocumentAsync(id, auth) {
	const docs = google.docs({ version: 'v1', auth: auth });
	try {
		const document = await docs.documents.get({ documentId: id });
		if (document && document.data) {
			return parseDocument(document.data);
		} else {
			throw { errorCode: "DOC_INVALID", message: "Invalid document", status: 404 };
		}
	} catch (err) {
		if (err.response && err.response.data && err.response.data.error) {
			const errorData = err.response.data.error;
			if (errorData.code === 404) {
				throw { errorCode: "DOC_NOT_FOUND", message: "Document not found", status: 404 };
			} else if (errorData === "invalid_grant") {
				throw { errorCode: "INVALID_GRANT", message: "Invalid grant", status: 401 };
			} else {
				console.log(errorData);
				throw errorData;
			}
		} else {
			console.log(err);
			throw err;
		}
	}
}


