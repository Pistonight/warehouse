import { Boss, ChestSpecial, Discover, fury, gale, Item, Koroks, Materials, Memories, Npc, Shrines, txt, WindbombStepCps } from "../../engine";

export const AfterCastle = [
	WindbombStepCps("S Super"),
	Koroks.C33,
	WindbombStepCps("S> Turn"),
	Koroks.C35,
	Memories.SacredGrounds,
	WindbombStepCps("S>>> Turn"),
	Koroks.C43,
	WindbombStepCps("W>"),
	Koroks.C42,
	WindbombStepCps("W"),
	Koroks.C41,
	Koroks.C40,
	WindbombStepCps("<W"),
	Koroks.C39,
	WindbombStepCps("W. Turn"),
	Koroks.C38,
	WindbombStepCps("N SQ"),
	Boss("Black Hinox", txt(fury(), " + ", "2 Shots")).extend({
		ability: { fury: 3 },
		suppressError: ["FuryRecharge" as const]
	}),
	WindbombStepCps("SW"),
	Materials.Tail(1, "Sneakstrike lizalfos"),
	Item("18 Acorns"),
	{
		important: true,
		text: "Dupe Bows",
		notes: "KEEP SINGLE SHOT"
	},
	Koroks.C36,
	ChestSpecial("Midna's Helm"),
	WindbombStepCps("W> SQ"),
	Koroks.R49,
	WindbombStepCps("N"),
	Koroks.R48,
	WindbombStepCps("NE"),
	Koroks.R42,
	WindbombStepCps("NE"),
	Koroks.R43.extend({
		timeOverride: 100
	}),
	"Drop down",
	{
		text: gale(),
		ability: { gale: 1 }
	},
	Koroks.R45.extend({
		timeOverride: 100
	}),
	Koroks.R44,
	WindbombStepCps("N>"),
	Koroks.C24,
	WindbombStepCps("N> SQ"),
	Koroks.R27,
	Koroks.R26,
	WindbombStepCps("W High"),
	Shrines.ZaltaWa,

	WindbombStepCps("S + S>"),
	Boss("Blue Hinox", fury()).extend({
		ability: { fury: 3 },
	}),
	Koroks.R40,
	WindbombStepCps("NW"),
	Koroks.R25,
	WindbombStepCps("S. Turn"),
	Koroks.R39,
	WindbombStepCps("<S SQ"),
	Koroks.R41,
	Npc("Kass"),
	Discover("Jeddo Bridge"),
	WindbombStepCps("W"),
	Shrines.SheemDagoze,

	WindbombStepCps("N"),
	Koroks.R38,
	WindbombStepCps("S>"),
	Koroks.R47,
	WindbombStepCps("W> Turn"),
	Koroks.R37,
	WindbombStepCps("<W + W midairs"),
	Npc("Kass"),
	Shrines.MijahRokee.extend({
		splitPrefix: "{Bloodmoon}"
	})
];