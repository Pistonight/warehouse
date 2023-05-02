import { Boss, fury, Item, Koroks, MakeNoon, Materials, Npc, Shrines, SnapElite, SnapQuest, STEP, txt, WindbombStepCps } from "../../engine";

export const KeiveTala = [
	WindbombStepCps("<S Turn"),
	Koroks.W25,
	WindbombStepCps("S Turn"),
	Boss("Molduga", "Spin-to-win"),
	Item("Molduga Gut"),
	Shrines.ThoKayu,

	WindbombStepCps("<S Turn"),
	Boss("Molduga", "Spin-to-win"),
	WindbombStepCps("W"),
	Koroks.W51,
	WindbombStepCps("S midairs"),
	Koroks.W65,
	SnapQuest("Gerudo Skeleton"),
	Materials.SilentPrincess(2),
	Npc("Fairy Fountain"),
	Shrines.HawaKoth.extend({
		timeOverride: 180
	}),

	Npc("Durian Lady"),
	WindbombStepCps("<E Turn"),
	Koroks.W55,
	WindbombStepCps("S>"),
	Koroks.W61,
	WindbombStepCps("SE"),
	Koroks.W66,
	Boss("Molduga", "Spin-to-win"),
	WindbombStepCps("<E Turn"),
	Boss("Molduga", "Spin-to-win"),
	WindbombStepCps("N"),
	Koroks.W57,
	WindbombStepCps("N midair"),
	Koroks.W56,
	WindbombStepCps("E>"),
	Koroks.W67,
	WindbombStepCps("E midair"),
	Koroks.W62,
	"Run to shrine",
	Shrines.MisaeSuma,
    
	WindbombStepCps("<<E Turn"),
	"Start race",
	WindbombStepCps("SE"),
	SnapElite("Molduking"),
	Boss("Molduking", txt(fury(), " + ", "1 GEB Shock")).extend({
		ability: { fury: 3 },
	}),
	Koroks.W63,
	{ type: STEP, text: "SQ to shrine" },
	Shrines.KeiveTala.extend({
		notes: "Use shock arrow method"
	}),

	MakeNoon("For Suma Sahma").extend({
		type: STEP
	}),
	WindbombStepCps("<W"),
	Koroks.W68
];