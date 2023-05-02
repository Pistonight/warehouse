import { Chest, emy, gale, Item, itm, Koroks, Npc, rne, Section, Shop, Shrines, SPLIT, STEP, txt, WindbombStepCps } from "../../engine";

export const ZoraDomain1 = [
	Section("ZORA DOMAIN 1"),
	{ type: STEP, text: "BLSS to top of ZD" },
	Koroks.Z17.extend({
		notes: "Might want to menu to load"
	}),
	Koroks.Z16,
	Npc("Zora King", "Equip Zora Armor"),
	Shop("All Arrows + Violets"),
	Item("4 Lotus", "Around shrine"),
	Shrines.NeezYohma,

	{
		text: "Mipha Memory",
		type: SPLIT,
		icon: "ruta-memory"
	},
	WindbombStepCps("E + E>"),
	{
		text: "Enter Ruta",
		type: SPLIT,
		icon: "ruta-enter"
	},
	Chest("Ancient Core", "Skip if 1 core").extend({
		notes: "After second to last terminal, stasis stone and cryo last terminal",
	}),
	{
		text: txt(gale(), " inside Ruta"),
		ability: { gale: 1 }
	},
	{
		text: emy("Waterblight 1"),
		type: SPLIT,
		icon: "ruta-done",
		notes: "No snap. ATK 3 BA headshots until BT ends (9 ok, 10 safe)",
		comment: txt("Equip ", rne("CRYONIS")),
	},

	WindbombStepCps("N> Delay to King"),
	Chest("Lightscale Trident"),//flameblade, axe, axe, hammer, hammer, EOD, trident
	Npc("Shrine Girl", "SPA area"),
	Npc("Fronk", "8PM at SPA area"),
	WindbombStepCps("W"),
	itm("1 Lotus"),
	Shrines.DagahKeek.extend({
		notes: "Check 1 empty slot before warping",
		splitPrefix: "{Zora's Domain 1}"
	}),
];