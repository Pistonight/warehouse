import { Boss, fury, gale, Koroks, MakeNight, Npc, Shrines, SnapQuest, txt, WindbombStepCps } from "../../engine";

export const ZunaKai = [
	WindbombStepCps("<N Turn"),
	Koroks.E02,
	SnapQuest("Eldin Skeleton"),
	WindbombStepCps("NE"),
	Koroks.E03,
	WindbombStepCps("E + E>"),
	Koroks.E04,
	WindbombStepCps("E>>"),
	Koroks.E05,
	Npc("Gut Check Challenge", gale()).extend({
		notes: "Gale and midair cardinal and climb up",
		ability: { gale: 1 }
	}),
	MakeNight("For Kilton & Xenoblade").extend({
		notes: "Need to skip BM at Zuna Kai if you are slow enough"
	}),
	Shrines.GoraeTorr,

	WindbombStepCps("<E Turn"),
	Boss("Black Hinox", txt(fury(), " + 1 Shot")).extend({
		ability: { fury: 3 },
		notes: "If fury not back, spin to win and don't slam in case fury is wasted.",
		suppressError: ["FuryRecharge"]
	}),
	WindbombStepCps("<<E Turn"),
	Koroks.A01,
	WindbombStepCps("<<S Turn"),
	SnapQuest("Kilton").extend({
		notes: "Might want to snap multiple to be safe"
	}),
	Npc("Kilton"),
	WindbombStepCps("S>"),
	Koroks.A05,
	WindbombStepCps("<E + E"),
	Boss("Black Hinox", "Shoot from above"),
	Koroks.A06,
	WindbombStepCps("W>"),
	Shrines.ZunaKai.extend({
		comment: "Skip BM if needed"
        
	}),

];