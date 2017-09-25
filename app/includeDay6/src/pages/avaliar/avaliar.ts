import { Component } from '@angular/core';
import { IonicPage, NavController, NavParams, LoadingController, Loading, ToastController } from 'ionic-angular';
import { Http, Headers, RequestOptions } from '@angular/http';
import { GlobalVars } from '../../providers/global-vars';

@IonicPage()
@Component({
  selector: 'page-avaliar',
  templateUrl: 'avaliar.html',
})
export class AvaliarPage {
  agendamento: any;
  nota: any;
  loading: Loading;

  constructor(
    public navCtrl: NavController,
    public navParams: NavParams,
    public loadingCtrl: LoadingController,
    public http: Http,
    private toastController: ToastController,
    private globalVars: GlobalVars) {
    this.agendamento = navParams.get("agendamento");
  }

  public confirmarAvaliacao() {
    this.showLoading();

    let headers = new Headers();
    headers.append('Content-Type', 'application/json');
    headers.append('numerogrupo', `${this.globalVars.numeroGrupo}`);
    let options = new RequestOptions({ headers: headers });

    let url = this.globalVars.apiUrl + "/agendamentos/v1/avaliacao?idAgendamento=" + this.agendamento.id + "&notaAvaliacao=" + this.nota;

    this.http.put(url, {}, options)
      .map(res => res.json())
      .subscribe(resposta => {
        this.loading.dismiss();
        this.presentToast("Avaliação registrada com sucesso");
        this.navCtrl.pop();
        this.navCtrl.parent.select(1);

      }, err => {
        this.showError("Não foi possível registrar avaliação");
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
