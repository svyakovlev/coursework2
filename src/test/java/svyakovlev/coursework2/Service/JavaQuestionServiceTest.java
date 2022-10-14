package svyakovlev.coursework2.Service;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import svyakovlev.coursework2.Exception.QuestionAlreadyExistException;
import svyakovlev.coursework2.Exception.QuestionNotFoundException;
import svyakovlev.coursework2.Implements.JavaQuestionService;
import svyakovlev.coursework2.Model.Question;

import static org.assertj.core.api.Assertions.*;

public class JavaQuestionServiceTest {
    private final QuestionService questionService = new JavaQuestionService();

    @AfterEach
    public void afterEach() {
        questionService.getAll().forEach(questionService::remove);
    }

    @Test
    public void addTest() {
        Question question1 = new Question("question1", "answer1");
        Question question2 = new Question("question2", "answer2");

        questionService.add(question1);
        assertThat(questionService.getAll())
                .contains(question1)
                .hasSize(1);

        questionService.add(question2);
        assertThat(questionService.getAll())
                .contains(question1, question2)
                .hasSize(2);
    }

    @Test
    public void shouldReturnExceptionWhenQuestionAlreadyExist() {
        Question question1 = new Question("question1", "answer1");
        questionService.add(question1);

        assertThatExceptionOfType(QuestionAlreadyExistException.class)
                .isThrownBy(() -> questionService.add(question1));
    }

    @Test
    public void removeTest() {
        Question question1 = new Question("question1", "answer1");

        assertThatExceptionOfType(QuestionNotFoundException.class)
                .isThrownBy(() -> questionService.remove(question1));

        questionService.add(question1);
        assertThat(questionService.getAll())
                .contains(question1)
                .hasSize(1);

        questionService.remove(question1);
        assertThat(questionService.getAll()).isEmpty();
    }

    @Test
    public void getRandomQuestionTest() {
        Question question1 = new Question("question1", "answer1");
        Question question2 = new Question("question2", "answer2");
        Question question3 = new Question("question3", "answer3");
        Question question4 = new Question("question4", "answer4");
        Question question5 = new Question("question5", "answer5");

        questionService.add(question1);
        questionService.add(question2);
        questionService.add(question3);
        questionService.add(question4);
        questionService.add(question5);

        assertThat(questionService.getAll())
                .hasSize(5)
                .contains(questionService.getRandomQuestion());
    }
}
