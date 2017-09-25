import { Component } from '@angular/core';
import { Platform } from 'ionic-angular';
import { StatusBar } from '@ionic-native/status-bar';
import { SplashScreen } from '@ionic-native/splash-screen';
import { UserData } from '../providers/user-data';
import { TabsPage } from '../pages/tabs/tabs';
import { InitPage } from '../pages/init/init';

@Component({
  templateUrl: 'app.html'
})
export class MyApp {
  rootPage: any;

  constructor(
    platform: Platform,
    statusBar: StatusBar,
    splashScreen: SplashScreen,
    public userData: UserData) {
    let env = this;
    platform.ready().then(() => {
      
      statusBar.styleDefault();
      splashScreen.hide();

      this.userData.estaLogado().then((hasLoggedIn) => {
        if (hasLoggedIn) {
          env.rootPage = TabsPage;
        } else {
          env.rootPage = InitPage;
        }
      });
    });
  }
}
