package com.learning.rest.service.impl;


import com.learning.exception.lesson.LessonNotFoundException;
import com.learning.exception.subject.SubjectNotFoundException;
import com.learning.exception.user.UserNotFoundException;
import com.learning.rest.domain.dto.lesson.LessonDetailsDto;
import com.learning.rest.domain.dto.lesson.LessonDto;
import com.learning.rest.domain.dto.lesson.LessonFileDto;
import com.learning.rest.domain.entity.Lesson;
import com.learning.rest.domain.entity.Subject;
import com.learning.rest.domain.entity.User;
import com.learning.rest.domain.entity.enums.LessonStatus;
import com.learning.rest.domain.mapper.LessonMapper;
import com.learning.rest.domain.repository.LessonRepository;
import com.learning.rest.domain.repository.SubjectRepository;
import com.learning.rest.domain.repository.UserRepository;
import com.learning.rest.service.LessonService;
import com.learning.rest.service.map.LessonMap;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class LessonServiceImpl implements LessonService {

    private final LessonMapper lessonMapper;
    private final SubjectRepository subjectRepository;
    private final UserRepository userRepository;
    private final LessonRepository lessonRepository;
    private final LessonMap lessonMap;

    @Transactional
    @Override
    public Long addLesson(Long teacherId, LessonDto lessonDto) {
        User teacher = userRepository.findById(teacherId).orElseThrow(UserNotFoundException::new);
        Subject subject = subjectRepository.findById(lessonDto.getSubjectId()).orElseThrow(SubjectNotFoundException::new);
        Lesson lesson = lessonMapper.toLesson(lessonDto);
        List<LessonFileDto> lessonFileDto = lessonDto.getFiles();
        if (lessonFileDto != null) {
            lessonFileDto.stream()
                    .map(lessonMap::mapToLessonFile)
                    .forEach(lesson::addFile);
        }
        List<User> students = subject.getStudents();
        if (students != null) {
            students.forEach(lesson::addStudent);
        }
        lesson.setUrl("https://player.twitch.tv/?channel=" + teacher.getTwitchNick() + "&parent=localhost");
        lesson.setSubject(subject);
        lesson.setTeacher(teacher);
        Lesson savedLesson = lessonRepository.save(lesson);
        return savedLesson.getLessonId();
    }

    @Override
    public LessonDetailsDto geLessonDetails(Long lessonId) {
        Lesson lesson = lessonRepository.findById(lessonId).orElseThrow(LessonNotFoundException::new);
        return lessonMapper.toLessonDetailsDto(lesson);
    }

    @Override
    public List<LessonDetailsDto> getLiveLesson(Long userId) {
        User user = userRepository.findById(userId).orElseThrow(UserNotFoundException::new);
        List<Lesson> lessons = user.getLessons();
        return lessons.stream()
                .filter(lesson -> lesson.getStatus() == LessonStatus.LIVE)
                .map(lessonMapper::toLessonDetailsDto)
                .collect(Collectors.toList());
    }
}
