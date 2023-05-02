import { getImage } from "../../data/image";

type Props = {
    src: string,
}

export const Icon:React.FunctionComponent<Props> = ({src})=>{
	const image = getImage(src);
	if(!image){
		return null;
	}
	
	return <img src={image} alt={`Icon ${src}`} width="100%" height="100%"/>;
};
