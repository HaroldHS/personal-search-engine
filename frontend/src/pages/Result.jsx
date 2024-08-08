import { useEffect, useState } from "react";

function Result(props) {
    
    // eslint-disable-next-line react/prop-types
    let query = props.search_query;
    let [queryResult, setQueryResult] = useState({});

    useEffect(() => {
        fetch("http://localhost:5000/search", {
            method: "POST",
            headers: {
                "Content-Type": "application/json"
            },
            body: JSON.stringify({"query": query})
        })
        .then((resp) => resp.json())
        .then((data) => data["response code"] === "200" ? setQueryResult(data["response body"]) : {});
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
        // NOTE: For windows, repalce C:\\ with C:\\\\ in order to prevent escaping sequence bug
        const reg = /^[A-Z]{0,1}:\\/;
        let document_path = queryResult[index]["document path"];
        let drive_match = document_path.match(reg)[0];
        let final_document_path = document_path.replace(drive_match, "C:\\\\");
        return (
            <div className={cssStyle}>
                {query} inside {queryResult[index]["document name"]}
                <br /><br />
                File location: <b className="text-blue-600">{final_document_path}</b>
                <br /><br />
                Click <a href={final_document_path} target="_blank" rel="noopener"><u>here</u></a> to open the file
            </div>
        );
    }

    if (Object.keys(queryResult).length === 0) {
        return emptyResult();
    } else {
        return (
            <>
                <div className="w-full grid grid-cols-1 place-items-center font-mono">
                    <h1 className="text-blue-600 lg:text-6xl lg:my-10 max-lg:text-xl max-lg:my-4">Results</h1>
                    {
                        queryResult.map((element, index) => (
                            <QueryResultContainer key={index} index={index} cssStyle={`border-2 lg:my-4 max-lg:my-2`} />
                        ))
                    }
                </div>
            </>
        );
    }
}

export default Result;