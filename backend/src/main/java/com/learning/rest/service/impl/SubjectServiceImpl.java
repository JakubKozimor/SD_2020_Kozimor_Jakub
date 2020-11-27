package com.learning.rest.service.impl;

import com.learning.constants.CustomConstants;
import com.learning.exception.calendar.DayNotFoundException;
import com.learning.exception.homework.HomeworkNotFoundException;
import com.learning.exception.subject.SubjectNotFoundException;
import com.learning.exception.user.UserNotFoundException;
import com.learning.rest.domain.dto.subject.SubjectDto;
import com.learning.rest.domain.dto.subject.SubjectFileDto;
import com.learning.rest.domain.entity.*;
import com.learning.rest.domain.entity.enums.Week;
import com.learning.rest.domain.mapper.SubjectMapper;
import com.learning.rest.domain.repository.BaseCalendarRepository;
import com.learning.rest.domain.repository.HomeworkRepository;
import com.learning.rest.domain.repository.SubjectRepository;
import com.learning.rest.domain.repository.UserRepository;
import com.learning.rest.pageable.PageHelper;
import com.learning.rest.service.SubjectService;
import com.learning.rest.service.map.SubjectMap;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SubjectServiceImpl implements SubjectService {

    private final SubjectRepository subjectRepository;
    private final HomeworkRepository homeworkRepository;
    private final UserRepository userRepository;
    private final SubjectMapper subjectMapper;
    private final SubjectMap subjectMap;
    private final BaseCalendarRepository baseCalendarRepository;

    @Override
    @Transactional
    public Long addSubject(Long teacherId, SubjectDto subjectDto) {
        User user = userRepository.findById(teacherId).orElseThrow(UserNotFoundException::new);
        Subject subject = subjectMapper.toSubject(subjectDto);
        List<SubjectFileDto> subjectFileDto = subjectDto.getFiles();
        if (subjectFileDto != null) {
            subjectFileDto.stream()
                    .map(subjectMap::mapToSubjectFile)
                    .forEach(subject::addFile);
        }
        subject.setTeacher(user);
        subject.setLongOfTime(Integer.valueOf(subjectDto.getLongOfTime()));
        Subject saveDSubject = subjectRepository.save(subject);
        return saveDSubject.getSubjectId();
    }

    @Override
    public void updateSubject(SubjectDto subjectDto, Long subjectId) {
        Subject oldSubject = subjectRepository.findById(subjectId).orElseThrow(SubjectNotFoundException::new);
        Subject subject = subjectMapper.toSubject(subjectDto);
        List<SubjectFileDto> subjectFileDto = subjectDto.getFiles();
        subject.setFiles(new ArrayList<>());
        if (subjectFileDto != null) {
            subjectFileDto.stream()
                    .map(subjectMap::mapToSubjectFile)
                    .forEach(subject::addFile);
        }
        subject.setSubjectId(subjectId);
        subject.setLongOfTime(Integer.valueOf(subjectDto.getLongOfTime()));
        subject.setHomeworks(oldSubject.getHomeworks());
        subject.setTeacher(oldSubject.getTeacher());
        subject.setStudents(oldSubject.getStudents());
        List<SubjectFile> subjectFiles = subject.getFiles();
        if (subjectFiles != null && !subjectFiles.isEmpty()) {
            subjectFiles.forEach(homeworkAnswerFile -> homeworkAnswerFile.setLessonFileId(null));
        }
        subjectRepository.save(subject);
    }

    @Override
    @Transactional
    public void addHomeworkToSubject(Long subjectId, Long homeworkId) {
        Subject subject = subjectRepository.findById(subjectId).orElseThrow(SubjectNotFoundException::new);
        Homework homework = homeworkRepository.findById(homeworkId).orElseThrow(HomeworkNotFoundException::new);
        subject.addHomework(homework);
        subjectRepository.save(subject);
    }

    @Override
    public Page<Subject> getAllSubjectsByUserId(Long userId, Pageable pageable, Week week) {
        User user = userRepository.findById(userId).orElseThrow(UserNotFoundException::new);
        List<Subject> subjectList = user.getSubjects()
                .stream()
                .filter(subject -> this.filterByWeek(subject, week))
                .sorted(this::sortByTime)
                .collect(Collectors.toList());
        return (Page<Subject>) PageHelper.preparePageFromList(subjectList, pageable);
    }

    @Override
    public Page<Subject> getAllSubjectsForTeacher(Long userId, Pageable pageable, Week week) {
        User user = userRepository.findById(userId).orElseThrow(UserNotFoundException::new);
        List<Subject> allSubjects = subjectRepository.findAllByTeacher(user);
        List<Subject> subjectList = allSubjects.stream()
                .filter(subject -> this.filterByWeek(subject, week))
                .sorted(this::sortByTime)
                .collect(Collectors.toList());
        return (Page<Subject>) PageHelper.preparePageFromList(subjectList, pageable);
    }

    private boolean filterByWeek(Subject subject, Week week) {
        if (week == Week.ALL || subject.getWeek() == Week.ALL || week == Week.FREE)
            return true;
        else
            return subject.getWeek() == week;
    }

    @Override
    public List<Subject> getFirstFiveSubjectsByUserId(Long userId, Week week) {
        Week notFreeWeek;
        if (week == Week.FREE)
            notFreeWeek = this.findNotFreeWeek(userId);
        else
            notFreeWeek = week;
        User user = userRepository.findById(userId).orElseThrow(UserNotFoundException::new);
        List<Subject> allSubjects = user.getSubjects();
        return this.getFivSubjects(allSubjects, notFreeWeek);
    }

    @Override
    public List<Subject> getFirstFiveSubjectsForTeacher(Long userId, Week week) {
        Week notFreeWeek;
        if (week == Week.FREE)
            notFreeWeek = this.findNotFreeWeek(userId);
        else
            notFreeWeek = week;
        User user = userRepository.findById(userId).orElseThrow(UserNotFoundException::new);
        List<Subject> allSubjects = subjectRepository.findAllByTeacher(user);
        return this.getFivSubjects(allSubjects, notFreeWeek);
    }

    @Override
    public Subject getSubjectDetails(Long subjectId) {
        return subjectRepository.findById(subjectId).orElseThrow(SubjectNotFoundException::new);
    }

    private List<Subject> getFivSubjects(List<Subject> allSubjects, Week notFreeWeek) {
        List<Subject> fiveSubjects = allSubjects
                .stream()
                .filter(subject -> this.filterByWeek(subject, notFreeWeek))
                .filter(this::filterByDay)
                .filter(this::filterByTime)
                .limit(CustomConstants.FIVE_SUBJECTS)
                .collect(Collectors.toList());
        if (fiveSubjects.size() < CustomConstants.FIVE_SUBJECTS)
            return this.addFromNextWeek(allSubjects, fiveSubjects, notFreeWeek);
        else
            return fiveSubjects;
    }

    private boolean filterByTime(Subject subject) {
        if (LocalDate.now().getDayOfWeek().getValue() == subject.getDay().ordinal() + 1) {
            int subjectTime = Integer.parseInt(subject.getStartTime().replaceAll(CustomConstants.INTEGER_FROM_HOURS_REGEX, ""));
            int minute = LocalTime.now().getMinute();
            String currentTimeString;
            if (minute < CustomConstants.TEN_MINUTE)
                currentTimeString = LocalTime.now().getHour() + ":0" + LocalTime.now().getMinute();
            else currentTimeString = LocalTime.now().getHour() + ":" + LocalTime.now().getMinute();
            int currentTimeValue = Integer.parseInt(currentTimeString.replaceAll(CustomConstants.INTEGER_FROM_HOURS_REGEX, ""));
            return subjectTime > currentTimeValue;
        } else return true;
    }

    private boolean filterByDay(Subject subject) {
        return LocalDate.now().getDayOfWeek().getValue() <= subject.getDay().ordinal() + 1;
    }

    private int sortByDayAndTime(Subject o1, Subject o2) {
        if (o1.getDay() == o2.getDay())
            return this.sortByTime(o1, o2);
        else
            return o1.getDay().compareTo(o2.getDay());
    }

    private int sortByTime(Subject o1, Subject o2) {
        return Integer.valueOf(o1.getStartTime().replaceAll(CustomConstants.INTEGER_FROM_HOURS_REGEX, "")).compareTo(Integer.valueOf(o2.getStartTime().replaceAll("[^0-9]+", "")));
    }

    private Week findNotFreeWeek(Long userId) {
        User user = userRepository.findById(userId).orElseThrow(UserNotFoundException::new);
        Week week = Week.FREE;
        LocalDate date = LocalDate.now().plusDays(1);
        while (week == Week.FREE) {
            BaseCalendar baseCalendar = baseCalendarRepository.findByDate(Date.valueOf(date)).orElseThrow(DayNotFoundException::new);
            Optional<CalendarSchool> calendarSchoolOptional = baseCalendar.getCalendarSchool().stream()
                    .filter(calendarSchool -> calendarSchool.getSchool().getSchoolId().equals(user.getSchool().getSchoolId()))
                    .findAny();
            if (calendarSchoolOptional.isPresent())
                week = calendarSchoolOptional.get().getWeek();
            else
                week = Week.ALL;
            date = date.plusDays(1);
        }
        return week;
    }

    private List<Subject> addFromNextWeek(List<Subject> allSubjects, List<Subject> fiveSubjects, Week week) {
        Week nextWeek;
        if (week == Week.A)
            nextWeek = Week.B;
        else
            nextWeek = Week.A;
        List<Subject> sortedListAllSubjects = allSubjects.stream()
                .filter(subject -> this.filterByWeek(subject, nextWeek))
                .sorted(this::sortByDayAndTime)
                .collect(Collectors.toList());
        for (Subject tempSubject : sortedListAllSubjects) {
            fiveSubjects.add(tempSubject);
            if (fiveSubjects.size() == CustomConstants.FIVE_SUBJECTS)
                break;
        }
        return fiveSubjects;
    }
}
