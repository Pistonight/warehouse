import { Section } from "../../engine";
import { AfterRudania } from "./AfterRudania";
import { Rudania } from "./Rudania";

export const Eldin1 = [
	Section("ELDIN 1"),
	...Rudania,
	...AfterRudania
];