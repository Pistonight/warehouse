import { Section } from "../../engine";
import { BalloonGirl } from "./BalloonGirl";
import { CastleTown } from "./CastleTown";
import { Maritta } from "./Maritta";
import { ShehRata } from "./ShehRata";

export const Central1 = [
	Section("CENTRAL 1"),
	...ShehRata,
	...BalloonGirl,
	...CastleTown,
	...Maritta
];