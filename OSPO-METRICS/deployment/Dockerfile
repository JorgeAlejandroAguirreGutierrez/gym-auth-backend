FROM #{artifactory-path}#/#{alpine-version}#
LABEL version="1.0.0" maintainer="jucahena@bancolombia.com.co" description="Open Source Project Metrics"
USER root
RUN apk --no-cache add curl
USER #{java_user}#
EXPOSE 80
VOLUME /tmp
COPY --chown=#{java_user}#:#{java_user}# *.jar OSPOMetrics.jar
#RUN keytool -import -noprompt -trustcacerts -file /etc/ssl/certs/cert.cer -alias bancolombiacert -keystore $JAVA_HOME/lib/security/cacerts -storepass #{changeit}#
ENV JAVA_OPTS="-Djava.security.egd=file:/dev/./urandom"
ENTRYPOINT ["sh","-c","java $JAVA_OPTS -jar OSPOMetrics.jar"]
