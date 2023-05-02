import { Boss, emy, Equipment, fury, Koroks, Npc, Shrines, STEP, WindbombStepCps } from "../../engine";

export const KorguChideh = [
	WindbombStepCps("E> Turn"),
	Koroks.F22,
	WindbombStepCps("NE"),
	Koroks.F18,
	"Stasis apple and bomb",
	Koroks.N64,
	Boss("Red Hinox", "shoot from above"),
	WindbombStepCps("S + S"),
	Koroks.F32,
	WindbombStepCps("E> + E"),
	Shrines.MuwoJeem,

	"Run",
	Koroks.F33,
	WindbombStepCps("W> Turn (drop down)"),
	Koroks.N65,
	WindbombStepCps("N Turn"),
	Koroks.N61,
	WindbombStepCps("E Turn"),
	Npc("Kass"),
	WindbombStepCps("<N Turn"),
	Koroks.N60,
	WindbombStepCps(".E Turn"),
	{
		text: emy(fury(), " kill camp"),
		ability: { fury: 1 }
	},
	WindbombStepCps("NE"),
	Koroks.N54,
	WindbombStepCps("S> Turn"),
	Koroks.N62,
	WindbombStepCps("S> Turn"),
	Shrines.ChaasQeta,

	Koroks.N66.extend({
		type: STEP
	}),
	WindbombStepCps("<S Turn + <S midairs"),
	"throw sword + arrow, 2 shots",
	Boss("Blue Hinox", fury()).extend({
		ability: { fury: 2 },
		notes: "Can also fury camp"
	}),
	"Orb 1 (camp)",
	Equipment("Potlid"),
	WindbombStepCps("NE"),
	"Orb 2 (BLSS)",
	WindbombStepCps("S + <S"),
	"Orb 3",
	Shrines.KorguChideh.extend({
		splitPrefix: "{Eventide}"
	})
];