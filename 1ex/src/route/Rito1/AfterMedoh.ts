import { Chest, cps, Equipment, important, Item, Koroks, Npc, Shop, Shrines, STEP, wb } from "../../engine";

export const AfterMedoh = [
	Item("Cold Darner", "On deck"),
	Npc("BN Shrine Lady"),
	Npc("Apple Lady", "Twice"),
	Npc("Chief"),
	Chest("Great Eagle Bow", "Don't use until duped"),
	Chest("Ancient Core", "Skip if 4 cores"),
	Shrines.AkhVaquot.extend({
		notes: "Back up darner on bridge",
		splitPrefix: "{Rito 1}"
	}),
	{ type: STEP, text: wb(cps)("W Turn") },
	Npc("Voo Lota Girl"),
	{ type: STEP, text: wb(cps)("E TS") },
	Item("10 Berries"),
	Shop("Beedle Twice", "Arrows + Darner"),
	important("ANCIENT ARROW"),
	"Farm Wood",
	Equipment("2 Axes"),
	important("SAVE"),
	Item("Balloon + Voltfin", "From RNG Octoroks"),
	"RELOAD Farm Wood",
	{ type: STEP, text: wb(cps)("<S") },
	Koroks.T19
];