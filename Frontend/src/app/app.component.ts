import { Component } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent {
  title = 'Frontend';

  constructor(private router: Router) { }

  navegarParaHome() {
    this.router.navigate(['/']);
  }

  navegarParaAtor() {
    this.router.navigate(['/ator']);
  }

  navegarParaClasse() {
    this.router.navigate(['/classe']);
  }

  navegarParaDiretor() {
    this.router.navigate(['/diretor']);
  }

  navegarParaTitulo() {
    this.router.navigate(['/titulo']);
  }

  navegarParaItem() {
    this.router.navigate(['/item']);
  }
}

