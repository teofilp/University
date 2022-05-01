import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { environment } from 'src/environments/environment';

@Component({
  selector: 'app-add-guestbook',
  templateUrl: './add-guestbook.component.html',
  styleUrls: ['./add-guestbook.component.css', '../common/main.css'],
})
export class AddGuestbookComponent implements OnInit {
  author: string = '';
  title: string = '';
  description: string = '';

  constructor(private router: Router, private route: ActivatedRoute) {}

  ngOnInit(): void {}

  handleSubmit() {
    if (!this.author) return alert('Author is required');

    if (!this.validateEmail(this.author))
      return alert('Author must be a valid email');

    if (!this.title) return alert('Title is required');

    if (!this.description) return alert('Description is required');

    const xhttp = new XMLHttpRequest();
    const that = this;
    xhttp.onreadystatechange = function () {
      console.log(this);
      if (this.readyState == 4 && this.status === 200) {
        alert('Guest book added successfully!');
        that.author = '';
        that.title = '';
        that.description = '';
      }
    };

    xhttp.open('POST', `${environment.baseURL}/guest-book/GuestBooks.php`);
    xhttp.setRequestHeader('Content-Type', 'application/json;charset=UTF-8');
    xhttp.send(
      JSON.stringify({
        author: this.author,
        title: this.title,
        comment: this.description,
      })
    );
  }

  validateEmail(email: string) {
    return String(email)
      .toLowerCase()
      .match(
        /^(([^<>()[\]\\.,;:\s@"]+(\.[^<>()[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/
      );
  }

  goToLogin() {
    this.router.navigate(['/login'], { relativeTo: this.route });
  }
}
