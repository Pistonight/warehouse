import { Section } from "../../engine";
import { FaronTower } from "./FaronTower";
import { KahYah } from "./KahYah";
import { ShaiUtoh } from "./ShaiUtoh";

export const Faron2 = [
	Section("FARON 2"),
	...FaronTower,
	...KahYah,
	...ShaiUtoh
];