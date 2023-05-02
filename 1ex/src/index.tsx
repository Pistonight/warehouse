import React from "react";
import ReactDOM from "react-dom";
import "./index.css";
import App from "./RouteApp";
import {MainRoute, Change} from "./route/config";

ReactDOM.render(
	<React.StrictMode>
		<App project="1EX" config={MainRoute} changelog={Change}/>
	</React.StrictMode>,
	document.getElementById("root")
);

