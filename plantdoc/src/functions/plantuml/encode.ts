/*
 * Taken from https://plantuml.com/code-javascript-synchronous and typescripted it
 */
const encode64 = (data: Uint8Array): string => {
	let r = "";
	for (let i = 0; i < data.length; i += 3) {
		r += append3bytes(data[i], data[i + 1], data[i + 2]);
	}
	return r;
};

const append3bytes = (b1: number, b2: number | undefined, b3: number | undefined): string => {
	if (b2 === undefined) {
		b2 = 0;
	}
	if (b3 === undefined) {
		b3 = 0;
	}
	const c1 = b1 >> 2;
	const c2 = ((b1 & 0x3) << 4) | (b2 >> 4);
	const c3 = ((b2 & 0xF) << 2) | (b3 >> 6);
	const c4 = b3 & 0x3F;
	let r = "";
	r += encode6bit(c1 & 0x3F);
	r += encode6bit(c2 & 0x3F);
	r += encode6bit(c3 & 0x3F);
	r += encode6bit(c4 & 0x3F);
	return r;
};

const encode6bit = (b: number): string => {
	if (b < 10) {
		return String.fromCharCode(48 + b);
	}
	b -= 10;
	if (b < 26) {
		return String.fromCharCode(65 + b);
	}
	b -= 26;
	if (b < 26) {
		return String.fromCharCode(97 + b);
	}
	b -= 26;
	if (b === 0) {
		return "-";
	}
	if (b === 1) {
		return "_";
	}
	return "?";
};

export default encode64;