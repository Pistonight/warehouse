import { Boss, Discover, fury, Koroks, Npc, Shrines, Warp, WindbombStepCps } from "../../engine";

export const Eldin3 = [
	Warp("Rinu Honika"),
	WindbombStepCps("SQ to talus"),
	Boss("Igneo Talus", fury()).extend({
		ability: { fury: 3 },
		notes: "Get parts if you need opal"
	}),
	WindbombStepCps("SW"),
	Koroks.E28,
	WindbombStepCps("S Turn right"),
	Koroks.E32,
	WindbombStepCps(".S Turn"),
	Koroks.E43,
	"BLSS to next korok",
	Koroks.E37,
	WindbombStepCps("W"),
	Koroks.E36,
	WindbombStepCps("E + E. Turn"),
	Koroks.E42,
	Koroks.E41,
	"Jump out",
	Discover("Maw of Death Mountain"),
	WindbombStepCps("N midair"),
	Koroks.E38,
	WindbombStepCps("SQ to rubble"),
	Koroks.E40,
	WindbombStepCps("NW"),
	Koroks.E33,
	WindbombStepCps("SQ"),
	Koroks.E29,
	WindbombStepCps("E Turn"),
	Koroks.E30,
	WindbombStepCps("S to camp"),
	Koroks.E34,
	WindbombStepCps("SQ to shrine"),
	Shrines.SahDahaj,

	WindbombStepCps("<S"),
	Koroks.E39,
	WindbombStepCps("S"),
	Shrines.MoaKeet,

	WindbombStepCps(".W SQ to stable"),
	Npc("Painting Guy"),
	WindbombStepCps("S Turn"),
	Koroks.E44,
	WindbombStepCps("E. Turn"),
	Shrines.TahMuhl.extend({
		splitPrefix: "{Eldin 3}"
	}),
	WindbombStepCps("SE"),
	Koroks.E45
];