import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Cidade } from '@domain/cidade';
import { Observable } from 'rxjs';
import { environment } from "../app/environments/environment";


@Injectable()
export class ProjetoService {

  private apiUrl = environment.apiUrl;

  constructor(private http: HttpClient) {}

  // Métodos para Cidade

  pesquisarCidades(): Observable<Cidade[]> {
    return this.http.get<Cidade[]>(`${this.apiUrl}/cidades`);
  }

  pesquisarCidade(id: number): Observable<Cidade> {
    return this.http.get<Cidade>(`${this.apiUrl}/cidades/${id}`);
  }

  incluirCidade(cidade: Cidade): Observable<Cidade> {
    return this.http.post<Cidade>(`${this.apiUrl}/cidades`, cidade);
  }

  alterarCidade(cidade: Cidade): Observable<Cidade> {
    return this.http.put<Cidade>(`${this.apiUrl}/cidades`, cidade);
  }

  excluir(cidade: Cidade): Observable<any> {
    return this.http.delete(`${this.apiUrl}/cidades/${cidade.id}`);
  }

  salvar(cidade: Cidade): Observable<any> {
    if (cidade.id) {
      return this.alterarCidade(cidade);
    } else {
      return this.incluirCidade(cidade);
    }
  }

  excluirCidade(id: number): Observable<any> {
    return this.http.delete(`${this.apiUrl}/cidades/${id}`);
  }

  // Métodos para Comercio

  pesquisarComercios(): Observable<any[]> {
    return this.http.get<any[]>(`${this.apiUrl}/comercios`);
  }

  pesquisarComercio(id: number): Observable<any> {
    return this.http.get<any>(`${this.apiUrl}/comercios/${id}`);
  }

  incluirComercio(comercio: any): Observable<any> {
    return this.http.post<any>(`${this.apiUrl}/comercios`, comercio);
  }

  alterarComercio(comercio: any): Observable<any> {
    return this.http.put<any>(`${this.apiUrl}/comercios`, comercio);
  }

  salvarComercio(comercio: any): Observable<any> {
    if (comercio.id) {
      return this.alterarComercio(comercio);
    } else {
      return this.incluirComercio(comercio);
    }
  }

  excluirComercio(id: number): Observable<any> {
    return this.http.delete(`${this.apiUrl}/comercios/${id}`);
  }
}
