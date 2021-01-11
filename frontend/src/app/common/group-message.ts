import { MessageFile } from './message-file';

export class GroupMessage {
    messageId: number;
    userFrom: number;
    subjectId: number;
    date: Date;
    status: string;
    files: MessageFile[];
    title: string;
    content: string;
}
