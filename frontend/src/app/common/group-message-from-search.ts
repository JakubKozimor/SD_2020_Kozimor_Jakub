import { MessageFile } from "./message-file";

export class GroupMessageFromSearch {
  messageId: number;
  userFrom: number;
  users: number[];
  date: Date;
  status: string;
  files: MessageFile[];
  title: string;
  content: string;
}
