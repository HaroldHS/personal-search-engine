# personal-search-engine
Personal Search Engine is a small scale "organic" search engine that is accessible via your local machine.

### Requirements

##### Frontend
* NodeJS (React Vite)

##### Backend
* Python 3 (Flask)
* Java 17 (Spring)
* MySQL 8

### Installation
```bash
# 1. Frontend installation
cd ./frontend
npm install

# 2.0 Backend installation (with Python Flask)
cd ./backend/flask

#########################################
#### 2.0.1 Setup virtual environment ####
#########################################
python3 -m venv env
# activate virtual environment
source ./env/bin/activate     # linux
source ./env/Scripts/activate # windows
#######################################

pip install -r requirements.txt

# 2.1 Backend installation (with Java Spring)
cd ./backend/spring
./gradlew build --refresh-dependencies   # linux
gradlew.bat build --refresh-dependencies # windows
```

### Environment variables for backend

In order to run the backend server properly, these environment variables are important and must be included.
In contrast, you can modify these variables according to your purpose inside `application.properties`.

```bash
MYSQL_DATABASE = #{mysql database name}
MYSQL_USER = #{mysql user}
MYSQL_USER_PASSWORD = #{mysql password for the specified user}
PERSONAL_SEARCH_ENGINE_TFIDF_PATH = #{local path for saving tfidf results}
```

> Note: `PERSONAL_SEARCH_ENGINE_TFIDF_PATH` is also defined and used in `AllServicesImpl.java`. If you want to change the variable name, make sure to update it in `application.properties` and `@Value` annotation in `AllServiceImpl.java`.

### Run the servers
```bash
# 1. Frontend
cd ./frontend
npm run dev

# 2.0 Backend (Python Flask)
cd ./backend/flask
python3 main.py

# 2.1 Backend (Java Spring)
cd ./backend/spring
./gradlew bootRun    # linux
gradlew.bat bootRun  # windows
```

> Note: To deactivate virtual environment, just run `deactivate` command in terminal/command prompt. In addition, don't forget to create `tfidf_json` folder inside `backend` folder in order to accommodate documents added via `/add_web_page`.

### License
This project is under [GPL-3.0](./LICENSE) license.