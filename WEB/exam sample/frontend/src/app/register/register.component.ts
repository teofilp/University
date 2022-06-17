import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import api from 'src/api';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.scss'],
})
export class RegisterComponent implements OnInit {
  public name: string = '';

  constructor(private router: Router, private route: ActivatedRoute) {}

  ngOnInit(): void {}

  public register() {
    if (!this.name) {
      return;
    }

    const params = new URLSearchParams({ username: this.name });
    api.post<boolean>(`/Developers/register?${params}`).then(({ data }) => {
      if (!data) {
        return alert('Something went wrong');
      }

      alert('Registered successfully!');
      sessionStorage.setItem('username', this.name);
      this.router.navigate(['/home'], { relativeTo: this.route });
    });
  }
}
