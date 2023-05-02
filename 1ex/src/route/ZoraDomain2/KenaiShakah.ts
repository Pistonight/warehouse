import { Boss, fury, important, Koroks, Shrines, WindbombStepCps } from "../../engine";

export const KenaiShakah = [
	WindbombStepCps("<W Turn"),
	Koroks.Z08,
	WindbombStepCps("SE"),
	Koroks.Z19,
	"Run to flower",
	Koroks.Z20,
	WindbombStepCps("S> Turn"),
	Boss("Luminous Talus", fury()).extend({
		ability: { fury: 3 }
	}),
	{
		important: true,
		text: important("BURN OUT FURY")
	},
	WindbombStepCps("<E"),
	Koroks.Z27,
	WindbombStepCps("<S Turn"),
	Koroks.Z35,
	WindbombStepCps("GALE + NE + <N").extend({
		ability: { gale: 1 }
	}),
	Koroks.Z28,
	WindbombStepCps("<<W Turn"),
	Koroks.Z10,
	WindbombStepCps("NW"),
	Koroks.Z09,
	WindbombStepCps("W"),
	Koroks.A56,
	WindbombStepCps("<W"),
	Koroks.A57,
	WindbombStepCps("N. Turn"),
	Shrines.KenaiShakah,

];