FROM tomcat:8.5-jre8

COPY build/libs/*.war /usr/local/tomcat/webapps/
RUN sed -i "s|securerandom.source=file:/dev/random|securerandom.source=file:/dev/urandom |g"  /etc/java-8-openjdk/security/java.security

CMD /usr/local/tomcat/bin/catalina.sh run
