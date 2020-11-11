import { LiveHomeworkFile } from './live-homework-file';

export class LiveHomeworksDetails {
    liveHomeworkId: number;
    description: string;
    files: LiveHomeworkFile[];
}
