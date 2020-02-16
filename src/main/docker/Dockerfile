FROM azul/zulu-openjdk:8u242
WORKDIR /opt/
COPY target/scala-2.12/solution.jar .
ENTRYPOINT  ["java", "-jar", "solution.jar", "data"]