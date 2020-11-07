import { Component, OnInit } from "@angular/core";
import { UserDto } from "src/app/common/user-dto";
import { UserService } from "src/app/services/user.service";

@Component({
  selector: "app-users",
  templateUrl: "./users.component.html",
  styleUrls: ["./users.component.css"],
})
export class UsersComponent implements OnInit {
  studentsList: UserDto[];

  thePageNumber: number = 1;
  thePageSize: number = 5;
  theElements: number = 0;

  searchValue = "";
  constructor(private userService: UserService) {}

  ngOnInit(): void {
    this.listOfStudents();
  }

  listOfStudents() {
    this.userService
      .searchStudents(
        this.searchValue,
        this.thePageNumber - 1,
        this.thePageSize
      )
      .subscribe((data) => {
        this.studentsList = data.content;
        this.thePageNumber = data.number + 1;
        this.thePageSize = data.size;
        this.theElements = data.totalElements;
      });
  }

  search(search: string) {
    this.searchValue = search;
    this.listOfStudents();
  }

  updateQuantity(pageSize: number) {
    this.thePageSize = pageSize;
    this.thePageNumber = 1;
    this.listOfStudents();
  }

  getRole(role) {
    if (role == "ROLE_TEACHER") {
      return "Nauczyciel";
    } else {
      return "Uczeń";
    }
  }
}
