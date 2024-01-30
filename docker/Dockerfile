FROM openjdk:17.0.2-slim
ARG targetname=^project.build.finalName^.jar
ENV TARGETNAME=$targetname
COPY $targetname $targetname

EXPOSE 8101
ENTRYPOINT java -Duser.timezone=GMT -Djava.security.egd=file:/dev/./urandom -jar /${TARGETNAME}
