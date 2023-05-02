import { cps,important, Koroks, lnk, Materials, Section, SnapElite, STEP, txt, wb, WindbombStepCps } from "../../engine";

export const Faron1 = [
	Section("FARON 1"),
	{ type: STEP, text: wb(cps)("<W TS + <W midair") },
	Koroks.F06,
	{ type: STEP, text: wb(cps)("SW SQ") },
	Koroks.F09,
	WindbombStepCps("W SQ"),
	Koroks.F08,
	{ type: STEP, text: wb(cps)("E") },
	Koroks.F12,
	{ type: STEP, text: wb(cps)("W") },
	Koroks.F11,
	{ type: STEP, text: wb(cps)("<W Turn") },
	{
		text: "Farosh (24H 3S 2C 1F)",
		notes: "Use Flameblade to lit campfire",
		important: true,
	},
	SnapElite("Farosh"),
	{ 
		type: STEP, 
		text: wb(cps)("SW Super"),
		notes: txt("Example beetle section by BingsF: ",lnk("https://discordapp.com/channels/269611402854006785/298175033224724500/911119688963072001"))
	},
	important("EAT STEALTH"),
	Materials.Beetle(5),
];