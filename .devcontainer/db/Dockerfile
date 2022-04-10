FROM mariadb

# mysql configuration
COPY ./db/my.cnf /etc/mysql/my.cnf

# copy exercises directory to mysql dir
COPY ./db/exercises/by-id /mysql/xfit

# give ownership and full permissions of /mysql dir to mysql
RUN chown mysql:mysql -R /mysql && chmod 777 -R /mysql

# copy sql tables script
COPY ./db/tables.sql /docker-entrypoint-initdb.d/xfit.sql

# copy script that populates db with exercises
COPY ./db/makeExercisesSQL.sh /tmp/xfit/

# copy output of script to docker-entrypoint-initdb.d (container runs sql scripts located there)
RUN apt update \
    # the script needs jq to be installed
    && apt install -y jq \
    # run script
    && /tmp/xfit/makeExercisesSQL.sh /mysql/xfit/ >> /docker-entrypoint-initdb.d/xfit.sql \
    # cleanup
    && apt remove -y jq \
    && rm -rf /tmp/xfit
