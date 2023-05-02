import { Section } from "../../engine";
import { AfterDLC } from "./AfterDLC";
import { DLC } from "./DLC";
import { BigHorse } from "./BigHorse";

export const Plateau2 = [
	Section("PLATEAU 2"),
	...DLC,
	...AfterDLC,
	...BigHorse,
];