import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppComponent } from './app.component';
import { HeaderComponent } from './components/header/header.component';
import { FooterComponent } from './components/footer/footer.component';
import { ContentComponent } from './components/content/content.component';
import { CalendarComponent } from './components/calendar/calendar.component';
import { Routes, RouterModule } from '@angular/router';
import { HomeworkComponent } from './components/homework/homework.component';
import { TeachersComponent } from './components/teachers/teachers.component';
import { ClassesComponent } from './components/classes/classes.component';
import { FormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';
import { MessageComponent } from './components/message/message.component';
import { MessageDetailsComponent } from './components/message-details/message-details.component';
import { AllMessagesComponent } from './components/all-messages/all-messages.component';


const routes: Routes = [
  { path: 'viewMessage/:messageId', component: MessageDetailsComponent},
  { path: 'createMessage/:teacherId', component: MessageComponent},
  { path: 'allMessages', component: AllMessagesComponent},
  { path: 'classes', component: ClassesComponent},
  { path: 'teachers', component: TeachersComponent},
  { path: 'homework', component: HomeworkComponent},
  { path: 'menu', component: ContentComponent},
  { path: '', redirectTo: '/menu', pathMatch: 'full' },
  { path: '**', redirectTo: '/menu', pathMatch: 'full' }
];

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
  ],
  imports: [
    RouterModule.forRoot(routes),
    BrowserModule,
    HttpClientModule,
    FormsModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
