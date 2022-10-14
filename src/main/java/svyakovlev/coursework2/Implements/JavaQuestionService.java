package svyakovlev.coursework2.Implements;

import org.springframework.stereotype.Service;
import svyakovlev.coursework2.Exception.QuestionAlreadyExistException;
import svyakovlev.coursework2.Exception.QuestionNotFoundException;
import svyakovlev.coursework2.Model.Question;
import svyakovlev.coursework2.Service.QuestionService;

import java.util.*;

@Service
public class JavaQuestionService implements QuestionService {

    private final Set<Question> questions;

    private final Random random;

    public JavaQuestionService() {
        this.questions = new HashSet<>();
        this.random = new Random();
    }

    @Override
    public Question add(String question, String answer) {
        return add(new Question(question, answer));
    }

    @Override
    public Question add(Question question) {
        if (questions.contains(question)) {
            throw new QuestionAlreadyExistException();
        }
        questions.add(question);
        return question;
    }

    @Override
    public Question remove(Question question) {
        if (!questions.contains(question)) {
            throw new QuestionNotFoundException();
        }
        questions.remove(question);
        return question;
    }

    @Override
    public Collection<Question> getAll() {
        return new HashSet<>(questions);
    }

    @Override
    public Question getRandomQuestion() {
        return new ArrayList<>(questions).get(random.nextInt(questions.size()));
    }

}
