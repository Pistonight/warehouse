import { Boss, Discover, fury, Item, Koroks, Npc, Shrines, STEP, Warp, WindbombStepCps } from "../../engine";

export const FlightRange = [
	Warp("Shada Naw"),
	WindbombStepCps("<<W Turn"),
	Koroks.H27,
	WindbombStepCps(".S Turn"),
	Koroks.H43,
	WindbombStepCps("S Turn"),
	Koroks.H55,
	WindbombStepCps("W> Turn"),
	Koroks.H54,
	WindbombStepCps("SQ to frost talus"),
	Boss("Frost Talus", fury()).extend({
		ability: { fury: 3 }
	}),
	WindbombStepCps("SQ to flight range"),
	Koroks.T02,
	"Activate shrine",
	Shrines.NoeRajee,

	{
		type: STEP,
		text: "SCW into Sha Warvo",
		notes: "SCW only saves 13 seconds"
	},
	Shrines.ShaWarvo,

	WindbombStepCps("S"),
	Koroks.T04,
	WindbombStepCps("<<E Turn"),
	Discover("Hebra Trailhead Lodge"),
	WindbombStepCps("<S"),
	Koroks.T05,
	WindbombStepCps("SE"),
	Koroks.T11,
	WindbombStepCps("SQ to stable"),
	Npc("Goron Spice Guy", "Twice"),
	Item("Farm Wood", ">= 30").extend({
		timeOverride: 30,
	}),
	WindbombStepCps("N> Super").extend({
		notes: "Aim at pillar"
	}),
	Koroks.T03
];