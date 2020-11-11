import { LiveHomeworkAnswerFile } from './live-homework-answer-file';

export class LiveHomeworkAnswer {
    liveHomeworkAnswerId: number;
    message: string;
    files: LiveHomeworkAnswerFile[];
    liveHomeworkId: number;
    studentId: number;
}
