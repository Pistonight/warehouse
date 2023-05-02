import { Section } from "../../engine";
import { BoshKala } from "./BoshKala";
import { CentralTower } from "./CentralTower";
import { HilaRao } from "./HilaRao";

export const Central2=[
	Section("CENTRAL 2"),
	...CentralTower,
	...HilaRao,
	...BoshKala
];