import { Boss, Discover, fury, Koroks, MakeNight, Memories, Npc, Shrines, STEP, txt, WindbombStepCps } from "../../engine";

export const Maritta = [
	MakeNight("For Stalnox").extend({type:STEP}),
	WindbombStepCps("W"),
	Koroks.R19,
	WindbombStepCps("N>"),
	Koroks.R16,
	WindbombStepCps("GALE + <N Turn").extend({
		ability: { gale: 1 }
	}),
	Koroks.R10,
	WindbombStepCps("N> Turn"),
	Koroks.R07,
	Memories.IrchPlain,

	WindbombStepCps(".E Turn"),
	Boss("Blue Hinox", "Spin-to-win").extend({
		notes: "Save the furies for stalnox"
	}),
	WindbombStepCps("N"),
	Koroks.K29,
	WindbombStepCps("W>> Turn"),
	Koroks.K26,
	WindbombStepCps("N. Turn"),
	Koroks.K19,
	WindbombStepCps("NW"),
	Koroks.K16,
	WindbombStepCps("S>"),
	Koroks.K18,
	WindbombStepCps("W>>"),
	"Summon Horse",
	Boss("Stalnox", txt("1 GEB + ", fury())).extend({
		ability: { fury: 3 }
	}),
	"Ride to korok",
	Koroks.K17,
	WindbombStepCps("GALE + SE").extend({
		ability: { gale: 1 }
	}),
	Koroks.K25,
	WindbombStepCps("SW"),
	Koroks.K24,
	WindbombStepCps("W> Turn"),
	Koroks.K23,
	WindbombStepCps("S>"),
	Shrines.MonyaToma,

	WindbombStepCps(".N Turn"),
	Npc("Leviathan Bones", "Group, then 3 guys each"),
	WindbombStepCps("<W Turn"),
	Discover("Maritta Exchange Ruins"),
	WindbombStepCps("SW midair"),
	Shrines.MaagNorah.extend({
		splitPrefix: "{Central 1}"
	})
];