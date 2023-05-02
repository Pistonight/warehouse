import { Boss, fury, important, Koroks, MakeNight, MakeNoon, Npc, Section, Shrines, txt, Warp, WindbombStepCps } from "../../engine";

export const Akkala2 = [
	Section("AKKALA 2"),
	Warp("Travel Medallion"),
	Npc("Hudson Twice", "50 Wood"),
	MakeNight("For Stalnox"),
	"Place TM near Rhonson",
	WindbombStepCps("NE"),
	Koroks.A34,
	WindbombStepCps("SE + SE"),
	Koroks.A44,
	"Shield surf down",
	Koroks.A36,
	Koroks.A35,
	Npc(important("READ STONE"), "DO NOT FORGET"),
	"BLSS with ball",
	Shrines.RitaagZumo,

	Koroks.A33,
	WindbombStepCps("W Turn"),
	Boss("Stalnox", txt("1 Shot + ", fury())).extend({
		ability: { fury: 3 }
	}),
	WindbombStepCps("GALE + N> Turn").extend({
		ability: { gale: 1 }
	}),
	Koroks.A29,
	WindbombStepCps("W"),
	Koroks.A28,
	WindbombStepCps("<N Turn"),
	Koroks.A19,
	WindbombStepCps("SW SQ"),
	Koroks.A22,
	WindbombStepCps(".E Turn"),
	Shrines.KatosaAug.extend({
		splitPrefix: "{Akkala 2}"
	}),

	Npc("Guard", "Twice"),
	MakeNoon(),
	Npc("Quest Lady", "2nd option"),
	WindbombStepCps("E"),
	Koroks.A23,
	WindbombStepCps("E> SQ"),
	Koroks.A24,
	WindbombStepCps("N. Turn"),
	Koroks.A17,
	WindbombStepCps("N Turn"),
	Koroks.A09,
	WindbombStepCps("W. Turn"),
	Koroks.A08
];