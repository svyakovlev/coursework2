package svyakovlev.coursework2.Service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import svyakovlev.coursework2.Exception.BadAmountException;
import svyakovlev.coursework2.Implements.ExaminerServiceImpl;
import svyakovlev.coursework2.Implements.JavaQuestionService;
import svyakovlev.coursework2.Model.Question;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.mockito.Mockito.lenient;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ExaminerServiceImplTest {

    private static final int SIZE = 5;

    @Mock
    private JavaQuestionService javaQuestionService;

    @InjectMocks
    private ExaminerServiceImpl examinerService;

    private List<Question> questions;

    @BeforeEach
    public void beforeEach() {

        questions = Stream.iterate(1, i -> i + 1)
                .limit(SIZE)
                .map(i -> new Question("question" + i, "answer" + i))
                .collect(Collectors.toList());

        lenient().when(javaQuestionService.getAll())
                .thenReturn(questions);
    }

    @ParameterizedTest
    @MethodSource("wrongAmount")
    public void shouldReturnExceptionWhenAmountIsWrong(int wrongAmount) {
        assertThatExceptionOfType(BadAmountException.class)
                .isThrownBy(() -> examinerService.getQuestions(wrongAmount));
    }

    @Test
    public void shouldReturnRandomQuestion() {
        when(javaQuestionService.getRandomQuestion())
                .thenReturn(
                        questions.get(4),
                        questions.get(3),
                        questions.get(0),
                        questions.get(0),
                        questions.get(2)
                );

        assertThat(examinerService.getQuestions(4))
                .containsExactly(
                        questions.get(4),
                        questions.get(3),
                        questions.get(0),
                        questions.get(2)
                );
    }

    public static Stream<Arguments> wrongAmount() {
        return Stream.of(
                Arguments.of(-1),
                Arguments.of(SIZE + 100)
        );
    }
}
