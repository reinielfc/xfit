FROM tomcat:8-jdk11

RUN apt update \
    && apt install -y xmlstarlet

# enable servlet manager app
RUN mv /usr/local/tomcat/webapps.dist/manager /usr/local/tomcat/webapps/

# allow remote connections for manager app
RUN xmlstarlet ed -L \
    -u "/Context/Valve/@allow" -v ".*" \
    /usr/local/tomcat/webapps/manager/META-INF/context.xml

# add manager role
RUN xmlstarlet ed -L \
    -s '/_:tomcat-users' -t elem -n 'roleTMP' -v '' \
    -i '//roleTMP' -t attr -n 'rolename' -v 'manager-script' \
    -r '//roleTMP' -v 'role' \
    /usr/local/tomcat/conf/tomcat-users.xml

# add manager user
RUN xmlstarlet ed -L \
    -s '_:tomcat-users' -t elem -n 'userTMP' -v '' \
    -i '//userTMP' -t attr -n 'username' -v 'manager' \
    -i '//userTMP' -t attr -n 'password' -v 's3cret' \
    -i '//userTMP' -t attr -n 'roles' -v 'standard,manager-script' \
    -r '//userTMP' -v 'user' \
    /usr/local/tomcat/conf/tomcat-users.xml