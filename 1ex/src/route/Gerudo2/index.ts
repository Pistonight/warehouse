import { Section } from "../../engine";
import { AfterFuryPlus } from "./AfterFuryPlus";
import { KeiveTala } from "./KeiveTala";
import { SasaKai } from "./SasaKai";
import { TakamaShiri } from "./TakamaShiri";

export const Gerudo2 = [
	Section("GERUDO 2"),
	...SasaKai,
	...TakamaShiri,
	...KeiveTala,
	...AfterFuryPlus,
];