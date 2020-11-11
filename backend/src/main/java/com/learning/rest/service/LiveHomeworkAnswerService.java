package com.learning.rest.service;

import com.learning.rest.domain.dto.live.homework.LiveHomeworkAnswerDto;

public interface LiveHomeworkAnswerService {
    void addLiveHomeworkAnswer(LiveHomeworkAnswerDto liveHomeworkAnswerDto);

    LiveHomeworkAnswerDto getLiveHomeworkAnswerDetails(Long liveHomeworkId, Long userId);
}
