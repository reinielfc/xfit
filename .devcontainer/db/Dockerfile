FROM mariadb

# mysql configuration
COPY ./db/my.cnf /etc/mysql/my.cnf

# copy exercises directory to mysql dir
COPY ./db/exercises/ /mysql/xfit/exercises

# give ownership and full permissions of /mysql dir to mysql
RUN chown mysql:mysql -R /mysql && chmod 770 -R /mysql

# copy script that makes sql script
COPY ./db/makeExercisesSQL.sh /tmp/xfit/

# copy output of script to docker-entrypoint-initdb.d (mysql runs scripts located there)
RUN apt update \
    # the script needs jq to be installed
    && apt install -y jq \
    # run script
    && /tmp/xfit/makeExercisesSQL.sh /mysql/xfit/exercises > /docker-entrypoint-initdb.d/exercises.sql
