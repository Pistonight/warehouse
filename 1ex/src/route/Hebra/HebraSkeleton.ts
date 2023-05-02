import { Boss, Discover, fury, Koroks, MakeNight, Shrines, SnapQuest, txt, WindbombStepCps } from "../../engine";

export const HebraSkeleton = [
	WindbombStepCps("W> TS"),
	Koroks.H10,
	WindbombStepCps("E"),
	Koroks.H11,
	MakeNight("For Stalnox"),
	"Drop down",
	Koroks.H09,
	WindbombStepCps("GALE + NW").extend({
		ability: { gale: 1 }
	}),
	Koroks.H04,
	WindbombStepCps("W Turn"),
	Boss("Stalnox", txt("1 Shot + ", fury())).extend({
		ability: { fury: 3 }
	}),
	WindbombStepCps("W"),
	Koroks.H01,
	Shrines.HiaMiu,

	"Take updraft up",
	WindbombStepCps("S + <S Turn"),
	Koroks.H23,
	WindbombStepCps("N> Turn"),
	Discover("Sherin's Secret Spring"),
	WindbombStepCps("N + E"),
	Koroks.H07,
	WindbombStepCps("GALE + E + E").extend({
		ability: { gale: 1 }
	}),
	Koroks.H08,
	WindbombStepCps("N SQ down"),
	Koroks.H02,
	"Mountain Clip",
	Koroks.H03,
	Shrines.ToQuomo,
	SnapQuest("Hebra Skeleton"),
];