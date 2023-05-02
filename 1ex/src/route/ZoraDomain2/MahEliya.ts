import { Chest, Item, Koroks, Npc, Shrines, STEP, Warp, WindbombStepCps } from "../../engine";

export const MahEliya = [
	Warp("Sato Koda"),
	WindbombStepCps("<E Turn"),
	Koroks.Z05,
	{ type: STEP, text: "SQ to lake" },
	Chest("Zora Helm"),
	WindbombStepCps("<W Turn"),
	Npc("Monument"),
	WindbombStepCps("E> Turn"),
	"Start ring chase",
	Item("3 Lotuses", "On the way"),
	Npc("Monument", "Run to it during ring chase"),
	Shrines.MahEliya
    
];