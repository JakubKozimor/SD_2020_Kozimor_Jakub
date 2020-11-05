import { NgModule } from "@angular/core";
import { RouterModule, Routes } from "@angular/router";
import { AddClassesComponent } from "./components/add-classes/add-classes.component";
import { AddHomeworkAnswerComponent } from "./components/add-homework-answer/add-homework-answer.component";
import { AddHomeworkComponent } from "./components/add-homework/add-homework.component";
import { AddScheduleComponent } from "./components/add-schedule/add-schedule.component";
import { AddSchoolComponent } from './components/add-school/add-school.component';
import { AddStudentsComponent } from "./components/add-students/add-students.component";
import { AddSubjectComponent } from "./components/add-subject/add-subject.component";
import { AllMessagesComponent } from "./components/all-messages/all-messages.component";
import { AllSchoolsComponent } from './components/all-schools/all-schools.component';
import { AllSubjectsComponent } from "./components/all-subjects/all-subjects.component";
import { CalendarComponent } from "./components/calendar/calendar.component";
import { ClassesComponent } from "./components/classes/classes.component";
import { ContentComponent } from "./components/content/content.component";
import { DoneHomeworksComponent } from "./components/done-homeworks/done-homeworks.component";
import { HomeworkAnswerDetailsComponent } from "./components/homework-answer-details/homework-answer-details.component";
import { HomeworkDetailsComponent } from "./components/homework-details/homework-details.component";
import { HomeworkComponent } from "./components/homework/homework.component";
import { HomeworksBySubjectComponent } from "./components/homeworks-by-subject/homeworks-by-subject.component";
import { LiveComponent } from "./components/live/live.component";
import { LoginComponent } from "./components/login/login.component";
import { MessageDetailsComponent } from "./components/message-details/message-details.component";
import { MessageComponent } from "./components/message/message.component";
import { RateHomeworkAnswerComponent } from "./components/rate-homework-answer/rate-homework-answer.component";
import { RegisterComponent } from "./components/register/register.component";
import { TeacherHomeworkComponent } from "./components/teacher-homework/teacher-homework.component";
import { TeachersComponent } from "./components/teachers/teachers.component";
import { UpdateHomeworkComponent } from "./components/update-homework/update-homework.component";
import { UpdateSchoolComponent } from './components/update-school/update-school.component';
import { UpdateSubjectComponent } from "./components/update-subject/update-subject.component";

const studentRoutes: Routes = [
  { path: "callendar", component: CalendarComponent },
  { path: "live", component: LiveComponent },
  { path: "classes/:classesId", component: ClassesComponent },
  {
    path: "subject-homework/:subjectId",
    component: HomeworksBySubjectComponent,
  },
  { path: "allSubjects", component: AllSubjectsComponent },
  {
    path: "homework-answer-details/:homeworkId",
    component: HomeworkAnswerDetailsComponent,
  },
  { path: "homeworks-done", component: DoneHomeworksComponent },
  {
    path: "createHomeworkAnswer/:homeworkId",
    component: AddHomeworkAnswerComponent,
  },
  { path: "viewHomework/:homeworkId", component: HomeworkDetailsComponent },
  { path: "viewMessage/:messageId", component: MessageDetailsComponent },
  { path: "createMessage/:userToId", component: MessageComponent },
  { path: "allMessages", component: AllMessagesComponent },
  { path: "teachers", component: TeachersComponent },
  { path: "homework", component: HomeworkComponent },
  { path: "menu", component: ContentComponent },
  { path: "", redirectTo: "/menu", pathMatch: "full" },
  { path: "**", redirectTo: "/menu", pathMatch: "full" },
];
const teacherRoutes: Routes = [
  { path: "update-subject/:subjectId", component: UpdateSubjectComponent },
  { path: "update-homework/:homeworkId", component: UpdateHomeworkComponent },
  { path: "homework", component: TeacherHomeworkComponent },
  { path: "rate/:homeworkAnswer", component: RateHomeworkAnswerComponent },
  { path: "callendar", component: CalendarComponent },
  { path: "add-students/:subjectId", component: AddStudentsComponent },
  { path: "live", component: LiveComponent },
  { path: "add-subject", component: AddSubjectComponent },
  { path: "classes/:classesId", component: ClassesComponent },
  { path: "add-classes/:subjectId", component: AddClassesComponent },
  { path: "allSubjects", component: AllSubjectsComponent },
  { path: "addHomework/:subjectId", component: AddHomeworkComponent },
  { path: "viewHomework/:homeworkId", component: HomeworkDetailsComponent },
  { path: "viewMessage/:messageId", component: MessageDetailsComponent },
  { path: "createMessage/:userToId", component: MessageComponent },
  { path: "allMessages", component: AllMessagesComponent },
  { path: "menu", component: ContentComponent },
  { path: "", redirectTo: "/menu", pathMatch: "full" },
  { path: "**", redirectTo: "/menu", pathMatch: "full" },
];
const adminRoutes: Routes = [
  { path: "update-school/:schoolId", component: UpdateSchoolComponent },
  { path: "add-school", component: AddSchoolComponent },
  { path: "school/all", component: AllSchoolsComponent },
  { path: "add-schedule/:schoolId", component: AddScheduleComponent },
  { path: "", redirectTo: "/school/all", pathMatch: "full" },
  { path: "**", redirectTo: "/school/all", pathMatch: "full" },
];

const loginRoute: Routes = [
  { path: "login", component: LoginComponent },
  { path: "register", component: RegisterComponent },
  { path: "", redirectTo: "/login", pathMatch: "full" },
  { path: "**", redirectTo: "/login", pathMatch: "full" },
];

let routes: Routes;

let isToken = localStorage.getItem("access_token");

let role = localStorage.getItem("user_role");

if (isToken) {
  if (role == "ROLE_STUDENT") {
    routes = studentRoutes;
  }
  if (role == "ROLE_TEACHER") {
    routes = teacherRoutes;
  }
  if (role == "ROLE_ADMIN") {
    routes = adminRoutes;
  }
} else {
  routes = loginRoute;
}

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule],
})
export class AppRoutingModule {}
