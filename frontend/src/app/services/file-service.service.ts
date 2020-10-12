import { HttpClient, HttpHeaders, HttpResponse } from '@angular/common/http';
import { Injectable } from '@angular/core';

const httpOptions = {
  headers: new HttpHeaders({
    'Accept': 'application/octet-stream'
  })
};

@Injectable({
  providedIn: 'root'
})
export class FileServiceService {

  private BASE_URL = 'http://localhost:8080/files';

  constructor(private httpClient: HttpClient) { }

  downloadMessageFile(fileId: number, fileName: string) {
    this.httpClient.get(`${this.BASE_URL}/message-file/download/${fileId}`, {
      responseType: 'blob'
    }).subscribe(blob => {
      const a = document.createElement('a')
      const objectUrl = URL.createObjectURL(blob)
      a.href = objectUrl;
      a.download = `${fileName}`;
      a.click();
      URL.revokeObjectURL(objectUrl);
    });
  }

  downloadHomeworkFile(fileId: number, fileName: string) {
    this.httpClient.get(`${this.BASE_URL}/homework-file/download/${fileId}`, {
      responseType: 'blob'
    }).subscribe(blob => {
      const a = document.createElement('a')
      const objectUrl = URL.createObjectURL(blob)
      a.href = objectUrl;
      a.download = `${fileName}`;
      a.click();
      URL.revokeObjectURL(objectUrl);
    });
  }

  downloadHomeworkAnswerFile(fileId: number, fileName: string) {
    this.httpClient.get(`${this.BASE_URL}/homework-answer/download/${fileId}`, {
      responseType: 'blob'
    }).subscribe(blob => {
      const a = document.createElement('a')
      const objectUrl = URL.createObjectURL(blob)
      a.href = objectUrl;
      a.download = `${fileName}`;
      a.click();
      URL.revokeObjectURL(objectUrl);
    });
  }

  downloadSubjectFile(fileId: number, fileName: string) {
    this.httpClient.get(`${this.BASE_URL}/subject/download/${fileId}`, {
      responseType: 'blob'
    }).subscribe(blob => {
      const a = document.createElement('a')
      const objectUrl = URL.createObjectURL(blob)
      a.href = objectUrl;
      a.download = `${fileName}`;
      a.click();
      URL.revokeObjectURL(objectUrl);
    });
  }

}
