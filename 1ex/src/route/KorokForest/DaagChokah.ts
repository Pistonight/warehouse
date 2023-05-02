import { ChestSpecial, Discover, Equipment, important, Item, itm, Koroks, MakeNight, Materials, Npc, Shop, Shrines, SnapQuest, STEP, Towers, WindbombStepCps } from "../../engine";

export const DaagChokah = [
	WindbombStepCps("<<W Turn"),
	Koroks.E35,
	WindbombStepCps("W> TS"),
	Towers.Woodland(),

	{ type: STEP, text: "SQ down" },
	Koroks.K31,
	WindbombStepCps("S> Turn"),
	Koroks.K32,
	WindbombStepCps("<N + <<N Turn"),
	ChestSpecial("Korok Mask"),
	WindbombStepCps("W> Turn"),
	Discover("Korok Forest"),
	WindbombStepCps("SW"),
	Npc("Start Oaki", "Make sure quest updates"),
	WindbombStepCps("E"),
	Npc("Riddles of Hyrule").extend({
		notes: "Apple, Sunshroom, Pumpkin, Voltfin, Lynel Hoof"
	}),
	SnapQuest("Sunshroom"),
	Materials.Rushroom(1, "Above Walson"),
	itm("Break pots for acorns"),
	{
		text: "Hestu",
		comment: "Bows > Weapons > Shields",
		icon: "hestu",
		korokChange: -(()=>{
			const bow = 3 + 5 + 8 + 12 + 17 + 25;
			const weapon = 1 + 2 + 3 + 5 + 8 + 12 + 17 + 25;
			const shield = 1 + 2 + 3 + 4 + 5;
			return bow + weapon + shield;
		})()
	},
	Shop("Rushroom + Arrows").extend({
		variableChange: { Rushroom: 4 }
	}),
	Item("Egg"),
	Equipment("Korok Leaf"),
	{
		text: important("Activate Keo Ruug"),
		important: true
	},

	Equipment("Master Sword"),
	Materials.SilentPrincess(3),
	Npc("Mag Trial Guy", "Pick up frogs on the way"),
	"Windbomb to shrine",
	Item("Farm Crickets/Frogs", "While waiting for oaki").extend({
		timeOverride: 300
	}),
	Npc("Finish Oaki Quest").extend({
		notes: "Take note of the speed food timer before passing time. Make sure when you exit beginning trials, the timer has passed 3 minutes so you skip midnight"
	}),
	MakeNight("For BM"),
	Shrines.DaagChokah
];