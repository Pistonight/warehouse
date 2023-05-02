import { Boss, Koroks, MakeNight, Shrines, WindbombStepCps } from "../../engine";

export const IshtoSoh = [
	WindbombStepCps("N> Turn"),
	Koroks.G36,
	WindbombStepCps("SE"),
	Koroks.W20,
	Koroks.W21,
	WindbombStepCps("<S"),
	Koroks.W27,
	WindbombStepCps("<E Turn"),
	Koroks.W22,
	WindbombStepCps("W + W + S"),
	Koroks.L23,
	WindbombStepCps("<N Turn"),
	Koroks.P17,
	Koroks.P16,
	WindbombStepCps("S> DT"),
	Koroks.L21,
	WindbombStepCps("E> Turn"),
	Koroks.L37,
	WindbombStepCps("<W + <W"),
	Koroks.L41,
	WindbombStepCps("E> Slight turn"),
	Boss("Luminous Talus", "GEB down").extend({
		notes: "Don't use fury here if they are up. Save 3 furies for the next stalnox"
	}),
	WindbombStepCps("S Turn"),
	MakeNight("For Stalnox if need"),
	Shrines.IshtoSoh
];