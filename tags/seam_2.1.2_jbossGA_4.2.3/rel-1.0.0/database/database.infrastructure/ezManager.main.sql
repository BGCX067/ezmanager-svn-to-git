-- Executes all pl/sql scripts for creating the EzManager database

-- Creates the database
@./database.scripts/ezManager.scripts.sql;

-- Creates the sequences
@./database.sequences/ezManager.sequences.sql;

-- Populates the database with default values
@./database.populate/ezManager.populate.sql;

-- Populates the database with demo values
@./database.populate/ezManager.populate.demo.sql;
