import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import api from 'src/api';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss'],
})
export class AppComponent implements OnInit {
  title = 'frontend';
  constructor(private router: Router, private route: ActivatedRoute) {}

  public ngOnInit(): void {
    if (!sessionStorage.getItem('username')) {
      this.router.navigate(['register'], { relativeTo: this.route });
      return;
    }
  }
}
