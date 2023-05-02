import React from "react";
import "./App.css";
import { InstructionTable } from "./render";
import ImageDirectionClock from "./img/direction-clock.png";
import ImageDirectionCompass from "./img/direction-compass.png";
import ImageMasterCycle from "./img/master-cycle.png";
import ImageTower from "./img/tower.png";
import ImageKorokMinus from "./img/korok-minus.png";
import { InstructionEngine } from "./engine/engine";
import { ChangeData, InstructionLike } from "./engine/types";
import { ChangeLog } from "./render/ChangLog";
import queryString from "query-string";
import { createLiveSplitFile } from "./data/livesplit";
import { saveAs } from "./external/FileSaver";

type Props = {
	config: InstructionLike[],
	changelog: ChangeData[],
	project: string,
}

type State ={
	frozenImage?: string,
	showMenu: boolean,
	directionMode: string,
	showSplitOnly: boolean,
	showNonKorokIconOnly: boolean,
	showChangeLog: boolean,
}

const engine = new InstructionEngine();

class App extends React.Component<Props, State> {
	constructor(props: Props){
		super(props);
		this.state = {
			showMenu: false,
			directionMode: "clock",
			showSplitOnly: false,
			showNonKorokIconOnly: false,
			showChangeLog: false,
		};
	}

	componentDidMount():void {
		const localStoredDirectionMode = localStorage.getItem("DirectionMode");
		if(localStoredDirectionMode){
			this.setState({
				directionMode:localStoredDirectionMode
			});
		}

		const lastViewedVersion = localStorage.getItem("LastVersion");
		const query = queryString.parse(window.location.search);
		const latestVersion = this.props.changelog[0].version;

		if(lastViewedVersion !== latestVersion || query.change){
			this.setState({
				showChangeLog:true
			});
			localStorage.setItem("LastVersion", latestVersion);
		}
	}

	toggleDirectionMode(): void {
		this.setState({
			directionMode: this.state.directionMode === "clock" ? "compass" : "clock"
		},()=>{
			localStorage.setItem("DirectionMode", this.state.directionMode);
		});
	}

	renderInstructionTable(): JSX.Element {
		const instructions = engine.compute(this.props.config);
		let filteredInstructions;
		if(this.state.showSplitOnly){
			filteredInstructions = instructions.filter((inst)=>{
				return inst.isSectionTitle || inst.isSplit;
			});
		}else if(this.state.showNonKorokIconOnly){
			filteredInstructions = instructions.filter((inst)=>{
				return inst.isSectionTitle || inst.isSplit || inst.isImportant || inst.icon && !inst.isKorok;
			});
		}else{
			filteredInstructions = instructions;
		}

		return <InstructionTable 
			instructions={filteredInstructions} 
			isImportantMode={this.state.showNonKorokIconOnly}
			directionMode ={this.state.directionMode}
			setFrozenImage={(img)=>this.setState({frozenImage: img})}/>;
	}

	downloadSplits(): void {
		const fileContent = createLiveSplitFile(engine.compute(this.props.config));
		const latestVersion = this.props.changelog[0].version;
		saveAs(fileContent, `${this.props.project}-${latestVersion}.lss`);
	}

	render(): JSX.Element{

		return <>
			{this.state.frozenImage &&
			<div className="frozen-image" onClick={()=>this.setState({frozenImage: undefined})}>
				<img src={this.state.frozenImage} alt="Frozen Route Image" title="Click to hide" width="100%" height="100%"/>
			</div>}
			<div className="fixed-control">
				<button onClick={()=>this.setState({showMenu: !this.state.showMenu})}>Toggle Menu</button>
				<br/>
				{this.state.showMenu &&
					<>
						<button onClick={()=>this.toggleDirectionMode()}>
							<img src={this.state.directionMode === "clock" ? ImageDirectionClock : ImageDirectionCompass} height="48" width="auto" alt="Direction" title="Toggle Direction Mode"/>
						</button>
						<br></br>
						<button onClick={()=>this.setState({showSplitOnly: !this.state.showSplitOnly})}>
							<img src={ImageMasterCycle} height="48" width="auto" alt="Split Only" title="Toggle Show Split Only"/>
						</button>
						<br></br>
						<button onClick={()=>this.setState({showNonKorokIconOnly: !this.state.showNonKorokIconOnly})}>
							<img src={ImageKorokMinus} height="48" width="auto" alt="Important Only" title="Toggle Important Only"/>
						</button>
						<br></br>
						<button onClick={()=>this.downloadSplits()}>
							<img src={ImageTower} height="48" width="auto" alt="Download Splits" title="Download Splits"/>
						</button>

					</>
				}
			</div>
			{this.state.showChangeLog && <ChangeLog log={this.props.changelog} close={()=>{this.setState({showChangeLog: false});}} />}
			{this.renderInstructionTable()}
		</>;
	}
}

export default App;
