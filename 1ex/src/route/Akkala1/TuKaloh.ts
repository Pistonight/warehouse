import { ChestSpecial, gale, important, Koroks, Npc, Shop, Shrines, STEP, WindbombStepCps } from "../../engine";

export const TuKaloh = [
	{ type: STEP, text: important("LOOK SKY EAST"), important: true },
	WindbombStepCps("N to camp"),
	Koroks.A02,
	WindbombStepCps(".E Turn"),
	Koroks.A03,
	WindbombStepCps("E> Turn"),
	ChestSpecial("Xenoblade Chest"),
	{ type: STEP, text: "SQ to flower" },
	Koroks.A04,
	WindbombStepCps("SE"),
	Koroks.A07,
	WindbombStepCps("SE"),
	Koroks.A13,
	WindbombStepCps("E"),
	{
		text: "Smuggle blue flame",
		notes: "With moblin club"
	},
	WindbombStepCps("E> to race start"),
	Koroks.A14,
	Koroks.A15,
	{ 
		type: STEP, 
		text: "Light furnace",
		notes: "Walk in the end to make sure robbie and his wife are loaded inside. Otherwise they go sleep.",
		timeOverride: 60,
	},
	Npc("Robbie"),
	Npc("Wife"),
	Shop("Ancient Short Sword"),
	{
		type: STEP,
		text: gale(),
		ability: { gale: 1 }
	},
	Koroks.A16,
	WindbombStepCps("N> TS"),
	Shrines.TuKaloh,

	{ type: STEP, text: "Drop down to basement" },
	ChestSpecial("Travel Medallion"),

];