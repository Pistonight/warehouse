import React, { ReactElement, ComponentType } from "react";

export function injectProps<E, I>(component: ComponentType<E & I>, internalProps: I): React.FunctionComponent<E> {
	return (props: E): ReactElement => React.createElement(component, { ...props, ...internalProps });
}