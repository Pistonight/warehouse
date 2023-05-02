import { Chest, emy, Equipment, important, Koroks, lnk, Materials, setImportant, Shrines, sm, SnapElite, SnapQuest, txt, Warp, WindbombStepCps } from "../../engine";

export const DLC = [
	Warp("Shrine of Resurrection"),
	Koroks.P09,
	...setImportant(
		{
			text: "OHO Overload:",
			notes: txt("Tutorial: ", lnk("https://youtu.be/iS-QrQoTOGU"), ". You don't have to skip it. Use Trident as Boulder Breaker in tutorial and Lynel Crusher as thunderblade. If you don't overload, dupe weapons at yiga hideout later.")
		},
		sm("Unequip Cloths"),
		sm("Drop Bomb"),
		sm("Drop 5 GEBs and put away GEB 6"),
		sm("Switch to Lynel Crusher"),
		sm("Check cannot switch to Trident"),
		sm("Unequip weapon"),
		sm("Pick up OHO"),
		"",
		sm("Pick up 1 GEB"),
		"Equip Trident",
		"Overload",
		sm("Transfer Trident>Crusher"),
		{
			text: sm("Dupe 5 Crushers"),
			notes: "Keep flameblade, throw away extra weapons to have 2 slots open"
		},
		important("HEAL + EQUIP ATK 3"),
	),
	Warp("Keh Namut"),
    
	WindbombStepCps("E>>"),
	SnapElite("Silver Moblin"),
	{
		text: emy("Kill Camp"),
		notes: "Headshot silver moblin first and kill other 2 moblins + blizzarobe, then sneakstrike silver moblin and kill others"
	},
	Chest("10 Ice Arrows"),
	Equipment("Blizzard Rod"),
	Shrines.EtsuKorima.extend({
		notes: "300 in shrine for shaft only"
	}),

	WindbombStepCps("N>> Turn"),
	{
		text: emy("Kill Camp"),
		notes: "Make keese load then stasis boko to take picture, sneakstrike him, GEB rest"
	},
	SnapElite("Silver Bokoblin"),
	Chest("10 Bomb Arrows"),
	Materials.Honey(1),
	Shrines.YowakaIta,

	Materials.Honey(1),
	WindbombStepCps("E> Turn").extend({
		notes: "Wait for stamina to fill before going off"
	}),
	{
		text: emy("Kill Camp"),
		notes: "Headshot silver lizalfos first, then kill 2 black lizalfos and archers, drop down take picture and sneakstrike"
	},
	SnapElite("Silver Lizalfos"),
	Materials.Honey(2, important("DO NOT JUMP")),
	Shrines.RohtaChigah,

	WindbombStepCps("SW"),
	SnapQuest("Stalhorse"),
	emy("Kill Camp"),
	Shrines.RuvoKorbah.extend({
		timeOverride: 720
	}),

];