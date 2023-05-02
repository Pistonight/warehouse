import { Boss, ChestSpecial, Equipment, fury, Item, Koroks, MakeMorning, MakeNight, Materials, Npc, Shrines, STEP, txt, WindbombStepCps } from "../../engine";

export const ShaiUtoh = [
	WindbombStepCps("GALE + W").extend({
		ability: { gale: 1 }
	}),
	Koroks.F37,
	Boss("Stone Talus", fury()).extend({
		ability: { fury: 1 },
		suppressError: ["FuryRecharge" as const]
	}),
	WindbombStepCps("W. Turn"),
	Koroks.F45,
	Item("Blueshell Snail"),
	Npc("Mom", "Twice"). extend({
		notes: "5AM - 6PM for Lurein NPCs"
	}),
	Koroks.F44,
	Npc("Dad"),
	Npc("Old Man"),
	WindbombStepCps("NE"),
	Koroks.F36,
	WindbombStepCps("W>"),
	Shrines.YahRin,

	MakeNight("For Stalnox + Beetle").extend({ type: STEP }),
	WindbombStepCps("W> Turn"),
	Koroks.F31,
	WindbombStepCps("W + W Turn"),
	Koroks.F30,
	WindbombStepCps("S SQ"),
	Boss("Stalnox", txt("4 Shots + ", fury())).extend({
		notes: "Don't headshot. His eye won't fall out and your furies can't kill him if you do.",
		ability: { fury: 2 }
	}),
	WindbombStepCps("GALE + NW").extend({
		ability: { gale: 1 }
	}),
	Item("Hyrule Bass to 15"),
	Materials.HeartyBass(5),
	WindbombStepCps("SW SQ"),
	Koroks.F35,
	ChestSpecial("Phantom Ganon Pants"),
	WindbombStepCps("W"),
	Boss("Stone Talus", "3 shots midair"),
	WindbombStepCps("N"),
	Koroks.F26,
	ChestSpecial("Phantom Ganon Helm"),
	"Run to flower",
	Koroks.F28,
	WindbombStepCps("N"),
	Koroks.F27,
	WindbombStepCps("<N"),
	Shrines.ShodaSah,

	WindbombStepCps("SQ out"),
	Koroks.F19,
	WindbombStepCps("N midair"),
	Koroks.F17,
	WindbombStepCps("W> Turn"),
	Koroks.F20,
	WindbombStepCps(".E Turn"),
	Koroks.F21,
	WindbombStepCps(".W Turn").extend({
		notes: "Unequip metal"
	}),
	Koroks.F29.extend({
		notes: "equip metal while running to kass"
	}),
	Npc("Kass"),
	Shrines.QukahNata.extend({
		notes: "Use ragdoll"
	}),

	MakeMorning("For beetle").extend({
		notes: "If after 5:30AM"
	}),
	WindbombStepCps("S> Turn"),
	Koroks.F46,
	WindbombStepCps("<<N"),
	Koroks.F42,
	WindbombStepCps("W>>"),
	Materials.Beetle(5, "Equip stealth 3"),
	Koroks.F34,
	WindbombStepCps("<S Turn"),
	Equipment("Axe", "For Quest"),
	Koroks.F41,
	Npc("Quest Lady", "Twice"),
	Shrines.ShaiUtoh.extend({
		splitPrefix: "{Faron 2}"
	})

];