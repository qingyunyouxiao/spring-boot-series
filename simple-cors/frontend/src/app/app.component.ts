import { Component, Injectable } from '@angular/core';
import { RouterOutlet } from '@angular/router';
import { HttpClient } from '@angular/common/http';
import { BackendContent } from './backend-content';

@Component({  
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrl: './app.component.css'
}) 

export class AppComponent {
  title: string = 'frontend'; 
  response: string = "empty";
    
  constructor(private http: HttpClient) {}
  ngOnInit(): void {
    this.http.get<BackendContent>('http://localhost:8080/greeting').subscribe(
      data => {
        this.response = data.content;
      }
    );
  }

}
