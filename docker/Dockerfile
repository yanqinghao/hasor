ARG DOCKER_BASE

FROM ${DOCKER_BASE}:3-openjdk-8

WORKDIR /home/hasor

COPY . /home/hasor

RUN sed -i "s@http://deb.debian.org@http://mirrors.aliyun.com@g" /etc/apt/sources.list && \
    rm -rf /var/lib/apt/lists/*  && apt-get clean

RUN apt-get update && \
    curl -sL https://deb.nodesource.com/setup_12.x | bash - && \
    apt-get install -y software-properties-common nodejs && \
    apt-get clean && npm config set registry http://registry.npm.taobao.org

RUN mkdir -p /root/.m2 && cp -f utils/settings.xml /root/.m2/ && \
    mvn clean install && mvn clean && \
    rm -rf hasor-dataql/hasor-dataway/src/console/node_modules

CMD ["bash"]
