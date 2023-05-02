import React from "react";
import { DialogContent, DialogContentText, TextField } from "@material-ui/core";

type Props = {
	label: string;
	value: string;
	onChange: (value: string) => void;
	error: string;
}

const InputDialogContent: React.FunctionComponent<Props> = ({ children, label, value, onChange, error }) => {
	return (
		<DialogContent>
			<DialogContentText>{children}</DialogContentText>
			<TextField
				margin="normal"
				autoFocus
				label={label}
				value={value}
				onChange={(e) => onChange(e.target.value)}
				error={!!error}
				helperText={error ? error : " "}
				required
				fullWidth
			/>
		</DialogContent>
	);
};
export default InputDialogContent;
