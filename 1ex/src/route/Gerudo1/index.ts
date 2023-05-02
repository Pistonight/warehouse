import { Section } from "../../engine";
import { AfterNaboris } from "./AfterNaboris";
import { KemaZoos } from "./KemaZoos";
import { Naboris } from "./Naboris";

export const Gerudo1 = [
	Section("GERUDO 1"),
	...KemaZoos,
	...Naboris,
	...AfterNaboris,
];