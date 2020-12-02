package com.learning.rest.domain.mapper;

import com.learning.rest.domain.dto.lesson.LessonDetailsDto;
import com.learning.rest.domain.dto.lesson.LessonDto;
import com.learning.rest.domain.entity.Lesson;
import com.learning.rest.domain.entity.LessonFile;
import com.learning.rest.domain.entity.Subject;
import com.learning.rest.domain.entity.enums.LessonStatus;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2020-12-02T19:33:22+0100",
    comments = "version: 1.3.1.Final, compiler: javac, environment: Java 11.0.2 (Oracle Corporation)"
)
@Component
public class LessonMapperImpl implements LessonMapper {

    @Override
    public Lesson toLesson(LessonDto lessonDto) {
        if ( lessonDto == null ) {
            return null;
        }

        Lesson lesson = new Lesson();

        lesson.setLessonId( lessonDto.getLessonId() );

        lesson.setStatus( LessonStatus.LIVE );

        return lesson;
    }

    @Override
    public Lesson toEditLesson(LessonDto lessonDto) {
        if ( lessonDto == null ) {
            return null;
        }

        Lesson lesson = new Lesson();

        lesson.setLessonId( lessonDto.getLessonId() );
        lesson.setUrl( lessonDto.getUrl() );
        if ( lessonDto.getStatus() != null ) {
            lesson.setStatus( Enum.valueOf( LessonStatus.class, lessonDto.getStatus() ) );
        }

        return lesson;
    }

    @Override
    public LessonDetailsDto toLessonDetailsDto(Lesson lesson) {
        if ( lesson == null ) {
            return null;
        }

        LessonDetailsDto lessonDetailsDto = new LessonDetailsDto();

        lessonDetailsDto.setStartTime( lessonSubjectStartTime( lesson ) );
        lessonDetailsDto.setSubjectId( lessonSubjectSubjectId( lesson ) );
        lessonDetailsDto.setSubjectName( lessonSubjectName( lesson ) );
        lessonDetailsDto.setLessonId( lesson.getLessonId() );
        lessonDetailsDto.setUrl( lesson.getUrl() );
        List<LessonFile> list = lesson.getFiles();
        if ( list != null ) {
            lessonDetailsDto.setFiles( new ArrayList<LessonFile>( list ) );
        }
        if ( lesson.getStatus() != null ) {
            lessonDetailsDto.setStatus( lesson.getStatus().name() );
        }

        return lessonDetailsDto;
    }

    private String lessonSubjectStartTime(Lesson lesson) {
        if ( lesson == null ) {
            return null;
        }
        Subject subject = lesson.getSubject();
        if ( subject == null ) {
            return null;
        }
        String startTime = subject.getStartTime();
        if ( startTime == null ) {
            return null;
        }
        return startTime;
    }

    private Long lessonSubjectSubjectId(Lesson lesson) {
        if ( lesson == null ) {
            return null;
        }
        Subject subject = lesson.getSubject();
        if ( subject == null ) {
            return null;
        }
        Long subjectId = subject.getSubjectId();
        if ( subjectId == null ) {
            return null;
        }
        return subjectId;
    }

    private String lessonSubjectName(Lesson lesson) {
        if ( lesson == null ) {
            return null;
        }
        Subject subject = lesson.getSubject();
        if ( subject == null ) {
            return null;
        }
        String name = subject.getName();
        if ( name == null ) {
            return null;
        }
        return name;
    }
}
