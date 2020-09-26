import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AllMessagesComponent } from './components/all-messages/all-messages.component';
import { ClassesComponent } from './components/classes/classes.component';
import { ContentComponent } from './components/content/content.component';
import { HomeworkComponent } from './components/homework/homework.component';
import { LoginComponent } from './components/login/login.component';
import { MessageDetailsComponent } from './components/message-details/message-details.component';
import { MessageComponent } from './components/message/message.component';
import { RegisterComponent } from './components/register/register.component';
import { TeachersComponent } from './components/teachers/teachers.component';

const appRoute: Routes = [
  { path: 'viewMessage/:messageId', component: MessageDetailsComponent },
  { path: 'createMessage/:teacherId', component: MessageComponent },
  { path: 'allMessages', component: AllMessagesComponent },
  { path: 'classes', component: ClassesComponent },
  { path: 'teachers', component: TeachersComponent },
  { path: 'homework', component: HomeworkComponent },
  { path: 'menu', component: ContentComponent },
  { path: '', redirectTo: '/menu', pathMatch: 'full' },
  { path: '**', redirectTo: '/menu', pathMatch: 'full' }
];

const loginRoute: Routes = [
  { path: 'login', component: LoginComponent },
  { path: 'register', component: RegisterComponent },
  { path: '', redirectTo: '/login', pathMatch: 'full' },
  { path: '**', redirectTo: '/login', pathMatch: 'full' }
];

let routes: Routes;

let isToken = localStorage.getItem("access_token");

if (isToken) {
  routes = appRoute;
} else {
  routes = loginRoute;
}

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule {
}
