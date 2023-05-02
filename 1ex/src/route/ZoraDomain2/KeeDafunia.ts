import { Koroks, MakeMorning, Shrines, STEP, WindbombStepCps } from "../../engine";

export const KeeDafunia = [
	WindbombStepCps("SW midair"),
	Koroks.A55,
	WindbombStepCps("<N"),
	Koroks.A54,
	WindbombStepCps("<E Turn"),
	Koroks.A53,
	WindbombStepCps("E Turn"),
	Koroks.Z07,
	"Drop down",
	Koroks.Z06,
	WindbombStepCps("N"),
	Koroks.Z01,
	Shrines.KahMael,
    
	MakeMorning("For Shrine").extend({ type: STEP }),
	WindbombStepCps("GALE + SE").extend({
		ability: { gale: 1 }
	}),
	Shrines.KeeDafunia
    
];