import { NgModule, ErrorHandler } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { IonicApp, IonicModule, IonicErrorHandler } from 'ionic-angular';
import { MyApp } from './app.component';
import { IonicStorageModule } from '@ionic/storage';
import { HttpModule } from '@angular/http';
import { Ionic2RatingModule } from 'ionic2-rating';
import { UserData } from '../providers/user-data';

import { InitPage } from '../pages/init/init';
import { TabsPage } from '../pages/tabs/tabs';
import { LocaisPage } from '../pages/locais/locais';
import { HistoricoPage } from '../pages/historico/historico';
import { AgendamentoPage } from '../pages/agendamento/agendamento';
import { LoginPage } from '../pages/login/login';
import { CriarUsuarioPage } from '../pages/criar-usuario/criar-usuario';
import { AvaliarPage } from '../pages/avaliar/avaliar';

import { StatusBar } from '@ionic-native/status-bar';
import { SplashScreen } from '@ionic-native/splash-screen';
import { GlobalVars } from '../providers/global-vars';

@NgModule({
  declarations: [
    MyApp,
    HistoricoPage,
    LocaisPage,
    AgendamentoPage,
    LoginPage,
    InitPage,
    TabsPage,
    CriarUsuarioPage,
    AvaliarPage
  ],
  imports: [
    BrowserModule,
    HttpModule,
    IonicModule.forRoot(MyApp),
    IonicStorageModule.forRoot(),
    Ionic2RatingModule
  ],
  bootstrap: [IonicApp],
  entryComponents: [
    MyApp,
    HistoricoPage,
    LocaisPage,
    AgendamentoPage,
    LoginPage,
    InitPage,
    TabsPage,
    CriarUsuarioPage,
    AvaliarPage
  ],
  providers: [
    StatusBar,
    SplashScreen,
    UserData,
    GlobalVars,
    {provide: ErrorHandler, useClass: IonicErrorHandler}
  ]
})
export class AppModule {}
