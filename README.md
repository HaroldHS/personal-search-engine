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

#######################################
#### 2.1 Setup virtual environment ####
#######################################
python3 -m venv env
# activate virtual environment
source ./env/bin/activate     # linux
source ./env/Scripts/activate # windows
#######################################

pip install -r requirements.txt

# 2.1 Backend installation (with Java Spring)
```

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