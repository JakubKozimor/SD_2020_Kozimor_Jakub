import { MessageFile } from './message-file';

export class MessageDetails {
    messageId: number;
    title: string;
    content: string;
    userFromId: number;
    userFromFirstName: string;
    userFromLastName: string;
    userFromEmail: string;
    userToId: number;
    userToFirstName: string;
    userToLastName: string;
    userToEmail: string;
    date: Date;
    files: MessageFile[];
}
