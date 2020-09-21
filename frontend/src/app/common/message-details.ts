import { MessageFile } from './message-file';

export class MessageDetails {
    messageId: number;
    title: string;
    content: string;
    userId: number;
    firstName: string;
    lastName: string;
    email: string;
    date: Date;
    files: MessageFile[];
}
