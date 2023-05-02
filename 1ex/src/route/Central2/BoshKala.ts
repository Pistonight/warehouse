import { Boss, Discover, fury, Koroks, Shrines, WindbombStepCps } from "../../engine";

export const BoshKala = [
	WindbombStepCps("SE"),
	Discover("Eagus Bridge"),
	Koroks.D01,
	WindbombStepCps("<S Turn"),
	Koroks.D16,
	WindbombStepCps("<<W"),
	Koroks.D25,
	WindbombStepCps("<S Turn"),
	Koroks.D29,
	WindbombStepCps("W"),
	Koroks.D28,
	WindbombStepCps("NW"),
	Koroks.D23,
	WindbombStepCps("E"),
	Koroks.D24,
	Boss("Stone Talus", fury()).extend({
		ability: { fury: 1 }
	}),
	WindbombStepCps("N>"),
	Koroks.D15,
	WindbombStepCps("W> Turn"),
	Koroks.D13,
	WindbombStepCps("<W Turn"),
	Koroks.C77,
	WindbombStepCps("SQ to bridge"),
	Koroks.D14,
	WindbombStepCps("S"),
	Koroks.D22,
	WindbombStepCps(".W Turn"),
	Koroks.C84,
	WindbombStepCps("S"),
	Koroks.D26,
	WindbombStepCps("SQ to shrine"),
	Shrines.BoshKala.extend({
		splitPrefix: "{Central 2}"
	})
    
];