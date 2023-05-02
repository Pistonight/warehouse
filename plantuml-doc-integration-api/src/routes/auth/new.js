import newOAuth2Client from '../../google/client.js';
import response from '../../util/response.js';
const SCOPES = ['https://www.googleapis.com/auth/documents.readonly'];
export default function configureRouter(router) {
	router.route('/new').get((_, res) => {

		const oAuth2Client = newOAuth2Client();

		const authUrl = oAuth2Client.generateAuthUrl({
			access_type: 'offline',
			scope: SCOPES,
			prompt: 'consent'
		});

		const data = {
			authUrl: authUrl,
		};
		response(res).ok(data);
	});
}
