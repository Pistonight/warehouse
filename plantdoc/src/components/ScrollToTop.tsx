import React, { useEffect } from "react";
import { useLocation } from "react-router-dom";
import { EmptyObject } from "types";

const ScrollToTop: React.FunctionComponent<EmptyObject> = () => {
	const { pathname } = useLocation();

	useEffect(() => {
		window.scrollTo(0, 0);
	}, [pathname]);

	return null;
};

export default ScrollToTop;