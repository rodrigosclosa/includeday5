import { Component } from '@angular/core';
import { IonicPage, NavController, NavParams } from 'ionic-angular';
import { LoginPage } from '../login/login';
import { CriarUsuarioPage } from '../criar-usuario/criar-usuario';

@IonicPage()
@Component({
  selector: 'page-init',
  templateUrl: 'init.html',
})
export class InitPage {

  constructor(public navCtrl: NavController, public navParams: NavParams) {
  }

  public login() {
    this.navCtrl.push(LoginPage);
  }

  public criarUsuario() {
    this.navCtrl.push(CriarUsuarioPage);
  }

}
