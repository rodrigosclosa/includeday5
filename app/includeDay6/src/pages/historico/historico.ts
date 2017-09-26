import { Component } from '@angular/core';
import { IonicPage, NavController, App, LoadingController, Loading, ToastController } from 'ionic-angular';
import { Http, Headers, RequestOptions } from '@angular/http';
import { GlobalVars } from '../../providers/global-vars';
import { UserData } from '../../providers/user-data';
import { AvaliarPage } from '../avaliar/avaliar';

import 'rxjs/add/operator/map';
import 'rxjs/add/operator/catch';


@IonicPage()
@Component({
  selector: 'page-historico',
  templateUrl: 'historico.html',
})
export class HistoricoPage {

  public agendamentos: Array<any>;
  usuarioId: string;
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

  ionViewWillEnter() {
    this.getUsuarioLogado();
  }

  private getHistorico() {
    this.showLoading();

    let headers = new Headers();
    headers.append('Content-Type', 'application/json');
    headers.append('numerogrupo', `${this.globalVars.numeroGrupo}`);

    let options = new RequestOptions({ headers: headers });

    let url = this.globalVars.apiUrl + "/agendamentos/v1/agendamentos/" + this.usuarioId;
  }

  private getUsuarioLogado() {
    this.userData.getId().then((id) => {
      this.usuarioId = id;
      this.getHistorico();
    });
  }

  public formatarData(dataAgendamento): string {
    let data = new Date(dataAgendamento);
    return data.toLocaleString("pt-BR");
  }

  public avaliar(agendamento) {
    this.navCtrl.push(AvaliarPage, { agendamento: agendamento });
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
