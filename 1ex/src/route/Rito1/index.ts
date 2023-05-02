import { Section } from "../../engine";
import { AfterMedoh } from "./AfterMedoh";
import { KuhTakkar } from "./KuhTakkar";
import { Medoh } from "./Medoh";
import { MoggLatan } from "./MoggLatan";
import { ShadaNaw } from "./ShadaNaw";

export const Rito1 = [
	Section("RITO 1"),
	...MoggLatan,
	...KuhTakkar,
	...ShadaNaw,
	...Medoh,
	...AfterMedoh,
];