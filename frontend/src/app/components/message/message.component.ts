import { Component, ElementRef, OnInit, ViewChild } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';
import { MessageFile } from 'src/app/common/message-file';
import { Message } from 'src/app/common/message';
import { ActivatedRoute, Router } from '@angular/router';
import { MessageService } from 'src/app/services/message.service';

@Component({
  selector: 'app-message',
  templateUrl: './message.component.html',
  styleUrls: ['./message.component.css']
})
export class MessageComponent implements OnInit {

  @ViewChild('inputFile1') myInputVariable1: ElementRef;
  @ViewChild('inputFile2') myInputVariable2: ElementRef;
  @ViewChild('inputFile3') myInputVariable3: ElementRef;
  @ViewChild('inputFile4') myInputVariable4: ElementRef;
  @ViewChild('inputFile5') myInputVariable5: ElementRef;

  messageFile: MessageFile;

  tempFiles: MessageFile[] = new Array;

  messageFile1: MessageFile;
  messageFile2: MessageFile;
  messageFile3: MessageFile;
  messageFile4: MessageFile;
  messageFile5: MessageFile;


  model = new Message('', '');

  constructor(private route: ActivatedRoute,
    private messageService: MessageService) {

  }
  ngOnInit(): void {
  }


  onUpload(event, fileNumber: number) {
    let me = this;
    let file = event.target.files[0];
    let reader = new FileReader();
    reader.readAsDataURL(file);
    reader.onload = function () {
      me.messageFile = new MessageFile;
      me.messageFile.fileName = String(event.target.files[0].name);
      me.messageFile.fileContent = String(reader.result);
      if (fileNumber == 1) {
        me.messageFile1 = me.messageFile;
      }
      if (fileNumber == 2) {
        me.messageFile2 = me.messageFile;
      }
      if (fileNumber == 3) {
        me.messageFile3 = me.messageFile;
      }
      if (fileNumber == 4) {
        me.messageFile4 = me.messageFile;
      }
      if (fileNumber == 5) {
        me.messageFile5 = me.messageFile;
      }
      me.tempFiles.push(me.messageFile);
    };
    reader.onerror = function (error) {
      console.log('Error: ', error);
    };
  }

  onSubmit() {
    this.model.userFrom = 1;
    this.model.userTo = Number(this.route.snapshot.paramMap.get("teacherId"));
    this.model.files = this.tempFiles;
    this.messageService.addMessage(this.model);
  }

  reset(fileNumber: number) {
    if (fileNumber == 1) {
      this.myInputVariable1.nativeElement.value = '';
      const index: number = this.tempFiles.indexOf(this.messageFile1);
      if (index !== -1) {
        this.tempFiles.splice(index, 1);
      }
      this.messageFile1 = undefined;
    }
    if (fileNumber == 2) {
      this.myInputVariable2.nativeElement.value = '';
      const index: number = this.tempFiles.indexOf(this.messageFile2);
      if (index !== -1) {
        this.tempFiles.splice(index, 1);
      }
      this.messageFile2 = undefined;
    }
    if (fileNumber == 3) {
      this.myInputVariable3.nativeElement.value = '';
      const index: number = this.tempFiles.indexOf(this.messageFile3);
      if (index !== -1) {
        this.tempFiles.splice(index, 1);
      }
      this.messageFile3 = undefined;
    }
    if (fileNumber == 4) {
      this.myInputVariable4.nativeElement.value = '';
      const index: number = this.tempFiles.indexOf(this.messageFile4);
      if (index !== -1) {
        this.tempFiles.splice(index, 1);
      }
      this.messageFile4 = undefined;
    }
    if (fileNumber == 5) {
      this.myInputVariable5.nativeElement.value = '';
      const index: number = this.tempFiles.indexOf(this.messageFile5);
      if (index !== -1) {
        this.tempFiles.splice(index, 1);
      }
      this.messageFile5 = undefined;
    }
  }
}
