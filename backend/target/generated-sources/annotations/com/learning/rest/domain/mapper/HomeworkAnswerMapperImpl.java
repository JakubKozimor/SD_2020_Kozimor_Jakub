package com.learning.rest.domain.mapper;

import com.learning.rest.domain.dto.homeworkAnswer.HomeworkAnswerDetailsDto;
import com.learning.rest.domain.dto.homeworkAnswer.HomeworkAnswerDto;
import com.learning.rest.domain.dto.homeworkAnswer.HomeworkAnswerUserDetailsDto;
import com.learning.rest.domain.entity.HomeworkAnswer;
import com.learning.rest.domain.entity.HomeworkAnswerFile;
import com.learning.rest.domain.entity.User;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2021-01-25T22:46:19+0100",
    comments = "version: 1.3.1.Final, compiler: javac, environment: Java 11.0.2 (Oracle Corporation)"
)
@Component
public class HomeworkAnswerMapperImpl implements HomeworkAnswerMapper {

    @Override
    public HomeworkAnswer toHomeworkAnswer(HomeworkAnswerDto homeworkAnswerDto) {
        if ( homeworkAnswerDto == null ) {
            return null;
        }

        HomeworkAnswer homeworkAnswer = new HomeworkAnswer();

        homeworkAnswer.setHomeworkAnswerId( homeworkAnswerDto.getHomeworkAnswerId() );
        homeworkAnswer.setMessage( homeworkAnswerDto.getMessage() );

        return homeworkAnswer;
    }

    @Override
    public HomeworkAnswerDetailsDto toHomeworkAnswerDetailsDto(HomeworkAnswer homeworkAnswer) {
        if ( homeworkAnswer == null ) {
            return null;
        }

        HomeworkAnswerDetailsDto homeworkAnswerDetailsDto = new HomeworkAnswerDetailsDto();

        homeworkAnswerDetailsDto.setHomeworkAnswerId( homeworkAnswer.getHomeworkAnswerId() );
        homeworkAnswerDetailsDto.setMessage( homeworkAnswer.getMessage() );
        homeworkAnswerDetailsDto.setGrade( homeworkAnswer.getGrade() );
        homeworkAnswerDetailsDto.setComment( homeworkAnswer.getComment() );
        List<HomeworkAnswerFile> list = homeworkAnswer.getFiles();
        if ( list != null ) {
            homeworkAnswerDetailsDto.setFiles( new ArrayList<HomeworkAnswerFile>( list ) );
        }

        return homeworkAnswerDetailsDto;
    }

    @Override
    public HomeworkAnswerUserDetailsDto toHomeworkAnswerUserDetailsDto(HomeworkAnswer homework) {
        if ( homework == null ) {
            return null;
        }

        HomeworkAnswerUserDetailsDto homeworkAnswerUserDetailsDto = new HomeworkAnswerUserDetailsDto();

        homeworkAnswerUserDetailsDto.setUserLastName( homeworkStudentLastName( homework ) );
        homeworkAnswerUserDetailsDto.setUserFirstName( homeworkStudentFirstName( homework ) );
        homeworkAnswerUserDetailsDto.setHomeworkAnswerId( homework.getHomeworkAnswerId() );
        homeworkAnswerUserDetailsDto.setMessage( homework.getMessage() );
        homeworkAnswerUserDetailsDto.setGrade( homework.getGrade() );
        homeworkAnswerUserDetailsDto.setComment( homework.getComment() );

        return homeworkAnswerUserDetailsDto;
    }

    private String homeworkStudentLastName(HomeworkAnswer homeworkAnswer) {
        if ( homeworkAnswer == null ) {
            return null;
        }
        User student = homeworkAnswer.getStudent();
        if ( student == null ) {
            return null;
        }
        String lastName = student.getLastName();
        if ( lastName == null ) {
            return null;
        }
        return lastName;
    }

    private String homeworkStudentFirstName(HomeworkAnswer homeworkAnswer) {
        if ( homeworkAnswer == null ) {
            return null;
        }
        User student = homeworkAnswer.getStudent();
        if ( student == null ) {
            return null;
        }
        String firstName = student.getFirstName();
        if ( firstName == null ) {
            return null;
        }
        return firstName;
    }
}
