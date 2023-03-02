import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { tap } from 'rxjs/operators';
import { Router } from '@angular/router';

@Injectable({
  providedIn: 'root',
})
export class PageService {
  url: string = 'localhost:7777';

  constructor(private httpClient: HttpClient) {}

  submit(
    newBranch: string,
    baseBranch: string,
    path: string,
    user: string,
    credentials: string
  ): Observable<HttpResponse<any>> {
    return this.httpClient.post(
      'http://' + this.url + '/git/createBranch',
      { newBranch, baseBranch, path, user, credentials },
      { observe: 'response', responseType: 'text' }
    );
  }

  submitCherry(originBranch: string,baseBranch: string,path: string): Observable<HttpResponse<any>> {
    return this.httpClient.post('http://' + this.url + '/git/createCherryPick'
    ,{ originBranch, baseBranch, path }
    ,{ observe: 'response', responseType: 'text' }
    );
  }
}
