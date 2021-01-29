# Window 10

### 1. [Docker Desktop 설치](https://www.docker.com/get-started)

### 2. Judger build

- 도커를 사용해 빌드 자동화
- libjudger.so 파일 생성

```sh
cd C:\
git clone https://github.com/dungbik/judger-docker.git

cd judger-docker

docker build --tag judger-docker .

docker run -v C:\judger-docker\judger:judger-docker judger-docker
```

### 3. [Demo](https://docs.onlinejudge.me/#/judger/api)

- compile

```sh
docker run --rm -v C:\judger-docker\judger:/judger gcc gcc /judger/demo/main.c -o /judger/demo/main
```

- judger

```sh
docker run --rm -v C:\judger-docker\judger:/judger gcc /judger/libjudger.so --max_cpu_time=1000 --max_real_time=2000 --max_memory=536870912 --max_process_number=200 --max_output_size=16384 --exe_path="/judger/demo/main" --input_path="/judger/demo/1.in" --output_path="/judger/demo/1.out" --error_path="/judger/demo/1.out" --uid=0 --gid=0 --seccomp_rule_name="c_cpp"
```
