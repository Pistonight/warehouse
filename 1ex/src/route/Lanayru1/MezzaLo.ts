import { Boss, Koroks, Npc, Shrines, Warp, WindbombStepCps } from "../../engine";

export const MezzaLo = [
	Warp("Ne'ez Yohma"),
	Npc("Frog Boy", "Twice. 7AM - 8PM"),
	Npc("Luminous Stone Guy", "Twice"),
	Npc("Letter Girl"),
	Npc("Diving Quest"),
	Npc("Hinox Lady"),
	Npc("Monument Guy"),
	Npc("Kapson"),
	Npc("Lynel Lady", "Twice"),
	WindbombStepCps("GALE + E>").extend({
		ability: { gale: 1 }
	}),
	Koroks.Z18,
	WindbombStepCps("S"),
	Koroks.Z26,
	WindbombStepCps(".S TS"),
	Boss("Blue Hinox", "Spin-to-win").extend({
		notes: "If fury is back, can use 3 spin SLAM 1 fury and use 1 each on next 2 hinox"
	}),
	WindbombStepCps("W Turn"),
	Koroks.Z44,
	WindbombStepCps("GALE + W").extend({
		ability: { gale: 1 }
	}),
	Koroks.Z43,
	WindbombStepCps("W SQ"),
	Koroks.Z42,
	WindbombStepCps("W Midair"),
	"Ride deer to shrine",
	Npc("Kass"),
	WindbombStepCps("W"),
	Koroks.N04,
	WindbombStepCps("NW"),
	Koroks.N02,
	WindbombStepCps("E"),
	Koroks.N03,
	Shrines.MezzaLo
];