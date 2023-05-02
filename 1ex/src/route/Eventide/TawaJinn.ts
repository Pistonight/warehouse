import { Boss, fury, important, Koroks, MakeNight, Materials, Shrines, txt, Warp, WindbombStepCps } from "../../engine";

export const TawaJinn = [
	Warp("Hateno Tower"),
	WindbombStepCps("W"),
	Koroks.D53,
	WindbombStepCps("W + S"),
	Koroks.D58,
	WindbombStepCps("GALE + S>").extend({
		ability: { gale: 1 }
	}),
	Koroks.D57,
	WindbombStepCps("<<S"),
	Koroks.F07,
	MakeNight("For beetle no rain"),
	WindbombStepCps("SW"),
	Koroks.F13,
	WindbombStepCps("GALE + SE").extend({
		ability: { gale: 1 }
	}),
	Boss("Black Hinox", txt("5 hits SLAM ", fury())).extend({
		ability: { fury: 1 }
	}),
	"Orb 1",
	WindbombStepCps("<<E Turn"),
	Koroks.F14,
	Materials.Beetle(2).extend({
		notes: "Get to 13+ beetles"
	}),
	Boss("Red Hinox", txt("1 hit SLAM ", fury())).extend({
		ability: { fury: 1 }
	}),
	"Orb 2",
	{
		important: true,
		text: important("READ STONE"),
	},
	WindbombStepCps("SQ"),
	Materials.Honey(5, "Need 13 here"),
	Boss("Blue Hinox", txt("3 hit SLAM ", fury())).extend({
		ability: { fury: 1 }
	}),
	{
		text: "Orb 3",
		timeOverride: 20
	},
	Shrines.TawaJinn
    
];