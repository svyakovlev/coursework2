package svyakovlev.coursework2.Service;

import svyakovlev.coursework2.Model.Question;

import java.util.Collection;

public interface ExaminerService {
    Collection<Question> getQuestions(int amount);
}
