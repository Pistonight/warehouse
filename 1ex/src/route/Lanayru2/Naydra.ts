import { Boss, fury, gale, Koroks, MakeMorning, Shrines, txt, WindbombStepCps } from "../../engine";

export const Naydra = [
	{
		text: gale(),
		ability: { gale: 1 }
	},
	Koroks.N47,
	WindbombStepCps("<E Turn"),
	Koroks.N44,
	WindbombStepCps("<N Turn"),
	Koroks.N32,
	Shrines.TalohOah,
	WindbombStepCps("N + N + N + E + <E"),
	"Naydra cutscene",
	"Shoot Eye 1",
	"Scale dupe",
	WindbombStepCps("N"),
	Koroks.N24,
	"Shoot Eye 2",
	WindbombStepCps("<S Turn"),
	Koroks.N31,
	WindbombStepCps("SW"),
	"Shoot Eye 3",
	Koroks.N39,
	WindbombStepCps("W> midairs"),
	Koroks.N21,
	WindbombStepCps("E Turn"),
	Koroks.N22,
	Koroks.N29,
	Koroks.N30,
	WindbombStepCps("N"),
	Boss("Red Hinox", txt("1 hit SLAM", fury())).extend({
		ability: { fury: 1 }
	}),
	WindbombStepCps("E"),
	"Shoot Eye 4",
	Shrines.JitanSami,
    
	WindbombStepCps("NE"),
	Koroks.N19,
	WindbombStepCps("W + W"),
	Koroks.N18,
	MakeMorning("Naydra Farming"),
	{
		text: "FARM NAYDRA",
		timeOverride: 240
	}, 
	Koroks.N23,
	WindbombStepCps("<N Turn"),
	Koroks.N17,
	WindbombStepCps("<<N Turn"),
	Koroks.N14,
	WindbombStepCps("<N Turn"),
	Koroks.Z51,
	WindbombStepCps("N>"),
	Shrines.RuccoMaag

];