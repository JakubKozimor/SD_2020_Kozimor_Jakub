package com.learning.rest.domain.mapper;

import com.learning.rest.domain.dto.subject.SubjectDto;
import com.learning.rest.domain.entity.Subject;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2021-01-25T22:46:19+0100",
    comments = "version: 1.3.1.Final, compiler: javac, environment: Java 11.0.2 (Oracle Corporation)"
)
@Component
public class SubjectMapperImpl implements SubjectMapper {

    @Override
    public Subject toSubject(SubjectDto homeworkDto) {
        if ( homeworkDto == null ) {
            return null;
        }

        Subject subject = new Subject();

        subject.setSubjectId( homeworkDto.getSubjectId() );
        subject.setName( homeworkDto.getName() );
        subject.setDay( homeworkDto.getDay() );
        subject.setWeek( homeworkDto.getWeek() );
        subject.setStartTime( homeworkDto.getStartTime() );

        return subject;
    }
}
