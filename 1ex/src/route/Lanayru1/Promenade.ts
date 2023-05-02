import { Boss, fury, Koroks, Memories, Shrines, WindbombStepCps } from "../../engine";

export const Promenade = [
	WindbombStepCps("<S Turn"),
	Koroks.N05,
	WindbombStepCps(".E Turn"),
	Koroks.N06,
	WindbombStepCps("<S Turn"),
	Boss("Blue Hinox", fury()).extend({
		ability: { fury: 3 },
	}),
	WindbombStepCps("GALE + SW").extend({
		ability: { gale: 1 }
	}),
	Koroks.N13,
	WindbombStepCps("W Turn"),
	Koroks.N11,
	WindbombStepCps("<S"),
	Boss("Red Hinox", "4BA"),
	WindbombStepCps("E>>"),
	Koroks.N20,
	"Run down",
	Shrines.DowNaeh,

	WindbombStepCps("<N Turn"),
	Koroks.N15,
	WindbombStepCps("SQ to middle"),
	Koroks.N16,
	WindbombStepCps("E super + E"),
	Memories.LanayruRoad.extend({
		splitPrefix: "{Lanayru 1}"
	})
];