import { MessageFile } from './message-file';

export class Message {
    messageId: string;
    userFrom: number;
    userTo: number;
    date: Date;
    status: string;
    files: MessageFile[];

    constructor(
        public title: string,
        public content: string,
    ) { }

    addFile(file: MessageFile) {
        if (this.files == undefined) {
            this.files = new Array;
        }
        this.files.push(file);
    }
}
