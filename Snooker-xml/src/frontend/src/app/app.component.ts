import {Component} from '@angular/core';
import { environment } from './../environments/environment';

@Component({
  selector: 'app-root',
  templateUrl: 'app.component.html'
})
export class AppComponent {

  createBlogEntry(title: string, image: string, text: string) {
	  console.log(environment.production);
    console.log(title, image, text);
  }
  
}

