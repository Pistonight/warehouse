import { Boss, ChestSpecial, Discover, fury, gale, Koroks, Materials, Memories, Shrines, STEP, Towers, txt, WindbombStepCps } from "../../engine";

export const SpringOfCourage = [
	Koroks.L53.extend({
		type: STEP
	}),
	Koroks.L55,
	WindbombStepCps("W>> Turn"),
	Koroks.L44,
	WindbombStepCps(".N Turn"),
	Koroks.L33,
	WindbombStepCps("N"),
	Koroks.L16,
	WindbombStepCps("<<N"),
	Koroks.L11,
	WindbombStepCps("N>"),
	Materials.Rushroom(4),
	Koroks.L05,
	"Surf down",
	Koroks.L06,
	WindbombStepCps("NW"),
	Koroks.L02,
	"Run",
	Koroks.L01,
	Boss("Stone Talus", fury()).extend({
		ability: { fury: 1 }
	}),
	WindbombStepCps("S>>"),
	Koroks.L04,
	WindbombStepCps(".N Turn"),
	Memories.WestNecluda,

	Koroks.D45,
	WindbombStepCps("NE"),
	Koroks.D35,
	WindbombStepCps("<E Turn"),
	Koroks.D36,
	WindbombStepCps("<S"),
	Koroks.D46,
	WindbombStepCps("N> Turn"),
	Koroks.D37,
	Koroks.D38,
	WindbombStepCps("E>"),
	Koroks.D47,
	WindbombStepCps("SQ"),
	Koroks.D39,
	WindbombStepCps(".W Turn"),
	Shrines.ReeDahee,

	{
		text: txt(gale(), " SCW"),
		ability: { gale: 1 }
	},
	Shrines.SheeVaneth,
	WindbombStepCps(".S"),
	Shrines.SheeVaneer,

	{
		text: "Bomb Boulder",
		notes: "Get traveler sword here if night"
	},
	{
		text: gale(),
		ability: { gale: 1 }
	},
	Koroks.D49,
	Koroks.D41,
	WindbombStepCps("SQ to other peak"),
	Koroks.D40,
	WindbombStepCps("N> Turn"),
	Koroks.D30,
	WindbombStepCps("N. Turn"),
	Boss("Stone Talus", fury()).extend({
		ability: { fury: 1 }
	}),
	WindbombStepCps("W."),
	Koroks.D17,
	WindbombStepCps("S> Turn"),
	Boss("Stone Talus", fury()).extend({
		ability: { fury: 1 }
	}),

	WindbombStepCps("SQ to tower"),
	Towers.DuelingPeaks(),

	WindbombStepCps("<<S"),
	Koroks.D48,
	WindbombStepCps("SW"),
	Koroks.D50,
	WindbombStepCps("E."),
	Koroks.L07,
	WindbombStepCps("<<S Turn"),
	Koroks.L19,
	Koroks.L20,
	ChestSpecial("Ravio's Hood"),
	Discover("Spring of Courage"),
	Shrines.ShaeKatha.extend({
		splitPrefix: "{Lake 2}"
	})
    
];