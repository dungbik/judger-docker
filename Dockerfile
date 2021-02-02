FROM ubuntu:18.04

RUN apt-get update && apt-get install -y cmake python libseccomp-dev gcc g++ git
# strace python3 python3-pip && pip3 install setuptools --upgrade

CMD ["/judger-docker/Scripts/build.sh" ]