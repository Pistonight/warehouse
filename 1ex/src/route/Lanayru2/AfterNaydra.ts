import { Boss, fury, important, Koroks, Materials, Npc, Shrines, WindbombStepCps } from "../../engine";

export const AfterNaydra = [
	WindbombStepCps("E>>"),
	Koroks.Z53,
	Koroks.Z52,
	WindbombStepCps("SE"),
	Koroks.Z54,
	WindbombStepCps("E"),
	Koroks.Z55,
	WindbombStepCps("E> Turn"),
	Koroks.N12,
	WindbombStepCps("<N Turn"),
	Koroks.Z45,
	WindbombStepCps("<<E"),
	Koroks.Z46,
	WindbombStepCps("NE"),
	Koroks.Z36,
	WindbombStepCps("<S Turn"),
	Boss("Blue Hinox", fury()).extend({
		ability: { fury: 3 }
	}),
	WindbombStepCps("S"),
	Koroks.Z58,
	WindbombStepCps(".W"),
	"Bomb rubbles",
	Koroks.Z56,
	WindbombStepCps("SQ to kass"),
	Npc("Kass"),
	Shrines.ShaiYota.extend({
		splitPrefix: "{Lanayru 2}"
	}),

	WindbombStepCps("<S + S"),
	Materials.Tail(3),
	Koroks.Z57,
	WindbombStepCps("E"),
	Koroks.Z59,
	WindbombStepCps("S> Turn"),
	Koroks.Z62,
	Koroks.Z61,
	WindbombStepCps("S> Turn + S"),
	Koroks.N25,
	WindbombStepCps("<S Turn"),
	Koroks.N40,
	WindbombStepCps("S> + S midairs"),
	Koroks.N55,
	WindbombStepCps("SQ to beach"),
	Boss("Luminous Talus", fury()).extend({
		ability: { fury: 3 }
	}),
	{
		text: important("BURN OUT FURY"),
		important: true
	},
	Koroks.N63,
	Koroks.N57,
	WindbombStepCps("SQ west ish"),
	Koroks.N56

];