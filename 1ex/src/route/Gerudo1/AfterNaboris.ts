import { Koroks, Npc, Towers, Warp, WindbombStepCps } from "../../engine";

export const AfterNaboris = [
	Npc("Boots Guy"),
	"Run to Riju",
	Npc("Riju"),
	Npc("Thunderhelm"),
	Npc("Captain"),
	Npc("Historian", "112"),
	Npc("Bar Lady"),
	Npc("Trash Girl"),
	WindbombStepCps("N. Turn"),
	Npc("Do Ice Quest"),
    
	Warp("Naboris"),
	WindbombStepCps("W"),
	Koroks.W32,
	Koroks.W31,
	WindbombStepCps("N>"),
	Towers.Wasteland().extend({
		splitPrefix: "{Gerudo 1}"
	})

];