import { RouteComponentProps } from "react-router-dom";

export type Document = {
	title: string,
	documentId: string,
	diagrams: Diagram[]
}

export type Diagram = {
	valid: boolean,
	data?: Record<string, string | boolean>,
	url?: string,
}

export type EmptyObject = Record<string, never>;

export type RouterPropsWithAnyParam = RouteComponentProps<Record<string, string | undefined>>;
