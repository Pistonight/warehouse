
// Use webpack require context to import all images under ../img directory and create a map
export default ((requireContext)=>{
	const imgMap = {};
	requireContext.keys().forEach(k=>{
		const module = requireContext(k);
		const name = k.substring(2, k.length - 4);
		// Clean image path ./name.png => name
		imgMap[name] = module["default"];
	});
	return imgMap;
})(require.context(".", true, /\.png$/));
