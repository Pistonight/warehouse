import { Boss, Equipment, fury, Koroks, Npc, Shrines, SnapElite, WindbombStepCps } from "../../engine";

export const KamiaOmuna = [
	WindbombStepCps("<<N"),
	Koroks.E21,
	WindbombStepCps("W"),
	Koroks.E20,
	WindbombStepCps("<N"),
	Equipment("Drilshaft"),
	WindbombStepCps("W. Turn"),
	Npc("Drillshaft Kid").extend({
		notes: "Yeet drillshaft afterwards to have slot for ancient sword"
	}),
	WindbombStepCps("<N Turn"),
	Npc("Talus Dude"),
	Npc("Amber Lady"),
	WindbombStepCps("<N Super"),
	Koroks.E10,
	WindbombStepCps("E + E"),
	Koroks.E11,
	WindbombStepCps("N>"),
	Koroks.E09,
	Boss("Igneo Talus", fury()).extend({
		ability: { fury: 3 },
		notes: "Pick up parts until 11 flints"
	}),
	WindbombStepCps("<E Turn"),
	Koroks.E06,
	WindbombStepCps("W> Turn"),
	SnapElite("Igneo Talus Titan"),
	Boss("Igneo Talus Titan", "Spin-to-win"),
	Shrines.KamiaOmuna.extend({
		splitPrefix: "{Eldin 2}"
	})
];