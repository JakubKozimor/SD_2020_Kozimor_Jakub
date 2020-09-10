import { SubjectFile } from './subject-file';
import { Homework } from './homework';

export class Subject {
    subjectId: number;
    name: string;
    day: string;
    week: string;
    startTime: string;
    longOfTime: string;
    files: SubjectFile[];
    homeworks: Homework[];
}
