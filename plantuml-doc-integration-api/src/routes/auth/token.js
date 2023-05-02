import newOAuth2Client from '../../google/client.js';
import response from '../../util/response.js';
import { encrypt } from '../../util/cryptoken.js';
export default function configureRouter(router) {
	router.route('/token').get(async (req, res) => {
		const { notFound, badRequest, propagateError, ok } = response(res);
		const code = req.query.code;

		if (!code) {
			badRequest("Please provide code");
			return;
		}

		const oAuth2Client = newOAuth2Client();
		try {
			const { tokens } = await oAuth2Client.getToken(code);
			if (tokens.refresh_token) {
				const encryptedToken = encrypt(tokens.refresh_token);
				ok({ token: encryptedToken });
			} else {
				notFound();
			}
		} catch (err) {
			console.log(err);
			propagateError(err);
			return;
		}
	});
}