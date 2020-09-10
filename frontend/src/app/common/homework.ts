import { HomeworkFile } from './homework-file';

export class Homework {
    homeworkId: number;
    title: string;
    description: string;
    deadline: Date;
    files: HomeworkFile;
    status: string;
}
