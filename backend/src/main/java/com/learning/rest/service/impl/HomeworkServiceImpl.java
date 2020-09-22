package com.learning.rest.service.impl;

import com.learning.rest.domain.entity.Homework;
import com.learning.rest.domain.repository.HomeworkRepository;
import com.learning.rest.service.HomeworkService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class HomeworkServiceImpl implements HomeworkService {

    private final HomeworkRepository homeworkRepository;

    @Override
    @Transactional
    public void saveHomework(Homework homework) {
        homeworkRepository.save(homework);
    }
}
