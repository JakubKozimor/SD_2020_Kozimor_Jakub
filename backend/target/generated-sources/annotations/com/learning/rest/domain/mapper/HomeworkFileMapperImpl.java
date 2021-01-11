package com.learning.rest.domain.mapper;

import com.learning.rest.domain.dto.homework.HomeworkFileDto;
import com.learning.rest.domain.entity.HomeworkFile;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2021-01-11T22:25:47+0100",
    comments = "version: 1.3.1.Final, compiler: javac, environment: Java 11.0.2 (Oracle Corporation)"
)
@Component
public class HomeworkFileMapperImpl implements HomeworkFileMapper {

    @Override
    public HomeworkFile toHomeworkFile(HomeworkFileDto homeworkFileDto) {
        if ( homeworkFileDto == null ) {
            return null;
        }

        HomeworkFile homeworkFile = new HomeworkFile();

        homeworkFile.setHomeworkFileId( homeworkFileDto.getHomeworkFileId() );
        homeworkFile.setFileName( homeworkFileDto.getFileName() );

        return homeworkFile;
    }
}
