import { HomeworkFile } from './homework-file';

export class HomeworkDetails {
    homeworkId: number;
    title: string;
    description: string;
    deadline: Date;
    subject: string;
    status: string;
    files: HomeworkFile[];
}
