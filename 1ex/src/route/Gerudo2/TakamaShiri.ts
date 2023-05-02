import { important, Koroks, Npc, Shrines, WindbombStepCps } from "../../engine";

export const TakamaShiri = [
	WindbombStepCps("S> DT"),
	Koroks.W29,
	WindbombStepCps("S> Turn"),
	important("EQUIP GERUDO"),
	Npc("Guard"),
	Npc("Sand Seal Lady"),
	Shrines.TakamaShiri.extend({
		timeOverride: 140
	})
];