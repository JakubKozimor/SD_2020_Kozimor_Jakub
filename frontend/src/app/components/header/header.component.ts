import { Component, OnInit } from "@angular/core";
import { Global } from "src/app/global";

@Component({
  selector: "app-header",
  templateUrl: "./header.component.html",
  styleUrls: ["./header.component.css"],
})
export class HeaderComponent implements OnInit {
  isTeacher: boolean;
  isAdmin: boolean;

  constructor(private global: Global) {}

  ngOnInit(): void {
    this.isTeacher = this.global.isTeacher();
    this.isAdmin = this.global.isAdmin();
  }

  logout() {
    localStorage.clear();
    window.location.reload();
  }
}
