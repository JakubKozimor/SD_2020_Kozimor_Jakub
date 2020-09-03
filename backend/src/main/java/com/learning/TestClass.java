package com.learning;

import com.learning.domain.entity.*;
import com.learning.service.HomeworkService;
import com.learning.service.LessonService;
import com.learning.service.SubjectService;
import com.learning.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Controller;

import java.io.IOException;
import java.sql.Date;
import java.time.LocalDate;

@Controller
@RequiredArgsConstructor
public class TestClass {
    private final UserService userService;
    private final SubjectService subjectService;
    private final HomeworkService homeworkService;
    private final LessonService lessonService;


    @EventListener(ApplicationReadyEvent.class)
    public void test() throws IOException {
        /*
        //create subject files

        SubjectFile subjectFile = new SubjectFile();
        subjectFile.setFileContent("file1");
        subjectFile.setFileName("nameSubject");
        SubjectFile subjectFile1 = new SubjectFile();
        subjectFile1.setFileContent("file1");
        subjectFile1.setFileName("name");

        //create subject
        Subject subject = new Subject();
        subject.setName("Subject");
        subject.setSubjectDate(Date.valueOf(LocalDate.now()));
        subject.addFile(subjectFile);
        subject.addFile(subjectFile1);
        subjectService.addSubject(subject);

        userService.addUserToSubject(1L,1L);

        //create homework file
        HomeworkFile homeworkFile = new HomeworkFile();
        homeworkFile.setFileContent("context");
        homeworkFile.setFileName("file");
        HomeworkFile homeworkFile1 = new HomeworkFile();
        homeworkFile1.setFileContent("context");
        homeworkFile1.setFileName("file");

        // create homework
        Homework homework = new Homework();
        homework.addFile(homeworkFile);
        homework.addFile(homeworkFile1);
        homework.setTitle("title");
        homework.setDescription("desc");
        homework.setDeadline(Date.valueOf(LocalDate.now()));
        homework.setDeadline(Date.valueOf(LocalDate.now()));
        homeworkService.saveHomework(homework);

        subjectService.addHomeworkToSubject(1L,1L);

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
        lessonService.addLesson(3L,lesson);

        userService.addUserToLesson(1L, 1L);
*/

//        //encode file
//        File file = new File("src/main/resource/Git.pdf");
//        String encodedString = new String(Base64.encodeBase64(FileUtils.readFileToByteArray(file)));
//
//      //decode file and write to file
//        File fileResult = new File("src/main/resources/result.pdf");
//        FileOutputStream fos = new FileOutputStream(fileResult);
//        byte[] decoder = Base64.decodeBase64(encodedString.getBytes());
//        fos.write(decoder);
    }
}
