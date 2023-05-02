import { Section, Icon, TextRender } from "./components";
import { InstructionData } from "../engine/types";
import React from "react";
import clsx from "clsx";

type InstructionProps = InstructionData & {
    directionMode: string,
	isImportantMode: boolean,
	setFrozenImage: (image:string)=>void,
}

export const Instruction: React.FunctionComponent<InstructionProps> = ({
	isImportantMode,
	isSectionTitle,
	lineNumber,
	lineNumberClassName,
	counterNumber,
	counterClassName,
	stepNumber,
	icon, text, comment,
	stepperClassName,
	hasNotes,
	notes,
	notesClass,
	notesRowSpan,
	notesEmptySecondRow,
	image,
	imageRowSpan,
	imageEmptySecondRow,
	variables,
	errors,
	warnings,
	directionMode,
	setFrozenImage,
})=>{
	if(isSectionTitle){
		return <Section title={text}/>;
	}
	const errorTooltip = errors || warnings || "";

	const lineNumberCell = <td className={clsx("line-number font-number", lineNumberClassName)} >
		<span title={errorTooltip}>{lineNumber}</span>
	</td>;
	const counterCell = <td className={clsx("counter font-number", counterClassName)}>
		{counterNumber || "."}
	</td>;
	const stepNumberCell = <td className="step-number font-number">{stepNumber}</td>;

	const indicatorCell = <td className={clsx("indicator", stepperClassName)} />;
	if (isImportantMode){
		notesEmptySecondRow = false;
		notesRowSpan = icon?2:1;
	}
	const detailCell = notesRowSpan && <td className={clsx("detail",hasNotes && notesClass)} rowSpan={notesRowSpan}>
		{notes && <TextRender textBlock={notes} variables={variables} directionMode={directionMode}/>}
	</td>;
	const imageCell = imageRowSpan && <td className={clsx("image-column",image&&"image-box") }
		style={{
			backgroundImage: `url(${image})`,
			backgroundSize: "100% auto",
			backgroundRepeat: "no-repeat"
		}}
		rowSpan={imageRowSpan} onClick={()=>{
			if(image){
				setFrozenImage(image);
			}
		}}>
	</td>;
	if(icon){
		//with icon, 2 rows
		return <>
			<tr>
				{lineNumberCell}{counterCell}{stepNumberCell}
				<td className="icon" rowSpan={2}>
					<Icon src={icon}/>
				</td>
				<td className="main-text">
					<div className="main-text">
						<TextRender textBlock={text} directionMode={directionMode} variables={variables}/>
					</div>
				</td>
				{indicatorCell}{detailCell}{imageCell}
			</tr>
			<tr>
				<td className="line-number font-number">{lineNumber && lineNumber+1}</td>
				<td className="counter font-number">.</td>
				<td className="step-number font-number"></td>
				<td className="main-text comment-text">{comment && <TextRender textBlock={comment} directionMode={directionMode} variables={variables}/>}</td>
				{indicatorCell}
				{notesEmptySecondRow && <td className="detail" ></td>}
				{imageEmptySecondRow && <td className="image-column"></td>}
			</tr>
		</>;

	}else{
		return <tr>
			{lineNumberCell}
			{counterCell}
			{stepNumberCell}
			<td className="main-text" colSpan={2}>
				<TextRender textBlock={text} directionMode={directionMode} variables={variables}/>
			</td>
			{indicatorCell}
			{detailCell}
			{imageCell}
		</tr>;
		
	}
};

type InstructionTableData = {
    instructions: InstructionData[],
	isImportantMode: boolean,
	directionMode: string,
	setFrozenImage: (image:string)=>void,
}

export const InstructionTable: React.FunctionComponent<InstructionTableData> = ({instructions, isImportantMode, directionMode, setFrozenImage})=>{ 
	let fillerKey = 0;
	return (
		<table>
			<tbody>
				{instructions.map((data, i)=>
					<Instruction key={`line${i}`} {...data} directionMode={directionMode} setFrozenImage={setFrozenImage} isImportantMode={isImportantMode}/>
				)}
				{Array.from({length:20},()=><tr key={fillerKey++}><td colSpan={2}>&nbsp;</td><td className="main-text" colSpan={3}>&nbsp;</td><td colSpan={3}>&nbsp;</td></tr>)}
			</tbody>
		</table>
	);
};
