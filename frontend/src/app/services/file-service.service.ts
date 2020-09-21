import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class FileServiceService {

  private BASE_URL = 'http://localhost:8080/files';

  constructor(private httpClient: HttpClient) { }

  downloadFile(fileId: number) {
    return this.httpClient.get(`${this.BASE_URL}/messageFile/download/${fileId}`).subscribe();
  }
}
