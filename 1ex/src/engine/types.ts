import { EngineCommand } from "./command";
import { EngineError } from "./error";

export type InstructionPacket = {
	// Main text
	text: TextLike,
	// Icon and comment to make a 2-line instruction
	icon?: string,
	comment?: TextLike,
	// Detailed Notes
	notes?: TextLike,
	// Image (in image column)
	image?: string,
	// Variable Changes
	shrineChange?: number,
	korokChange?: number, // adding = add total and seeds, subtracting = remove seed (hestu)
	memoryChange?: number,
	variableChange?: {[key: string]:number},
	// Champion Ability Usage
	ability?: AbilityUsage,
	// Override the time this step takes, for calculating ability recharge
	timeOverride?: number,
	// Type is either none, step, split or section (section ignores other options)
	type?: "step" | "split" | "section",
	// Only for splits, the text to prepend to the split name when generating livesplit file
	splitPrefix?: TextLike,
	// Korok Code to check korok completion
	korokCode?: string,
	// Boss type to check boss completion
	bossType?: string,
	// Force show in important mode
	important?: boolean,
	// Suppress error type
	suppressError?: EngineError[],
	// Engine command
	command?: EngineCommand[],
}

export type Text = {
    colorClass?: string,
    content: string
}

export type TextBlock = Text | Text[];
export type TextLike = string | TextBlock;
export type InstructionLike = TextLike | InstructionPacket | undefined;

export type InstructionData = {
	// If is section title, display as section title
	isSectionTitle: boolean,
	// Used to calculate if something should show in split-only view
	isSplit: boolean,
	// Used to calculate if something should be hidden in important-only view
	isKorok: boolean,
	isImportant: boolean,

	splitPrefix?: TextBlock,
	// Line number column
    lineNumber?: number,
	lineNumberClassName?: string,
	// Counter (boss, korok, shrine, memory)
    counterNumber?: string,
	// Counter type
    counterClassName?: string,
	// Step number
    stepNumber?: string,
	// Main text
	text: TextBlock,
	icon?: string,
    comment?: TextBlock,
	// A little colored square to indicate step/split
	stepperClassName?: string,

    // Note column
	hasNotes?: boolean,
    notes?:TextBlock,
	notesClass?:string, // used to display borders
    notesRowSpan?: number,
	notesEmptySecondRow?: boolean, // For 2-line blocks without notes
	// Image
	hasImage?: boolean,
    image?:string,
    imageRowSpan?: number,
	imageEmptySecondRow?: boolean, // For 2-line blocks without images
	// Variables
    variables: {[key: string]:number},
	// Errors. If errors are suppressed they become warnings
	errors?: string,
	warnings?: string,
}

export type AbilityUsage = {
	fury?: number,
	gale?: number,
}

export type ChangeData = {
	version: string,
	date: string,
	changes: (string | string[])[],
}

// Type helpers
// export const useInstructionLike = <T>(instructionFunction: (input: Instruction)=>T):(input?: InstructionLike)=>T => {
// 	return input =>instructionFunction(instructionLikeToInstruction(input));
// };

// export const useMultiText= <T>(instructionFunction: (input?: InstructionLike)=>T):(input?: InstructionLike, ...moreInput: TextLike[])=>T => {
// 	return (input, ...moreInput)=>{
// 		if(isInstruction(input) || moreInput.length === 0){
// 			return instructionFunction(instructionLikeToInstruction(input));
// 		}
// 		//If there is multiple inputs, cat the texts
// 		const text = txt(input as TextLike, ...moreInput);
// 		return instructionFunction(instructionLikeToInstruction(text));
// 	};
    
// };

