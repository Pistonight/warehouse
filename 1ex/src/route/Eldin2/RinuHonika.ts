import { Equipment, fury, Item, Koroks, Shrines, STEP, txt, WindbombStepCps } from "../../engine";

export const RinuHonika = [
	{
		text: txt(fury()," 3 Guardians"),
		ability: { fury: 3 },
		type: STEP,
		notes: "windbomb to middle and attract far skywatcher, fury when the middle watcher is low. Stun turrent if needed"
	},
	Item("Parts if need", "15 springs, 5 shafts"),
	"Kill last skywatcher",
	Equipment("Moblin Club").extend({
		notes: "Throw used lynel crusher into him. Also throw away flameblade. Keep 1 slot for drillshaft and ancient sword"
	}),
	{ type: STEP, text: "Activate Sato Koda"},
	"Shield surf SCW",
	WindbombStepCps("N> Super + N"),
	"BLSS from Akkala Tower",
	Shrines.SharoLun,

	WindbombStepCps("<<W Turn"),
	Koroks.E23,
	WindbombStepCps("W"),
	Koroks.E22,
	Shrines.RinuHonika
    
];