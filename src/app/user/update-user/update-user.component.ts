import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { UserService } from 'src/app/services/user.service';



@Component({
  selector: 'app-update-user',
  templateUrl: './update-user.component.html',
  styleUrls: ['./update-user.component.scss']
})
export class UpdateUserComponent implements OnInit {
  constructor(private userService: UserService, private route: ActivatedRoute, private router: Router) {}
  userToUpdate!: any;

  ngOnInit() {
    this.route.paramMap.subscribe(params => {
      const idUser = Number(params.get('idUser'));

      this.userService.getUserById(idUser).subscribe(
        (        user: any) => {
          this.userToUpdate = user;
        },
        (        error: any) => console.log('Error fetching User:', error)
      );
    });
  }

  update() {
    this.userService.updateUser(this.userToUpdate.idUser, this.userToUpdate).subscribe(
      () => {
        console.log('User updated successfully');
      },
      (      error: any) => console.error('Error updating User:', error)
    );

    this.router.navigate(['/dashboard/user/listeUser']);
  }
}
