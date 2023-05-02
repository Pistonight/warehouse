/*
  Starting point of backend
*/
import express from 'express';
import bodyParser from 'body-parser';
import cors from 'cors';
import methodOverride from 'method-override';
import morgan from 'morgan';
import routingEcho from './routes/echo/__routing.js';
import routingAuth from './routes/auth/__routing.js';
import routingDocs from './routes/docs/__routing.js';
import response from './util/response.js';

const api = express();

const API_PREFIX = "/api";
const getRouteString = (route) => `${API_PREFIX}${route}`;

api.use(morgan('combined'));

api.use(cors({
	origin: [
		"https://plantdoc.herokuapp.com"
	]
}));
api.use(bodyParser.json());
api.use(bodyParser.urlencoded({ extended: true }));

//Allow HTTP verbs when client doesn't support them
api.use(methodOverride((req) => {
	if (req.body && typeof req.body === 'object' && '_method' in req.body) {
		// look in urlencoded POST bodies and delete it
		const method = req.body._method;
		delete req.body._method;
		return method;
	}
}));
api.use(getRouteString('/echo'), routingEcho);
api.use(getRouteString('/auth'), routingAuth);
api.use(getRouteString('/docs'), routingDocs);

//Not Found
const routerNotFound = express.Router();
routerNotFound.use((_, res) => {
	response(res).notFound();
});
api.use(routerNotFound);

//Start listening
const port = process.env.PORT || 5000;
api.listen(port, () => {
	console.log(`Listening on port ${port}`);
});