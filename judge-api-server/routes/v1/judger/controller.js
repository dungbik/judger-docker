const fs = require("fs");
const execSync = require("child_process").execSync;

exports.createPost = createPost;

async function createPost(req, res, next) {
  try {
    const { input, sourceCode } = req.body;

    fs.writeFileSync("C:/judger-docker/Judger/demo/input.txt", input, "utf8");
    fs.writeFileSync("C:/judger-docker/Judger/demo/test.c", sourceCode, "utf8");

    const compile =
      "docker run --rm -v C:/judger-docker:/judger-docker judger-docker gcc /judger-docker/Judger/demo/test.c -o /judger-docker/Judger/demo/test";
    execSync(compile).toString();

    const judger =
      'docker run --rm -v C:/judger-docker:/judger-docker judger-docker /judger-docker/libjudger.so --max_cpu_time=1000 --max_real_time=2000 --max_memory=536870912 --max_process_number=200 --max_output_size=16384 --exe_path="/judger-docker/Judger/demo/test" --input_path="/judger-docker/Judger/demo/input.txt" --output_path="/judger-docker/Judger/demo/output.txt" --error_path="/judger-docker/Judger/demo/error.txt" --uid=0 --gid=0 --seccomp_rule_name="c_cpp"';
    const stdout = execSync(judger).toString();
    const status = JSON.parse(stdout);
    if (status.result == 0) {
      const output = fs.readFileSync(
        "C:/judger-docker/Judger/demo/output.txt",
        {
          encoding: "utf8",
          flag: "r",
        }
      );
      return res.json({ status, output });
    }
    return res.json({ status, output: "fail" });
  } catch (e) {
    next(e);
  }
}
