import { Component, OnInit } from '@angular/core';
import { Book } from '../../model/book';
import { ActivatedRoute, RouterLink } from '@angular/router';
import { debounceTime, distinctUntilChanged, switchMap } from 'rxjs/operators';
import { BooksService } from '../../services/books.service';
import { FormControl, ReactiveFormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';

@Component({
    selector: 'bs-book-list',
    templateUrl: './book-list.component.html',
    styleUrls: ['./book-list.component.scss'],
    standalone: true,
    imports: [RouterLink, CommonModule, ReactiveFormsModule]
})
export class BookListComponent {

  books: Book[] = [];
  searchControl = new FormControl('');

  constructor(
    private readonly activatedRoute: ActivatedRoute,
    private readonly booksService: BooksService
  ) {}

  ngOnInit(): void {
    // Startowe dane z resolvera
    this.books = this.activatedRoute.snapshot.data['books'];

    // Reagowanie na wpisywanie
    this.searchControl.valueChanges
      .pipe(
        debounceTime(200),
        distinctUntilChanged(),
        switchMap(query => {
          if (query && query.length >= 2) {
            return this.booksService.searchBooks(query);
          } else {
            return this.booksService.getAllBooks();
          }
        })
      )
      .subscribe(results => {
        this.books = results;
      });
  }
  
}
