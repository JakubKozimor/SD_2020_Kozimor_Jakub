import { Component, OnInit } from "@angular/core";
import { FormBuilder, FormGroup, Validators } from "@angular/forms";
import { ActivatedRoute, Router } from "@angular/router";
import { GroupMessageFromSearch } from "src/app/common/group-message-from-search";
import { MessageFile } from "src/app/common/message-file";
import { Global } from "src/app/global";
import { MessageService } from "src/app/services/message.service";

@Component({
  selector: "app-group-message-search",
  templateUrl: "./group-message-search.component.html",
  styleUrls: ["./group-message-search.component.css"],
})
export class GroupMessageSearchComponent implements OnInit {
  validateForm!: FormGroup;
  formSubmitted = false;
  messageFile: MessageFile;
  tempFiles: MessageFile[] = new Array();
  message: GroupMessageFromSearch;

  constructor(
    private route: ActivatedRoute,
    private messageService: MessageService,
    private fb: FormBuilder,
    private router: Router,
    private global: Global
  ) {}
  ngOnInit(): void {
    this.validateForm = this.createMessageForm();
  }

  createMessageForm(): FormGroup {
    const form = this.fb.group({
      title: [null, [Validators.required]],
      content: [null],
      files: [null],
    });
    return form;
  }

  get title(): any {
    return this.validateForm.get("title");
  }

  onUpload(event) {
    let me = this;
    let file = event.target.files[0];
    let reader = new FileReader();
    reader.readAsDataURL(file);
    reader.onload = function () {
      me.messageFile = new MessageFile();
      me.messageFile.fileName = String(event.target.files[0].name);
      me.messageFile.fileContent = String(reader.result);
      me.tempFiles.push(me.messageFile);
    };
    reader.onerror = function (error) {
      console.log("Error: ", error);
    };

    this.validateForm.controls["files"].setValue(this.tempFiles);
  }

  reset(fileNumber: MessageFile) {
    const index: number = this.tempFiles.indexOf(fileNumber);
    if (index !== -1) {
      this.tempFiles.splice(index, 1);
    }
    this.validateForm.controls["files"].setValue(this.tempFiles);
  }

  submitForm() {
    this.formSubmitted = true;
    if (this.validateForm.valid) {
      this.message = this.validateForm.value;
      this.message.users = this.global.userList;
      this.messageService.addGroupMessageFromSearch(this.message);
      setTimeout(() => {
        this.changePage();
      }, 500);
    }
  }

  changePage() {
    this.router.navigate(["allMessages"]);
  }
}
