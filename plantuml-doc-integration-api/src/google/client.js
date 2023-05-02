import googleapis from 'googleapis';

const CREDENTIALS = {
	web: {
		"client_id": process.env.CLIENT_ID,
		"project_id": process.env.GCP_PROJECT_ID,
		"auth_uri": "https://accounts.google.com/o/oauth2/auth",
		"token_uri": "https://oauth2.googleapis.com/token",
		"auth_provider_x509_cert_url": "https://www.googleapis.com/oauth2/v1/certs",
		"client_secret": process.env.CLIENT_SECRET,
		"redirect_uris": [process.env.AUTH_REDIRECT]
	}
}
const { google } = googleapis;
export default function newOAuth2Client() {
	const oAuth2Client = new google.auth.OAuth2(CREDENTIALS.web.client_id, CREDENTIALS.web.client_secret, CREDENTIALS.web.redirect_uris[0]);
	return oAuth2Client;
}