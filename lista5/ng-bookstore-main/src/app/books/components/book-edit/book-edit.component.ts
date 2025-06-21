import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators, ReactiveFormsModule } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Book } from '../../model/book';
import { CommonModule } from '@angular/common';
import { Router } from '@angular/router'; 
import { BooksService } from '../../services/books.service'; 

@Component({
  selector: 'bs-book-edit',
  standalone: true,
  imports: [CommonModule, ReactiveFormsModule],
  templateUrl: './book-edit.component.html',
  styleUrls: ['./book-edit.component.scss']
})
export class BookEditComponent implements OnInit {
  book!: Book;
  form!: FormGroup;

  constructor(
    private route: ActivatedRoute,
    private fb: FormBuilder,
    private router: Router,
    private booksService: BooksService
  ) {}
  //2e
  onSave(): void {
    const updatedBook = {
      ...this.book,
      ...this.form.value
    };
  
    this.booksService.saveBook(updatedBook).subscribe(() => {
      this.router.navigate(['/books']);
    });
  }
  //2f
  onCancel(): void {
    this.router.navigate(['/books']);
  }

  ngOnInit(): void {
    this.book = this.route.snapshot.data['book'];

    this.form = this.fb.group({ //2(c) walidacja formularza
      title: [this.book.title, [Validators.required, Validators.maxLength(50)]],
      author: [this.book.author, [Validators.required, Validators.maxLength(50)]],
      year: [this.book.year, [Validators.required, Validators.min(1000), Validators.max(2023)]],
      description: [this.book.description || '', [Validators.maxLength(1000)]]
    });
  }
}
