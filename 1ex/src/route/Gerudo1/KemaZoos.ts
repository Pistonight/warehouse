import { Koroks, WindbombStepCps, Shrines, Cook, cps, Npc, Shop, STEP, txt, Warp, wb } from "../../engine";

export const KemaZoos = [
	Warp("Keh Namut"),
	{ type: STEP, text: "BLSS to Gerudo Town" },
	Npc("Spy Guy"),
	Shrines.DaqoChisay,

	{ type: STEP, text: "WB to Kara Kara" },
	"Get Gerudo Outfit",
	{ type: STEP, text: "WB to Gerudo Town" },
	Shop("All Arrows"),
	Cook("10 Speed"),
	Npc("Riju"),
	{ type: STEP, text: txt("Walk out + ", wb(cps)(".W Turn")) },
	Koroks.W47,
	WindbombStepCps("<N Turn"),
	Koroks.W36,
	WindbombStepCps("NW midair"),
	Koroks.W28,
	WindbombStepCps("NW"),
	Koroks.W18,
	WindbombStepCps("<<N (Follow Skeleton)"),
	Koroks.W13,
	WindbombStepCps("N drop after 3-5 seconds"),
	Koroks.W09,
	Shrines.KemaZoos,
];