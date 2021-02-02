FROM ubuntu:18.04

RUN apt-get update
RUN apt-get install libseccomp-dev -y
RUN apt-get install cmake -y

CMD ["/judger-docker/build.sh" ]