#!/usr/bin/env bash

# data
EXERCISES_DIR=${1:-./exercises}
EXERCISE_JSON_FILES=`find "$EXERCISES_DIR" -name '*.json' -printf '%p\n' | sort`

# exercise
exerciseInsertList=""

# image
imageInsertList=""
exerciseImageInsertList=""

imageID=0

for exerciseJSON in $EXERCISE_JSON_FILES; do

	# exercise
	eval "`jq -r '@sh "
		exerciseID=\(.id)
		exerciseTitle=\(.title | @json)
		exercisePrimer=\(.primer | @json)
		exerciseType=\(.type | @json)
		exerciseSteps=\(.steps | join("\\\\n") | @json)
		exerciseTips=\(.tips | join("\\\\n") | @json)
		exerciseReferences=\(.references | join("\\\\n") | @json)
		"' $exerciseJSON`"

	exerciseID=`echo $exerciseID | sed "s/^0*//"`
	exerciseInsert=`cat <<-END
		\t(
		\t\t$exerciseID,
		\t\t$exerciseTitle,
		\t\t$exercisePrimer,
		\t\t$exerciseType,
		\t\t$exerciseSteps,
		\t\t$exerciseTips,
		\t\t$exerciseReferences
		\t),\n
	END
	`
	exerciseInsertList="$exerciseInsertList""$exerciseInsert"

	# image
	for image in `dirname $exerciseJSON`/images/*.png; do
		imageID=$((imageID+1))

		imageInsert="\t( LOAD_FILE('$image') ),\n"
		imageInsertList="$imageInsertList""$imageInsert"

		# exercise image
		exerciseImageState=`basename $image | cut -d'.' -f1`
		exerciseImageInsert="\t( $exerciseID, $imageID, '$exerciseImageState' ),\n"
		exerciseImageInsertList="$exerciseImageInsertList""$exerciseImageInsert"
	done
	
done

# remove last comma and/or newline
exerciseInsertList=${exerciseInsertList::-3}
imageInsertList=${imageInsertList::-3}
exerciseImageInsertList=${exerciseImageInsertList::-3}

echo -e "
DROP DATABASE IF EXISTS xfit;

CREATE DATABASE xfit;

USE xfit;

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
		FOREIGN KEY(imageID) REFERENCES Image(ID)
	);

-- INSERT ---------------------------------------------------------- EXERCISE --

INSERT INTO
	Exercise (ID, title, primer, type, steps, tips, links)
VALUES
$exerciseInsertList;

-- INSERT ------------------------------------------------------------- IMAGE --

INSERT INTO
	Image (image)
VALUES
$imageInsertList;

INSERT INTO
	ExerciseImage (exerciseID, imageID, exerciseState)
VALUES
$exerciseImageInsertList;
"
