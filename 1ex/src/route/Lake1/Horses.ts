import { Boss, ChestSpecial, Discover, emy, fury, Koroks, MakeNight, Materials, Npc, Shrines, txt, WindbombStepCps } from "../../engine";

export const Horses = [
	WindbombStepCps("W"),
	Koroks.L67,
	WindbombStepCps("SQ"),
	Koroks.L68,
	WindbombStepCps("S> Turn"),
	Boss("Stalnox", txt("1 Shot + ", fury())).extend({
		ability: { fury: 3 }
	}),
	WindbombStepCps("GALE + N> Turn").extend({
		ability: { gale: 1 }
	}),
	Koroks.L69,
	WindbombStepCps("SE"),
	Koroks.L80,
	WindbombStepCps("E Turn"),
	Koroks.L81,
	Materials.Rushroom(2, "On the way down"),
	Koroks.L82,
	Koroks.L84,
	WindbombStepCps("SQ to rock"),
	Koroks.L83,
	WindbombStepCps("SQ to beach"),
	Koroks.L85,
	WindbombStepCps("SQ across water"),
	Koroks.L86,
	WindbombStepCps("E>"),
	Koroks.L87,
	Shrines.ShoqaTatone,

	WindbombStepCps("SQ out"),
	Koroks.L88,
	WindbombStepCps("SQ to pond"),
	Koroks.L89,
	WindbombStepCps("SQ"),
	Koroks.L90,
	WindbombStepCps("E> Turn + <E"),
	Koroks.L92,
	WindbombStepCps("GALE + N").extend({
		ability: { gale: 1 },
	}),
	Koroks.L78,
	"Surf down",
	ChestSpecial("Ancient Saddle"),
	Npc("Horse God"),
	WindbombStepCps("W."),
	Koroks.L77,
	WindbombStepCps("<E Turn"),
	Koroks.L91,
	WindbombStepCps("SQ north"),
	Koroks.L76,
	Discover("Horse God Bridge"),
	WindbombStepCps("N>"),
	Koroks.L75,
	WindbombStepCps("<N"),
	{
		important: true,
		text: emy(fury(), " 5 bokos"),
		ability: { fury: 3 }
	},
	"Run to stable",
	Npc("Quest Guy"),
	"Take out horse",
	{
		text: "Do minigame",
		notes: "1:15 and 1:30",
		timeOverride: 140
	},
	Npc("Put on Ancient Saddle"),
	MakeNight("For Stalnox"),
	WindbombStepCps("SQ to shrine"),
	Shrines.KaoMakagh,

	WindbombStepCps("S"),
	Koroks.L65,
	WindbombStepCps("S + S"),
	Koroks.L73,
	WindbombStepCps("E"),
	Koroks.L74,
	WindbombStepCps("<N Turn midair"),
	Koroks.L71,
	WindbombStepCps("W"),
	Koroks.L70,
	WindbombStepCps("NW"),
	Boss("Stalnox", txt("1 Shot + ", fury())).extend({
		ability: { fury: 3 }
	}),
	WindbombStepCps("W"),
	Materials.Rushroom(10, "Shock below bridge").extend({
		notes: "Should be back"
	}),
	WindbombStepCps("W"),
	"Summon Horse",
	Npc("Minigame Guy", "Buy all arrows").extend({
		notes: "20 and 23. Jump off after you got the number"
	}),
	"Run to flower",
	Koroks.L64,
	"Glide to race start",
	WindbombStepCps(".W Turn"),
	Koroks.L63,
	WindbombStepCps("N>"),
	Materials.Tail(1, "Sneakstrike Lizal"),
	Koroks.L47,
	WindbombStepCps("<N Turn"),
	Koroks.L38,
	WindbombStepCps("<N Turn"),
	Koroks.L24,
	WindbombStepCps("<N Turn"),
	Koroks.L08,
	WindbombStepCps("SE"),
	Koroks.L25,
	"SQ to shrine",
	MakeNight("For Stalnox"),
	Shrines.YaNaga.extend({
		splitPrefix: "{Lake 1}"
	}),
    
	WindbombStepCps("E"),
	Npc("Wife")

];