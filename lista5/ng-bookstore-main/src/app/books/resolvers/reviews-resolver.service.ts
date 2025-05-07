import { Injectable } from '@angular/core';
import { Resolve, ActivatedRouteSnapshot } from '@angular/router';
import { Observable } from 'rxjs';
import { ReviewsService } from '../services/reviews.service';
import {Review} from '../model/review'

@Injectable({
  providedIn: 'root'
})
export class ReviewsResolver implements Resolve<Review[]> {
  constructor(private reviewsService: ReviewsService) {}

  resolve(route: ActivatedRouteSnapshot): Observable<Review[]> {
    const bookId = Number(route.paramMap.get('bookId'));
    return this.reviewsService.getReviewsForBook(bookId);
  }
}
