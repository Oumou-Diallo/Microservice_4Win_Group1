import { Component } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { UserService } from 'src/app/services/user.service';


@Component({
  selector: 'app-add-user',
  templateUrl: './add-user.component.html',
  styleUrls: ['./add-user.component.scss']
})
export class AddUserComponent {
  addUserForm!: FormGroup;

  constructor(private userService: UserService, private formBuilder: FormBuilder, private router: Router) {
    this.initializeForm();
  }

  private initializeForm() {
    this.addUserForm = this.formBuilder.group({
      nomUser: ['', Validators.required],
      numeroUser: [null, [Validators.required, Validators.min(1)]]
    });
  }

  onSubmit() {
    if (this.addUserForm.valid) {
      const user = this.addUserForm.value;
      this.userService.addUser(user).subscribe(
        () => {
          this.router.navigate(['/dashboard/user/listeUser']);
        },
        (error: any) => {
          console.error('An error occurred while adding the user:', error);
        }
      );
    }
  }
}