package com.learning.rest.domain.mapper;

import com.learning.rest.domain.dto.live.homework.LiveHomeworkAnswerDetailsDto;
import com.learning.rest.domain.dto.live.homework.LiveHomeworkAnswerDto;
import com.learning.rest.domain.entity.LiveHomeworkAnswer;
import com.learning.rest.domain.entity.User;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2020-12-05T15:40:43+0100",
    comments = "version: 1.3.1.Final, compiler: javac, environment: Java 11.0.2 (Oracle Corporation)"
)
@Component
public class LiveHomeworkAnswerMapperImpl implements LiveHomeworkAnswerMapper {

    @Override
    public LiveHomeworkAnswer toLiveHomeworkAnswer(LiveHomeworkAnswerDto liveHomeworkAnswerDto) {
        if ( liveHomeworkAnswerDto == null ) {
            return null;
        }

        LiveHomeworkAnswer liveHomeworkAnswer = new LiveHomeworkAnswer();

        liveHomeworkAnswer.setLiveHomeworkAnswerId( liveHomeworkAnswerDto.getLiveHomeworkAnswerId() );
        liveHomeworkAnswer.setMessage( liveHomeworkAnswerDto.getMessage() );

        return liveHomeworkAnswer;
    }

    @Override
    public LiveHomeworkAnswerDto toLiveHomeworkAnswerDto(LiveHomeworkAnswer liveHomeworkAnswer) {
        if ( liveHomeworkAnswer == null ) {
            return null;
        }

        LiveHomeworkAnswerDto liveHomeworkAnswerDto = new LiveHomeworkAnswerDto();

        liveHomeworkAnswerDto.setLiveHomeworkAnswerId( liveHomeworkAnswer.getLiveHomeworkAnswerId() );
        liveHomeworkAnswerDto.setMessage( liveHomeworkAnswer.getMessage() );

        return liveHomeworkAnswerDto;
    }

    @Override
    public LiveHomeworkAnswerDetailsDto toLiveHomeworkAnswerDetailsDto(LiveHomeworkAnswer liveHomeworkAnswer) {
        if ( liveHomeworkAnswer == null ) {
            return null;
        }

        LiveHomeworkAnswerDetailsDto liveHomeworkAnswerDetailsDto = new LiveHomeworkAnswerDetailsDto();

        liveHomeworkAnswerDetailsDto.setUserFirstName( liveHomeworkAnswerStudentFirstName( liveHomeworkAnswer ) );
        liveHomeworkAnswerDetailsDto.setUserLastName( liveHomeworkAnswerStudentLastName( liveHomeworkAnswer ) );
        liveHomeworkAnswerDetailsDto.setLiveHomeworkAnswerId( liveHomeworkAnswer.getLiveHomeworkAnswerId() );
        liveHomeworkAnswerDetailsDto.setMessage( liveHomeworkAnswer.getMessage() );

        return liveHomeworkAnswerDetailsDto;
    }

    private String liveHomeworkAnswerStudentFirstName(LiveHomeworkAnswer liveHomeworkAnswer) {
        if ( liveHomeworkAnswer == null ) {
            return null;
        }
        User student = liveHomeworkAnswer.getStudent();
        if ( student == null ) {
            return null;
        }
        String firstName = student.getFirstName();
        if ( firstName == null ) {
            return null;
        }
        return firstName;
    }

    private String liveHomeworkAnswerStudentLastName(LiveHomeworkAnswer liveHomeworkAnswer) {
        if ( liveHomeworkAnswer == null ) {
            return null;
        }
        User student = liveHomeworkAnswer.getStudent();
        if ( student == null ) {
            return null;
        }
        String lastName = student.getLastName();
        if ( lastName == null ) {
            return null;
        }
        return lastName;
    }
}
