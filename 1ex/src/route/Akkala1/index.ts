import { Section } from "../../engine";
import { TarryTown1 } from "./TarryTown1";
import { TuKaloh } from "./TuKaloh";
import { ZunaKai } from "./ZunaKai";

export const Akkala1 = [
	Section("AKKALA 1"),
	...ZunaKai,
	...TuKaloh,
	...TarryTown1
];