import { Boss, fury, Koroks, Npc, Shrines, Towers, WindbombStepCps } from "../../engine";

export const AfterRudania = [
	"SQ to Bludo",
	Npc("Bludo"),
	WindbombStepCps("E to race start"),
	Koroks.E17,
	WindbombStepCps("S + E"),
	Shrines.DaqaKoh,
	WindbombStepCps("<<S"),
	Boss("Igneo Talus", fury()).extend({
		ability: { fury: 3 },
		notes: "Break luminous stone with the attack from fury"
	}),
	WindbombStepCps("SW"),
	Npc("Do Kayra Mah Quest", "Unequip Bow"),
	Shrines.KayraMah,
	WindbombStepCps("S> Turn + <S Turn"),
	Towers.Eldin().extend({
		splitPrefix:"{Eldin 1}"
	})
];