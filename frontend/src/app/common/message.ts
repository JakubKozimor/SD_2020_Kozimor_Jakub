import { MessageFile } from './message-file';

export class Message {
    messageId: number;
    userFrom: number;
    userTo: number;
    date: Date;
    status: string;
    files: MessageFile[];
    title: string;
    content: string;
}
