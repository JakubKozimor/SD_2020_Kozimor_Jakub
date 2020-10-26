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
import { DatePipe } from '@angular/common';
import { AddHomeworkComponent } from './components/add-homework/add-homework.component';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { AddHomeworkAnswerComponent } from './components/add-homework-answer/add-homework-answer.component';
import { DoneHomeworksComponent } from './components/done-homeworks/done-homeworks.component';
import { HomeworkAnswerDetailsComponent } from './components/homework-answer-details/homework-answer-details.component';
import { AllSubjectsComponent } from './components/all-subjects/all-subjects.component';
import { HomeworksBySubjectComponent } from './components/homeworks-by-subject/homeworks-by-subject.component';
import { AddClassesComponent } from './components/add-classes/add-classes.component';
import { LiveComponent } from './components/live/live.component';
import { AddSubjectComponent } from './components/add-subject/add-subject.component';
import { AddStudentsComponent } from './components/add-students/add-students.component';


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
  ],
  imports: [
    AppRoutingModule,
    BrowserModule,
    NgbModule,
    HttpClientModule,
    FormsModule,
    ReactiveFormsModule
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
