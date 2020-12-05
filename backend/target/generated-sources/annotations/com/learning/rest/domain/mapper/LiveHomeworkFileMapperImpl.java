package com.learning.rest.domain.mapper;

import com.learning.rest.domain.dto.live.homework.LiveHomeworkFileDto;
import com.learning.rest.domain.entity.LiveHomeworkFile;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2020-12-05T15:40:43+0100",
    comments = "version: 1.3.1.Final, compiler: javac, environment: Java 11.0.2 (Oracle Corporation)"
)
@Component
public class LiveHomeworkFileMapperImpl implements LiveHomeworkFileMapper {

    @Override
    public LiveHomeworkFile toLiveHomeworkFile(LiveHomeworkFileDto homeworkFileDto) {
        if ( homeworkFileDto == null ) {
            return null;
        }

        LiveHomeworkFile liveHomeworkFile = new LiveHomeworkFile();

        liveHomeworkFile.setFileName( homeworkFileDto.getFileName() );

        return liveHomeworkFile;
    }
}
