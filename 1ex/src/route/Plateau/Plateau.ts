import { Chest, Cook, Equipment, Item, itm, Koroks, MakeMorning, Section, Shrines, SPLIT, Towers } from "../../engine";

export const Plateau = [
	Section("PLATEAU 1"),

	"SOR Clip, get TOD",
	Equipment("Tree Branch").extend({
		notes: "For FDC at bombs and tower"
	}),
	itm("Foods"),
	Equipment("Potlid").extend({
		notes: "Ideally keep this potlid for Thunderblight, or you can get another one in kakariko if you break this one"
	}),

	"TOT Clip",
	Equipment("Bow + Arrow").extend({
		notes: "Don't break any bows"
	}),
	"BLSS to bombs",
	Shrines.JaBaij,

	"BLSS to tower",
	Towers.Plateau(),
	{
		text: "FDC Down",
		notes: "Throw away Tree Branch"
	},
	"BLSS to Mud arrows",
	Chest("Elemental Arrows"),
	"SQ WB to Mag",
	Shrines.OmanAu,

	"BLSS to old man hut",
	Koroks.P18,
	Item("Baked Apple"),
	Equipment("Axe", "Inside hut"),
	
	Equipment("Torch"),
	MakeMorning("For Rupee Printing"),
	Cook("2 Peppers + 3 Peppers", "For Early Hebra / Medoh"),

	"WB to stasis",
	Shrines.OwaDaim.extend({
		notes: "Can go for WB no need to eat"
	}),
	Shrines.KehNamut,
	{
		text: "Plateau",
		icon: "king",
		type: SPLIT,
		splitPrefix: "{Plateau}"
	}
];