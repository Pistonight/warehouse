import Cryptr from 'cryptr';

const BASE64 = 'ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/';
const HEXBIN = {
	"0": "0000",
	"1": "0001",
	"2": "0010",
	"3": "0011",
	"4": "0100",
	"5": "0101",
	"6": "0110",
	"7": "0111",
	"8": "1000",
	"9": "1001",
	"a": "1010",
	"b": "1011",
	"c": "1100",
	"d": "1101",
	"e": "1110",
	"f": "1111",
}

const BINHEX = {
	"0000": "0",
	"0001": "1",
	"0010": "2",
	"0011": "3",
	"0100": "4",
	"0101": "5",
	"0110": "6",
	"0111": "7",
	"1000": "8",
	"1001": "9",
	"1010": "a",
	"1011": "b",
	"1100": "c",
	"1101": "d",
	"1110": "e",
	"1111": "f",
}
const binToB64Char = (bin) => {
	let i = 0;
	for (let c = 0; c < bin.length; c++) {
		i *= 2;
		if (bin[c] === "1") {
			i++;
		}
	}
	return BASE64[i];
};
const B64CharToBinLen6 = (b64Char) => {
	let i = BASE64.indexOf(b64Char);
	if (i < 0) {
		return "=";
	}
	const vals = [32, 16, 8, 4, 2, 1];
	let str = "";
	for (let n = 0; n < vals.length; n++) {
		if (i >= vals[n]) {
			str += "1";
			i -= vals[n];
		} else {
			str += "0";
		}
	}
	return str;
}

const binStrToB64 = (bin) => {
	//bin has length multiple of 8
	const last = bin.length % 24;
	let b64 = "";
	let paddedBin = bin;
	let b64Padding = "";
	if (last === 16) {
		paddedBin += "00";
		b64Padding = "=";
	} else if (last === 8) {
		paddedBin += "0000";
		b64Padding = "==";
	}
	for (let i = 0; i < paddedBin.length; i += 6) {
		const bin6 = paddedBin.substring(i, i + 6);
		const b64Char = binToB64Char(bin6);
		b64 = b64 + b64Char;
	}
	return b64 + b64Padding;
}

const b64ToBinString = (b64) => {
	let bin = "";
	for (let i = 0; i < b64.length; i++) {
		bin += B64CharToBinLen6(b64[i]);
	}
	if (bin.endsWith("==")) {
		bin = bin.substring(0, bin.length - 6);
	} else if (bin.endsWith("=")) {
		bin = bin.substring(0, bin.length - 3);
	}
	return bin;
}
const b64ToHex = (b64) => {
	b64 = b64.replace(/[\s\n]/g, "");
	if (b64.length % 4 !== 0) return "";
	if (b64.length === 0) return "";
	for (let i = 0; i < b64.length - 2; i++) {
		if (!/[A-Za-z0-9+\/]/.test(b64[i])) return "";
	}
	if (!/[A-Za-z0-9+\/=]/.test(b64[b64.length - 2])) return "";
	if (!/[A-Za-z0-9+\/=]/.test(b64[b64.length - 1])) return "";
	const binString = b64ToBinString(b64);
	let hex = "";
	for (let i = 0; i < binString.length; i += 4) {
		hex += BINHEX[binString.substring(i, i + 4)];
	}
	return hex;
}

const hexToB64 = (hex) => {
	if (hex.length % 2 === 1) return "";
	let bin = "";
	for (let i = 0; i < hex.length; i++) {
		bin += HEXBIN[hex[i].toLowerCase()];
	}
	return binStrToB64(bin);
}

export const encrypt = (plain) => {
	const cryptr = new Cryptr(process.env.TOKEN_ENCRYPTION_KEY);
	const cipher = cryptr.encrypt(plain);
	const b64 = hexToB64(cipher);
	return b64;
}
export const decrypt = (cipher) => {
	try {
		const hex = b64ToHex(cipher);
		if (!hex) return "";
		const cryptr = new Cryptr(process.env.TOKEN_ENCRYPTION_KEY);
		const plain = cryptr.decrypt(hex);
		return plain;
	} catch (err) {
		console.log(err);
		return "";
	}
}

