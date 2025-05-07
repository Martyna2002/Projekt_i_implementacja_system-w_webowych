import { Routes } from '@angular/router';
import { BookListComponent } from "./books/components/book-list/book-list.component";
import { bookListResolver } from "./books/resolvers/book-list.resolver";
import { BookDetailsComponent } from "./books/components/book-details/book-details.component";
import { BookResolver } from "./books/resolvers/book.service"; 
import { BookEditComponent } from './books/components/book-edit/book-edit.component';
import { ReviewsResolver } from './books/resolvers/reviews-resolver.service';




export const routes: Routes = [
  {
    path: '',
    pathMatch: 'full',
    redirectTo: '/books'
  },
  {
    path: 'books',
    component: BookListComponent,
    resolve: {
      books: bookListResolver
    }
  },
  
  {
    path: 'books/:bookId/edit',
    component: BookEditComponent,
    resolve: {
      book: BookResolver
    }
  },

  {
    path: 'books/:bookId/reviews',
    component: BookDetailsComponent,
    resolve: {
      book: BookResolver,
      reviews: ReviewsResolver
    }
  }  
];
