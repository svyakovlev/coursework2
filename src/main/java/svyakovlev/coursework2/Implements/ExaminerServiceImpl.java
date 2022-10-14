package svyakovlev.coursework2.Implements;

import org.springframework.stereotype.Service;
import svyakovlev.coursework2.Exception.BadAmountException;
import svyakovlev.coursework2.Model.Question;
import svyakovlev.coursework2.Service.ExaminerService;
import svyakovlev.coursework2.Service.QuestionService;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@Service
public class ExaminerServiceImpl implements ExaminerService {

    private final QuestionService questionService;

    public ExaminerServiceImpl(QuestionService questionService) {
        this.questionService = questionService;
    }

    @Override
    public Collection<Question> getQuestions(int amount) {
        if (amount <= 0 || amount > questionService.getAll().size()) {
            throw new BadAmountException();
        }
        Set<Question> question = new HashSet<>(amount);
        while (question.size() < amount) {
            question.add(questionService.getRandomQuestion());
        }
        return question;
    }
}
