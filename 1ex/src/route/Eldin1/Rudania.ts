import { Boss, Chest, cps, emy, Equipment, fury, gale, Item, Koroks, MakeMorning, Materials, Npc, Shop, Shrines, SnapElite, SPLIT, STEP, txt, Warp, wb, WindbombStepCps } from "../../engine";

export const Rudania = [
	"BLSS to Goron City",
	Shop("2 Flamebreaker Piece"),
	Npc("Kayra Mah Guy"),
	Shop("Goron Spice"),
	Npc("Bludo"),
	"WB to shrine",
	Shrines.ShaeMosah,

	WindbombStepCps("<N Turn"),
	Chest("10 Ice Arrows", "Inside Yunobo Cave").extend({
		notes: "Clip in if rock loaded. Don't let potlid burn"
	}),
	{ 
		type: STEP, 
		text: txt(gale(), " up"), 
		ability: { gale: 1 },
		suppressError: ["GaleRecharge"] 
	},
	Koroks.E08,
	WindbombStepCps("<N Turn"),
	Koroks.E07,
	Shrines.ShoraHah,

	{
		type: STEP,
		text: txt("Minecart + ", wb(cps)("<W Turn")),
	},
    
	MakeMorning("For Dinraal (1S2H)").extend({
		notes: "Do it near luminous stones so dinraal spawns lower"
	}),
	Boss("Igneo Talus", fury()).extend({
		ability: { fury: 3 },
		notes: "Get parts, break the luminous stones during fury",
	}),
	WindbombStepCps("W>> DT"),
	Koroks.K04,
	WindbombStepCps("W> Turn"),
	Koroks.K01,
	WindbombStepCps("N> SQ to lynel"),
	{
		text: emy("Kill Lynel"),
		notes: "Don't use bullet time so dinraal flies faster"
	},
	Equipment("Savage Lynel Crusher"),
	Item("Lynel Hoof").extend({
		notes: "If no hoof drop also kill the next lynel"
	}),
	WindbombStepCps("N>").extend({
		notes: "Make morning after part falls"
	}),
	SnapElite("Dinraal"),
	Koroks.K02,
	WindbombStepCps("<E Turn"),
	Koroks.K03,
	WindbombStepCps("E"),
	Koroks.E01,
	Materials.Dinraal(3),

	Warp("Shae Mosah"),
	"SQ to Bludo",
	{
		text: "Daruk Memory",
		icon: "rudania-memory",
		type: SPLIT
	},
	WindbombStepCps("E"),
	Koroks.E16,
	WindbombStepCps("E + SE midair"),
	emy("Kill Moblins"),
	Npc("Do escort", "Farm ambers on the way"),
	{
		text: "Enter Rudania",
		icon: "rudania-enter",
		type: SPLIT,
		timeOverride: 400,
	},
	{
		text: txt(gale(), " inside Rudania"),
		ability: { gale: 1 },
		notes: "Drop a new GEB in the middle of the second room, pick up after trick shot",
		timeOverride: 200,
	},
	SnapElite("Fireblight"),
	{
		text: emy("Fireblight 1"),
		icon: "rudania-done",
		comment: "ATK 2, 8 headshots",
		type: SPLIT,
	}

];