import { Item, Shrines, WindbombStepCps } from "../../engine";

export const ShadaNaw = [
	WindbombStepCps("W>"),
	Item("Raw Gourmet Meat", "Kill 2-3 wolves").extend({
		notes: "If you don't get one you have to reload. Watch for stal spawn"
	}),
	"BLSS + WB to Shada Naw",
	Shrines.ShadaNaw
];