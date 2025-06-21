import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { HttpClientModule } from '@angular/common/http';
import { RouterOutlet } from '@angular/router';
import { RezerwacjaComponent } from './rezerwacja/rezerwacja.component';
import { SeanseComponent } from './components/seanse/seanse.component'; 


@Component({
  selector: 'app-root',
  standalone: true,
  imports: [CommonModule, SeanseComponent, RezerwacjaComponent, HttpClientModule, RouterOutlet,],
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css'],
})
export class AppComponent {
  wybranySeansId: number | null = null;

  ustawSeans(id: number) {
    this.wybranySeansId = id;
  }

  wrocDoRepertuaru() {
    this.wybranySeansId = null;
  }
}

