import { Chest, gale, Koroks, MakeMorning, Materials, Npc, Section, Shrines, txt, Warp, WindbombStepCps } from "../../engine";

export const ForgottenTemple = [
	Section("ALL SHRINES"),
	Warp("Goma Asaagh"),
	Koroks.H35,
	WindbombStepCps("E>"),
	Koroks.H36,
	WindbombStepCps("N Turn"),
	Koroks.H29,
	WindbombStepCps("W>"),
	Koroks.H31,
	WindbombStepCps("W>> Turn"),
	Koroks.H37,
	WindbombStepCps("SE"),
	Npc("Stalhorse Lady", "Twice"),
	Shrines.RinOyaa,

	MakeMorning("If missed scale dupe"),
	WindbombStepCps("W. Turn"),
	Koroks.H52,
	WindbombStepCps("<E Turn"),
	Koroks.H58,
	WindbombStepCps(".S Turn"),
	Koroks.H62,
	Materials.Dinraal(1, "If need"),
	WindbombStepCps("NE"),
	Koroks.H59,
	WindbombStepCps("N> Turn"),
	Koroks.H38,
	WindbombStepCps("E> Turn"),
	Koroks.K10,
	WindbombStepCps("<W + S"),
	Koroks.K12,
	WindbombStepCps("<W Turn"),
	Koroks.K13,
	WindbombStepCps("SW"),
	Koroks.H63,
	WindbombStepCps("SQ"),
	Koroks.K15,
	WindbombStepCps("<<E Turn"),
	Koroks.K14,
	{
		text: txt(gale(), " BLSS to end"),
		ability: { gale: 1 }
	},
	Koroks.K09,
	"SQ to shrine",
	Shrines.RonaKachta.extend({
		splitPrefix: "{Forgotten Temple}"
	}),

	Chest("Wild Set"),
];