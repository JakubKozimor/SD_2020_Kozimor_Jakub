package com.learning;

import com.learning.domain.dto.TeacherDto;
import com.learning.domain.entity.*;
import com.learning.domain.entity.enums.Day;
import com.learning.domain.entity.enums.HomeworkStatus;
import com.learning.domain.entity.enums.MessageStatus;
import com.learning.domain.entity.enums.Week;
import com.learning.domain.mapper.TeacherMapper;
import com.learning.service.*;
import lombok.RequiredArgsConstructor;
import org.apache.commons.io.FileUtils;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Date;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.LocalDateTime;


@Controller
@RequiredArgsConstructor
public class TestClass {
    private final UserService userService;
    private final SubjectService subjectService;
    private final HomeworkService homeworkService;
    private final LessonService lessonService;
    private final TeacherMapper teacherMapper;
    private final MessageService messageService;


    @EventListener(ApplicationReadyEvent.class)
    public void test() throws IOException, ParseException {
/*
        //create subject files

        SubjectFile subjectFile = new SubjectFile();
        subjectFile.setFileContent("file1");
        subjectFile.setFileName("nameSubject");
        SubjectFile subjectFile1 = new SubjectFile();
        subjectFile1.setFileContent("file1");
        subjectFile1.setFileName("name");

        //create subject
        LocalDateTime dateTime = LocalDateTime.now();
        Subject subject = new Subject();
        subject.setName("Programowanie");
        subject.setDay(Day.MONDAY);
        subject.setWeek(Week.A);
        //todo should be if with 0
        subject.setStartTime(dateTime.getHour() + ":" + dateTime.getMinute());
        subject.setLongOfTime(90);

        subjectService.addSubject(subject);

        userService.addUserToSubject(1L, 1L);
        userService.addUserToSubject(3L, 1L);

        //create homework file
        HomeworkFile homeworkFile = new HomeworkFile();
        homeworkFile.setFileContent("context");
        homeworkFile.setFileName("file");
        HomeworkFile homeworkFile1 = new HomeworkFile();
        homeworkFile1.setFileContent("context");
        homeworkFile1.setFileName("file");

        // create homework
        Homework homework = new Homework();
        homework.setTitle("Formatka w html");
        homework.setDescription("Zrobić najprostszą formatę w html");
        homework.setDeadline(Date.valueOf(LocalDate.now()));
        homework.setStatus(HomeworkStatus.LATE);

        homeworkService.saveHomework(homework);

        subjectService.addHomeworkToSubject(1L, 1L);

        //create lesson file
        LessonFile file = new LessonFile();
        file.setFileContent("file1");
        file.setFileName("name");
        LessonFile file1 = new LessonFile();
        file1.setFileContent("file1");
        file1.setFileName("name");

        //create lesson
        Lesson lesson = new Lesson();
        lesson.setName("Programowanie");
        lesson.addFile(file);
        lesson.addFile(file1);
        lesson.setLessonDate(Date.valueOf(LocalDate.now()));
        lessonService.addLesson(3L, lesson);

        userService.addUserToLesson(1L, 1L);
*/
    }
}
