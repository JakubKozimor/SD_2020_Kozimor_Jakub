package com.learning.rest.service.map;

import com.learning.rest.base64.Base64Helper;
import com.learning.rest.domain.dto.subject.SubjectFileDto;
import com.learning.rest.domain.entity.SubjectFile;
import com.learning.rest.domain.mapper.SubjectFileMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SubjectMap {

    private final SubjectFileMapper subjectFileMapper;

    public SubjectFile mapToSubjectFile(SubjectFileDto subjectFileDto) {
        SubjectFile subjectFile = subjectFileMapper.toSubjectFile(subjectFileDto);
        String fileContentInBase64 = Base64Helper.getContentFromFileInBase64(subjectFileDto.getFileContent());
        byte[] decodedFile = Base64Helper.decodeFileFromBase64(fileContentInBase64);
        subjectFile.setFileContent(decodedFile);
        return subjectFile;
    }
}
