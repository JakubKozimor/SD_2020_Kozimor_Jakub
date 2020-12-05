package com.learning.rest.domain.mapper;

import com.learning.rest.domain.dto.lesson.LiveHomeworkDetailsDto;
import com.learning.rest.domain.dto.live.homework.LiveHomeworkDto;
import com.learning.rest.domain.entity.LiveHomework;
import com.learning.rest.domain.entity.LiveHomeworkFile;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2020-12-05T15:40:43+0100",
    comments = "version: 1.3.1.Final, compiler: javac, environment: Java 11.0.2 (Oracle Corporation)"
)
@Component
public class LiveHomeworkMapperImpl implements LiveHomeworkMapper {

    @Override
    public LiveHomework toLiveHomework(LiveHomeworkDto liveHomeworkDto) {
        if ( liveHomeworkDto == null ) {
            return null;
        }

        LiveHomework liveHomework = new LiveHomework();

        liveHomework.setLiveHomeworkId( liveHomeworkDto.getLiveHomeworkId() );
        liveHomework.setDescription( liveHomeworkDto.getDescription() );

        return liveHomework;
    }

    @Override
    public LiveHomeworkDetailsDto toLiveHomeworkDetailsDto(LiveHomework homework) {
        if ( homework == null ) {
            return null;
        }

        LiveHomeworkDetailsDto liveHomeworkDetailsDto = new LiveHomeworkDetailsDto();

        liveHomeworkDetailsDto.setLiveHomeworkId( homework.getLiveHomeworkId() );
        liveHomeworkDetailsDto.setDescription( homework.getDescription() );
        List<LiveHomeworkFile> list = homework.getFiles();
        if ( list != null ) {
            liveHomeworkDetailsDto.setFiles( new ArrayList<LiveHomeworkFile>( list ) );
        }

        return liveHomeworkDetailsDto;
    }
}
