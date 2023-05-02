import { Boss, Discover, fury, important, Koroks, Npc, Shrines, STEP, Towers, Warp, WindbombStepCps } from "../../engine";

export const TarryTown1 = [
	Warp("Sato Koda"),
	WindbombStepCps("N>"),
	Koroks.Z04,
	WindbombStepCps("N> Super + N"),
	Towers.Akkala(),

	{ type: STEP, text: "SQ south down" },
	Koroks.A42,
	"Drop down",
	Koroks.A43,
	WindbombStepCps("SE"),
	Koroks.A46,
	WindbombStepCps("W> Turn"),
	Discover("Akkala Bridge Ruins"),
	"SQ to rock",
	Koroks.A45,
	{ type: STEP, text: "SQ to bridge" },
	Koroks.A50,
	WindbombStepCps("E Turn"),
	Boss("Blue Hinox", fury()).extend({
		ability: { fury: 3 },
	}),
	WindbombStepCps("E>> Turn"),
	Koroks.A52,
	Discover("West Sokkala Bridge"),
	Discover("Sokkala Bridge"),
	WindbombStepCps("N>"),
	Koroks.A51,
	WindbombStepCps("E>"),
	Discover("East Sokkala Bridge"),
	WindbombStepCps("N> midair"),
	Koroks.A47,
	Shrines.DahHesho.extend({
		splitPrefix: "{Akkala 1}"
	}),

	WindbombStepCps("N> Turn"),
	Npc("Hudson Twice", "10 Wood"),
	{
		text: important("Place Travel Medallion"),
		important: true
	}
];