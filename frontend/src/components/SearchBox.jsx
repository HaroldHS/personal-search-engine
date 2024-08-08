function SearchBox() {
    return (
        <>
            {/* Desktop view */}
            <div className="max-lg:hidden">
                <div className="grid grid-rows-1 grid-cols-4">
                    <input className="col-span-3 p-2 mx-4 border-2 border-blue-600 rounded text-xl" type="text" id="query" name="query" />
                    <button className="text-xl border rounded">search</button>
                </div>
            </div>

            {/* Mobile view */}
            <div className="lg:hidden">
                <div className="grid grid-rows-2 grid-cols-1 place-items-center">
                    <input className="w-full p-2 my-2 border-2 border-blue-600 rounded text-lg" type="text" id="query" name="query" />
                    <button className="p-2 text-lg border rounded">search</button>
                </div>
            </div>
        </>
    );
}

export default SearchBox;