FROM ubuntu:18.04

WORKDIR /judger-docker

RUN apt-get update
RUN apt-get install libseccomp-dev -y
RUN apt-get install cmake -y

COPY ./Judger /judger-docker/Judger

RUN cd /judger-docker/Judger && mkdir build && cd build && cmake .. && make && make install

CMD cp /usr/lib/judger/libjudger.so /judger-docker/libjudger.so