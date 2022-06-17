import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import api from 'src/api';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss'],
})
export class LoginComponent implements OnInit {
  public playerName: string = '';

  constructor(private router: Router, private route: ActivatedRoute) {}

  ngOnInit(): void {}

  public register() {
    api
      .post('Players.php', { name: this.playerName })
      .then(() => {
        sessionStorage.setItem('player', this.playerName);
        alert('logged in successfully!');
        this.router.navigate(['../home'], { relativeTo: this.route });
      })
      .catch(() => alert('something went wrong!'));
  }
}
