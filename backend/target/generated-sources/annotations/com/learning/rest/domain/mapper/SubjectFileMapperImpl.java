package com.learning.rest.domain.mapper;

import com.learning.rest.domain.dto.subject.SubjectFileDto;
import com.learning.rest.domain.entity.SubjectFile;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2020-12-05T11:30:39+0100",
    comments = "version: 1.3.1.Final, compiler: javac, environment: Java 11.0.2 (Oracle Corporation)"
)
@Component
public class SubjectFileMapperImpl implements SubjectFileMapper {

    @Override
    public SubjectFile toSubjectFile(SubjectFileDto subjectFileDto) {
        if ( subjectFileDto == null ) {
            return null;
        }

        SubjectFile subjectFile = new SubjectFile();

        subjectFile.setFileName( subjectFileDto.getFileName() );

        return subjectFile;
    }
}
