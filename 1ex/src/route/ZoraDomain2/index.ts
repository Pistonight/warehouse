import { Section } from "../../engine";
import { KeeDafunia } from "./KeeDafunia";
import { KenaiShakah } from "./KenaiShakah";
import { MahEliya } from "./MahEliya";
import { LetterQuest } from "./LetterQuest";
import { ProtectionPlus } from "./ProtectionPlus";

export const ZoraDomain2 = [
	Section("ZD 2 + Refights"),
	...MahEliya,
	...KenaiShakah,
	...KeeDafunia,
	...LetterQuest,
	...ProtectionPlus
];