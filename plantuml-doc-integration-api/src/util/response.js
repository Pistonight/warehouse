export default function respond(res) {
	const noAuth = () => {
		res.sendStatus(401);
	}
	const notFound = () => {
		res.sendStatus(404);
	}
	const error = (errorCode, message, status) => {
		const data = { error: errorCode, message };
		res.status(status ? status : 500).json(data);
	}
	const unknownError = () => {
		error("UNKNOWN", "An error has occured");
	}
	const propagateError = (err) => {
		if (err.errorCode && err.message) {
			error(err.errorCode, err.message, err.status);
		} else {
			unknownError();
		}
	}
	const badRequest = (message) => {
		error("BAD_REQUEST", message, 400);
	}
	const ok = (data) => {
		res.status(200).json(data);
	}
	return { noAuth, notFound, error, badRequest, ok, unknownError, propagateError };
}