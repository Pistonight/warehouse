import { Boss, Discover, fury, Koroks, MakeNight, Memories, Npc, Shrines, Warp, WindbombStepCps } from "../../engine";

export const QuaRaym = [
	Warp("Shae Mosah"),
	WindbombStepCps("W"),
	"Start race",
	Koroks.E14,
	"Glide down",
	Koroks.E13,
	WindbombStepCps("S"),
	Discover("Stolock Bridge"),
	Koroks.E15,
	WindbombStepCps("<S Turn midair"),
	Koroks.E18,
	Koroks.E19,
	WindbombStepCps("S>> Turn"),
	Koroks.E25,
	WindbombStepCps("<<W"),
	Koroks.E24,
	WindbombStepCps("S"),
	MakeNight("For Greyson & BM"),
	Koroks.E31,
	Memories.EldinCanyon,

	WindbombStepCps("<E Turn"),
	Koroks.E26,
	WindbombStepCps("N"),
	Npc("Lizard Quest"),
	Npc("Greyson"),
	WindbombStepCps("SE"),
	Boss("Igneo Talus", fury()).extend({
		notes: "Fury from above",
		ability: { fury: 3 }
	}),
	WindbombStepCps("<<E"),
	Koroks.E27,
	WindbombStepCps("S> Turn"),
	Shrines.QuaRaym.extend({
		comment: "Skip BM"
	}),

];