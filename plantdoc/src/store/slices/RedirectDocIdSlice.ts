import { createSlice } from "@reduxjs/toolkit";
import useLocalStore from "store/localStore";
const REDIRECT_DOC_ID_NAME = "plantuml-doc-integration-redirect-doc-id";
const redirectDocIdStore = useLocalStore(REDIRECT_DOC_ID_NAME);

export type Slice = {
	value: string | null;
}
export type State = {
	redirectDocId: Slice;
}

export const slice = createSlice({
	name: "redirectDocId",
	initialState: {
		value: redirectDocIdStore.load(),
	},
	reducers: {
		setRedirectDocId: (state, action) => {
			// Redux immer proxy allows "mutating" syntax
			state.value = action.payload;
		}
	},
});

export const { setRedirectDocId } = slice.actions;

// The function below is called a thunk and allows us to perform async logic. It
// can be dispatched like a regular action: `dispatch(incrementAsync(10))`. This
// will call the thunk with the `dispatch` function as the first argument. Async
// code can then be executed and other actions can be dispatched
// export const incrementAsync = amount => dispatch => {
// 	setTimeout(() => {
// 		dispatch(incrementByAmount(amount));
// 	}, 1000);
// };

// The function below is called a selector and allows us to select a value from
// the state. Selectors can also be defined inline where they're used instead of
// in the slice file. For example: `useSelector((state) => state.counter.value)`
export const getRedirectDocId = (state: State): string | null => state.redirectDocId.value;
export const persistRedirectDocId = (reduxStore: { getState: () => State }): (() => void) => {
	return () => {
		redirectDocIdStore.store(getRedirectDocId(reduxStore.getState()));
	};
};

export default slice.reducer;
