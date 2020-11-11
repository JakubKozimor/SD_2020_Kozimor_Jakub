package com.learning.rest.service.impl;

import com.learning.exception.homeworkAnswer.HomeworkAnswerNotFoundException;
import com.learning.exception.live.LiveHomeworkNotFoundException;
import com.learning.exception.user.UserNotFoundException;
import com.learning.rest.base64.Base64Helper;
import com.learning.rest.domain.dto.live.homework.LiveHomeworkAnswerDto;
import com.learning.rest.domain.dto.live.homework.LiveHomeworkAnswerFileDto;
import com.learning.rest.domain.entity.*;
import com.learning.rest.domain.mapper.LiveHomeworkAnswerFileMapper;
import com.learning.rest.domain.mapper.LiveHomeworkAnswerMapper;
import com.learning.rest.domain.repository.LiveHomeworkAnswerRepository;
import com.learning.rest.domain.repository.LiveHomeworkRepository;
import com.learning.rest.domain.repository.UserRepository;
import com.learning.rest.service.LiveHomeworkAnswerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class LiveHomeworkAnswerServiceImpl implements LiveHomeworkAnswerService {

    private final LiveHomeworkAnswerRepository liveHomeworkAnswerRepository;
    private final UserRepository userRepository;
    private final LiveHomeworkRepository liveHomeworkRepository;
    private final LiveHomeworkAnswerMapper liveHomeworkAnswerMapper;
    private final LiveHomeworkAnswerFileMapper liveHomeworkAnswerFileMapper;

    @Override
    public LiveHomeworkAnswerDto getLiveHomeworkAnswerDetails(Long liveHomeworkId, Long userId) {
        LiveHomework liveHomework = liveHomeworkRepository.findById(liveHomeworkId).orElseThrow(LiveHomeworkNotFoundException::new);
        List<LiveHomeworkAnswer> liveHomeworkAnswers = liveHomework.getLiveHomeworkAnswer();
        Optional<LiveHomeworkAnswer> liveHomeworkAnswerOptional = liveHomeworkAnswers.stream()
                .filter(liveHomeworkAnswer -> liveHomeworkAnswer.getStudent().getUserId().equals(userId))
                .findAny();
        LiveHomeworkAnswer liveHomeworkAnswer = liveHomeworkAnswerOptional.orElseThrow(HomeworkAnswerNotFoundException::new);
        LiveHomeworkAnswerDto liveHomeworkAnswerDto = liveHomeworkAnswerMapper.toLiveHomeworkAnswerDto(liveHomeworkAnswer);
        List<LiveHomeworkAnswerFile> files = liveHomeworkAnswer.getFiles();
        if (files != null && !files.isEmpty()) {
            List<LiveHomeworkAnswerFileDto> filesDto = files.stream()
                    .map(liveHomeworkAnswerFileMapper::toLiveHomeworkAnswerFileDto)
                    .collect(Collectors.toList());
            liveHomeworkAnswerDto.setFiles(filesDto);
        }
        return liveHomeworkAnswerDto;
    }

    @Override
    public void addLiveHomeworkAnswer(LiveHomeworkAnswerDto liveHomeworkAnswerDto) {
        LiveHomeworkAnswer liveHomeworkAnswer = this.mapFromLiveHomeworkAnswerDtoToLiveHomeworkAnswer(liveHomeworkAnswerDto);
        if (liveHomeworkAnswerDto.getLiveHomeworkAnswerId() != null) {
            if (liveHomeworkAnswer.getFiles() != null && !liveHomeworkAnswer.getFiles().isEmpty()) {
                liveHomeworkAnswer.getFiles()
                        .forEach(homeworkAnswerFile -> homeworkAnswerFile.setLiveHomeworkAnswerFileId(null));
            }
        }
        liveHomeworkAnswerRepository.save(liveHomeworkAnswer);
    }

    private LiveHomeworkAnswer mapFromLiveHomeworkAnswerDtoToLiveHomeworkAnswer(LiveHomeworkAnswerDto liveHomeworkAnswerDto) {
        LiveHomeworkAnswer liveHomeworkAnswer = liveHomeworkAnswerMapper.toLiveHomeworkAnswer(liveHomeworkAnswerDto);
        List<LiveHomeworkAnswerFileDto> liveHomeworkAnswerFilesDto = liveHomeworkAnswerDto.getFiles();
        if (liveHomeworkAnswerFilesDto != null && !liveHomeworkAnswerFilesDto.isEmpty()) {
            liveHomeworkAnswerDto.getFiles()
                    .stream()
                    .map(this::mapToLiveHomeworkAnswerFile)
                    .forEach(liveHomeworkAnswer::addFile);
        }
        User student = userRepository.findById(liveHomeworkAnswerDto.getStudentId()).orElseThrow(UserNotFoundException::new);
        LiveHomework liveHomework = liveHomeworkRepository.findById(liveHomeworkAnswerDto.getLiveHomeworkId()).orElseThrow(LiveHomeworkNotFoundException::new);
        liveHomeworkAnswer.setStudent(student);
        liveHomeworkAnswer.setLiveHomework(liveHomework);
        return liveHomeworkAnswer;
    }

    private LiveHomeworkAnswerFile mapToLiveHomeworkAnswerFile(LiveHomeworkAnswerFileDto liveHomeworkAnswerFileDto) {
        LiveHomeworkAnswerFile liveHomeworkAnswerFile = liveHomeworkAnswerFileMapper.toLiveHomeworkAnswerFile(liveHomeworkAnswerFileDto);
        String fileContentInBase64 = Base64Helper.getContentFromFileInBase64(liveHomeworkAnswerFileDto.getFileContent());
        byte[] decodedFile = Base64Helper.decodeFileFromBase64(fileContentInBase64);
        liveHomeworkAnswerFile.setFileContent(decodedFile);
        return liveHomeworkAnswerFile;
    }
}
