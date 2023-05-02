import React from "react";
import "./App.css";

import { Switch, Route, withRouter } from "react-router-dom";
import ScrollToTop from "components/ScrollToTop";
import DocsPage from "pages/DocsPage";
import HomePage from "pages/HomePage";
import PrivacyPage from "pages/PrivacyPage";
import TermsPage from "pages/TermsPage";
import AuthPage from "pages/AuthPage";
import Footer from "components/Footer";
import NotFoundPage from "pages/NotFoundPage";
import { RouterPropsWithAnyParam } from "types";


class App extends React.Component<RouterPropsWithAnyParam> {

	render() {

		return (
			<div className="main-content">
				<Switch>
					<Route path="/docs/:docId" component={DocsPage} />
					<Route path="/auth" component={AuthPage} />
					<Route exact path="/privacy"  >
						<ScrollToTop />
						<PrivacyPage />
					</Route>
					<Route exact path="/terms"  >
						<ScrollToTop />
						<TermsPage />
					</Route>
					<Route exact path="/" component={HomePage} />
					<Route path="*" component={NotFoundPage} />
				</Switch>
				<Footer />
			</div>
		);
	}
}
export default withRouter(App);