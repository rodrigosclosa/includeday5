import { Component } from '@angular/core';
import { IonicPage, NavController, LoadingController, Loading, ToastController } from 'ionic-angular';
import { Http, Headers, RequestOptions } from '@angular/http';
import { GlobalVars } from '../../providers/global-vars';
import { UserData } from '../../providers/user-data';

import { TabsPage } from '../tabs/tabs';

@IonicPage()
@Component({
  selector: 'page-criar-usuario',
  templateUrl: 'criar-usuario.html',
})
export class CriarUsuarioPage {

  loading: Loading;
  novoUsuario = { nome: '', email: '', senha: '', confirmarSenha: '' };

  constructor(
    public navCtrl: NavController, 
    public loadingCtrl: LoadingController,
    public http: Http,
    private toastController: ToastController,
    private globalVars: GlobalVars,
    private userData: UserData) {
  }

  public criarUsuario() {
    this.showLoading();

    let data = JSON.stringify(this.novoUsuario);
    let headers = new Headers();
    headers.append('Content-Type', 'application/json');
    headers.append('numerogrupo', `${this.globalVars.numeroGrupo}`);
    let options = new RequestOptions({ headers: headers });

    let url = this.globalVars.apiUrl + "/usuarios/v1/create";

    this.http.post(url, data, options)
      .map(res => res.json())
      .subscribe(data => {
        this.loading.dismiss();
        this.userData.login(data.id, data.nome, data.email);
        this.navCtrl.push(TabsPage);
      }, err => {
        this.showError("Não foi possível criar usuário");
      });
  }

  showLoading() {
    this.loading = this.loadingCtrl.create({
      content: 'Carregando...'
    });
    this.loading.present();
  }

  showError(message) {
    setTimeout(() => {
      this.loading.dismiss();
    });

    this.presentToast(message);
  }

  presentToast(message) {

    let toast = this.toastController.create({
      message: message.toUpperCase(),
      duration: 4000,
      position: 'top',
    });
    toast.present();
  }

}
