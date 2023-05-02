import { Boss, fury, gale, Koroks, Npc, setImportant, Shop, sm, SPLIT, STEP, WindbombStepCps } from "../../engine";

export const AllPictures = [
	WindbombStepCps("< Turn"),
	Koroks.N34,
	WindbombStepCps("<N"),
	Koroks.N27,
	WindbombStepCps("N>"),
	Koroks.N26,
	WindbombStepCps("<S Turn"),
	Boss("Stone Talus", fury()).extend({
		ability: { fury: 1 }
	}),
	WindbombStepCps("N SQ"),
	Koroks.N35,
	WindbombStepCps(".E Turn"),
	Koroks.N36,
	WindbombStepCps("<E"),
	Koroks.N37,
	WindbombStepCps("S"),
	Koroks.N38,
	"SQ to stone left of talus",
	Boss("Stone Talus", fury()).extend({
		ability: { fury: 1 }
	}),
	WindbombStepCps("<W Turn"),
	Koroks.N41,
	WindbombStepCps("<W"),
	{
		text: "Shoot 10 Targets",
		notes: "2 on upper lane, then 3 leading to house, 3 more leading to last 2"
	},
	Koroks.N45,
	"Run",
	Koroks.N46,
	WindbombStepCps("E>>"),
	Koroks.N51,
	WindbombStepCps("E"),
	Koroks.N52,
	WindbombStepCps("E."),
	Koroks.N53,
	{
		text: gale(),
		type: STEP,
		ability: { gale: 1 }
	},
	Npc("Invite Bolson"),
	"SQ to weapon boy",
	Npc("Nebb", "Get Diamond"),
	...setImportant(
		Shop("Sell Material if need", "2nd Option"),
		sm("Core = 54.9K"),
		{
			text: sm("Sh Ge = 52K"),
			notes: "12.8K before selling shaft"
		},
		{
			text: sm("Sh Sc Sp = 52"),
			notes: "25.8K before selling screws + springs"
		},
		{
			text: sm("Sh Sc = 44K"),
			notes: "32.5K before selling screws"
		},
		{
			text: sm("Sh Sp = 44K"),
			notes: "29.5K before selling springs"
		}
	),
	
	Npc("Shrine Quest Lady"),
	Npc("Cricket Guy"),
	Npc("Cricket Lady"),
	Npc("Cricket Guy", "Twice"),
	WindbombStepCps("E"),
	Npc("Sheep Lady"),
	WindbombStepCps("E"),
	Npc("Picture Guy"),
	{
		type: SPLIT,
		text: "All Pictures",
		icon: "camera-plus",
		splitPrefix: "{Hateno 2}"
	}
];