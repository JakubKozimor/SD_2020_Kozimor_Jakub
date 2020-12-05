package com.learning.rest.domain.mapper;

import com.learning.rest.domain.dto.live.homework.LiveHomeworkAnswerFileDto;
import com.learning.rest.domain.entity.LiveHomeworkAnswerFile;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2020-12-05T11:30:39+0100",
    comments = "version: 1.3.1.Final, compiler: javac, environment: Java 11.0.2 (Oracle Corporation)"
)
@Component
public class LiveHomeworkAnswerFileMapperImpl implements LiveHomeworkAnswerFileMapper {

    @Override
    public LiveHomeworkAnswerFile toLiveHomeworkAnswerFile(LiveHomeworkAnswerFileDto liveHomeworkAnswerFileDto) {
        if ( liveHomeworkAnswerFileDto == null ) {
            return null;
        }

        LiveHomeworkAnswerFile liveHomeworkAnswerFile = new LiveHomeworkAnswerFile();

        liveHomeworkAnswerFile.setLiveHomeworkAnswerFileId( liveHomeworkAnswerFileDto.getLiveHomeworkAnswerFileId() );
        liveHomeworkAnswerFile.setFileName( liveHomeworkAnswerFileDto.getFileName() );

        return liveHomeworkAnswerFile;
    }

    @Override
    public LiveHomeworkAnswerFileDto toLiveHomeworkAnswerFileDto(LiveHomeworkAnswerFile liveHomeworkAnswerFile) {
        if ( liveHomeworkAnswerFile == null ) {
            return null;
        }

        LiveHomeworkAnswerFileDto liveHomeworkAnswerFileDto = new LiveHomeworkAnswerFileDto();

        liveHomeworkAnswerFileDto.setLiveHomeworkAnswerFileId( liveHomeworkAnswerFile.getLiveHomeworkAnswerFileId() );
        liveHomeworkAnswerFileDto.setFileName( liveHomeworkAnswerFile.getFileName() );

        return liveHomeworkAnswerFileDto;
    }
}
