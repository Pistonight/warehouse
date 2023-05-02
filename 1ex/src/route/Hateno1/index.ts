import { Cook, cps, important, Item, itm, Koroks, Materials, Npc, npc, rne, Section, setImportant, Shrines, SPLIT, STEP, Towers, wb, WindbombStepCps } from "../../engine";

export const Hateno1 = [
	Section("HATENO 1"),
	{ type: STEP, text: wb(cps)("N") },
	Materials.SilentPrincess(1).extend({
		text: itm("3 Silent Princess"),
		notes: "2 for stealth potion",
	}),
	Item("4 Grass", "3 for potion + 1 for quest"),
	{ type: STEP, text: "BLSS to farmer" },
	Npc("Farmer"),
	Item("3-4 Frogs"),
	
	WindbombStepCps("N>"),
	Koroks.N42,
	{
		text: "Smuggle flame to lab",
		notes: "Can toss away torch"
	},{
		icon: "camera-plus",
		text: "Camera",
		comment: "A until all upgraded",
		type: SPLIT
	},
	{ type: STEP, text: wb(cps)("<E SQ") },
	Koroks.N48,
	{ type: STEP, text: wb(cps)("<N Turn") },
	Koroks.N43,
	{ type: STEP, text: wb(cps)("S>") },
	Npc("Sheep Lady"),
	{ type: STEP, text: wb(cps)("<W Turn") },
	Npc("Bolson"),
	Cook("Speed | Cold | Stealth").extend({
		notes: "Speed = 3 Frogs+Carrot+Core/Gut, Cold = Darner+Screw"
	}),
	"SQ to shrine",
	important("EQUIP ", rne("CAMERA")),
	Shrines.MyahmAgana.extend({
		notes: "Equip camera for auto save. Remember to switch to bombs"
	}),
	...setImportant(
		{ type: STEP, text: "SQ to horned statue"},
		{ 
			text: "SELL 3 (0H0S | 3E), SAVE",
			notes: "All first options"
		},
		"Load in shrine + Glitch",
		{ 
			text: "SELL 3 (0H0S | 6E), SAVE",
			notes: "All first options"
		},
		"Load in shrine + Glitch",
		{ 
			text: "SELL 3 (0H0S | 9E), SAVE",
			notes: "All first options"
		},
		"Load in shrine + Glitch DUPE",
		{ 
			text: "SELL 1 (2H0S | 10E), SAVE",
			notes: "All first options"
		},
		{ 
			text: "BUY 10H (12H0S | 0E)",
			notes: "121+"
		},
		"WB to shrine + Glitch",
		{ 
			text: "BUY 5H5S (17H5S | 0E)",
			notes: "122+221+"
		},
		"WB to shrine + Glitch",
		{ 
			text: "BUY 5H5S (17H5S | 0E)",
			notes: "122+21+"
		},
		important("CHECK 25 Hearts"),
		{
			icon: "link",
			text: "Essence Duped",
			type: SPLIT,
			notes: "Reload autosave when autosave happens. split on last heart"
		},
	),

	{ type: STEP, text: wb(cps)("<S") },
	Npc("Bolson Twice", "Unequip Phantom"),
	Npc("Hudson"),
	npc("Upgrade House"),
	{
		icon: "house-plus",
		text: "House Upgraded",
		type: SPLIT,
		notes: "split on last fade out"
	},
    
	"Run up hill",
	Koroks.N59.extend({
		notes: "Reload autosave if you haven't"
	}),
	{ type: STEP, text: wb(cps)("W Turn") },
	Koroks.N58,
	{ type: STEP, text: wb(cps)("SW") },
	Materials.HeartyBass(5, "Bomb them"),
	{ type: STEP, text: wb(cps)("W> Turn") },
	Koroks.N50,
	Koroks.N49,
	{ type: STEP, text: wb(cps)("N + N") },
	Towers.Hateno().extend({
		splitPrefix: "{Hateno 1}"
	}),
    
];