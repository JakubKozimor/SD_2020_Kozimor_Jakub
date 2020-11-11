package com.learning.rest.domain.dto.lesson;

import com.learning.rest.domain.entity.LiveHomeworkFile;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@Data
public class LiveHomeworkDetailsDto {
    private Long liveHomeworkId;
    private String description;
    private List<LiveHomeworkFile> files;
}
