package svyakovlev.coursework2.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import svyakovlev.coursework2.Model.Question;
import svyakovlev.coursework2.Service.QuestionService;

import java.util.Collection;

@RestController
public class JavaQuestionController {

    private final QuestionService questionService;

    public JavaQuestionController(QuestionService questionService) {
        this.questionService = questionService;
    }

    @GetMapping("/exam/java/add")
    public Question addQuestion(@RequestParam String question, @RequestParam String answer) {
        return questionService.add(question, answer);
    }

    @GetMapping("/exam/java/remove")
    public Question removeQuestion(@RequestParam String question, @RequestParam String answer) {
        return questionService.remove(new Question(question, answer));
    }

    @GetMapping("/exam/java")
    public Collection<Question> getQuestions() {
        return questionService.getAll();
    }
}
