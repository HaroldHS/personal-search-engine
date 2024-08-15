import Banner from "../components/Banner";

function AddWebPage() {

    const submitDocument = () => {
        let page_name = document.getElementById("page_name").value;
        let page_loc = document.getElementById("page_location").value;
        let page_cont = document.getElementById("page_content").value

        fetch("http://localhost:5000/add_web_page", {
            method: "POST",
            headers: {
                "Content-Type": "application/json"
            },
            body: JSON.stringify({
                "page name": page_name,
                "page location": page_loc,
                "page content": page_cont
            })
        })
        .then((resp) => resp.json())
        .then((data) => data["response code"] === "200" ? alert("[+] Web page submitted") : alert("[-] Failed to submit web page"));
    };

    // eslint-disable-next-line react/prop-types
    const SubmitButton = ({clickHandler}) => {
        return (
            <button className="border-red-600 border-2 rounded lg:hover:bg-red-600 lg:hover:text-white lg:text-xl max-lg:bg-red-600 max-lg:text-white lg:px-10 lg:py-2 max-lg:px-6 max-lg:py-2" onClick={clickHandler}>submit</button>
        );
    };

    return (
        <>
            <div className="w-full grid grid-rows-1 grid-cols-1 place-items-center">
                <Banner />
            </div>
            <div className="w-full font-mono">
                <div className="w-full grid grid-rows-2 grid-cols-1 place-items-left lg:px-10 max-lg:px-4 lg:my-6 max-lg:my-2">
                    <h1 className="lg:mb-4 max-lg:mb-2">Document / Web page name</h1>
                    <input className="border-2 border-solid rounded" id="page_name" name="page_name" />
                </div>
                <div className="w-full grid grid-rows-2 grid-cols-1 place-items-left lg:px-10 max-lg:px-4 lg:my-6 max-lg:my-2">
                    <h1 className="lg:mb-4 max-lg:mb-2">Document location / Web page url</h1>
                    <input className="border-2 border-solid rounded" id="page_location" name="page_location" />
                </div>
                <div className="w-full lg:px-10 max-lg:px-4">
                    <h1 className="lg:mb-4 max-lg:mb-2">Document / Web page content</h1>
                    <div className="w-full grid grid-rows-1 grid-cols-1 place-items-left">
                        <textarea rows="10" className="border-2 border-solid rounded resize-none" id="page_content" name="page_content">
                            Copy the text in the document and pasti it here
                        </textarea>
                    </div>
                    <div className="grid grid-rows-1 grid-cols-1 place-items-center lg:my-8 max-lg:my-6">
                        <SubmitButton clickHandler={() => submitDocument()} />
                    </div>
                </div>
            </div>
        </>
    );
}

export default AddWebPage;