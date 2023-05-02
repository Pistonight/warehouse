import { Boss, cps, Equipment, fury, gale, Koroks, rne, Shrines, SPLIT, STEP, txt, Warp, wb, WindbombStepCps } from "../../engine";

export const KuhnSidajj = [
	Warp("Keo Ruug"),
	Shrines.KeoRuug,
	{
		text: rne("Trial of the Sword"),
		icon: "tots",
		comment: "Skip BM in Beginning Trial",
		type: SPLIT
	},
    
	WindbombStepCps("<E"),
	"Do quest",
	Shrines.MaagHalan,

	{
		type: STEP,
		text: txt(gale(), " + ", wb(cps)("NE + W> midair")),
		ability: { gale: 1 }
	},
	Koroks.K08,
	WindbombStepCps("NW"),
	Boss("Blue Hinox", fury()).extend({
		ability: { fury: 3 }
	}),
	Shrines.KetohWawai,
	WindbombStepCps("S"),
	Koroks.K07,
	WindbombStepCps("W>"),
	Koroks.K06,
	{
		type: STEP,
		text: txt(wb(cps)("SW"), " + ",gale(), " + " ,wb(cps)("SW midair")),
		ability: { gale: 1 }
	},
	Koroks.K11,
	WindbombStepCps("<S Turn"),
	Boss("Blue Hinox", "5 Spin SLAM"),
	WindbombStepCps("<S"),
	Koroks.K27,
	WindbombStepCps("E SQ"),
	Koroks.K28,
	WindbombStepCps("<W Turn"),
	Equipment("Rusty Shield", "Keep for korok"),
	Shrines.KuhnSidajj.extend({
		splitPrefix: "{Korok Forest}"
	})
];