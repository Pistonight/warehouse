/*
 * Wrapper class to get url from plant uml code
 */
import plant64 from "./encode";
import zlib from "zlib";
const compressAndEncode = (diagramText: string) => {
	const compressed = zlib.deflateRawSync(diagramText, { level: 9 });
	const encoded = plant64(compressed);
	return encoded;
};
export const svgUrl = (diagramText: string): string => `https://www.plantuml.com/plantuml/svg/${compressAndEncode(diagramText)}`;
export const pngUrl = (diagramText: string): string => `https://www.plantuml.com/plantuml/img/${compressAndEncode(diagramText)}`;
