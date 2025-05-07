import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Book } from '../model/book';


const booksApiPrefix = '/api/books';

@Injectable({
  providedIn: 'root'
})
export class BooksService {

  constructor(private readonly http: HttpClient) { }

  getAllBooks(): Observable<Book[]> {
    return this.http.get<Book[]>(booksApiPrefix);
  }

  // zad 1 (a)
  findBookById(id: number): Observable<Book> {
    return this.http.get<Book>(`${booksApiPrefix}/${id}`);
  }
  
  saveBook(book: Book): Observable<Book> {
    return this.http.put<Book>(`/api/books/${book.id}`, book);
  }

  searchBooks(query: string): Observable<Book[]> {
    return this.http.get<Book[]>(`/api/books?q=${query}`);
  }


   
}
