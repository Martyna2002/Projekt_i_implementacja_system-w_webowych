import { Component, EventEmitter, OnInit, Output } from '@angular/core';
import { CommonModule } from '@angular/common';
import { SeansService, Seans } from '../../services/seans.service';

interface SeanseDlaFilmu {
  film: Seans['film'];
  godziny: { godzina: string, id: number }[];
}

@Component({
  selector: 'app-seanse',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './seanse.component.html',
  styleUrls: ['./seanse.component.css']
})
export class SeanseComponent implements OnInit {
  seanse: Seans[] = [];
  filteredSeanse: SeanseDlaFilmu[] = [];
  daty: string[] = [];
  wybranaData: string = '';

  @Output() wybranoSeans = new EventEmitter<number>();

  constructor(private seansService: SeansService) {}

  ngOnInit(): void {
    const today = new Date();
    this.daty = Array.from({ length: 14 }, (_, i) => {
      const d = new Date(today);
      d.setDate(d.getDate() + i);
      return d.toISOString().split('T')[0];
    });
    this.wybranaData = this.daty[0];

    this.seansService.getSeanse().subscribe(data => {
      this.seanse = data;
      this.filtrujPoDacie();
    });
  }

  filtrujPoDacie(): void {
    const dzienSeanse = this.seanse.filter(s =>
      s.dataCzas.startsWith(this.wybranaData)
    );

    const mapa = new Map<number, SeanseDlaFilmu>();

    for (const seans of dzienSeanse) {
      const godzina = new Date(seans.dataCzas).toLocaleTimeString([], {
        hour: '2-digit',
        minute: '2-digit'
      });

      if (!mapa.has(seans.film.id)) {
        mapa.set(seans.film.id, {
          film: seans.film,
          godziny: [{ godzina, id: seans.id }]
        });
      } else {
        mapa.get(seans.film.id)!.godziny.push({ godzina, id: seans.id });
      }
    }

    this.filteredSeanse = Array.from(mapa.values());
  }

  wybierzDate(data: string): void {
    this.wybranaData = data;
    this.filtrujPoDacie();
  }

  kliknijGodzine(seansId: number): void {
    this.wybranoSeans.emit(seansId);
  }
}
