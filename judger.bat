@echo off
@title judger
docker run --rm -v C:\judger-docker\judger:/judger gcc /judger/libjudger.so --max_cpu_time=1000 --max_real_time=2000 --max_memory=536870912 --max_process_number=200 --max_output_size=16384 --exe_path="/judger/demo/main" --input_path="/judger/demo/1.in" --output_path="/judger/demo/1.out" --error_path="/judger/demo/1.out" --uid=0 --gid=0 --seccomp_rule_name="c_cpp"
pause