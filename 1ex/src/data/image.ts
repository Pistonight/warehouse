import ImageMap from "../img";

export const getImage = (name: string): string=>{
	const map = ImageMap as {[name: string]:string};
	const image = map[name];
	if(!image){
		return "";
	}
	
	return image;
};