package com.learning.rest.service;

import com.learning.rest.domain.dto.live.homework.LiveHomeworkAnswerDetailsDto;
import com.learning.rest.domain.dto.live.homework.LiveHomeworkAnswerDto;

import java.util.List;

public interface LiveHomeworkAnswerService {
    void addLiveHomeworkAnswer(LiveHomeworkAnswerDto liveHomeworkAnswerDto);

    LiveHomeworkAnswerDto getLiveHomeworkAnswerDetails(Long liveHomeworkId, Long userId);

    List<LiveHomeworkAnswerDetailsDto> getAllAnswersByLiveHomeworkId(Long liveHomeworkId);
}
