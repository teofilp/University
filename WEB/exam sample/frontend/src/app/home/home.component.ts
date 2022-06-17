import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import api from 'src/api';
import { Developer } from 'src/models/developer';
import { Project } from 'src/models/project';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.scss'],
})
export class HomeComponent implements OnInit {
  public projects: Project[] = [];
  public myProjects: Project[] = [];
  public assignedProjects: string = '';
  public developerName: string = '';
  public devs: Developer[] = [];
  public searchKey: string = '';

  constructor(private router: Router, private route: ActivatedRoute) {}

  ngOnInit(): void {
    if (!sessionStorage.getItem('username')) {
      this.router.navigate(['register'], { relativeTo: this.route });
      return;
    }

    this.fetchData();
  }

  public fetchData() {
    api
      .get<Project[]>(
        `/Projects/getByUsername/${sessionStorage.getItem('username')}`
      )
      .then(({ data }) => (this.myProjects = data));
    api.get<Project[]>('/Projects').then(({ data }) => (this.projects = data));
    api.get<Developer[]>('/Developers').then(({ data }) => (this.devs = data));
  }

  public assign() {
    if (!this.developerName) return;
    if (!this.assignedProjects) return;

    var assignedProjects = this.assignedProjects
      .split(',')
      .map((x) => x.trim());

    api
      .post('Projects/assign', {
        username: this.developerName,
        projects: assignedProjects,
      })
      .then(() => this.fetchData());
  }

  public filteredDevs() {
    if (!this.searchKey) {
      return this.devs;
    }

    return this.devs.filter((x) =>
      x.skills.toLowerCase().includes(this.searchKey.toLowerCase())
    );
  }
}
