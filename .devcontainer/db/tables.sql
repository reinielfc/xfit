DROP DATABASE IF EXISTS xfit;

CREATE DATABASE xfit;

USE xfit;

-- USER ---------------------------------------------------------------- USER --

CREATE TABLE
	User (
		ID			INT			NOT NULL AUTO_INCREMENT,
		name		VARCHAR(32)	NOT NULL,
		email		VARCHAR(64)	NOT NULL UNIQUE,
		password	CHAR(255)	NOT NULL,
		
		PRIMARY KEY(ID)
);

-- EXERCISE -------------------------------------------------------- EXERCISE --

CREATE TABLE
	Exercise (
		ID			INT				NOT NULL AUTO_INCREMENT,
		title		VARCHAR(256)	NOT NULL,
		primer		VARCHAR(512),
		type		VARCHAR(32),
		steps		TEXT,
		tips		TEXT,
		links		TEXT,

		PRIMARY KEY(ID)
	);

CREATE TABLE
	FavoriteExercise (
		ID			INT	NOT NULL AUTO_INCREMENT,
		userID		INT	NOT NULL,
		exerciseID	INT	NOT NULL,

		PRIMARY KEY(ID),
		FOREIGN KEY(userID)		REFERENCES User(ID),
		FOREIGN KEY(exerciseID)	REFERENCES Exercise(ID)
	);

-- MUSCLE ------------------------------------------------------------ MUSCLE --

CREATE TABLE
	Muscle (
		ID		INT			NOT NULL AUTO_INCREMENT,
		name	VARCHAR(32)	NOT NULL,

		PRIMARY KEY(ID)
	);

CREATE TABLE
	ExerciseMuscle (
		ID					INT	NOT NULL AUTO_INCREMENT,
		exerciseID			INT	NOT NULL,
		muscleID			INT	NOT NULL,
		isSecondary			INT	NOT NULL DEFAULT 0,

		PRIMARY KEY(ID),
		FOREIGN KEY(exerciseID)	REFERENCES Exercise(ID),
		FOREIGN KEY(muscleID)	REFERENCES Muscle(ID)
	);

-- EQUIPMENT ------------------------------------------------------ EQUIPMENT --

CREATE TABLE
	Equipment (
		ID		INT			NOT NULL AUTO_INCREMENT,
		name	VARCHAR(32)	NOT NULL,

		PRIMARY KEY(ID)
	);

CREATE TABLE
	ExerciseEquipment (
		ID			INT	NOT NULL AUTO_INCREMENT,
		exerciseID	INT	NOT NULL,
		equipmentID	INT	NOT NULL,

		PRIMARY KEY(ID),
		FOREIGN KEY(exerciseID)		REFERENCES Exercise(ID),
		FOREIGN KEY(equipmentID)	REFERENCES Equipment(ID)
	);

CREATE TABLE
	UserEquipment (
		ID			INT	NOT NULL AUTO_INCREMENT,
		userID		INT	NOT NULL,
		equipmentID	INT	NOT NULL,

		PRIMARY KEY(ID),
		FOREIGN KEY(userID)			REFERENCES User(ID),
		FOREIGN KEY(equipmentID)	REFERENCES Equipment(ID)
	);

-- IMAGE -------------------------------------------------------------- IMAGE --

CREATE TABLE
	Image (
		ID		INT			NOT NULL AUTO_INCREMENT,
		image	MEDIUMBLOB,

		PRIMARY KEY(ID)
	);

CREATE TABLE
	ExerciseImage (
		ID				INT			NOT NULL AUTO_INCREMENT,
		exerciseID		INT			NOT NULL,
		imageID			INT			NOT NULL,
		exerciseState	VARCHAR(32)	NOT NULL,
		
		PRIMARY KEY(ID),
		FOREIGN KEY(exerciseID)	REFERENCES Exercise(ID),
		FOREIGN KEY(imageID) 	REFERENCES Image(ID)
	);

-- PLAN ---------------------------------------------------------------- PLAN --

CREATE TABLE
	Plan (
		ID			INT	NOT NULL AUTO_INCREMENT,
		userID		INT	NOT NULL,
		exerciseID	INT	NOT NULL,
		dayOfWeek	INT	NOT	NULL,
		sets		SMALLINT,
		reps		SMALLINT,
		weight		SMALLINT,
		duration	TIME,

		PRIMARY KEY(ID),
		FOREIGN KEY(userID)		REFERENCES User(ID),
		FOREIGN KEY(exerciseID)	REFERENCES Exercise(ID)
	);
