import { Component } from '@angular/core';
import { IonicPage, NavController, NavParams, LoadingController, Loading, ToastController } from 'ionic-angular';
import { Http, Headers, RequestOptions } from '@angular/http';
import { GlobalVars } from '../../providers/global-vars';
import { UserData } from '../../providers/user-data';

@IonicPage()
@Component({
  selector: 'page-agendamento',
  templateUrl: 'agendamento.html'
})
export class AgendamentoPage {

  local: any;
  data: any;
  hora: any;
  dataMin: any;
  dataMax: any;
  descricao: any;
  usuarioId: string;
  loading: Loading;

  constructor(
    public navCtrl: NavController,
    public navParams: NavParams,
    public loadingCtrl: LoadingController,
    public http: Http,
    private toastController: ToastController,
    private globalVars: GlobalVars,
    private userData: UserData) {
    this.local = navParams.get("local");
    this.setDateRange();
    this.getUsuarioLogado();
  }

  public confirmarAgendamento() {
    this.showLoading();

    let dataHora = this.formatarDataHora();
    let agendamento = { idEstabelecimento: this.local.id, idUsuario: this.usuarioId, dataHora: dataHora }
    console.log(agendamento);

    let headers = new Headers();
    headers.append('Content-Type', 'application/json');
    headers.append('numerogrupo', `${this.globalVars.numeroGrupo}`);
    let options = new RequestOptions({ headers: headers });

    let url = this.globalVars.apiUrl + "/agendamentos/v1/create";

    this.http.post(url, agendamento, options)
      .map(res => res.json())
      .subscribe(resposta => {
        this.loading.dismiss();
        this.presentToast("Agendamento criado com sucesso");
        this.navCtrl.pop();
        this.navCtrl.parent.select(1);

      }, err => {
        this.showError("Não foi possível criar agendamento");
      });
  }

  private formatarDataHora(){
    let horaSelecionada = this.hora.split(":");
    let valorHora = horaSelecionada[0];
    let valorMinutos = horaSelecionada[1];

    let dataSelecionada = new Date(this.data);
    let dataHora = new Date(Date.UTC(dataSelecionada.getFullYear(), dataSelecionada.getMonth(), dataSelecionada.getDate(),valorHora, valorMinutos)).toISOString();

    return dataHora;
  }

  public getUsuarioLogado() {
    this.userData.getId().then((id) => {
      this.usuarioId = id;
      console.log("Usuario Id " + this.usuarioId);
    });
  }

  public setDateRange() {
    var newDate = new Date();

    // convert to msec
    // subtract local time zone offset
    // get UTC time in msec
    var utc = newDate.getTime() - (newDate.getTimezoneOffset() * 60000);

    // create new Date object for different city
    // using supplied offset
    var currentDate = new Date(utc + (3600000 * (-3)));

    this.dataMin = currentDate.toISOString();
    this.dataMax = currentDate.getFullYear();
    this.data = this.dataMin;
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
