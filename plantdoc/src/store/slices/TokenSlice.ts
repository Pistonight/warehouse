import { createSlice } from "@reduxjs/toolkit";
import useLocalStore from "store/localStore";
const TOKEN_NAME = "plantuml-doc-integration-token-v1";
const tokenStore = useLocalStore(TOKEN_NAME);

export type TokenSlice = {
	value: string | null;
}
export type State = {
	token: TokenSlice;
}

export const slice = createSlice({
	name: "token",
	initialState: {
		value: tokenStore.load(),
	},
	reducers: {
		setToken: (state, action) => {
			// Redux immer proxy allows "mutating" syntax
			state.value = action.payload;
		}
	},
});

export const { setToken } = slice.actions;

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
export const getToken: (state: State) => string | null = state => state.token.value;
export const persistToken: (reduxStore: { getState: () => State }) => (() => void) = reduxStore => {
	return () => {
		tokenStore.store(getToken(reduxStore.getState()));
	};
};

export default slice.reducer;
