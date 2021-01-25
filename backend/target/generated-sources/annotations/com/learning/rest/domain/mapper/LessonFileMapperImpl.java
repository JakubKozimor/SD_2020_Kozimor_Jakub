package com.learning.rest.domain.mapper;

import com.learning.rest.domain.dto.lesson.LessonFileDto;
import com.learning.rest.domain.entity.LessonFile;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2021-01-25T22:46:19+0100",
    comments = "version: 1.3.1.Final, compiler: javac, environment: Java 11.0.2 (Oracle Corporation)"
)
@Component
public class LessonFileMapperImpl implements LessonFileMapper {

    @Override
    public LessonFile toLessonFile(LessonFileDto lessonFileDto) {
        if ( lessonFileDto == null ) {
            return null;
        }

        LessonFile lessonFile = new LessonFile();

        lessonFile.setFileName( lessonFileDto.getFileName() );

        return lessonFile;
    }
}
