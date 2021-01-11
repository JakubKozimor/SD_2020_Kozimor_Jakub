import { Injectable } from "@angular/core";

@Injectable({
  providedIn: "root",
})
export class Global {
  week = "ALL";
  actualUserRole = "";

  lastHomework: number;

  setLastHomework(homeworkId: number) {
    this.lastHomework = homeworkId;
  }

  getLastHomework(): number {
    return this.lastHomework;
  }

  setWeek(week: string) {
    this.week = week;
  }

  getWeek(): string {
    return this.week;
  }

  setActualUserRole(role: string) {
    this.actualUserRole = role;
  }

  getActualUserRole(): string {
    return this.actualUserRole;
  }

  isTeacher(): boolean {
    if (String(this.getActualUserRoleFromLocalStorage()) == "ROLE_TEACHER") {
      return true;
    } else {
      return false;
    }
  }

  isAdmin(): boolean {
    if (String(this.getActualUserRoleFromLocalStorage()) == "ROLE_ADMIN") {
      return true;
    } else {
      return false;
    }
  }

  getActualUserRoleFromLocalStorage() {
    return localStorage.getItem("user_role");
  }
}
