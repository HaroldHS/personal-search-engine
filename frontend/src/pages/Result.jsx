import { useEffect, useState } from "react";
import SearchBox from "../components/SearchBox";

function Result(props) {
    
    // eslint-disable-next-line react/prop-types
    let query = props.search_query;
    let [queryResult, setQueryResult] = useState({});

    useEffect(() => {
        fetch("http://localhost:8080/api/v1/search", {
            method: "POST",
            headers: {
                "Content-Type": "application/json"
            },
            body: JSON.stringify({"query": query})
        })
        .then((resp) => resp.json())
        .then((data) => setQueryResult(data));
    }, []);

    const emptyResult = () => {
        return (
            <div className="w-full h-screen grid grid-rows-1 grid-cols-1 place-items-center font-mono">
                <div className="text-red-700 lg:text-6xl max-lg:text-xl">
                    Result Not Found
                </div>
            </div>
        );
    }

    // eslint-disable-next-line react/prop-types
    const QueryResultContainer = ({index, cssStyle}) => {
        let document_path = queryResult[index]["document url"];
        return (
            <div className={cssStyle}>
                <a href={document_path} target="_blank" rel="noopener" onMouseEnter={(e) => {e.target.style.textDecoration = "underline"}} onMouseLeave={(e) => {e.target.style.textDecoration = "none"}}>
                    <b>{queryResult[index]["document name"]}</b>
                </a>
                <br />
                <b className="text-red-500">{document_path.substring(0,30)}...</b>
                <br /><br />
                <p>
                    {queryResult[index]["document summary"]}
                </p>
            </div>
        );
    }

    if (Object.keys(queryResult).length === 0) {
        return emptyResult();
    } else {
        return (
            <>
                <div className="w-full grid grid-cols-1 place-items-left font-mono lg:p-4 max-lg:p-2">
                    <SearchBox result_search_box={true} result_search_value={query} />
                    <h1 className="text-red-600 lg:text-xl lg:my-4 max-lg:text-lg max-lg:my-2">Search results for &quot;<b>{query}</b>&quot;. {queryResult.length} results are found.</h1><br />
                    {
                        queryResult.map((element, index) => (
                            <QueryResultContainer key={index} index={index} cssStyle={`border-2 lg:my-4 lg:p-4 max-lg:my-2 max-lg:p-2`} />
                        ))
                    }
                </div>
            </>
        );
    }
}

export default Result;