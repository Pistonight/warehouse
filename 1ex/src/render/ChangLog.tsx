import { ChangeData } from "../engine/types";

type Props = {
    log: ChangeData[],
    close: ()=>void
};

export const ChangeLog: React.FunctionComponent<Props> = ({log, close})=>{
	return <div style={{position: "fixed", width: "100vw", height:"100vh", backgroundColor: "rgba(0,0,0,0.5)"}}>
		<div style={{margin: "0 10em", height: "75%"}}>
			<div style={{height: "10%", backgroundColor: "white", padding:"0 1em"}}>
				<h1>Change Log <button onClick={()=>close()}>Close</button></h1> 
			</div>
			<div style={{overflowY: "scroll", height: "90%", backgroundColor: "white",  padding:"0 1em"}}>
				{log.map((l, i)=><div key={`version_${i}`}>
					<h3>{l.version} | {l.date}</h3>
					<hr/>
					<ul>
						{l.changes.map((c,i)=>
							Array.isArray(c) ? 
								<ul>
									{c.map((c2,j)=><li key={j}>{c2}</li>)}
								</ul>
								: <li key={i}>{c}</li>
						)}
					</ul>
                    
				</div>)}
			</div>
		</div>
	</div>;
};