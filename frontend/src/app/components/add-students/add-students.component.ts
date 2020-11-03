import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { User } from 'src/app/common/user';
import { UserDto } from 'src/app/common/user-dto';
import { UserService } from 'src/app/services/user.service';

@Component({
  selector: 'app-add-students',
  templateUrl: './add-students.component.html',
  styleUrls: ['./add-students.component.css']
})
export class AddStudentsComponent implements OnInit {

  studentsList: UserDto[];

  thePageNumber: number = 1;
  thePageSize: number = 5;
  theElements: number = 0;

  searchValue = "";

  tempUsers = new Set();

  constructor(
    private route: ActivatedRoute,
    private userService: UserService,
    private router: Router
  ) { }

  ngOnInit(): void {
    this.listOfStudents();
  }

  listOfStudents() {
    this.userService.searchStudents(this.searchValue, this.thePageNumber - 1, this.thePageSize).subscribe(data => {
      this.studentsList = data.content;
      this.thePageNumber = data.number + 1;
      this.thePageSize = data.size;
      this.theElements = data.totalElements;
    })
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

  add(user: UserDto) {
    this.tempUsers.add(user);
  }

  remove(user: UserDto) {
    this.tempUsers.delete(user);
  }

  submit() {
    let subjectId = Number(this.route.snapshot.paramMap.get("subjectId"));
    let table = new Array;
    table = Array.from(this.tempUsers)
    this.userService.addStudents(table, subjectId);
    this.changePage()
    this.tempUsers = new Set();
  }

  changePage() {
    this.router.navigate(['allSubjects']);
  }

}
