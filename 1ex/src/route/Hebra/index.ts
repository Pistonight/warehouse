import { Section } from "../../engine";
import { Coldsnap } from "./Coldsnap";
import { FlightRange } from "./FlightRange";
import { HebraSkeleton } from "./HebraSkeleton";
import { HyruleRidge } from "./HyruleRidge";
import { MakaRah } from "./MakaRah";
import { NorthMaze } from "./NorthMaze";
import { TarryTownRito } from "./TarryTownRito";

export const Hebra = [
	Section("HEBRA"),
	...FlightRange,
	...TarryTownRito,
	...NorthMaze,
	...Coldsnap,
	...HyruleRidge,
	...HebraSkeleton,
	...MakaRah
];