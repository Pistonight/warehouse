import { TextBlock } from "../../engine/types";
import { TextRender } from "./Text";

type Props = {
    title: TextBlock
}
export const Section:React.FunctionComponent<Props> = ({title})=>{
	return (
		<tr>
			<td className="line-number font-number"></td>
			<td className="counter font-number"></td>
			<td className="step-number font-number"></td>
			<td className="main-text" colSpan={5}>
				<div className="section-header">
					<TextRender textBlock={title} directionMode="" variables={{}}/>
				</div>
			</td>
		</tr>
	);
};