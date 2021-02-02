#! /bin/bash
set -ex

if [ ! -f /judger-docker/libjudger.so ]; then
    cd /judger-docker/Judger
    rm -rf build && mkdir build && cd build && cmake ..
    make
    make install
    cp /usr/lib/judger/libjudger.so /judger-docker/libjudger.so
fi