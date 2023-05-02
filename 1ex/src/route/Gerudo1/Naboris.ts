import { Chest, emy, Equipment, gale, important, Item, MakeMorning, Materials, Npc, rne, setImportant, SnapElite, SnapQuest, SPLIT, STEP, txt, Warp, WindbombStepCps } from "../../engine";

export const Naboris = [
	Warp("Kuh Takkar"),

	{ type: STEP, text: txt("WB to camp") },
	{
		text: emy("Kill 4 lizalfos"),
		notes: "Use Royal Bow 2 IA headshots"
	},
	Materials.Tail(4),
	Equipment("Steel Lizal Bow"),
	MakeMorning("For Gerudo Town Quests").extend({
		notes: "DO NOT FORGET"
	}),
	Equipment("Dragon Bone Club", "For Kohga"),
	Equipment("Knight's Shield", "For use before radiant shield"),
	WindbombStepCps("SW"),
	Chest("Radiant Shield"),
	WindbombStepCps("NW Delay"),
	important("EQUIP GERUDO"),
	Equipment("Duplex Bow"),
	...setImportant(
		{
			text: "2 Bow Dupe 4 GEBS",
			notes: "Start from GEB, drop and +>A>+ quickly and cycle between GEB and duplex"
		},
		"Drop 3 GEBS + Duplex",
		"Dupe 2 more GEBs",
		Item("15 Arrows"),
		{
			text: "Enter Kohga",
			notes: "Use Dragonbone. ATK 3 recommended but it's possible to do without"
		}
	),
	SnapElite("Master Kohga"),
	Chest("Thunderhelm"),
	Warp("Daqo Chisay"),

	{ type: STEP, text: txt("WB to Riju") },
	{
		text: "Urbosa Memory",
		icon: "naboris-memory",
		type: SPLIT
	},
	{ type: STEP, text: txt("Super to watch tower") },
	"Ride to Misae Suma",
	Npc("Noble Pursuit Lady"),
	{
		text: "Backflip to Naboris",
		notes: "Aim a bit left of quest dot"
	},
	{
		text: "Enter Naboris",
		icon: "naboris-enter",
		comment: "Shoot legs with GEB",
		type: SPLIT
	},
	{
		text: txt(gale(), " Inside Naboris"),
		ability: { gale: 2 }
	},
	SnapQuest("Small Guardian"),
	{
		text: important("EQUIP ", rne("CAMERA/STASIS")),
		important: true
	},
	{
		text: important("CLEAR RAGDOLL"),
		important: true
	},
	SnapElite("Thunderblight"),
	{
		text: emy("Thunderblight 1"),
		icon: "naboris-done",
		comment: "8 headshots",
		type: SPLIT
	}

];