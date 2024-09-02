# personal-search-engine
Personal Search Engine is a small scale "organic" search engine that is accessible via your local machine. The search engine uses TF-IDF algorithm for searching keywords / tokens / terms inside documents / web pages. However, since the project is a small scaled project, manual web pages indexing is needed in order to make the search engine works (look at `Databse page setup` section).

> Note: This project is still on development and the current code base is intended for prototyping. Don't deploy this project on production server/service.

### Requirements

##### Frontend
* NodeJS (React Vite)

##### Backend
* Java 17 (Spring & Gradle)
* MySQL 8

##### Setup
* Python 3

### Installation
```bash
# 1. Frontend installation
cd ./frontend
npm install

# 2. Backend installation
cd ./backend/spring
./gradlew build --refresh-dependencies   # linux
gradlew.bat build --refresh-dependencies # windows

# 3. Database setup (initialization)
python3 SETUP.py # Put arguments here #
```

> Note: In order to setup/initialize your database, you need to specify arguments when running `SETUP.py`. For more details, run `python3 SETUP.py --help`.

### Database page setup

For manual indexing, you can read a csv file containing web page name and url in order to be displayed by backend server. Below is the intended format of the csv file:

```text
Page Name | Page URL
[Page name e.g. "Test website"] | [Page URL e.g. "https://www.test.com/"]
[Page name e.g. "Test website 1"] | [Page URL e.g. "https://www.test1.com/"]
.
.
.
```

> Note: Make sure that `Page Name` doesn't contain `|` delimiter.

### Environment variables for backend

In order to run the backend server properly, these environment variables are important and must be included.
In contrast, you can modify these variables according to your purpose in `application.properties`.

```text
MYSQL_DATABASE = #{environment variable for mysql database name}
MYSQL_USER = #{environment variable for mysql user}
MYSQL_USER_PASSWORD = #{environment variable for mysql password of specified user}
PERSONAL_SEARCH_ENGINE_TFIDF_PATH = #{environment variable for directory path to save tfidf results}
```

> Note: `PERSONAL_SEARCH_ENGINE_TFIDF_PATH` is also defined and used in `AllServicesImpl.java`. If you want to change the variable name, make sure to update it in `application.properties` and `@Value` annotation in `AllServiceImpl.java`.

### Run the servers
```bash
# 1. Frontend
cd ./frontend
npm run dev

# 2. Backend
cd ./backend/spring
./gradlew bootRun    # linux
gradlew.bat bootRun  # windows
```

### License
This project is under [GPL-3.0](./LICENSE) license.