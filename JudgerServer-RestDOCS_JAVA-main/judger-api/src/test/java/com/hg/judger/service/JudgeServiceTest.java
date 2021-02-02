package com.hg.judger.service;

import com.hg.judger.vo.ScoringResult;
import com.hg.judger.vo.SubmissionInfo;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class JudgeServiceTest {
    @Autowired
    private JudgeService judgeService;

    @Test
    void run() throws Exception {
        String c_code = "#include<stdio.h>\n" +
                "int main(){\n" +
                "\tint a, b;\n" +
                "\tscanf(\"%d %d\", &a, &b);\n" +
                "\tprintf(\"%d\", a+b);\n" +
                "\treturn 0;\n" +
                "}";
        String cpp_code = "#include<iostream>\n" +
                "int main(){\n" +
                "\tint a, b;\n" +
                "\tscanf(\"%d %d\", &a, &b);\n" +
                "\tprintf(\"%d\", a+b);\n" +
                "\treturn 0;\n" +
                "}";
        String java_code = "import java.util.Scanner;\n" +
                "\n" +
                "class Main {  \n" +
                "  public static void main(String args[]) { \n" +
                "    Scanner scan = new Scanner(System.in);\n" +
                "    System.out.println(scan.nextInt() + scan.nextInt());\n" +
                "  } \n" +
                "}";
        String python_code = "x, y = map(int, raw_input().split(' '))\n" +
                "print x + y";
        SubmissionInfo submissionInfo = new SubmissionInfo(c_code, "c", "1 2", "3");
        ScoringResult scoringResult = judgeService.run(submissionInfo);
        assertThat(scoringResult.getScoringCode()).contains("CORRECT");
    }
}