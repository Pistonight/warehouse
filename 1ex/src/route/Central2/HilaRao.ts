import { Discover, Koroks, Npc, Shrines, WindbombStepCps } from "../../engine";

export const HilaRao = [
	WindbombStepCps("N> SQ"),
	Koroks.C48,
	WindbombStepCps("SE"),
	Discover("Hyrule Garrison Ruins"),
	Koroks.C54,
	WindbombStepCps("NE"),
	Koroks.C49,
	WindbombStepCps("SE"),
	Koroks.C55,
	WindbombStepCps("S + S"),
	Koroks.C64,
	WindbombStepCps(".S"),
	Koroks.C70,
	WindbombStepCps("E"),
	Koroks.C71,
	WindbombStepCps("NE"),
	Koroks.C65,
	WindbombStepCps("SQ to shrine"),
	Shrines.WahgoKatta,

	WindbombStepCps("SQ to chef"),
	Npc("Chef"),
	Npc("Royal Guard Girl", "Switch to sword"),
	WindbombStepCps(".S Turn"),
	Discover("Horwell Bridge"),
	WindbombStepCps("NE"),
	Npc("Flower Lady"),
	Shrines.HilaRao
];