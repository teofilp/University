import { Component, OnInit } from '@angular/core';
import api from 'src/api';
import { Player } from 'src/models/Player';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.scss'],
})
export class HomeComponent implements OnInit {
  public players: Player[] = [];
  public degreePlayers: Player[] = [];
  public searchKey: string = '';
  public id: any = null;
  public check: any = '';

  constructor() {}

  ngOnInit(): void {}

  public handleChange(event: any) {
    this.debounce(() => {
      const params = new URLSearchParams({ searchKey: event.target.value });
      api
        .get<Player[]>(`Players.php?${params}`)
        .then(({ data }) => (this.players = data));
    });
  }

  public debounce(cb: any) {
    clearInterval(this.id);

    this.id = setTimeout(cb, 250);
  }

  public getSortedPlayers() {
    return this.players.sort(
      (a: Player, b: Player) => -a.Name.localeCompare(b.Name)
    );
  }

  public getDegree(degree: any) {
    var params = new URLSearchParams({
      name: sessionStorage.getItem('player')!,
      degree: degree,
      samePosition: this.check,
    });
    return api.get<Player[]>(`Players.php?${params}`).then(({ data }) => {
      this.degreePlayers = data;
    });
  }
}
