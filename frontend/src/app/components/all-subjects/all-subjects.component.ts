import { Component, OnInit } from '@angular/core';
import { Subject } from 'src/app/common/subject';
import { SubjectFile } from 'src/app/common/subject-file';
import { FileServiceService } from 'src/app/services/file-service.service';
import { SubjectService } from 'src/app/services/subject.service';


@Component({
  selector: 'app-all-subjects',
  templateUrl: './all-subjects.component.html',
  styleUrls: ['./all-subjects.component.css']
})
export class AllSubjectsComponent implements OnInit {

  subjectsList: Subject[];
  tempSubjectsFiles: SubjectFile[];

  showFilesBoolean = false;
  lastFileId: number;

  thePageNumber: number = 1;
  thePageSize: number = 5;
  theElements: number = 0;

  constructor(
    private subjectService: SubjectService,
    private fileService: FileServiceService
  ) { }

  ngOnInit(): void {
    this.listOfSubjects();
    console.log(this.subjectsList)
  }

  showFiles(subjectId: number) {
    if (this.showFilesBoolean && this.lastFileId == subjectId) {
      this.showFilesBoolean = false;
    } else {
      this.lastFileId = subjectId;
      let subject = this.subjectsList.find(i => i.subjectId === subjectId);
      this.tempSubjectsFiles = subject.files;
      console.log(this.tempSubjectsFiles.length)
      this.showFilesBoolean = true;
    }
  }

  filesPresent(): boolean{
    return this.tempSubjectsFiles.length < 1;
  }

  listOfSubjects() {
    this.subjectService.getAllSubjectsByUser(this.thePageNumber - 1, this.thePageSize, 'ALL').subscribe(data => {
      this.subjectsList = data.content;
      this.thePageNumber = data.number + 1;
      this.thePageSize = data.size;
      this.theElements = data.totalElements;
    })
  }

  updateQuantity(pageSize: number) {
    this.thePageSize = pageSize;
    this.thePageNumber = 1;
    this.listOfSubjects();
  }

  downloadSubjectFile(fileId: number, fileName: string) {
    console.log(fileId)
    this.fileService.downloadSubjectFile(fileId, fileName);
  }
}
