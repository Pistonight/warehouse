import { Chest, cps, important, Item, Koroks, Shrines, STEP, txt, wb } from "../../engine";

export const KuhTakkar = [
	{ type: STEP, text: txt("BLSS + ", cps("S")) },
	Koroks.G19,
	important("EAT LONG COLD PROTECTION"),
	{ type: STEP, text: wb(cps)("NW + W") },
	Koroks.G13,
	{ type: STEP, text: wb(cps)("N + W") },
	Koroks.G10,
	{ type: STEP, text: wb(cps)("W + S HIGH") },
	Koroks.G12,
	{ type: STEP, text: wb(cps)("<S Turn") },
	Item("2 Truffles"),
	{
		text: "Start melting ice",
		notes: "Farm luminous stones while melting ice, but don't need to break all the ores. There are plenty of luminous stones later."
	},
	Chest("5 Fire Arrows"),
	Shrines.KuhTakkar,
];