import { emy, EngineCommands, Equipment, gale, Item, Koroks, Npc, rne, Shrines, SPLIT, STEP, txt, Warp, WindbombStepCps } from "../../engine";

export const AfterFuryPlus = [
	Warp("Naboris"),
	{
		text: emy("Thunderblight 2"),
		icon: "naboris-plus",
		comment: txt("Equip ", rne("STASIS")),
		type: SPLIT,
		command: [EngineCommands.EnableFuryPlus]
	},

	WindbombStepCps("<N Turn"),
	Koroks.W12,
	Item("Electric Darner").extend({
		notes: "Might want to kill the electric lizalfos if he is too annoying"
	}),
	Koroks.W16,
	{ type: STEP, text: "SQ to tree" },
	Koroks.W11,
	emy("Kill Wizzrobe"),
	Equipment("Fire Rod"),
	WindbombStepCps(".S Turn"),
	"Start race",
	WindbombStepCps("<<S"),
	Koroks.W24,
	Koroks.W23,
	WindbombStepCps("E + S + S"),
	Koroks.W34,
	WindbombStepCps("E>> Turn"),
	Koroks.W41,
	WindbombStepCps("S + S"),
	Koroks.W53,
	WindbombStepCps("S"),
	Npc("Diary", "1112"),
	Shrines.SumaSahma,

	WindbombStepCps(".S Turn"),
	Koroks.W60,
	WindbombStepCps("S SQ"),
	Koroks.W64,
	WindbombStepCps("W> Turn"),
	Koroks.W59,
	WindbombStepCps("N> Turn"),
	Koroks.W54,
	WindbombStepCps("<N Turn (Steer right)"),
	Koroks.W46,
	{
		text: gale(),
		ability: { gale: 1 },
	},
	Koroks.W45,
	WindbombStepCps("S>> Turn"),
	Shrines.DilaMaag.extend({
		splitPrefix: "{Gerudo 2}"
	})

];