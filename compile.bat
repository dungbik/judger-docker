@echo off
@title compile
docker run --rm -v C:\judger-docker\judger:/judger gcc gcc /judger/demo/main.c -o /judger/demo/main
pause