import { Injectable } from '@angular/core';

import { Storage } from '@ionic/storage';


@Injectable()
export class UserData {
  ESTA_LOGADO = 'estaLogado';

  constructor(
    public storage: Storage
  ) { }

  login(id: string, nome: string, email: string): void {
    this.storage.set(this.ESTA_LOGADO, true);
    this.setId(id);
    this.setNome(name);
    this.setEmail(email);
  };

  logout(): void {
    this.storage.remove(this.ESTA_LOGADO);
    this.storage.remove('usuarioId');
    this.storage.remove('usuarioNome');
    this.storage.remove('usuarioEmail');
  };

  setId(id: string): void {
    this.storage.set('usuarioId', id);
  };

  setNome(nome: string): void {
    this.storage.set('usuarioNome', nome);
  };

  setEmail(email: string): void {
    this.storage.set('usuarioEmail', email);
  };

  getId(): Promise<string> {
    return this.storage.get('usuarioId').then((value) => {
      return value;
    });
  };

  getNome(): Promise<string> {
    return this.storage.get('usuarioNome').then((value) => {
      return value;
    });
  };
  
  getEmail(): Promise<string> {
    return this.storage.get('usuarioEmail').then((value) => {
      return value;
    });
  };

  estaLogado(): Promise<boolean> {
    return this.storage.get(this.ESTA_LOGADO).then((value) => {
      return value === true;
    });
  };
}