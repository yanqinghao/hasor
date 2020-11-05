#!/bin/bash

set -e

VERSION=$(bash tools/version.sh)
DOCKERBASE="maven"
if [[ $1 == "master" ]]; then
    echo "build from maven:3-openjdk-8"
    echo "build suanpan-hasor-base:latest and ${VERSION}"
    BUILD_VERSIONS=${VERSION}
    BUILD_TAGS="latest"
else
    echo "build from maven:3-openjdk-8"
    echo "build suanpan-hasor-base:preview and preview-${VERSION}"
    BUILD_VERSIONS="preview-${VERSION}"
    BUILD_TAGS="preview"
fi
BUILDNAMES="suanpan-hasor-base"
NAMESPACE="shuzhi-amd64"

docker build --build-arg DOCKER_BASE=${DOCKERBASE} -t \
    registry-vpc.cn-shanghai.aliyuncs.com/${NAMESPACE}/${BUILDNAMES}:${BUILD_VERSIONS} \
    -f docker/Dockerfile .
docker push registry-vpc.cn-shanghai.aliyuncs.com/${NAMESPACE}/${BUILDNAMES}:${BUILD_VERSIONS}

docker tag registry-vpc.cn-shanghai.aliyuncs.com/${NAMESPACE}/${BUILDNAMES}:${BUILD_VERSIONS} \
    registry-vpc.cn-shanghai.aliyuncs.com/${NAMESPACE}/${BUILDNAMES}:${BUILD_TAGS}
docker push registry-vpc.cn-shanghai.aliyuncs.com/${NAMESPACE}/${BUILDNAMES}:${BUILD_TAGS}
