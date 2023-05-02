import { emy, Koroks, MakeNight, Npc, SnapQuest, Warp, WindbombStepCps } from "../../engine";

export const TarryTownRito = [
	Warp("Travel Medallion"),
	Npc("Hudson", "Twice"),
	MakeNight("For XB Chest"),
	WindbombStepCps("W> Turn"),
	Koroks.A37,
	WindbombStepCps("SQ to between 2 guardians"),
	emy("2 Stalkers"),
	SnapQuest("Guardian Stalker"),
	"Wait for them to explode",
    
];