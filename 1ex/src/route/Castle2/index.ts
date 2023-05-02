import { Section } from "../../engine";
import { AfterCastle } from "./AfterCastle";
import { Castle } from "./Castle";
import { Tabantha } from "./Tabantha";

export const Castle2 = [
	Section("CASTLE 2 + BM"),
	...Castle,
	...AfterCastle,
	...Tabantha
];