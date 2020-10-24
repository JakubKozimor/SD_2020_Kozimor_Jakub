import { ClassesFile } from './classes-file';

export class Classes {
    lessonId: number;
    subjectId: number;
    subjectName: string;
    url: string;
    startTime: String;
    files: ClassesFile;
    status: string;
}
