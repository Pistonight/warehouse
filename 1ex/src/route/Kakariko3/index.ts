import { Equipment, important, Koroks, MakeNight, MakeNoon, Materials, Npc, Section, Shop, Shrines, Warp, WindbombStepCps } from "../../engine";

export const Kakariko3 = [
	Section("KAKARIKO 3"),
	Warp("Travel Medallion"),
	Npc("Hudson"),
	Warp("Ta'loh Naeg"),
	MakeNoon("11AM-3PM no rain"),
	"2 Cucoos",
	Npc("Cucoo Guy"),
	Npc("Cottla", "12"),
	Npc("Catch Cottla"),
	"3 Cucoos",
	Npc("Fairy Fountain Guy x2"),
	"1 Cucoo",
	"Shoot torches with GEB",
	Npc("Coco x4"),

	MakeNight("For beetle"),
	important("EQUIP STEALTH"),
	Materials.Beetle(1),
	Npc("Fire Lady"),
	Shop("Fire Arrows", "For Naydra"),
	"Last Cucoo",
	Npc("Cucoo Guy"),
	Equipment("Traveler's Sword", "in river if need"),
	Npc("Firefly Lady", "Twice").extend({
		notes: "If you scare her with phantom armor you need to wait for her to sit down"
	}),
	WindbombStepCps("SQ to Impa"),
	Npc("Impa"),
	Npc("Paya"),
	MakeNight(),
	Koroks.D03,
	Koroks.D02,
	WindbombStepCps("GALE + S").extend({
		ability: { gale: 1 }
	}),
	Koroks.D04,
	Koroks.D05,
	WindbombStepCps("E Turn right"),
	Koroks.D08,
	Koroks.D06,
	WindbombStepCps("SE Turn"),
	Koroks.D12,
	WindbombStepCps("<N"),
	Materials.Beetle(1),
	WindbombStepCps("N + NE"),
	Koroks.N07,
	"Surf down",
	Koroks.N08,
	WindbombStepCps("E"),
	Koroks.N10,
	WindbombStepCps("E + W"),
	Koroks.N09,
	WindbombStepCps("GALE + S + S").extend({
		ability: { gale: 1 }
	}),
	Koroks.D07,
	WindbombStepCps("SW"),
	Koroks.D11,
	WindbombStepCps("W"),
	Koroks.D10,
	Koroks.D09,
	WindbombStepCps("N> Turn"),
	Materials.SilentPrincess(3),
	WindbombStepCps("SE"),
	"Kill Blade Master",
	Equipment("Windcleaver", "If need"),
	Shrines.LaknaRokee.extend({
		splitPrefix: "{Kakariko 3}"
	}),
];