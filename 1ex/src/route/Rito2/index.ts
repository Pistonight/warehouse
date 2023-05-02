import { Boss, emy, EngineCommands, fury, Item, itm, Koroks, MakeMorning, MakeNoon, Materials, Npc, Section, Shop, Shrines, SnapElite, SPLIT, STEP, txt, Warp, WindbombStepCps } from "../../engine";

export const Rito2 = [
	Section("RITO 2"),
	Warp("Medoh"),
	SnapElite("Windblight"),
	{
		text: emy("Windblight 2"),
		icon: "medoh-plus",
		comment: fury(),
		notes: "EQUIP STASIS. Phase 1: 2 fury + stasis 5BA duplex bow. Phase 2: fury, snap, throw sword, 17 shots (8 then stasis then 9)",
		type: SPLIT,
		ability: { fury: 3 },
		suppressError: ["FuryRecharge" as const],
		command: [EngineCommands.EnableGalePlus]
	},

	MakeNoon("For Bareeda Naag").extend({ type: STEP }),
	"Drop down",
	Koroks.T14,
	Npc("Little Bird Singing"),
	Npc("Kheel Mom"),
	WindbombStepCps("SQ to platform"),
	Koroks.T15,
	WindbombStepCps("SQ to pond"),
	Npc("Fish Girl"),
	WindbombStepCps(".W"),
	Npc("Mozo Shenno Girl"),
	"Drop down to shop",
	Npc("Shop Girl"),
	Shop("All Arrows").extend({
		notes: "Can skip if bought bomb arrows in gerudo town 2"
	}),
	"Run up 3 houses",
	Npc("Cook Girl"),
	"Drop down",
	Npc("Fyson"),
	"Run up next house",
	Npc("Flint Guy", "Twice"),
	"Drop out",
	WindbombStepCps("W Turn"),
	Koroks.T13,
	Shrines.VooLota,

	itm("Pepper pause unpause"),
	Koroks.T16,
	WindbombStepCps("W> Turn"),
	Koroks.T12,
	WindbombStepCps("GALE + N>").extend({
		ability: { gale: 1 }
	}),
	Koroks.T09,
	WindbombStepCps("GALE + E super").extend({
		ability: { gale: 1 }
	}),
	Koroks.T10,
	WindbombStepCps("S"),
	Koroks.T17,
	WindbombStepCps("S>>"),
	Boss("Black Hinox", txt("1 Shot + ", fury())).extend({
		ability: { fury: 3 }
	}),
	WindbombStepCps("SQ to shrine"),
	Shrines.BereedaNaag.extend({
		splitPrefix: "{Rito 2}"
	}),
	WindbombStepCps("N>>"),
	Npc("Guard", "Twice"),
	Item("Farm Wood", ">=51").extend({
		timeOverride: 50
	}),
	WindbombStepCps("S TS"),
	Koroks.T21,
	WindbombStepCps("<W Turn"),
	Koroks.T20,
	WindbombStepCps("E Turn"),
	Koroks.T22,
	Materials.Tail(4).extend({
		timeOverride: 40
	}),
	WindbombStepCps("N + NE"),
	Koroks.H72,
	WindbombStepCps("W> (aim at medoh)"),
	Koroks.T18,
	WindbombStepCps("<N Turn"),
	Koroks.H66,
	WindbombStepCps("<E"),
	Koroks.H65,
	WindbombStepCps("S>"),
	Boss("Rare Talus", fury()).extend({
		ability: { fury: 3 }
	}),
	MakeMorning("For Dinraal (Claw)"),
	WindbombStepCps("E Turn"),
	Koroks.H69,
	Materials.Dinraal(1).extend({
		timeOverride: 30
	}),
	Koroks.R01,
	WindbombStepCps("N>> Turn"),
	Koroks.H68,
	WindbombStepCps("E> Turn"),
	Koroks.K21,
	WindbombStepCps("W"),
	Koroks.K20,
	WindbombStepCps("<W + S"),
	Koroks.K22
];

