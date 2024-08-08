import Banner from "../components/Banner";
import SearchBox from "../components/SearchBox";

function HomeContent() {
    return (
        <div className="w-full h-screen grid grid-cols-1 grid-rows-1 place-items-center">
            <div>
                <Banner />
                <SearchBox />
            </div>
        </div>
    );
}

function Home() {
    return (
        <>
            {/* Desktop view */}
            <div className="max-lg:hidden font-mono">
                <HomeContent />
            </div>

            {/* Mobile view */}
            <div className="lg:hidden font-mono">
                <HomeContent />
            </div>
        </>
    );
}

export default Home;