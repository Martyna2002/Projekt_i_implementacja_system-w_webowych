import { Component, EventEmitter, Output } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormBuilder, FormGroup, Validators, ReactiveFormsModule } from '@angular/forms';

@Component({
  selector: 'bs-review-form',
  standalone: true,
  imports: [CommonModule, ReactiveFormsModule],
  templateUrl: './review-form.component.html',
  styleUrls: ['./review-form.component.scss']
})
export class ReviewFormComponent {
  @Output() reviewCreated = new EventEmitter<{ title: string; description: string; rate: number }>();
  // 3a fromularz
  form: FormGroup = this.fb.group({
    title: ['', [Validators.required]],
    description: ['', [Validators.required]],
    rate: [5, [Validators.required, Validators.min(1), Validators.max(5)]]
  });

  constructor(private fb: FormBuilder) {}

  onSubmit(): void {
    if (this.form.valid) {
      this.reviewCreated.emit(this.form.value);
      this.form.reset({ rate: 5 }); // resetujemy formularz, zostawiając domyślną ocenę
    }
  }
}
