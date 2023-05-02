import { Discover, emy, fury, Koroks, SnapQuest, SPLIT, STEP, Warp, WindbombStepCps } from "../../engine";

export const ProtectionPlus = [
	Warp("Rudania"),
	{
		text: emy("Fireblight 2"),
		icon: "rudania-plus",
		type: SPLIT,
		ability: { fury: 2 },
		comment: fury(),
	},
	{
		text: "Do Ring Chase",
		timeOverride: 60,
		type: STEP
	},
	Discover("Sharo Lun Shrine"),
	WindbombStepCps("SQ down"),
	Discover("Shadow Hamlet Ruins"),
	WindbombStepCps("GALE + NE").extend({
		ability: { gale: 1 }
	}),
	Koroks.E12,
	WindbombStepCps("NW"),
	Koroks.A10,
	WindbombStepCps("E"),
	SnapQuest("Blupee"),
	Koroks.A11,
	WindbombStepCps("E> Turn"),
	Koroks.A12
];