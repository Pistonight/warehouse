import response from '../../util/response.js';
export default function configureRouter(router) {
	router.use((_, res) => {
		response(res).ok({ message: "Server is working" });
	});
}