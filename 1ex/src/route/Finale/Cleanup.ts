import { Boss, Cook, Discover, emy, fury, important, Item, Koroks, MakeNight, Npc, Section, Shop, SPLIT, Warp, WindbombStepCps } from "../../engine";

export const Cleanup = [
	Section("PLATEAU 3"),
	Warp("Joloo Nah"),
	WindbombStepCps("S."),
	Npc("Sell Horse to guy"),
	WindbombStepCps("N"),
	Npc("Friend 4"),
	WindbombStepCps("GALE + SW").extend({
		ability: { gale: 1 }
	}),
	Boss("Luminous Talus", fury()).extend({
		ability: { fury: 3 }
	}),
	{
		important: true,
		text: important("BURN OUT FURY")
	},
	WindbombStepCps("W> Turn"),
	Koroks.W08,
	Koroks.G32,
	WindbombStepCps("S"),
	Koroks.W10,
	WindbombStepCps("W + W + S"),
	Koroks.G35,
	"Surf down",
	Npc("Rushroom Guy", "Twice"),
	Npc("Sesami"),
	MakeNight("For Kilton if need"),
	WindbombStepCps("GALE + SW").extend({
		ability: { gale: 1 }
	}),
	Discover("Gerudo Desert Gateway"),

	Warp("Dah Hesho 99.75%"),
	WindbombStepCps("SQ DEFUSE trees"),
	Koroks.A49,
	WindbombStepCps("SQ to fountain"),
	Koroks.A48,
	Npc("Fairy Fountain"),
	WindbombStepCps("N"),
	Npc("Kilton", "11 Sell 1"),
	Shop("Monster Extract + Gear", "Need 707"),
	Npc("Kilton","313233,31,32,33"),
	WindbombStepCps("W"),
	Cook("Monster Cake", "Wheat Butter Suger Extract"),
	"Listen at window",
	Npc("Mother", "Twice"),
	Npc("Guardian Guy", "Twice"),
	Npc("Hudson"),
	Npc("Hudson after wedding"),

	Warp("Keo Ruug (99.91%)"),
	Npc("Blupee Guy"),
	Npc("Blizzard Rod Guy"),
	Npc("Trial Guy"),
	Npc("Hestu"),
	Item("GET GOLD POOP"),

	Warp("Shrine of Resurrection").extend({
		notes: "Check: All Quests, 4 Key Items on second page. 99.91% map",
		splitPrefix: "{Clean up}"
	}),
	{
		text: emy("Maz Koshia"),
		icon: "maz",
		comment: "Skip his trial and kill him",
		type: SPLIT,
		splitPrefix: ""
	},
	Section("GG")
];