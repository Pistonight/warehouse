import { Section } from "../../engine";
import { DaagChokah } from "./DaagChokah";
import { KuhnSidajj } from "./KuhnSidajj";
import { QuaRaym } from "./QuaRaym";

export const KorokForest = [
	Section("KOROK FOREST"),
	...QuaRaym,
	...DaagChokah,
	...KuhnSidajj
];
