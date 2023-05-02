import { Boss, Chest, Discover, fury, Koroks, MakeNight, Materials, Memories, Npc, Shrines, txt, WindbombStepCps } from "../../engine";

export const FinalMemory = [
	WindbombStepCps("S. slight turn + E> TS"),
	Koroks.N28,
	Shrines.KamUrog,

	MakeNight("For stalnox & doctor"),
	WindbombStepCps("S + W"),
	Koroks.N33,
	WindbombStepCps("SQ to house"),
	Npc("Doctor", "B"),
	Materials.Honey(3),
	WindbombStepCps("<<W Turn"),
	Koroks.D52,
	WindbombStepCps("S> Turn"),
	Boss("Stalnox", txt("GEB + ", fury())).extend({
		ability: { fury: 3 }
	}),
	WindbombStepCps("S"),
	Chest("Sapphire", "In cave for quest"),
	Koroks.D59,
	WindbombStepCps("SQ to shrine"),
	Shrines.TotoSah,

	"Cryo + run out",
	Koroks.D56,
	WindbombStepCps("<N + W"),
	Koroks.D55,
	WindbombStepCps("N. Turn down"),
	Discover("Little Twin Bridge"),
	"SQ DEFUSE",
	Discover("Big Twin Bridge"),
	"SQ to shrine",
	Shrines.HaDahamar,

	WindbombStepCps("E to top"),
	Koroks.D42,
	"SQ to horse",
	"Ride near stable",
	Npc("Horse Catching", "Twice, B first time").extend({
		timeOverride: 20,
	}),
	Npc("Two Guys Inside", "Twice"),
	WindbombStepCps("SE"),
	Koroks.D51,
	WindbombStepCps("N>>"),
	Memories.AshSwamp,
	
];