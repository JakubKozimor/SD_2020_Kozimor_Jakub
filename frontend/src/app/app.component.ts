import { Component } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {

  actualRouter: string;

  constructor(private router: Router) {
    router.events.subscribe((url: any) => {
      if (url.url != undefined) {
        this.actualRouter = url.url;
        console.log(this.actualRouter);
      }
    });
  }

  title = 'frontend';
}
