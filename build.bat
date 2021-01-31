@echo off
@title build
docker build --tag judger-docker .
docker run -v C:\judger-docker:/judger-docker judger-docker
pause