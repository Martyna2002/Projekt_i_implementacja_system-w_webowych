import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import {Review} from '../model/review'

@Injectable({
  providedIn: 'root'
})
export class ReviewsService {
  constructor(private http: HttpClient) {}

  getReviewsForBook(bookId: number): Observable<Review[]> {
    return this.http.get<Review[]>(`/api/reviews?forBook=${bookId}`);
  }
  
  addReview(review: Review): Observable<Review> {
    return this.http.post<Review>('/api/reviews', review);
  }
}
