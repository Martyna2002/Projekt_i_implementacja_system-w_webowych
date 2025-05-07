import { Component, Input } from '@angular/core';
import { CommonModule } from '@angular/common';
import { Review } from '../../model/review';

@Component({
  selector: 'bs-review-item',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './review-item.component.html',
  styleUrls: ['./review-item.component.scss']
})
export class ReviewItemComponent {
  @Input() review!: Review;
}
