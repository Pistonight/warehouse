import { Koroks, Memories, Npc, Shrines, Warp, WindbombStepCps } from "../../engine";

export const Akkala3=[
	Warp("Akkala Tower"),
	WindbombStepCps("W ish SQ"),
	Koroks.A41,
	WindbombStepCps("N>"),
	Koroks.A40,
	"Drop down",
	Koroks.A39,
	WindbombStepCps("<W Turn"),
	Shrines.ZeKasho,

	WindbombStepCps("SQ forward"),
	Npc("Big Sister", "2nd option"),
	Npc("Little Sister"),
	Npc("Big Sister", "2nd option"),
	Npc("Little Sister"),
	Npc("Big Sister"),
	WindbombStepCps("N> Turn"),
	Koroks.A30,
	WindbombStepCps("<E Turn"),
	Koroks.A31,
	WindbombStepCps("E"),
	Koroks.A32,
	WindbombStepCps("NW"),
	Koroks.A25,
	WindbombStepCps("N> Turn"),
	Koroks.A20,
	WindbombStepCps("E> Turn"),
	Koroks.A27,
	"Shield surf down",
	Koroks.A26,
	WindbombStepCps("N Turn"),
	Koroks.A18,
	WindbombStepCps("S"),
	Koroks.A21,
	Memories.SpringOfPower,

	"Dupe scale",
	Shrines.TutsuwaNima.extend({
		splitPrefix: "{Akkala 3}"
	})
];