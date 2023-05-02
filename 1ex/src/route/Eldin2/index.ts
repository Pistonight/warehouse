import { Section } from "../../engine";
import { KamiaOmuna } from "./KamiaOmuna";
import { RinuHonika } from "./RinuHonika";
import { SatoKoda } from "./SatoKoda";

export const Eldin2 = [
	Section("ELDIN 2"),
	...SatoKoda,
	...RinuHonika,
	...KamiaOmuna
];