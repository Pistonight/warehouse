import { WindbombStepCps, Koroks, Boss, fury, Materials, Npc, Shrines, important } from "../../engine";

export const JeeNoh = [
	WindbombStepCps("SQ HIGH up hill"),
	Koroks.G17,
	WindbombStepCps("GALE + E").extend({
		ability: { gale: 1 }
	}),
	Boss("Frost Talus", fury()).extend({
		ability: { fury: 3 }
	}),
	WindbombStepCps("NE"),
	Koroks.G18,
	WindbombStepCps("SE"),
	Koroks.G23,
	WindbombStepCps("SW"),
	Koroks.G27,
	WindbombStepCps("E> Turn"),
	Koroks.G28,
	WindbombStepCps("<<E Turn"),
	Koroks.G24,
	WindbombStepCps("S. Turn"),
	Materials.Tail(2),
	WindbombStepCps("SQ to bokos"),
	Npc("Friend 1"),
	"Drop down",
	Npc("Friend 2"),
	WindbombStepCps("E. Turn"),
	Shrines.JolooNah.extend({
		timeOverride: 90
	}),

	WindbombStepCps("S>"),
	Koroks.W07,
	WindbombStepCps("S"),
	Npc("Friend 3"),
	WindbombStepCps("S midair"),
	Koroks.W14,
	WindbombStepCps("SQ to talus"),
	Boss("Luminous Talus", fury()).extend({
		ability: { fury: 3 }
	}),
	{
		important: true,
		text: important("BURN OUT FURY")
	},
	WindbombStepCps("S Turn"),
	Koroks.W26,
	Shrines.JeeNoh,

];