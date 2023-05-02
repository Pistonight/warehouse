import { Boss, cps, Discover, fury, gale, Koroks, MakeNoon, Npc, Shrines, STEP, Towers, txt, Warp, wb, WindbombStepCps } from "../../engine";

export const SasaKai = [
	Warp("Kuh Takkar"),
	{
		text: txt(gale()," SCW + midair"),
		ability: { gale: 1 },
		type: STEP,
	},
	Shrines.KihiroMoh,

	{
		text: "SQ to hideout",
		type: STEP,
	},
	{
		text: txt(gale(), " inside hideout"),
		ability: { gale: 1 }
	},
	Discover("Kihiro Moh Shrine"),
	MakeNoon("For Sasa Kai"),
	{
		text: txt(gale()," + ", wb(cps)("E + S")),
		ability: { gale: 1 },
		type: STEP,
	},
	Boss("Frost Talus", fury()).extend({
		ability: { fury: 3 },
		notes: "Back up rare ore if not enough amber"
	}),
	WindbombStepCps("W"),
	Koroks.G26,
	WindbombStepCps("W> Turn"),
	Koroks.W01,
	WindbombStepCps("<<W Turn"),
	Koroks.G30,
	WindbombStepCps("E> Turn"),
	Koroks.G31,
	WindbombStepCps("<E"),
	Towers.Gerudo(),
    
	Npc("Kass"),
	Shrines.SasaKai

];