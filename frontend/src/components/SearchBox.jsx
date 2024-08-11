import { useState } from "react";

function SearchBox() {

    const [query, setQuery] = useState("");

    // eslint-disable-next-line react/prop-types
    const SearchButton = ({index, cssStyle, clickHandler}) => {
        return(
            <button key={index} className={cssStyle} onClick={clickHandler}>search</button>
        );
    };
    const updateQuery = (view_type) => {
        setQuery(document.getElementById(view_type.concat("-query")).value);
    };

    if(query !== "") {
        window.location.href = "/result/".concat(query);
    }

    return (
        <>
            {/* Desktop view */}
            <div className="max-lg:hidden">
                <div className="grid grid-rows-1 grid-cols-4">
                    <input className="col-span-3 p-2 mr-4 border-red-600 border-2 rounded focus:border-red-600 text-xl" type="text" id="desktop-query" name="desktop-query" />
                    <SearchButton index={`1`} cssStyle={`border-red-600 border-2 rounded hover:bg-red-600 hover:text-white text-xl`} clickHandler={() => updateQuery("desktop")}/>
                </div>
            </div>

            {/* Mobile view */}
            <div className="lg:hidden">
                <div className="grid grid-rows-2 grid-cols-1 place-items-center">
                    <input className="w-full p-2 my-2 border-2 border-red-600 rounded text-lg" type="text" id="mobile-query" name="mobile-query" />
                    <SearchButton index={`2`} cssStyle={`bg-red-600 border-2 rounded text-white text-lg p-2`} clickHandler={() => updateQuery("mobile")}/>
                </div>
            </div>
        </>
    );
}

export default SearchBox;