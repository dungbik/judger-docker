# Window 10

### 1. [Docker Desktop 설치](https://www.docker.com/get-started)

### 2. Judger build

- 도커를 사용해 빌드 자동화
- libjudger.so 파일 생성

```sh
cd C:\
git clone https://github.com/dungbik/judger-docker.git

build.bat 실행
```

### 3. [Demo](https://docs.onlinejudge.me/#/judger/api)

- compile (c언어)

```sh
docker run --rm -v C:\judger-docker:/judger-docker judger-docker gcc /judger-docker/Judger/demo/main.c -o /judger-docker/Judger/demo/main
```

- judger (c언어)

```sh
docker run --rm -v C:\judger-docker:/judger-docker judger-docker /judger-docker/libjudger.so --max_cpu_time=1000 --max_real_time=2000 --max_memory=536870912 --max_process_number=200 --max_output_size=16384 --exe_path="/judger-docker/Judger/demo/main" --input_path="/judger-docker/Judger/demo/1.in" --output_path="/judger-docker/Judger/demo/1.out" --error_path="/judger-docker/Judger/demo/1.out" --uid=0 --gid=0 --seccomp_rule_name="c_cpp"
```

### 4. Node Example

![](/Images/JudgerApiTest.png)

### 5. Todo

- [x] judger-docker 이미지를 실행환경으로 사용
- [x] Judger 소스코드 git clone을 이용해 다운
- [ ] judger-docker 이미지에 자바 실행환경 구축
