import sqlite3

class add_document:

    def __init__(self, db_name):
        self.db_name = db_name
        self.db_conn = sqlite3.connect(db_name)
        self.db_cursor = self.db_conn.cursor()
    
    def add_url(self, url):