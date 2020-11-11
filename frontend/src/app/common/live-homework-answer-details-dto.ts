import { LiveHomeworkAnswerFile } from "./live-homework-answer-file";

export class LiveHomeworkAnswerDetailsDto {
  liveHomeworkAnswerId: number;
  message: string;
  userFirstName: string;
  userLastName: string;
  files: LiveHomeworkAnswerFile[];
}
