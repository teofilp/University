import { Component } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import api from 'src/api';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss'],
})
export class AppComponent {
  title = 'frontend';
  constructor(private router: Router, private route: ActivatedRoute) {
    if (!sessionStorage.getItem('player')) {
      this.router.navigate(['login'], { relativeTo: this.route });
    }
  }
}
