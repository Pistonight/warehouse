import { configureStore } from "@reduxjs/toolkit";
import tokenReducer, { persistToken } from "./slices/TokenSlice";
import redirectDocIdReducer, { persistRedirectDocId } from "./slices/RedirectDocIdSlice";

const store = configureStore({
	reducer: {
		token: tokenReducer,
		redirectDocId: redirectDocIdReducer
	},
});

store.subscribe(persistToken(store));
store.subscribe(persistRedirectDocId(store));
export default store;