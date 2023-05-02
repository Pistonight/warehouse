import { gale, MakeMorning, Shrines, STEP, txt, Warp, WindbombStepCps } from "../../engine";

export const SatoKoda = [
	Warp("Dagah Keek"),
	{
		type: STEP,
		text: txt(gale(), " SCW"),
		ability: { gale: 1 },
	},
	WindbombStepCps("N midair + N"),
	MakeMorning("For drillshaft kid"),
	Shrines.SatoKoda,
];