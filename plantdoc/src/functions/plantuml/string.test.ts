import { setDiagramTitle } from "./string";

describe("plantuml/string", () => {
	describe("setDiagramTitle", () => {
		test("when diagram is empty", () => {
			expect(setDiagramTitle("", "testTitle")).toBe("TITLE testTitle\n");
		});
		test("when diagram does not have title", () => {
			expect(setDiagramTitle("some diagram text", "testTitle")).toBe("TITLE testTitle\nsome diagram text");
		});
		test("when diagram has title and content", () => {
			expect(setDiagramTitle("TITLE old title\nsome diagram text", "testTitle")).toBe("TITLE testTitle\nsome diagram text");
		});
		test("when diagram has title only", () => {
			expect(setDiagramTitle("TITLE old title", "testTitle")).toBe("TITLE testTitle");
			expect(setDiagramTitle("TITLE old title\n", "testTitle")).toBe("TITLE testTitle\n");
		});
	});
});