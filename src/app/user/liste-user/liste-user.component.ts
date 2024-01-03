import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { User } from 'src/app/model/user';

import { PDFDocument, StandardFonts, rgb } from 'pdf-lib';
import { UserService } from 'src/app/services/user.service';


@Component({
  selector: 'app-liste-user',
  templateUrl: './liste-user.component.html',
  styleUrls: ['./liste-user.component.scss']
})
export class ListeUserComponent implements OnInit {
  searchTerm: string = '';
  users: User[] = [];
  capaciteDisponible!: number;

  constructor(
    private userService: UserService,
    private router: Router,
  ) {}

  ngOnInit() {
    this.userService.getAllUsers().subscribe((data) => {
      this.users = data;
    });
  }

  get filteUsers() {
    return this.users ? this.users.filter(
      (user: User) =>
        user.idUser.toString().includes(this.searchTerm) ||
        user.numeroUser.toString().includes(this.searchTerm) ||
        user.nomUser.toLowerCase().includes(this.searchTerm.toLowerCase())
    ) : [];
  }

  deleteUser(user: User) {
    this.userService.deleteUser(user.idUser).subscribe(() => {
      this.users = this.users.filter((c: User) => c.idUser !== user.idUser);
    });
  }
  

  updateUser(id: any) {
    this.router.navigate(['/dashboard/user/updateUser', id]);
  }

  async generatePdfForUser(user: User) {
    const pdfDoc = await PDFDocument.create();
    const page = pdfDoc.addPage();
    const { width, height } = page.getSize();

    const titleFont = await pdfDoc.embedFont(StandardFonts.HelveticaBold);
    const textFont = await pdfDoc.embedFont(StandardFonts.Helvetica);

    const userText = `
    Nom: ${user.nomUser}
    Numéro: ${user.numeroUser}
    `;

    page.drawRectangle({
      x: 50,
      y: height - 160,
      width: width - 100,
      height: 120,
      color: rgb(0.95, 0.95, 0.95),
    });
    page.drawText(userText, {
      x: 60,
      y: height - 170,
      font: textFont,
      color: rgb(0, 0, 0),
      size: 14,
    });

    page.drawLine({
      start: { x: 50, y: height - 170 },
      end: { x: width - 50, y: height - 170 },
      thickness: 1,
      color: rgb(0, 0, 0),
    });

    const pdfBytes = await pdfDoc.save();
    const blob = new Blob([pdfBytes], { type: 'application/pdf' });

    const link = document.createElement('a');
    link.href = window.URL.createObjectURL(blob);
    link.download = `user_${user.idUser}.pdf`;
    link.click();
  }
}
