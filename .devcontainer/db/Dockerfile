FROM mariadb

RUN mkdir /mysql && chown mysql:mysql -R /mysql && chmod 777 -R /mysql
COPY ./db/my.cnf /etc/mysql/my.cnf
COPY ./db/exercises /mysql/xfit/exercises

COPY ./db/makeExercisesSQL.sh /tmp/xfit/
RUN apt update \
    && apt install jq\
    && /tmp/xfit/makeExercisesSQL.sh /mysql/xfit/exercises > /docker-entrypoint-initdb.d/exercises.sql
