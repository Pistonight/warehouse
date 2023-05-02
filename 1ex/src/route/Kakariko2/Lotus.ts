import { cps, Discover, important, Item, itm, Koroks, Materials, Npc, Shop, Shrines, SnapQuest, STEP, Warp, wb } from "../../engine";

export const Lotus = [
	Warp("Taloh Naeg"),
	{ type: STEP, text: "SQ to farmer" },
	Npc("Pumpkin Man"),
	{ type: STEP, text: "SQ to shops" },
	Shop("Arrows"),
	Shop("Carrots"),
	Npc("Impa"),
	{ type: STEP, text: wb(cps)("<<E") },
	Materials.SilentPrincess(3),
	Item("Backup Endura Carrots"),
	itm("4 Fairies"),
	Npc("Fairy Fountain"),
	SnapQuest("Fairy Fountain"),
	{ type: STEP, text: wb(cps)("N Super") },
	Koroks.N01,
	{ type: STEP, text: wb(cps)("SW Super") },
	Koroks.Z60,
	important("EAT SPEED"),
	{ type: STEP, text: wb(cps)("N>>") },
	Koroks.Z50,
	{ type: STEP, text: wb(cps)("<N Turn") },
	Shrines.DakaTuss.extend({
		notes: "2 Hearts dmg with 2 pieces of phantom armor. Use a fairy if need. (Eating takes too long)"
	}),
	{ type: STEP, text: wb(cps)("<W Turn") },
	Koroks.Z49,
	{ type: STEP, text: wb(cps)("W> SQ") },
	Koroks.Z48,
	{ type: STEP, text: wb(cps)("<W Turn") },
	Koroks.Z47,
	{ type: STEP, text: wb(cps)("N>> Turn") },
	Koroks.Z38,
	{ type: STEP, text: wb(cps)("SE") },
	Discover("Goponga Village Ruins"),
	{ type: STEP, text: wb(cps)("N Midair") },
	Koroks.Z39,
	"Start farming Lotus",
	Koroks.Z40,
	Item("27 Lotus"),
    
];