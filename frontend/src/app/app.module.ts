import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppComponent } from './app.component';
import { HeaderComponent } from './components/header/header.component';
import { FooterComponent } from './components/footer/footer.component';
import { ContentComponent } from './components/content/content.component';
import { CalendarComponent } from './components/calendar/calendar.component';
import { HomeworkComponent } from './components/homework/homework.component';
import { TeachersComponent } from './components/teachers/teachers.component';
import { ClassesComponent } from './components/classes/classes.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { HttpClientModule, HTTP_INTERCEPTORS } from '@angular/common/http';
import { MessageComponent } from './components/message/message.component';
import { MessageDetailsComponent } from './components/message-details/message-details.component';
import { AllMessagesComponent } from './components/all-messages/all-messages.component';
import { LoginComponent } from './components/login/login.component';
import { RegisterComponent } from './components/register/register.component';
import { AppRoutingModule } from './app-routing.module';
import { TokenInterceptorService } from './services/token-interceptor.service';
import { HomeworkDetailsComponent } from './components/homework-details/homework-details.component';
import { CommonModule, DatePipe } from '@angular/common';
import { AddHomeworkComponent } from './components/add-homework/add-homework.component';
import { NgbModalModule, NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { AddHomeworkAnswerComponent } from './components/add-homework-answer/add-homework-answer.component';
import { DoneHomeworksComponent } from './components/done-homeworks/done-homeworks.component';
import { HomeworkAnswerDetailsComponent } from './components/homework-answer-details/homework-answer-details.component';
import { AllSubjectsComponent } from './components/all-subjects/all-subjects.component';
import { HomeworksBySubjectComponent } from './components/homeworks-by-subject/homeworks-by-subject.component';
import { AddClassesComponent } from './components/add-classes/add-classes.component';
import { LiveComponent } from './components/live/live.component';
import { AddSubjectComponent } from './components/add-subject/add-subject.component';
import { AddStudentsComponent } from './components/add-students/add-students.component';
import { AddScheduleComponent } from './components/add-schedule/add-schedule.component';
import { FlatpickrModule } from 'angularx-flatpickr';
import { CalendarModule, DateAdapter } from 'angular-calendar';
import { adapterFactory } from 'angular-calendar/date-adapters/date-fns';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { RateHomeworkAnswerComponent } from './components/rate-homework-answer/rate-homework-answer.component';
import { TeacherHomeworkComponent } from './components/teacher-homework/teacher-homework.component';
import { UpdateHomeworkComponent } from './components/update-homework/update-homework.component';
import { UpdateSubjectComponent } from './components/update-subject/update-subject.component';
import { AllSchoolsComponent } from './components/all-schools/all-schools.component';
import { AddSchoolComponent } from './components/add-school/add-school.component';
import { UpdateSchoolComponent } from './components/update-school/update-school.component';


@NgModule({
  declarations: [
    AppComponent,
    HeaderComponent,
    FooterComponent,
    ContentComponent,
    CalendarComponent,
    HomeworkComponent,
    TeachersComponent,
    ClassesComponent,
    MessageComponent,
    MessageDetailsComponent,
    AllMessagesComponent,
    LoginComponent,
    RegisterComponent,
    HomeworkDetailsComponent,
    AddHomeworkComponent,
    AddHomeworkAnswerComponent,
    DoneHomeworksComponent,
    HomeworkAnswerDetailsComponent,
    AllSubjectsComponent,
    HomeworksBySubjectComponent,
    AddClassesComponent,
    LiveComponent,
    AddSubjectComponent,
    AddStudentsComponent,
    AddScheduleComponent,
    RateHomeworkAnswerComponent,
    TeacherHomeworkComponent,
    UpdateHomeworkComponent,
    UpdateSubjectComponent,
    AllSchoolsComponent,
    AddSchoolComponent,
    UpdateSchoolComponent,
  ],
  imports: [
    CommonModule,
    AppRoutingModule,
    BrowserModule,
    NgbModule,
    NgbModalModule,
    HttpClientModule,
    FormsModule,
    BrowserAnimationsModule,
    ReactiveFormsModule,
    FlatpickrModule.forRoot(),
    CalendarModule.forRoot({
      provide: DateAdapter,
      useFactory: adapterFactory,
    })
  ],
  providers: [
    DatePipe,
    {
      provide: HTTP_INTERCEPTORS,
      useClass: TokenInterceptorService,
      multi: true
    }
  ],
  bootstrap: [AppComponent]
})
export class AppModule {
}
