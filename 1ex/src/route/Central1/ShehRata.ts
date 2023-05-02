import { Warp, WindbombStepCps, Koroks, Towers, Boss, txt, fury, Discover, Shrines } from "../../engine";

export const ShehRata = [
	Warp("Sato Koda"),
	WindbombStepCps("SW"),
	Koroks.Z15,
	WindbombStepCps("<W Turn"),
	Koroks.Z14,
	WindbombStepCps("S> TS"),
	Towers.Lanayru(),
	WindbombStepCps("<E Turn"),
	Koroks.Z22,
	Boss("Black Hinox", txt("1 Shot + ", fury())).extend({
		ability: { fury: 3 }
	}),
	WindbombStepCps("SQ to bridge"),
	Discover("Inogo Bridge"),
	WindbombStepCps("W>"),
	Shrines.SohKofi,

	WindbombStepCps("GALE + W").extend({
		ability: { gale: 1 }
	}),
	Koroks.Z13,
	WindbombStepCps("E Turn"),
	Koroks.Z12,
	WindbombStepCps("S>> Ordinal Turn"),
	Shrines.ShehRata,
];