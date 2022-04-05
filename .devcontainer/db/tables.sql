DROP DATABASE IF EXISTS xfit;

CREATE DATABASE xfit;

USE xfit;

-- USER ---------------------------------------------------------------- USER --

CREATE TABLE
	User (
		id			INT UNSIGNED	NOT NULL AUTO_INCREMENT,
		name		VARCHAR(32)		NOT NULL,
		email		VARCHAR(64)		NOT NULL UNIQUE,
		password	CHAR(255)		NOT NULL,
		
		PRIMARY KEY(id)
	) ENGINE=InnoDB;

-- EXERCISE -------------------------------------------------------- EXERCISE --

CREATE TABLE
	Exercise (
		id			INT UNSIGNED	NOT NULL AUTO_INCREMENT,
		userId		INT UNSIGNED	DEFAULT NULL,
		name		VARCHAR(256)	UNIQUE NOT NULL,
		title		VARCHAR(256)	NOT NULL,
		primer		VARCHAR(512),
		type		VARCHAR(32),
		steps		TEXT,
		tips		TEXT,
		links		TEXT,

		PRIMARY KEY(id), INDEX(name),
		FOREIGN KEY(userId) REFERENCES User(id)
	) ENGINE=InnoDB;

CREATE TABLE
	FavoriteExercise (
		userId		INT UNSIGNED	NOT NULL,
		exerciseId	INT UNSIGNED	NOT NULL,

		PRIMARY KEY(userId, exerciseId), INDEX(exerciseId, userId),
		FOREIGN KEY(userId)		REFERENCES User(id)		ON DELETE CASCADE,
		FOREIGN KEY(exerciseId)	REFERENCES Exercise(id) ON DELETE CASCADE
	) ENGINE=InnoDB;

-- MUSCLE ------------------------------------------------------------ MUSCLE --

CREATE TABLE
	Muscle (
		id		INT UNSIGNED	NOT NULL AUTO_INCREMENT,
		name	VARCHAR(32)		NOT NULL,

		PRIMARY KEY(id)
	) ENGINE=InnoDB;

CREATE TABLE
	ExerciseMuscle (
		exerciseId			INT UNSIGNED	NOT NULL,
		muscleId			INT UNSIGNED	NOT NULL,
		isSecondary			INT UNSIGNED	NOT NULL DEFAULT 0,

		PRIMARY KEY(exerciseId, muscleId), INDEX(muscleId, exerciseId),
		FOREIGN KEY(exerciseId)	REFERENCES Exercise(id)	ON DELETE CASCADE,
		FOREIGN KEY(muscleId)	REFERENCES Muscle(id)	ON DELETE CASCADE
	) ENGINE=InnoDB;

-- EQUIPMENT ------------------------------------------------------ EQUIPMENT --

CREATE TABLE
	Equipment (
		id		INT UNSIGNED			NOT NULL AUTO_INCREMENT,
		name	VARCHAR(32)	NOT NULL,

		PRIMARY KEY(id)
	) ENGINE=InnoDB;

CREATE TABLE
	ExerciseEquipment (
		exerciseId	INT UNSIGNED	NOT NULL,
		equipmentId	INT UNSIGNED	NOT NULL,

		PRIMARY KEY(exerciseId, equipmentId), INDEX(equipmentId, exerciseId),
		FOREIGN KEY(exerciseId)		REFERENCES Exercise(id)		ON DELETE CASCADE,
		FOREIGN KEY(equipmentId)	REFERENCES Equipment(id)	ON DELETE CASCADE
	) ENGINE=InnoDB;

CREATE TABLE
	UserEquipment (
		userId		INT UNSIGNED	NOT NULL,
		equipmentId	INT UNSIGNED	NOT NULL,

		PRIMARY KEY(userId, equipmentId), INDEX(equipmentId, userId),
		FOREIGN KEY(userId)			REFERENCES User(id)			ON DELETE CASCADE,
		FOREIGN KEY(equipmentId)	REFERENCES Equipment(id)	ON DELETE CASCADE
	) ENGINE=InnoDB;

-- IMAGE -------------------------------------------------------------- IMAGE --

CREATE TABLE
	Image (
		id		INT UNSIGNED			NOT NULL AUTO_INCREMENT,
		image	MEDIUMBLOB,

		PRIMARY KEY(id)
	) ENGINE=InnoDB;

CREATE TABLE
	ExerciseImage (
		exerciseId		INT UNSIGNED	NOT NULL,
		imageId			INT UNSIGNED	NOT NULL,
		exerciseState	VARCHAR(32)		NOT NULL,
		
		PRIMARY KEY(exerciseId, imageId), INDEX(imageId, exerciseId),
		FOREIGN KEY(exerciseId)	REFERENCES Exercise(id)	ON DELETE CASCADE,
		FOREIGN KEY(imageId) 	REFERENCES Image(id)	ON DELETE CASCADE
	) ENGINE=InnoDB;

-- PLAN ---------------------------------------------------------------- PLAN --

CREATE TABLE
	Plan (
		userId		INT UNSIGNED		NOT NULL,
		exerciseId	INT UNSIGNED		NOT NULL,
		dayOfWeek	TINYINT UNSIGNED	NOT	NULL,
		position	INT UNSIGNED		NOT NULL,
		sets		SMALLINT UNSIGNED,
		reps		SMALLINT UNSIGNED,
		weight		SMALLINT UNSIGNED,
		isDone		TINYINT UNSIGNED DEFAULT 0,

		PRIMARY KEY(userId, exerciseId), INDEX(exerciseId, userId),
		FOREIGN KEY(userId)		REFERENCES User(id)		ON DELETE CASCADE,
		FOREIGN KEY(exerciseId)	REFERENCES Exercise(id)	ON DELETE CASCADE
	) ENGINE=InnoDB;
