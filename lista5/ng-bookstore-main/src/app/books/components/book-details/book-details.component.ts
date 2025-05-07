import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Book } from '../../model/book';
import { CommonModule } from '@angular/common';
import { RouterModule } from '@angular/router';
import { Review } from '../../model/review';
import { ReviewItemComponent } from '../review-item/review-item.component'; 
import { ReviewFormComponent } from '../review-form/review-form.component';
import { ReviewsService } from '../../services/reviews.service';


@Component({
  selector: 'bs-book-details',
  standalone: true,
  imports: [CommonModule, RouterModule, ReviewItemComponent, ReviewFormComponent], 
  templateUrl: './book-details.component.html',
  styleUrls: ['./book-details.component.scss']
})
export class BookDetailsComponent implements OnInit {
  book!: Book;
  reviews: Review[] = [];

  constructor(private route: ActivatedRoute, private reviewsService: ReviewsService) {}

  ngOnInit(): void {
    this.book = this.route.snapshot.data['book'];
    this.reviews = this.route.snapshot.data['reviews'];
    console.log('LOADED REVIEWS:', this.reviews);
  }

  onReviewCreated(review: Omit<Review, 'id' | 'forBook'>): void {
    const newReview: Review = {
      ...review,
      forBook: this.book.id,
      id: 0 
    };
  
    this.reviewsService.addReview(newReview).subscribe(saved => {
      this.reviews.push(saved); 
    });
  }
  
}
