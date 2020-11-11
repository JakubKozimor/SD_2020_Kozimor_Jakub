package com.learning.rest.service.impl;

import com.learning.exception.lesson.LessonNotFoundException;
import com.learning.exception.live.LiveHomeworkNotFoundException;
import com.learning.rest.base64.Base64Helper;
import com.learning.rest.domain.dto.lesson.LiveHomeworkDetailsDto;
import com.learning.rest.domain.dto.live.homework.LiveHomeworkDto;
import com.learning.rest.domain.dto.live.homework.LiveHomeworkFileDto;
import com.learning.rest.domain.entity.Lesson;
import com.learning.rest.domain.entity.LiveHomework;
import com.learning.rest.domain.entity.LiveHomeworkFile;
import com.learning.rest.domain.mapper.LiveHomeworkFileMapper;
import com.learning.rest.domain.mapper.LiveHomeworkMapper;
import com.learning.rest.domain.repository.LessonRepository;
import com.learning.rest.domain.repository.LiveHomeworkRepository;
import com.learning.rest.service.LiveHomeworkService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class LiveHomeworkServiceImpl implements LiveHomeworkService {

    private final LiveHomeworkMapper liveHomeworkMapper;
    private final LessonRepository lessonRepository;
    private final LiveHomeworkFileMapper liveHomeworkFileMapper;
    private final LiveHomeworkRepository liveHomeworkRepository;

    @Override
    public LiveHomeworkDetailsDto getLiveHomeworkDetails(Long lessonId) {
        LiveHomework liveHomework = liveHomeworkRepository.findById(lessonId).orElseThrow(LiveHomeworkNotFoundException::new);
        return liveHomeworkMapper.toLiveHomeworkDetailsDto(liveHomework);
    }

    @Override
    public List<LiveHomework> getAllLiveHomeworks(Long lessonId) {
        Lesson lesson = lessonRepository.findById(lessonId).orElseThrow(LessonNotFoundException::new);
        return lesson.getHomeworks();
    }

    @Override
    public void createLiveHomework(LiveHomeworkDto liveHomeworkDto, Long lessonId) {
        LiveHomework liveHomework = liveHomeworkMapper.toLiveHomework(liveHomeworkDto);
        Lesson lesson = lessonRepository.findById(lessonId).orElseThrow(LessonNotFoundException::new);
        List<LiveHomeworkFileDto> liveHomeworkFileDto = liveHomeworkDto.getFiles();
        if (liveHomeworkFileDto != null) {
            liveHomeworkFileDto
                    .stream()
                    .map(this::mapToLiveHomeworkFile)
                    .forEach(liveHomework::addFile);
        }
        liveHomework.setLesson(lesson);
        liveHomeworkRepository.save(liveHomework);
    }

    @Override
    public void updateLiveHomework(LiveHomeworkDto liveHomeworkDto) {
        LiveHomework oldLiveHomework = liveHomeworkRepository.findById(liveHomeworkDto.getLiveHomeworkId()).orElseThrow(LiveHomeworkNotFoundException::new);
        LiveHomework liveHomework = liveHomeworkMapper.toLiveHomework(liveHomeworkDto);
        liveHomework.setFiles(new ArrayList<>());
        List<LiveHomeworkFileDto> liveHomeworkFileDto = liveHomeworkDto.getFiles();
        if (liveHomeworkFileDto != null) {
            liveHomeworkFileDto
                    .stream()
                    .map(this::mapToLiveHomeworkFile)
                    .forEach(liveHomework::addFile);
        }
        liveHomework.setLiveHomeworkId(liveHomeworkDto.getLiveHomeworkId());
        liveHomework.setLesson(oldLiveHomework.getLesson());
        liveHomework.setLiveHomeworkAnswer(oldLiveHomework.getLiveHomeworkAnswer());
        List<LiveHomeworkFile> liveHomeworkFiles = liveHomework.getFiles();
        if (liveHomeworkFiles != null && !liveHomeworkFiles.isEmpty()) {
            liveHomeworkFiles.forEach(liveHomeworkFile -> liveHomeworkFile.setLiveHomeworkFileId(null));
        }
        liveHomeworkRepository.save(liveHomework);
    }

    private LiveHomeworkFile mapToLiveHomeworkFile(LiveHomeworkFileDto liveHomeworkFileDto) {
        LiveHomeworkFile livehomeworkFile = liveHomeworkFileMapper.toLiveHomeworkFile(liveHomeworkFileDto);
        String fileContentInBase64 = Base64Helper.getContentFromFileInBase64(liveHomeworkFileDto.getFileContent());
        byte[] decodedFile = Base64Helper.decodeFileFromBase64(fileContentInBase64);
        livehomeworkFile.setFileContent(decodedFile);
        return livehomeworkFile;
    }
}
