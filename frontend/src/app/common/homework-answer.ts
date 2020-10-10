import { HomeworkAnswerFile } from './homework-answer-file';

export class HomeworkAnswer {
    homeworkAnswerId: number;
    message: string;
    files: HomeworkAnswerFile[];
    homeworkId: number;
    studentId: number;
    rate: number;
}
