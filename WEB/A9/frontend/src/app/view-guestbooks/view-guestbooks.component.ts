import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { environment } from 'src/environments/environment';

@Component({
  selector: 'app-view-guestbooks',
  templateUrl: './view-guestbooks.component.html',
  styleUrls: ['../common/main.css', './view-guestbooks.component.css'],
})
export class ViewGuestbooksComponent implements OnInit {
  pagination: any = {
    pages: 0,
    currentPage: 1,
  };
  items: any[] = [];
  search: string = '';
  showSearch: boolean = true;

  constructor(private router: Router, private route: ActivatedRoute) {}

  ngOnInit(): void {
    this.fetchItems();
  }

  fetchItems() {
    const xhttp = new XMLHttpRequest();
    const that = this;
    xhttp.onreadystatechange = function () {
      if (this.readyState == 4 && this.status === 200) {
        const data = JSON.parse(this.responseText);
        const { items, pages } = data;

        that.pagination.pages = pages;
        that.pagination.currentPage =
          that.pagination.currentPage > pages
            ? pages
            : that.pagination.currentPage;

        that.items = items.map((x: any) => ({
          ...x,
          date: new Date(x.date),
        }));
      }
    };

    const urlParams = new URLSearchParams({
      search: this.search,
      currentPage: this.pagination.currentPage,
    });

    xhttp.open('GET', `${environment.baseURL}/Guestbooks?${urlParams}`);
    xhttp.setRequestHeader('x-userId', `${sessionStorage.getItem('userId')}`);
    xhttp.send();
  }

  nextPage() {
    if (this.pagination.currentPage === this.pagination.pages) return;
    this.pagination.currentPage++;
    this.fetchItems();
  }

  previousPage() {
    if (this.pagination.currentPage === 1) return;
    this.pagination.currentPage--;
    this.fetchItems();
  }

  handleDelete(id: number) {
    if (confirm(`Are you sure you want to delete record with id ${id}`)) {
      const xhttp = new XMLHttpRequest();
      const that = this;
      xhttp.onreadystatechange = function () {
        if (this.readyState == 4 && this.status === 200) {
          alert('Record deleted successfully!');
          if (that.pagination.currentPage > 1 && that.items.length === 1) {
            that.pagination.currentPage--;
          }
          that.fetchItems();
        }
      };
      xhttp.open('DELETE', `${environment.baseURL}/Guestbooks/${id}`);
      xhttp.setRequestHeader('x-userId', `${sessionStorage.getItem('userId')}`);
      xhttp.send();
    }
  }

  handleEdit(id: number) {
    this.router.navigate([`/edit/${id}`], {
      relativeTo: this.route,
    });
  }

  toggleSearch() {
    this.showSearch = !this.showSearch;
  }
  logout() {
    sessionStorage.removeItem('userId');
    this.router.navigate(['/'], { relativeTo: this.route });
  }
}
