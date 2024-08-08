import { useState } from "react";
import { Navigate } from "react-router-dom";

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
        return (
            <Navigate to={`/result/`.concat(query)} replace />
        );
    }

    return (
        <>
            {/* Desktop view */}
            <div className="max-lg:hidden">
                <div className="grid grid-rows-1 grid-cols-4">
                    <input className="col-span-3 p-2 mx-4 border-2 border-blue-600 rounded text-xl" type="text" id="desktop-query" name="desktop-query" />
                    <SearchButton index={`1`} cssStyle={`text-xl border rounded`} clickHandler={() => updateQuery("desktop")}/>
                </div>
            </div>

            {/* Mobile view */}
            <div className="lg:hidden">
                <div className="grid grid-rows-2 grid-cols-1 place-items-center">
                    <input className="w-full p-2 my-2 border-2 border-blue-600 rounded text-lg" type="text" id="mobile-query" name="mobile-query" />
                    <SearchButton index={`2`} cssStyle={`p-2 text-lg border rounded`} clickHandler={() => updateQuery("mobile")}/>
                </div>
            </div>
        </>
    );
}

export default SearchBox;