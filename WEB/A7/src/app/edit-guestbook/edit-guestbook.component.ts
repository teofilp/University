import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { environment } from 'src/environments/environment';

@Component({
  selector: 'app-edit-guestbook',
  templateUrl: './edit-guestbook.component.html',
  styleUrls: ['./edit-guestbook.component.css', '../common/main.css'],
})
export class EditGuestbookComponent implements OnInit {
  author: string = '';
  title: string = '';
  description: string = '';
  recordId: number = 0;

  constructor(private route: ActivatedRoute, private router: Router) {}

  ngOnInit(): void {
    this.recordId = Number(this.route.snapshot.params['id']);
    this.getRecord(this.recordId);
  }

  getRecord(id: number) {
    const xhttp = new XMLHttpRequest();
    const that = this;
    xhttp.onreadystatechange = function () {
      if (this.readyState == 4 && this.status == 200) {
        const { Author, Title, Comment } = JSON.parse(this.responseText);

        that.author = Author;
        that.title = Title;
        that.description = Comment;
      }
    };

    xhttp.open(
      'GET',
      `${environment.baseURL}/guest-book/GuestBooks.php?id=${id}`
    );
    xhttp.send();
  }

  handleUpdate() {
    console.log(this.author);
    if (!this.author) return alert('author is required');
    if (!this.validateEmail(this.author))
      return alert('author must be a valid email');
    if (!this.title) return alert('title is required');
    if (!this.description) return alert('description is required');

    const xhttp = new XMLHttpRequest();
    const that = this;
    xhttp.onreadystatechange = function () {
      if (this.readyState == 4 && this.status === 200) {
        alert('Record updated successfully');
        that.router.navigate(['/view'], { relativeTo: that.route });
      }
    };

    xhttp.open('PUT', `${environment.baseURL}/guest-book/GuestBooks.php`);
    xhttp.setRequestHeader('Content-Type', 'application/json;charset=UTF-8');

    xhttp.send(
      JSON.stringify({
        id: that.recordId,
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
}
