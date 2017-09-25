import { Component } from '@angular/core';

import { LocaisPage } from '../locais/locais';
import { HistoricoPage } from '../historico/historico';

@Component({
  templateUrl: 'tabs.html'
})
export class TabsPage {

  tab1Root = LocaisPage;
  tab2Root = HistoricoPage;

  constructor() {

  }
}
