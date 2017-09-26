import { Component } from '@angular/core';
import { IonicPage, NavController, App, LoadingController, Loading, ToastController } from 'ionic-angular';
import { Http, Headers, RequestOptions } from '@angular/http';
import { GlobalVars } from '../../providers/global-vars';
import { UserData } from '../../providers/user-data';

import 'rxjs/add/operator/map';
import 'rxjs/add/operator/catch';

import { InitPage } from '../init/init';
import { AgendamentoPage } from '../agendamento/agendamento';

@IonicPage()
@Component({
  selector: 'page-locais',
  templateUrl: 'locais.html'
})
export class LocaisPage {

  public usuarioId: any;
  public usuarioNome: any;
  public locais: Array<any>;
  loading: Loading;

  constructor(
    public navCtrl: NavController,
    public appCtrl: App,
    public loadingCtrl: LoadingController,
    public http: Http,
    private toastController: ToastController,
    private globalVars: GlobalVars,
    private userData: UserData) {
  }

  private getLocais() {
    this.showLoading();

    let headers = new Headers();
    headers.append('Content-Type', 'application/json');
    headers.append('numerogrupo', `${this.globalVars.numeroGrupo}`);

    let options = new RequestOptions({ headers: headers });

    let url = this.globalVars.apiUrl + "/estabelecimentos/v1/estabelecimentos";
    this.http.get(url, options)
      .map(res => res.json())
      .subscribe(data => {
        
        this.loading.dismiss();
      }, err => {
        this.showError("Não foi possível recuperar a lista de locais");
      });
  }

  public agendar(local) {
    this.navCtrl.push(AgendamentoPage, { local: local });
  }

  public sair() {
    this.userData.logout();
    this.appCtrl.getRootNav().setRoot(InitPage);
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
