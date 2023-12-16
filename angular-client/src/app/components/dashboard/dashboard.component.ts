import { Component, OnInit } from '@angular/core';
import {Task} from "../../model/task";
import {TaskService} from "../../services/task.service";
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {NotificationService} from "../../services/notification.service";

@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.css']
})
export class DashboardComponent implements OnInit {

  tasks: Array<Task> = [];

  editTaskForms: any = {};

  createTaskForm: FormGroup = this.fb.group({
    title: ['', Validators.minLength(1)],
    description: ['']
  });

  constructor(private taskService: TaskService, private notification: NotificationService, private fb: FormBuilder) { }

  ngOnInit(): void {
    this.taskService.getTasks().subscribe(data => {
      this.tasks = data;

      for (let task of this.tasks) {
        if (task.id !== null) {
          this.editTaskForms[task.id] = this.fb.group({
            title: [task.title, Validators.minLength(1)],
            description: [task.description]
          });
        }
      }
    });
  }

  onTaskCreate(e: Event) {
    const newTask = new Task();
    newTask.title = this.createTaskForm.get('title')?.value;
    newTask.description = this.createTaskForm.get('description')?.value;

    this.taskService.createTask(newTask).subscribe({
      next: () => {
        window.location.reload();
      },
      error: this.notification.defaultErrorHandler
    });
  }

  onTaskUpdate(e: Event, taskId: any) {
    const newTask = new Task();
    newTask.title = this.editTaskForms[taskId].get('title')?.value;
    newTask.description = this.editTaskForms[taskId].get('description')?.value;

    this.taskService.updateTask(taskId, newTask).subscribe({
      next: () => {
        window.location.reload();
      },
      error: this.notification.defaultErrorHandler
    });
  }

  onTaskDelete(e: Event, taskId: any) {
    e.preventDefault();
    this.taskService.deleteTask(taskId).subscribe({
      next: () => {
        window.location.reload();
      },
      error: this.notification.defaultErrorHandler
    });
  }

}
