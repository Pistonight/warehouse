import { STEP, wb, cps, ChestSpecial, Chest, Equipment, Koroks, important, txt, lnk, sm, setImportant } from "../../engine";

export const RupeePrinting = [
	{ type: STEP, text: wb(cps)("<N TS") },
	ChestSpecial("EX Phantom Helm"),
	{ type: STEP, text: wb(cps)("N> Turn") },
	"Activate Kaam Yatak",

	{ type: STEP, text: wb(cps)("<E Turn") },
	ChestSpecial("EX Phantom Pants"),
	{ type: STEP, text: "BLSS to sacred grounds"},
	ChestSpecial("EX Phantom Armor"),
	{ type: STEP, text: wb(cps)("N Super + N") },
	Chest("3 AA", "Balcony"),
	Equipment("Royal Bow", "Also get 10 arrows"),
	{ type: STEP, text: wb(cps)("SE") },
	Koroks.C29,
	...setImportant(
		important("SAVE"),
		{
			text:"Part Combinations", 
			notes: txt("Tutorial by Smich: ", lnk("https://youtu.be/FPuzVW2aXl8")),
		},
		{
			text: sm("Core = No Pic"),
			notes: txt("Rupee Routing: ", lnk("https://docs.google.com/spreadsheets/d/1MR-aw13a_v27ZC4LYW2bVkveobDvHRJfS9nTe_4kpl0/")),
		},
		sm("Sh Ge = No Pic"),
		sm("Sh Sc Sp = No Pic"),
		sm("Sh Sc/Sp = Elite"),
		sm("Ge Sc/Sp = Elite Talus Shrine"),
		sm("Sh = Elite Talus Shrine"),
	),
	Koroks.C28,
	Koroks.C32,
	"Kill Stalker for parts",
	Koroks.C30,
];