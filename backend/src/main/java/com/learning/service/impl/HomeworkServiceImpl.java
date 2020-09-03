package com.learning.service.impl;

import com.learning.domain.entity.Homework;
import com.learning.domain.repository.HomeworkRepository;
import com.learning.service.HomeworkService;
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
