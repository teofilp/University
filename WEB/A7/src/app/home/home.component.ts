import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['../common/main.css', './home.component.css'],
})
export class HomeComponent implements OnInit {
  constructor(private router: Router, private route: ActivatedRoute) {}

  ngOnInit(): void {
    setTimeout(() => {
      this.router.navigate(
        [sessionStorage.getItem('userId') !== null ? '/view' : '/add'],
        { relativeTo: this.route }
      );
    }, 1000);
  }
}
