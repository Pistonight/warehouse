import { WindbombStepCps, Koroks, Npc, Warp, SnapQuest, Materials } from "../../engine";

export const BigHorse = [
	Warp("Owa Daim"),
	WindbombStepCps("SW"),
	Koroks.L22,
	"Land on big horse",
	Koroks.L36,
	Koroks.L62,
	SnapQuest("Lynel"),
	{
		text: "Ride to camp",
		timeOverride: 180,
	},
	Npc("Big Horse Guy"),
	WindbombStepCps("SQ below bridge"),
	Materials.Rushroom(13, "Shock them"),
	WindbombStepCps("N>"),
	Koroks.L59
];