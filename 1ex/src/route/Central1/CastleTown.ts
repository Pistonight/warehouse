import { Boss, Chest, ChestSpecial, Discover, Equipment, fury, Koroks, Shrines, txt, WindbombStepCps } from "../../engine";

export const CastleTown = [
	WindbombStepCps("W> Turn"),
	Discover("Rauru Settlements Ruins"),
	WindbombStepCps("S> Turn"),
	Koroks.C02,
	WindbombStepCps("W> Turn"),
	Discover("Helmhead Bridge"),
	WindbombStepCps("S> Turn"),
	Koroks.C05,
	Boss("Stalnox", txt("6 Shots + ", fury())).extend({
		ability: { fury: 1 }
	}),
	Koroks.C04,
	WindbombStepCps("<S Turn"),
	Koroks.C10,
	WindbombStepCps("<<E Delay"),
	Koroks.C06,
	WindbombStepCps("<E"),
	Boss("Luminous Talus", fury()).extend({
		ability: { fury: 2 }
	}),
	WindbombStepCps("GALE + S").extend({
		ability: { gale: 1 }
	}),
	Equipment("Guardian Axe +", "GEB down guardian"),
	Chest("Frost Spear"),
	Shrines.NamikaOzz,

	WindbombStepCps("W"),
	Koroks.C09,
	WindbombStepCps("SW SQ"),
	Koroks.C11,
	WindbombStepCps("E> Turn"),
	Koroks.C12,
	WindbombStepCps("<W Turn"),
	Koroks.C19,
	WindbombStepCps("<W Turn"),
	Koroks.C23,
	WindbombStepCps("NW"),
	Koroks.C18,
	WindbombStepCps(".W Turn"),
	Koroks.C17,
	WindbombStepCps("SW Super maybe"),
	Koroks.C27,
	WindbombStepCps("<<W Turn"),
	Koroks.C31,
	WindbombStepCps("NW"),
	Shrines.KatahChuki,

	WindbombStepCps("N"),
	Koroks.C26,
	Discover("Water Reservoir"),
	Koroks.C22,
	WindbombStepCps("SW"),
	Koroks.C25,
	WindbombStepCps("N"),
	Koroks.C21,
	"Run",
	Koroks.C20,
	WindbombStepCps(".N Turn"),
	Boss("Rare Talus", fury()).extend({
		ability: { fury: 3 }
	}),
	WindbombStepCps("SE"),
	Koroks.C15,
	WindbombStepCps("<N Turn"),
	ChestSpecial("Tingle Shirt"),
	Koroks.C08,
	Koroks.C03,
	WindbombStepCps("NW"),
	Koroks.C01,
	WindbombStepCps("W Turn"),
	Koroks.R11,
	WindbombStepCps("<S Turn"),
	Koroks.C07,
	WindbombStepCps("N"),
	Koroks.C14,
	Shrines.NoyaNeha

];