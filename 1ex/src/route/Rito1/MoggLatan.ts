import { Chest, cps, Equipment, Item, Koroks, Shrines, STEP, Warp, wb } from "../../engine";

export const MoggLatan = [
	Warp("Kaam Ya'tak"),
	Chest("Ancient Core", "Skip if 1 core").extend({
		notes: "5 Cores: This, Medoh, Akq, Ruta, Dah Kaso"
	}),
	Shrines.KaamYatak,
	
	{ type: STEP, text: wb(cps)("<N") },
	Equipment("Great Flameblade").extend({
		notes: "Blue moblin can only do quarter heart dmg"
	}),
	{ type: STEP, text: wb(cps)("S") },
	"BTB to Durians",
	Item("10 Durians", "And wood"),
	{ type: STEP, text: wb(cps)("<W") },
	Item("4 Endura Carrots"),
	"Run up",
	Koroks.R60,
	Shrines.MoggLatan,
];