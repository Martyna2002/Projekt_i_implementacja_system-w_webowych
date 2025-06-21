import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

export interface Seans {
  id: number;
  dataCzas: string;
  film: {
    id: number;
    tytul: string;
    opis: string;
    zdjecieUrl: string;
    czasTrwania: number;
  };
  sala: {
    id: number;
    nazwa: string;
  };
}

@Injectable({
  providedIn: 'root'
})
export class SeansService {

  private baseUrl = 'http://localhost:8080/api/seanse';

  constructor(private http: HttpClient) {}

  getSeanse(): Observable<Seans[]> {
    return this.http.get<Seans[]>(this.baseUrl);
  }
}


