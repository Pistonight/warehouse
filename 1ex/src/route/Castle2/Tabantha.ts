import { Boss, Discover, fury, Koroks, MakeMorning, Materials, Memories, Npc, Shrines, STEP, Towers, WindbombStepCps } from "../../engine";

export const Tabantha = [
	MakeMorning("For Dinraal").extend({
		notes: "Twice if pass 12",
		type: STEP,
	}),
	WindbombStepCps("W> Turn"),
	Boss("Rare Talus", fury()).extend({
		ability: { fury: 3 }
	}),
	WindbombStepCps("<W Turn"),
	Koroks.R46.extend({
		notes: "Farm some wood here because need to wait for dinraal anyway"
	}),
	WindbombStepCps("N"),
	Koroks.R36,
	WindbombStepCps("W. Turn"),
	Koroks.R35,
	Koroks.R34,
	WindbombStepCps("W + N"),
	Koroks.T36,
	WindbombStepCps("SQ S"),
	Koroks.R33.extend({
		notes: "Turn off Daruk"
	}),
	Materials.Dinraal(1),
	WindbombStepCps("SW"),
	Koroks.R32.extend({
		notes: "Turn on Daruk"
	}),
	WindbombStepCps("N"),
	Shrines.KahOkeo,

	WindbombStepCps("GALE + N + <E").extend({
		ability: { gale: 1 }
	}),
	Koroks.T32,
	WindbombStepCps("<N"),
	Koroks.T27,
	WindbombStepCps("N> Turn"),
	Koroks.T26,
	WindbombStepCps("<N Turn"),
	Koroks.T23,
	WindbombStepCps("SE"),
	Boss("Rare Talus", fury()).extend({
		ability: { fury: 3 }
	}),
	WindbombStepCps("<S Turn"),
	Koroks.T28,
	Koroks.T29,
	WindbombStepCps("SQ to race start"),
	Koroks.T30,
	WindbombStepCps("<N"),
	"Shoot Eye",
	"Kill Boko",
	"Freeze Moblin",
	Koroks.T24,
	"BTB up tower",
	Towers.Tabantha(),

	WindbombStepCps("<N Turn"),
	Npc("Rito Monument", "Cycle shield for load"),
	WindbombStepCps("<S TS"),
	Materials.SilentPrincess(2),
	Npc("Fairy Fountain"),
	WindbombStepCps("GALE + SE").extend({
		ability: { gale: 1 }
	}),
	Koroks.T33,
	WindbombStepCps("S"),
	Shrines.TenaKosah,

	Memories.AncientColumns,
	Koroks.T35,
	WindbombStepCps("W"),
	Koroks.T37,
	WindbombStepCps("N>> Turn"),
	Koroks.T31,
	WindbombStepCps("N> Turn"),
	Boss("Rare Talus", fury()).extend({
		ability: { fury: 3 }
	}),
	WindbombStepCps("<E"),
	Koroks.T25,
	MakeMorning("For DLC Dinraal Horn"),
	WindbombStepCps("E + <E Turn"),
	Koroks.R05,
	Discover("Footrace CHeck-In"),
	WindbombStepCps(".N Turn"),
	Materials.Dinraal(1).extend({
		notes: "Want to be fast here or horn stops glowing. There's 1 backup later"
	}),
	Koroks.R04,
	WindbombStepCps("W + W start race"),
	Koroks.H73,
	"Drop down",
	Shrines.DunbaTaag.extend({
		splitPrefix: "{Tabantha}"
	})

];