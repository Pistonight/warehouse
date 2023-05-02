import { Boss, Discover, fury, Koroks, Shrines, txt, WindbombStepCps } from "../../engine";

export const MakaRah = [
	WindbombStepCps("<W Turn"),
	Shrines.MozoShenno,
	WindbombStepCps("GALE + <E Turn").extend({
		ability: { gale: 1 },
	}),
	Koroks.H26,
	WindbombStepCps("N> + N midair"),
	Koroks.H19,
	WindbombStepCps("W. Turn"),
	Koroks.H18,
	WindbombStepCps("W Turn"),
	Koroks.H17,
	WindbombStepCps("S Turn"),
	Koroks.H24,
	WindbombStepCps("E> Turn + E"),
	Koroks.H25,
	WindbombStepCps("<W Turn + W midair"),
	Koroks.H32,
	WindbombStepCps("<S Turn"),
	Koroks.H40,
	WindbombStepCps("W>> Turn"),
	Boss("Black Hinox", txt("1 Shot + ", fury())).extend({
		ability: { fury: 3 }
	}),
	WindbombStepCps("S"),
	"Start race",
	WindbombStepCps("SE"),
	Koroks.H53,
	WindbombStepCps("SW"),
	Koroks.H60,
	WindbombStepCps("S"),
	Koroks.T08,
	Koroks.T06,
	"Run",
	Koroks.T07,
	WindbombStepCps("NE"),
	Koroks.T01,
	WindbombStepCps("N + <N"),
	Koroks.H39,
	"Drop down",
	Discover("Sturnida Secret Spring"),
	WindbombStepCps("GALE + S").extend({
		ability: { gale: 1 }
	}),
	Koroks.H41,
	"Surf down",
	Koroks.H42,
	WindbombStepCps("E"),
	Shrines.MakaRah.extend({
		splitPrefix: "{Hebra}"
	})
];