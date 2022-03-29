#!/usr/bin/env bash

# data
EXERCISES_DIR=${1:-./exercises}
EXERCISE_JSON_FILES=`find "$EXERCISES_DIR" -name '*.json' -printf '%p\n' | sort`

# exercise
exerciseInsertList=""

# muscle
MUSCLES_JSON=`jq '.primary, .secondary' $EXERCISE_JSON_FILES | jq -s 'add | unique'`
muscleInsertList=`echo $MUSCLES_JSON | jq -r $'.[] | "\t( \'\(.)\' ),"'`
exerciseMuscleInsertList=""

# equipment
EQUIPMENT_JSON=`jq .equipment $EXERCISE_JSON_FILES | jq -s 'add | unique'`
equipmentInsertList=`echo $EQUIPMENT_JSON | jq -r $'.[] | "\t( \'\(.)\' ),"'`
exerciseEquipmentInsertList=""

# image
imageInsertList=""
exerciseImageInsertList=""
imageId=0

# junction table inserts
junctionTableInsert() {
	ARRAY_PROPERTY=$1
	PROPERTY_TABLE_JSON=$2
	INSERT_FORMAT=$3
	insertList=""

	[[ ! -z "$ARRAY_PROPERTY" ]] && while IFS= read -r item; do
		propertyTableId=`echo $PROPERTY_TABLE_JSON | jq 'map(. == "'"$item"'") | index(true)'`
		printf "$INSERT_FORMAT" $((propertyTableId+1))
	done <<< "$ARRAY_PROPERTY"
}

for exerciseJSON in $EXERCISE_JSON_FILES; do

	# exercise
	eval "`jq -r '@sh "
		exerciseId=\(.id)
		exerciseTitle=\(.title | @json)
		exercisePrimer=\(.primer | @json)
		exerciseType=\(.type | @json)
		exerciseEquipment=\(.equipment | join("\n"))
		exercisePrimaryMuscles=\(.primary | join("\n"))
		exerciseSecondaryMuscles=\(.secondary | join("\n"))
		exerciseSteps=\(.steps | join("\\\\n") | @json)
		exerciseTips=\(.tips | join("\\\\n") | @json)
		exerciseReferences=\(.references | join("\\\\n") | @json)
		"' $exerciseJSON`"

	exerciseId=`echo $exerciseId | sed "s/^0*//"`
	exerciseInsert=`cat <<-END
		\t(
		\t\t$exerciseId,
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

	# muscle
	exercisePrimaryMuscleInsert="$(junctionTableInsert "$exercisePrimaryMuscles" "$MUSCLES_JSON" "\t( $exerciseId, %u, 0 ),\\\n")"
	exerciseSecondaryMuscleInsert="$(junctionTableInsert "$exerciseSecondaryMuscles" "$MUSCLES_JSON" "\t( $exerciseId, %u, 1 ),\\\n")"
	exerciseMuscleInsertList="$exerciseMuscleInsertList""$exercisePrimaryMuscleInsert""$exerciseSecondaryMuscleInsert"

	# equipment
	exerciseEquipmentInsert="$(junctionTableInsert "$exerciseEquipment" "$EQUIPMENT_JSON" "\t( $exerciseId, %u ),\\\n" )"
	exerciseEquipmentInsertList="$exerciseEquipmentInsertList""$exerciseEquipmentInsert"

	# image
	for image in `dirname $exerciseJSON`/images/*.png; do
		imageId=$((imageId+1))

		imageInsert="\t( LOAD_FILE('$image') ),\n"
		imageInsertList="$imageInsertList""$imageInsert"

		# exercise image
		exerciseImageState=`basename $image | cut -d'.' -f1`
		exerciseImageInsert="\t( $exerciseId, $imageId, '$exerciseImageState' ),\n"
		exerciseImageInsertList="$exerciseImageInsertList""$exerciseImageInsert"
	done

done

# remove last comma and/or newline
exerciseInsertList=${exerciseInsertList::-3}

muscleInsertList=${muscleInsertList::-1}
exerciseMuscleInsertList=${exerciseMuscleInsertList::-3}

equipmentInsertList=${equipmentInsertList::-1}
exerciseEquipmentInsertList=${exerciseEquipmentInsertList::-3}

imageInsertList=${imageInsertList::-3}
exerciseImageInsertList=${exerciseImageInsertList::-3}

# SQL
echo -e "
-- INSERT ---------------------------------------------------------- EXERCISE --

INSERT INTO
	Exercise (id, title, primer, type, steps, tips, links)
VALUES
$exerciseInsertList;

-- INSERT ------------------------------------------------------------ MUSCLE --

INSERT INTO
	Muscle (name)
VALUES
$muscleInsertList;

INSERT INTO
	ExerciseMuscle (exerciseId, muscleId, isSecondary)
VALUES
$exerciseMuscleInsertList;

-- INSERT --------------------------------------------------------- EQUIPMENT --

INSERT INTO
	Equipment (name)
VALUES
$equipmentInsertList;

INSERT INTO
	ExerciseEquipment (exerciseId, equipmentId)
VALUES
$exerciseEquipmentInsertList;

-- INSERT ------------------------------------------------------------- IMAGE --

INSERT INTO
	Image (image)
VALUES
$imageInsertList;

INSERT INTO
	ExerciseImage (exerciseId, imageId, exerciseState)
VALUES
$exerciseImageInsertList;"
