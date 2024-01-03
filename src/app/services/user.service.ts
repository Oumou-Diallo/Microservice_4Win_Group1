import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { User } from '../model/user';

@Injectable({
  providedIn: 'root'
})
export class UserService {
  private userUrl = "http://localhost:8089/users";

  private httpOptions = {
    headers: new HttpHeaders({
      'Content-Type': 'application/json'
    })
  };

  constructor(private http: HttpClient) { }

  getAllUsers(): Observable<User[]> {
    return this.http.get<User[]>(this.userUrl);
  }

  addUser(user: User): Observable<User> {
    return this.http.post<User>(this.userUrl + "/add", user, this.httpOptions);
  }

  getUserById(iduser: number): Observable<User> {
    const url = `${this.userUrl}/${iduser}`;
    return this.http.get<User>(url);
  }

  updateUser(iduser: number, user: User): Observable<User> {
    const url = `${this.userUrl}/${iduser}`;
    return this.http.put<User>(url, user, this.httpOptions);
  }

  deleteUser(iduser: number): Observable<User> {
    const url = `${this.userUrl}/${iduser}`;
    return this.http.delete<User>(url, this.httpOptions);
  }
}


