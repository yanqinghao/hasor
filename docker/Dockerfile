ARG DOCKER_BASE

FROM ${DOCKER_BASE}:3-openjdk-8

WORKDIR /home/hasor

COPY . /home/hasor

RUN mkdir -p /root/.m2 && cp -f utils/settings.xml /root/.m2/ && \
    cd hasor-dataql/hasor-dataway/ && mvn package

ENTRYPOINT ["/usr/local/bin/mvn-entrypoint.sh"]

CMD ["mvn"]
