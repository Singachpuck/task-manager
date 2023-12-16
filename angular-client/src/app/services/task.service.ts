import { Injectable } from '@angular/core';
import {Observable} from "rxjs";
import {Task} from "../model/task";
import {HttpClient} from "@angular/common/http";
import {API_ENDPOINT} from "./util.service";
import {TokenStorageService} from "./token-storage.service";

@Injectable({
  providedIn: 'root'
})
export class TaskService {

  constructor(private http: HttpClient, private tokenService: TokenStorageService) { }

  getTasks(): Observable<Array<Task>> {
    const userId = this.tokenService.getUserId();
    return this.http.get<Array<Task>>(API_ENDPOINT + 'tasks/users/' + userId);
  }

  createTask(newTask: Task) {
    return this.http.post(API_ENDPOINT + 'tasks', newTask.toJson());
  }

  updateTask(taskId: any, newTask: Task): Observable<Task> {
    return this.http.put<Task>(API_ENDPOINT + 'tasks/' + taskId, newTask.toJson());
  }

  deleteTask(taskId: any) {
    return this.http.delete(API_ENDPOINT + 'tasks/' + taskId);
  }
}
