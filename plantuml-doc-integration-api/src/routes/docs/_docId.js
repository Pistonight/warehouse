import { decrypt } from '../../util/cryptoken.js';
import response from '../../util/response.js';
import getDocumentAsync from '../../google/getDocument.js';
import newOAuth2Client from '../../google/client.js';

export default function configureRouter(router) {
	router.route('/:docId').get(async (req, res) => {
		const { noAuth, badRequest, ok, propagateError } = response(res);
		if (!req.headers || !req.headers.authorization) {
			noAuth();
			return;
		}
		const authorization = req.headers.authorization.split(" ");
		if (authorization.length !== 2 || authorization[0].toLowerCase() !== "bearer") {
			noAuth();
			return;
		}
		const token = authorization[1];
		const decryptedToken = decrypt(token);
		if (!decryptedToken) {
			noAuth();
			return;
		}

		const docId = req.params.docId;
		if (!docId) {
			badRequest("Please provide doc id");
			return;
		}

		const oAuth2Client = newOAuth2Client();
		oAuth2Client.setCredentials({ refresh_token: decryptedToken });

		try {
			const documentObject = await getDocumentAsync(docId, oAuth2Client);
			ok(documentObject)
		} catch (err) {
			if (err.errorCode === "INVALID_GRANT") {
				noAuth();
			} else {
				propagateError(err);
			}
		}
	});
}






