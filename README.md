# personal-search-engine
Personal Search Engine is a small scale "organic" search engine that is accessible via your local machine.

### Requirements
* NodeJS (React Vite)
* Python 3 (Flask)

### Installation
```bash
# 1. Frontend installation
cd ./frontend
npm install

# 2. Backend installation
cd ./backend

#######################################
#### 2.1 Setup virtual environment ####
#######################################
python3 -m venv env
# activate virtual environment
source ./env/bin/activate     # linux
source ./env/Scripts/activate # windows
#######################################

pip install -r requirements.txt
```

### Run the servers
```bash
# 1. Frontend
cd ./frontend
npm run dev

# 2. Backend
cd ./backend
python3 main.py
```

> Note: To deactivate virtual environment, just run `deactivate` command in terminal/command prompt. In addition, don't forget to create `tfidf_json` folder inside `backend` folder in order to accommodate documents added via `/add_web_page`.

### License
This project is under [GPL-3.0](./LICENSE) license.