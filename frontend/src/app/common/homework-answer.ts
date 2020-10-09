import { HomeworkAnswerFile } from './homework-answer-file';

export class HomeworkAnswer {
    message: string;
    files: HomeworkAnswerFile[];
    homeworkId: number;
    studentId: number;
    rate: number;
}
