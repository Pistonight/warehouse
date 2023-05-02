import { Boss, Chest, ChestSpecial, Discover, emy, Equipment, fury, gale, important, Item, Koroks, MakeNight, Materials, Memories, Shrines, SnapElite, SnapQuest, SPLIT, STEP, txt, WindbombStepCps } from "../../engine";

export const Castle = [
	WindbombStepCps("S> Turn Escape"),
	WindbombStepCps("S midairs to top"),
	Koroks.X06,
	"Drop down",
	Koroks.X05,
	Chest("5AA"),
	"Drop down outside",
	Koroks.X10,
	WindbombStepCps("W"),
	Koroks.X07,
	WindbombStepCps("<E"),
	ChestSpecial("Royal Guard Cap"),
	"Climb out get skew bounce",
	"Enter calamity",
	SnapElite("Calamity Ganon"),
	{
		text: emy("Kill Calamity"),
		notes: "snap + fury + stasis + 8AA, unequip bow"
	},
	SnapElite("Dark Beast"),
	{
		text: "Dark Beast",
		icon: "calamity",
		comment: "Clear skew and ragdoll",
		type: SPLIT
	},

	"Run down north",
	Koroks.X03,
	"Glide out",
	Chest("Naydra Scale"),
	Koroks.X02,
	"Run",
	Chest("Dinraal Scale"),
	WindbombStepCps("N> Turn"),
	Koroks.K30,
	WindbombStepCps(".S Turn"),
	"Enter East Passage",
	MakeNight("For BM").extend({ type:STEP }),
	Koroks.X11,
	Boss("Rare Talus", fury()).extend({
		ability: { fury: 3 }
	}),
	"Updraft up blow up eye",
	Chest("Farosh Fang"),
	"Enter Library",
	Materials.Tail(5).extend({
		notes: "Get their bows"
	}),
	Discover("King's Study"),
	Shrines.SaasKosah,

	"Boxes behind shrine",
	"Glide to middle of bridge",
	Materials.Tail(3).extend({
		comment: txt(fury(), " Lizalfos"),
		ability: { fury: 3 },
		suppressError: ["FuryRecharge" as const]
	}),
	Chest("Star Fragment"),
	"Run out",
	Koroks.X01,
	WindbombStepCps("<<W aim at pillar"),
	Koroks.X04,
	"Enter lock up",
	SnapElite("Silver Lizalfos if need"),
	Materials.Tail(1).extend({
		comment: "GEB down"
	}),
	Equipment("Blizzard Rod"),
	Boss("Stalnox", txt("BA + ", fury())).extend({
		ability: { fury: 3 },
		notes: "Fury should be back but spin him if not",
		suppressError: ["FuryRecharge" as const]
	}),
	Chest("Hylian Shield"),
	"Run toward West Passage",
	Koroks.X13,
	"Run to water room",
	{
		text: gale(),
		notes: "Watch the moblin",
		ability: { gale: 1 },
		suppressError: ["GaleRecharge" as const]
	},
	Koroks.X08,
	"Shoot switch + glide",
	Materials.Tail(1),
	Chest("Dinraal Fang"),
	"Glide up + run out",
	{
		text: gale(),
		ability: { gale: 2 }
	},
	Materials.SilentPrincess(1),
	Memories.HyruleCastle,
	SnapQuest("Backup Skywatcher"),
	Koroks.X09,
	Discover("Zelda's Room"),
	WindbombStepCps("E. Super"),
	Koroks.X12,
	"WB to 2nd gatehouse",
	Koroks.X16,
	Discover("Second Gatehouse"),
	WindbombStepCps("W"),
	Koroks.X14,
	"WB to 1st gatehouse",
	Koroks.X17,
	WindbombStepCps("E> Turn"),
	Koroks.X23,
	"Walk down",
	Discover("Observation Room"),
	Koroks.X21,
	Koroks.X22,

	WindbombStepCps("W>"),
	Koroks.X18,
	{
		text: "Enter Guard's Chamber",
		notes: "Small chance for unloaded, or clip in"
	},
	Materials.Tail(3).extend({
		comment: fury(),
		ability: { fury: 3 },
		suppressError: ["FuryRecharge" as const],
		notes: "Fury first on stairs, then fury closer to also kill the lizalfos up there"
	}),
	ChestSpecial("Royal Guard Boots"),
	Chest("Naydra Fang"),
	"Run to 3AA",
	{
		text: important("CLEAR RAGDOLL"),
		important: true
	},
	Koroks.X19,
	Chest("3AA", "Only if wasted too many AAs"),
	"Clip, WB to other side",
	Materials.Tail(1),
	"Break boxes",
	Koroks.X15,
	"Turn left",
	Equipment("Royal Guard Sword", "For the girl"),
	Chest("Royal Guard Uniform"),
	Materials.Tail(1),
	Discover("Dining Hall"),
	Item("Raw Meat"),
	"Run out drop down",
	Koroks.X20,
	WindbombStepCps("<<S"),
	Koroks.X24,
	"Drop down",
	Koroks.C16,
	Koroks.X25,
    
];