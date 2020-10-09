import { DatePipe } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HomeworkDetails } from 'src/app/common/homework-details';
import { FileServiceService } from 'src/app/services/file-service.service';
import { HomeworkService } from 'src/app/services/homework.service';

@Component({
  selector: 'app-homework-details',
  templateUrl: './homework-details.component.html',
  styleUrls: ['./homework-details.component.css']
})
export class HomeworkDetailsComponent implements OnInit {

  homeworkDetails: HomeworkDetails;

  constructor(
    private homeworkService: HomeworkService,
    private route: ActivatedRoute,
    private fileService: FileServiceService,
    private datePipe: DatePipe
  ) { }

  ngOnInit(): void {
    let homeworkId = this.route.snapshot.paramMap.get('homeworkId');
    this.getHomeworkDetails(Number(homeworkId));
  }

  getHomeworkDetails(homeworkId: number) {
    this.homeworkService.getHomeworkDetails(homeworkId).subscribe(data => this.homeworkDetails = data);
  }

  downloadFile(fileId: number, fileName: string) {
    this.fileService.downloadHomeworkFile(fileId, fileName);
  }

  transformDate(date) {
    return this.datePipe.transform(date, 'yyyy-MM-dd');
  }
}
