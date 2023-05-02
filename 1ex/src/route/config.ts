import { Castle1 } from "./Castle1";
import { Castle2 } from "./Castle2";
import { Central1 } from "./Central1";
import { Central2 } from "./Central2";
import { Eldin1 } from "./Eldin1";
import { Eldin2 } from "./Eldin2";
import { Faron1 } from "./Faron1";
import { Faron2 } from "./Faron2";
// import { Finale } from "./Finale";
import { Gerudo1 } from "./Gerudo1";
import { Gerudo2 } from "./Gerudo2";
import { Hateno1 } from "./Hateno1";
import { Hateno2 } from "./Hateno2";
import { Hebra } from "./Hebra";
import { Kakariko1 } from "./Kakariko1";
import { Kakariko2 } from "./Kakariko2";
import { Kakariko3 } from "./Kakariko3";
import { KorokForest } from "./KorokForest";
import { Lake1 } from "./Lake1";
import { Lake2 } from "./Lake2";
import { ChangeData } from "../engine";
import { Plateau } from "./Plateau";
import { Plateau2 } from "./Plateau2";
import { Rito1 } from "./Rito1";
import { Rito2 } from "./Rito2";

import { ZoraDomain1 } from "./ZoraDomain1";
import { ZoraDomain2 } from "./ZoraDomain2";
import { Akkala1 } from "./Akkala1";
import { Gerudo3 } from "./Gerudo3";
import { Akkala2 } from "./Akkala2";
import { Satori } from "./Satori";
import { SpringOfPower } from "./SpringOfPower";
import { Lanayru1 } from "./Lanayru1";
import { Eventide } from "./Eventide";
import { Lanayru2 } from "./Lanayru2";
import { Finale } from "./Finale";

//The Main Route Object
export const MainRoute = [
	...Plateau,
	...Castle1,
	...Rito1,
	...Kakariko1,
	...Hateno1,
	...Faron1,
	...Kakariko2,
	...Gerudo1,
	...Eldin1,
	...ZoraDomain1,
	...Plateau2,
	...Gerudo2,
	...Eldin2,
	...Akkala1,
	...KorokForest,
	...Castle2,
	...ZoraDomain2,
	...Gerudo3,
	...Hebra,
	...Rito2,
	...Akkala2,
	...Lake1,
	...Satori,
	...Central1,
	...Lanayru1,
	...Central2,
	...Lake2,
	...Faron2,
	...SpringOfPower,
	...Eventide,
	...Kakariko3,
	...Hateno2,
	...Lanayru2,
	...Finale
];

export const Change: ChangeData[] = [
	{
		version: "4.0.0",
		date: "2022-02-18",
		changes: [
			"Completely new route :)",
		]
	},
	
];