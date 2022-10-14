package svyakovlev.coursework2.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import svyakovlev.coursework2.Model.Question;
import svyakovlev.coursework2.Service.ExaminerService;

import java.util.Collection;

@RestController
public class ExamController {

    private final ExaminerService examinerService;

    public ExamController(ExaminerService examinerService) {
        this.examinerService = examinerService;
    }

    @GetMapping("/exam/get/{amount}")
    public Collection<Question> getAmountQuestions(@PathVariable int amount) {
        return examinerService.getQuestions(amount);
    }
}
