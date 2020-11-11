package com.learning.rest.service;

import com.learning.rest.domain.dto.lesson.LiveHomeworkDetailsDto;
import com.learning.rest.domain.dto.live.homework.LiveHomeworkDto;
import com.learning.rest.domain.entity.LiveHomework;

import java.util.List;

public interface LiveHomeworkService {
    void createLiveHomework(LiveHomeworkDto liveHomework, Long lessonId);

    List<LiveHomework> getAllLiveHomeworks(Long lessonId);

    LiveHomeworkDetailsDto getLiveHomeworkDetails(Long lessonId);
}
