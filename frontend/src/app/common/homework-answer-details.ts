import { HomeworkAnswerFile } from './homework-answer-file';

export class HomeworkAnswerDetails {
    homeworkAnswerId: number;
    message: string;
    grade: string;
    files: HomeworkAnswerFile[];
}
