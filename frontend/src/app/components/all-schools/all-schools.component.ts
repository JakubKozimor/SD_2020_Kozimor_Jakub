import { Component, OnInit } from "@angular/core";
import { School } from "src/app/common/school";
import { SchoolServiceService } from "src/app/services/school-service.service";

@Component({
  selector: "app-all-schools",
  templateUrl: "./all-schools.component.html",
  styleUrls: ["./all-schools.component.css"],
})
export class AllSchoolsComponent implements OnInit {
  schoolList: School[];

  thePageNumber: number = 1;
  thePageSize: number = 5;
  theElements: number = 0;

  constructor(private schoolService: SchoolServiceService) {}

  ngOnInit(): void {
    this.listOfSchools();
  }

  listOfSchools() {
    this.schoolService
      .getAllSchools(this.thePageNumber - 1, this.thePageSize)
      .subscribe((data) => {
        this.schoolList = data.content;
        this.thePageNumber = data.number + 1;
        this.thePageSize = data.size;
        this.theElements = data.totalElements;
      });
  }

  updateQuantity(pageSize: number) {
    this.thePageSize = pageSize;
    this.thePageNumber = 1;
    this.listOfSchools();
  }
}
