import { Boss, ChestSpecial, fury, important, Koroks, MakeMorning, Materials, Shrines, STEP, Warp, WindbombStepCps } from "../../engine";

export const NorthMaze = [
	Warp("Shada Naw"),
	WindbombStepCps("S + E + S"),
	Koroks.H28,
	{
		important: true,
		text: important("LOOK SKY SE")
	},
	Shrines.GomaAsaagh,

	"Start Ring Chase",
	ChestSpecial("Xenoblade"),
	Shrines.KiahToza,

	WindbombStepCps("SQ to korok"),
	Koroks.H30,
	WindbombStepCps("E"),
	Boss("Frost Talus", fury()).extend({
		ability: { fury: 3 }
	}),
	WindbombStepCps("W>> Ordinal Turn"),
	Shrines.RokUwog,

	WindbombStepCps("N + <E"),
	Koroks.H13,
	WindbombStepCps("NE"),
	Koroks.H05,
	WindbombStepCps("E Turn"),
	Shrines.ShaGehma,
	WindbombStepCps("E> Turn"),
	Koroks.H06,
	WindbombStepCps("SW"),
	Koroks.H14,
	WindbombStepCps("S + W + W"),
	Koroks.H22,
	WindbombStepCps("NE"),
	Koroks.H15,
	WindbombStepCps("<E Turn"),
	Shrines.QazaTokki,

	MakeMorning("For Dinraal (Fang)").extend({
		type: STEP
	}),
	WindbombStepCps("S"),
	Koroks.H16,
	WindbombStepCps("E> Turn"),
	Koroks.K05,
	Materials.Dinraal(1)
];