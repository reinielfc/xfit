#!/usr/bin/env bash

EXERCISES_DIR=${1:-./exercises}

imageInsertList=""
exerciseImageInsertList=""

imageID=0

for folder in $EXERCISES_DIR/by-id/*; do
    exerciseID=`basename $folder | sed 's/^0*//'`
    
    for image in $folder/images/*; do
        imageID=$((imageID+1))

        # images
        imageInsert="\t( LOAD_FILE('$image') ),\n"
        imageInsertList="$imageInsertList $imageInsert"
        
        # exercise images
        imageType=`basename $image | cut -d'.' -f1`
        exerciseImageInsert="\t( $exerciseID, $imageID, '$imageType' ),\n"
        exerciseImageInsertList="$exerciseImageInsertList $exerciseImageInsert"
    done

done

# remove last comma and newline
imageInsertList=${imageInsertList::-3}
exerciseImageInsertList=${exerciseImageInsertList::-3}

echo -e "
DROP DATABASE IF EXISTS xfit;

CREATE DATABASE xfit;

USE xfit;

CREATE TABLE
    Image (
        id      INT         NOT NULL AUTO_INCREMENT,
        image   MEDIUMBLOB,

        PRIMARY KEY(id)
    );

CREATE TABLE
    ExerciseImage (
        id          INT         NOT NULL AUTO_INCREMENT,
        exerciseID  INT         NOT NULL,
        imageID     INT         NOT NULL,
        type        VARCHAR(32) NOT NULL,
        
        PRIMARY KEY(id),
        FOREIGN KEY(imageID) REFERENCES Image(id)
    );

INSERT INTO
    Image (image)
VALUES
$imageInsertList;

INSERT INTO
    ExerciseImage (exerciseID, imageID, type)
VALUES
$exerciseImageInsertList;
"
