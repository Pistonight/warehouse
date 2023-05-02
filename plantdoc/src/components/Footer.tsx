
import React from "react";
import { Typography } from "@material-ui/core";
import { Link } from "react-router-dom";
import { EmptyObject } from "types";

const Footer: React.FunctionComponent<EmptyObject> = () => {
	return (
		<footer>
			<Typography variant="caption" className="text-light">
				PlantUML Doc Integration &copy; 2020-2021 |
				<a href="https://github.com/plantuml-doc-integration/">Source on GitHub</a>
				| <Link to="/privacy">Privacy Policy</Link> |
				<Link to="/terms">Terms and Conditions</Link>
			</Typography>
		</footer>
	);
};
export default Footer;