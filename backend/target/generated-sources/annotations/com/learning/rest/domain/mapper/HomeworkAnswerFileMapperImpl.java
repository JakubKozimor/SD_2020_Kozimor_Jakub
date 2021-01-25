package com.learning.rest.domain.mapper;

import com.learning.rest.domain.dto.homeworkAnswer.HomeworkAnswerFileDto;
import com.learning.rest.domain.entity.HomeworkAnswerFile;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2021-01-25T22:46:19+0100",
    comments = "version: 1.3.1.Final, compiler: javac, environment: Java 11.0.2 (Oracle Corporation)"
)
@Component
public class HomeworkAnswerFileMapperImpl implements HomeworkAnswerFileMapper {

    @Override
    public HomeworkAnswerFile toHomeworkAnswerFile(HomeworkAnswerFileDto homeworkAnswerFileDto) {
        if ( homeworkAnswerFileDto == null ) {
            return null;
        }

        HomeworkAnswerFile homeworkAnswerFile = new HomeworkAnswerFile();

        homeworkAnswerFile.setHomeworkAnswerFileId( homeworkAnswerFileDto.getHomeworkAnswerFileId() );
        homeworkAnswerFile.setFileName( homeworkAnswerFileDto.getFileName() );

        return homeworkAnswerFile;
    }
}
