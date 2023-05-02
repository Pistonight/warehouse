import { Boss, ChestSpecial, Discover, fury, Koroks, Npc, Towers, txt, WindbombStepCps } from "../../engine";

export const FaronTower = [
	WindbombStepCps("W"),
	Koroks.L13,
	WindbombStepCps("W"),
	Koroks.L17,
	Koroks.L18,
	WindbombStepCps("SQ north ish"),
	Koroks.L12,
	WindbombStepCps("GALE + SW").extend({
		ability: { gale: 1 }
	}),
	Koroks.L28,
	WindbombStepCps("E"),
	Koroks.L29,
	WindbombStepCps("E> Turn"),
	Koroks.L34,
	WindbombStepCps("<<W"),
	Koroks.L35.extend({
		notes: "Get 2 durians if needed"
	}),
	WindbombStepCps("S"),
	Koroks.L45,
	WindbombStepCps("SQ to river"),
	Koroks.L54,
	WindbombStepCps("E"),
	Discover("Zonai Ruins").extend({
		notes: "Land below camp near pillar"
	}),
	Koroks.L56,
	WindbombStepCps("SQ to kass"),
	Npc("Kass"),
	Koroks.L57,
	WindbombStepCps("S>"),
	Koroks.L61,
	Koroks.L66,
	WindbombStepCps("SE + SE"),
	Koroks.L72,
	WindbombStepCps(".S Turn"),
	Koroks.L79,
	WindbombStepCps("E>> DT"),
	Koroks.F56,
	WindbombStepCps("<N Turn"),
	Koroks.F54,
	WindbombStepCps("SQ to beach"),
	Boss("Stone Talus", fury()).extend({
		ability: { fury: 1 }
	}),
	Koroks.F53,
	WindbombStepCps("<<N"),
	Koroks.F38,
	WindbombStepCps("W"),
	Boss("Blue Hinox", txt("3 Hits SLAM ", fury())).extend({
		ability: { fury: 1 }
	}),
	WindbombStepCps("N"),
	Koroks.F24,
	Discover("Sarjon Bridge"),
	Koroks.F23,
	ChestSpecial("Phantom Ganon Armor"),
	WindbombStepCps("NE"),
	Koroks.L58,
	WindbombStepCps("N"),
	Koroks.L46,
	WindbombStepCps("GALE + N midairs").extend({
		ability: { gale: 1 }
	}),
	Koroks.F02,
	WindbombStepCps("N> Turn"),
	Boss("Stone Talus", fury()).extend({
		ability: { fury: 1 }
	}),
	WindbombStepCps("SE"),
	Koroks.F05,
	WindbombStepCps("SQ to forest"),
	Koroks.F01,
	WindbombStepCps("S. Turn"),
	Koroks.F04,
	Koroks.F03,
	ChestSpecial("Zant's Helm"),
	WindbombStepCps(".S Turn"),
	Boss("Red Hinox", "4BA (heashot 1)"),
	WindbombStepCps(".S Turn"),
	Koroks.F10,
	WindbombStepCps("E>> Turn"),
	Koroks.F16,
	WindbombStepCps("<W Turn"),
	Koroks.F15,
	WindbombStepCps("GALE + E").extend({
		ability: { gale: 1 }
	}),
	Boss("Red Hinox", "4BA (heashot 1)"),
	WindbombStepCps("S midair/run"),
	Koroks.F25,
	WindbombStepCps("SW"),
	Towers.Faron()
];