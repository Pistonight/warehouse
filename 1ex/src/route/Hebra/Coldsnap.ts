import { Boss, Discover, fury, important, Item, Koroks, MakeMorning, Shrines, Towers, Warp, WindbombStepCps } from "../../engine";

export const Coldsnap = [
	Warp("Shada Naw"),
	WindbombStepCps("S> Turn"),
	Koroks.H33,
	WindbombStepCps("SQ to talus"),
	Boss("Frost Talus", fury()).extend({
		ability: { fury: 3 }
	}),
	WindbombStepCps(".S"),
	Koroks.H34,
	WindbombStepCps("GALE + <S").extend({
		ability: { gale: 1 },
		notes: "Gale in updraft"
	}),
	Koroks.H44,
	WindbombStepCps("S>"),
	Koroks.H46,
	WindbombStepCps("SE"),
	Koroks.H48,
	WindbombStepCps("N start race"),
	WindbombStepCps("SE"),
	Koroks.H50,
	Koroks.H47,
	Koroks.H49,
	WindbombStepCps("NE"),
	Koroks.H45,
	WindbombStepCps("S"),
	Koroks.H56,
	WindbombStepCps("NE"),
	Koroks.H51,
	WindbombStepCps("E> Turn"),
	Shrines.GeeHarah,

	WindbombStepCps("N + S + E"),
	Koroks.H57,
	WindbombStepCps("<W Turn"),
	Item("2 Wood"),
	Shrines.LannoKooh,

	Item("2 Wood Again"),
	Koroks.H64,
	"Swim out",
	WindbombStepCps("<E"),
	Koroks.H61,
	WindbombStepCps("E>>"),
	Towers.Hebra(),
	MakeMorning("For Dinraal").extend({
		notes: "Only if need to activate DLC shrine or already missed a part"
	}),
	WindbombStepCps("SQ toward lodge"),
	Discover("Pondo's Lodge"),
	WindbombStepCps("GALE + SW").extend({
		ability: { gale: 1 }
	}),
	Koroks.H67,
	Koroks.H70,
	WindbombStepCps("<S Turn"),
	Koroks.R02,
	Boss("Luminous Talus", fury()).extend({
		ability: { fury: 3 }
	}),
	{
		important: true,
		text: important("BURN OUT FURY")
	},
	WindbombStepCps("E SQ"),
	Koroks.R03.extend({
		notes: "Shoot dinraal after this korok if need to activate DLC shrine. If not missing any part, no need to pick up the part"
	}),
	WindbombStepCps("<N Turn"),
	Koroks.H71,
	WindbombStepCps("S> Turn"),
	Shrines.ShiraGomar

];