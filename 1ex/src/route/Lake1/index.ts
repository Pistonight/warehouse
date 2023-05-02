import { Section } from "../../engine";
import { Horses } from "./Horses";
import { IshtoSoh } from "./IshtoSoh";
import { JeeNoh } from "./JeeNoh";

export const Lake1=[
	Section("LAKE 1 (Horse)"),
	...JeeNoh,
	...IshtoSoh,
	...Horses

];