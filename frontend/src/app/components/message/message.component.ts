import { Component, ElementRef, OnInit, ViewChild } from '@angular/core';
import { MessageFile } from 'src/app/common/message-file';
import { Message } from 'src/app/common/message';
import { ActivatedRoute, Router } from '@angular/router';
import { MessageService } from 'src/app/services/message.service';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';

@Component({
  selector: 'app-message',
  templateUrl: './message.component.html',
  styleUrls: ['./message.component.css']
})
export class MessageComponent implements OnInit {


  validateForm!: FormGroup;
  formSubmitted = false;
  messageFile: MessageFile;
  tempFiles: MessageFile[] = new Array;
  message: Message;

  constructor(
    private route: ActivatedRoute,
    private messageService: MessageService,
    private fb: FormBuilder) {

  }
  ngOnInit(): void {
    this.validateForm = this.createMessageForm();
  }

  createMessageForm(): FormGroup {
    const form = this.fb.group({
      title: [null, [Validators.required]],
      content: [null],
      files: [null]
    });
    return form;
  }

  get title(): any {
    return this.validateForm.get('title');
  }

  onUpload(event) {
    let me = this;
    let file = event.target.files[0];
    let reader = new FileReader();
    reader.readAsDataURL(file);
    reader.onload = function () {
      me.messageFile = new MessageFile;
      me.messageFile.fileName = String(event.target.files[0].name);
      me.messageFile.fileContent = String(reader.result);
      me.tempFiles.push(me.messageFile);
    };
    reader.onerror = function (error) {
      console.log('Error: ', error);
    };

    this.validateForm.controls['files'].setValue(this.tempFiles);
  }

  reset(fileNumber: MessageFile) {
    const index: number = this.tempFiles.indexOf(fileNumber);
    if (index !== -1) {
      this.tempFiles.splice(index, 1);
    }
    this.validateForm.controls['files'].setValue(this.tempFiles);
  }

  submitForm() {
    this.formSubmitted = true;
    if (this.validateForm.valid) {
      let userToId = Number(this.route.snapshot.paramMap.get("userToId"));
      this.message = this.validateForm.value;
      this.message.userTo = userToId;
      this.messageService.addMessage(this.message);
      this.validateForm.reset();
      this.tempFiles = new Array;
    } else {
      this.formSubmitted = false;
    }
  }
}
