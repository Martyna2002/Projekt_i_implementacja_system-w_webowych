import { Component, Input, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { HttpClient } from '@angular/common/http';

@Component({
  selector: 'app-rezerwacja',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './rezerwacja.component.html',
  styleUrls: ['./rezerwacja.component.css']
})
export class RezerwacjaComponent implements OnInit {
  @Input() seansId!: number;
  miejsca: any[] = [];
  kodRezerwacji: string | null = null;

  constructor(private http: HttpClient) {}

  ngOnInit(): void {
    // zakładamy że sala ma 30 miejsc
    this.miejsca = Array.from({ length: 30 }, (_, i) => ({
      numer: i + 1,
      zajete: false,
      wybrane: false
    }));

    if (this.seansId) {
      this.http.get<string[]>(`http://localhost:8080/api/rezerwacje/${this.seansId}`).subscribe(zajete => {
        this.miejsca.forEach(m => {
          if (zajete.includes(m.numer.toString())) {
            m.zajete = true;
          }
        });
      });
    }
  }

  toggleMiejsce(miejsce: any) {
    if (!miejsce.zajete) {
      miejsce.wybrane = !miejsce.wybrane;
    }
  }

  odswiezZajete() {
    this.http.get<string[]>(`http://localhost:8080/api/rezerwacje/${this.seansId}`).subscribe(zajete => {
      this.miejsca.forEach(m => {
        m.zajete = zajete.includes(m.numer.toString());
        if (m.zajete) m.wybrane = false;
      });
    });
  }


  zarezerwuj() {
    const wybrane = this.miejsca.filter(m => m.wybrane).map(m => m.numer.toString());

    this.http.post('http://localhost:8080/api/rezerwacje', {
      seansId: this.seansId,
      miejsca: wybrane
    }, { responseType: 'text' }).subscribe({
      next: (kod: string) => {
        this.kodRezerwacji = kod;
        this.odswiezZajete(); // odśwież miejsca po rezerwacji
      },
      error: err => {
        if (err.status === 409) {
          alert('❌ Wybrane miejsca są już zajęte. Odśwież widok.');
        } else {
          alert('⚠️ Wystąpił błąd podczas rezerwacji.');
        }
      }
    });
  }


}
