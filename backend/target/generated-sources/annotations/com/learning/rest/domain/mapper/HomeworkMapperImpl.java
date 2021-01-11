package com.learning.rest.domain.mapper;

import com.learning.rest.domain.dto.homework.HomeworkDetailsDto;
import com.learning.rest.domain.dto.homework.HomeworkDto;
import com.learning.rest.domain.dto.homework.HomeworkForFirstView;
import com.learning.rest.domain.dto.homework.RatedHomeworkDto;
import com.learning.rest.domain.entity.Homework;
import com.learning.rest.domain.entity.HomeworkFile;
import com.learning.rest.domain.entity.Subject;
import com.learning.rest.domain.entity.enums.HomeworkStatus;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;
import javax.annotation.processing.Generated;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2021-01-11T22:25:47+0100",
    comments = "version: 1.3.1.Final, compiler: javac, environment: Java 11.0.2 (Oracle Corporation)"
)
@Component
public class HomeworkMapperImpl implements HomeworkMapper {

    private final DatatypeFactory datatypeFactory;

    public HomeworkMapperImpl() {
        try {
            datatypeFactory = DatatypeFactory.newInstance();
        }
        catch ( DatatypeConfigurationException ex ) {
            throw new RuntimeException( ex );
        }
    }

    @Override
    public HomeworkDetailsDto toHomeworkDetailsDto(Homework homework) {
        if ( homework == null ) {
            return null;
        }

        HomeworkDetailsDto homeworkDetailsDto = new HomeworkDetailsDto();

        homeworkDetailsDto.setSubject( homeworkSubjectName( homework ) );
        homeworkDetailsDto.setHomeworkId( homework.getHomeworkId() );
        homeworkDetailsDto.setTitle( homework.getTitle() );
        homeworkDetailsDto.setDescription( homework.getDescription() );
        homeworkDetailsDto.setDeadline( xmlGregorianCalendarToString( dateToXmlGregorianCalendar( homework.getDeadline() ), null ) );
        homeworkDetailsDto.setStatus( homework.getStatus() );
        List<HomeworkFile> list = homework.getFiles();
        if ( list != null ) {
            homeworkDetailsDto.setFiles( new ArrayList<HomeworkFile>( list ) );
        }

        return homeworkDetailsDto;
    }

    @Override
    public Homework toHomework(HomeworkDto homeworkDto) {
        if ( homeworkDto == null ) {
            return null;
        }

        Homework homework = new Homework();

        homework.setTitle( homeworkDto.getTitle() );
        homework.setDescription( homeworkDto.getDescription() );
        if ( homeworkDto.getDeadline() != null ) {
            homework.setDeadline( new Date( homeworkDto.getDeadline().atStartOfDay( ZoneOffset.UTC ).toInstant().toEpochMilli() ) );
        }

        homework.setStatus( HomeworkStatus.ACTIVE );

        return homework;
    }

    @Override
    public RatedHomeworkDto toRatedHomework(Homework homework) {
        if ( homework == null ) {
            return null;
        }

        RatedHomeworkDto ratedHomeworkDto = new RatedHomeworkDto();

        ratedHomeworkDto.setHomeworkId( homework.getHomeworkId() );
        ratedHomeworkDto.setTitle( homework.getTitle() );
        ratedHomeworkDto.setDeadline( homework.getDeadline() );

        return ratedHomeworkDto;
    }

    @Override
    public HomeworkForFirstView toHomeworkForFirstView(Homework homework) {
        if ( homework == null ) {
            return null;
        }

        HomeworkForFirstView homeworkForFirstView = new HomeworkForFirstView();

        homeworkForFirstView.setSubject( homeworkSubjectName( homework ) );
        homeworkForFirstView.setHomeworkId( homework.getHomeworkId() );
        homeworkForFirstView.setTitle( homework.getTitle() );
        homeworkForFirstView.setDescription( homework.getDescription() );
        homeworkForFirstView.setDeadline( homework.getDeadline() );
        List<HomeworkFile> list = homework.getFiles();
        if ( list != null ) {
            homeworkForFirstView.setFiles( new ArrayList<HomeworkFile>( list ) );
        }
        homeworkForFirstView.setStatus( homework.getStatus() );

        return homeworkForFirstView;
    }

    private String xmlGregorianCalendarToString( XMLGregorianCalendar xcal, String dateFormat ) {
        if ( xcal == null ) {
            return null;
        }

        if (dateFormat == null ) {
            return xcal.toString();
        }
        else {
            java.util.Date d = xcal.toGregorianCalendar().getTime();
            SimpleDateFormat sdf = new SimpleDateFormat( dateFormat );
            return sdf.format( d );
        }
    }

    private XMLGregorianCalendar dateToXmlGregorianCalendar( java.util.Date date ) {
        if ( date == null ) {
            return null;
        }

        GregorianCalendar c = new GregorianCalendar();
        c.setTime( date );
        return datatypeFactory.newXMLGregorianCalendar( c );
    }

    private String homeworkSubjectName(Homework homework) {
        if ( homework == null ) {
            return null;
        }
        Subject subject = homework.getSubject();
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
