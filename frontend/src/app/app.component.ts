import { Component } from "@angular/core";
import { Router } from "@angular/router";

@Component({
  selector: "app-root",
  templateUrl: "./app.component.html",
  styleUrls: ["./app.component.css"],
})
export class AppComponent {
  actualRouter: string;
  isToken: boolean;

  constructor(private router: Router) {
    router.events.subscribe((url: any) => {
      if (url.url != undefined) {
        this.actualRouter = url.url;
      }
    });
    const remainingTime =
      new Date(localStorage.getItem("token_time")).getTime() -
      new Date().getTime();
    if (remainingTime < 0) {
      setTimeout(() => {
        localStorage.clear();
      }, 1000);
    }
    console.log(remainingTime);
    this.isToken = localStorage.getItem("access_token") != null;
  }

  title = "frontend";
}
