import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { environment } from 'src/environments/environment';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css'],
})
export class LoginComponent implements OnInit {
  public username: any = '';
  public password: any = '';

  constructor(private router: Router, private route: ActivatedRoute) {}

  ngOnInit(): void {}

  handleSubmit() {
    console.log(this.username, this.password);

    if (!this.username) {
      return alert('Username is required');
    }

    if (!this.password) {
      return alert('Password is required');
    }

    const xhttp = new XMLHttpRequest();
    const that = this;
    xhttp.onreadystatechange = function () {
      if (this.readyState == 4 && this.status == 200) {
        sessionStorage.setItem('userId', this.responseText);
        that.router.navigate(['/view'], { relativeTo: that.route });
      }
    };

    xhttp.open('POST', `${environment.baseURL}/Users/login`);
    xhttp.setRequestHeader('Content-Type', 'application/json;charset=UTF-8');
    xhttp.send(
      JSON.stringify({
        username: this.username,
        password: this.password,
      })
    );
  }
}
