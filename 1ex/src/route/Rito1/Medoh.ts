import { Chest, Cook, cps, emy, Equipment, important, itm, Koroks, Npc, setImportant, Shop, SPLIT, STEP, wb, WindbombStepCps } from "../../engine";

export const Medoh = [
	WindbombStepCps("<S"),
	Koroks.H20,
	{ type: STEP, text: "BLSS to Rito" },
	...setImportant(
		Npc("Chief + Wife"),
		{
			text: "Revali Memory",
			icon: "medoh-memory",
			type: SPLIT,
		},
		"Drop down",
		{
			text: itm("Cold Darner"),
			notes: "Should be day time here. If it's night time you can grab 2 later"
		},
		"Drop down to shop",
	),
	Shop("Buy Everything").extend({
		notes: "Gear Combo: Other: Drop 8 Shafts and sell all shafts."
	}),
	{ type: STEP, text: wb(cps)(".N TS") },
	Equipment("Bow + Arrows"),
	Cook("Hearty + Endura", "Save 1 durian"),
	"Do minigame",
	Chest("Falcon Bow"),
	{
		icon: "medoh-enter",
		text: "Enter Medoh",
		comment: important("EAT COLD PROTECTION"),
		type: SPLIT
	},
	Chest("Ancient Core", "Skip if 5 cores"),
	{
		icon: "medoh-done",
		text: emy("Windblight 1"),
		comment: "EQUIP Falcon + BA",
		type: SPLIT,
		notes: "3 BA, Royal Wait, Glide 2 BA | Falcon 3BA, Royal 2BA Immediately"
	},
];

