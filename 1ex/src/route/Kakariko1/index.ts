import { cps, MakeMorning, Materials, Npc, Section, Shop, Shrines, STEP, Warp, wb } from "../../engine";

export const Kakariko1 = [
	Section("KAKARIKO 1"),
	Warp("Keh Namut"),
	"BLSS to Impa",
	MakeMorning("For beetle & Farmer"),
	Shop("Material Shop", "Carrot (Gut)"),
	Materials.Beetle(1),
	Shop("Arrow Shop", "All Arrows"),
	Npc("Fire Lady", "Twice"),
	Npc("Statue", "2 Hearts"),
	{ type: STEP, text: wb(cps)("N to shrine") },
	Shrines.TalohNaeg.extend({
		splitPrefix: "{Kakariko 1}"
	}),

];