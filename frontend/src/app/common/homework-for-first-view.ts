import { HomeworkFile } from "./homework-file";
import { Subject } from "./subject";

export class HomeworkForFirstView {
  homeworkId: number;
  title: string;
  description: string;
  deadline: Date;
  files: HomeworkFile;
  status: string;
  subject: string;
}
