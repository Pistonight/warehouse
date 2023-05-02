import { Boss, Discover, fury, important, Item, Koroks, Materials, Shrines, SnapQuest, Warp, WindbombStepCps } from "../../engine";

export const Highland = [
	Warp("Shada Naw"),
	WindbombStepCps("E"),
	Koroks.H21,
	WindbombStepCps("NW"),
	Koroks.H12,

	Warp("Kuh Takkar"),
	WindbombStepCps("S to camp"),
	Materials.Tail(4),
	Item("Wood"),
	WindbombStepCps(".S"),
	important("EQUIP SNOW BOOTS"),
	Koroks.G22,
	WindbombStepCps("W> TS or midair"),
	Boss("Rare Talus", fury()).extend({
		ability: { fury: 3 },
	}),
	WindbombStepCps("N + N"),
	SnapQuest("Big Sword").extend({
		notes: "Snap the tip of the handle is enough"
	}),
	WindbombStepCps("NE"),
	Koroks.G09,
	WindbombStepCps("W> Turn"),
	Shrines.KeehaYoog,

	WindbombStepCps(".E Turn"),
	Koroks.G06,
	WindbombStepCps("<<N Turn"),
	Koroks.G03,
	WindbombStepCps("<W Turn"),
	Discover("8th Heroine"),
	SnapQuest("8th Heroine"),
	WindbombStepCps("NE"),
	Koroks.G02,
	WindbombStepCps("GALE + E + E + S").extend({
		ability: { gale: 1 }
	}),
	Koroks.G01,
	WindbombStepCps("<S + S + E"),
	Koroks.G05,
	Koroks.G04,
	"Drop down",
	Koroks.G08,
	WindbombStepCps("<W Turn"),
	Koroks.G07,
	Shrines.KemaKossasa,

	WindbombStepCps("<S Turn"),
	Materials.Tail(3).extend({
		notes: "3 black lizalfos. GEB down"
	}),
	WindbombStepCps("<S Turn"),
	Koroks.G15,
	WindbombStepCps("SQ to rock circle"),
	Koroks.G20,
	WindbombStepCps("N>>"),
	Koroks.G11,
	WindbombStepCps("<<S Turn"),
	Koroks.G16,
	WindbombStepCps("<W Turn"),
	Boss("Luminous Talus", fury()).extend({
		ability: { fury: 3 }
	}),
	{
		important: true,
		text: important("BURN OUT FURY")
	},
	Koroks.G21,
	WindbombStepCps("E> Turn"),
	Shrines.ShoDantu,

	WindbombStepCps("NW"),
	Koroks.W02,
	Koroks.G25,
	"Drop down WB low",
	Koroks.W04,
	Koroks.W05,
	WindbombStepCps("SQ to last"),
	Koroks.W03,
	WindbombStepCps("S. Turn"),
	Koroks.W06,
	WindbombStepCps("E> Turn"),
	Koroks.G33
];