interface LocalStore {
	store: (obj: Record<string, unknown> | string | null) => void,
	load: () => string | null,
	clear: () => void,
	exists: () => boolean
}
export default function useLocalStore(name: string): LocalStore {
	const store = (obj: Record<string, unknown> | string | null): void => {
		localStorage.setItem(name, JSON.stringify(obj));
	};

	const load = (): string | null => {
		const string = localStorage.getItem(name);
		if (string === null) return null;
		return JSON.parse(string);
	};

	const clear = (): void => {
		localStorage.removeItem(name);
	};

	const exists = (): boolean => {
		return localStorage.getItem(name) !== null;
	};
	return { store, load, clear, exists };
}

