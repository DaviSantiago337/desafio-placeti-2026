 import { Component, OnInit } from '@angular/core';
import { ProjetoService } from '../service/projeto-service';
import { MessageService } from 'primeng/api';
import { CommerceDTO } from '../domain/commerce';
import { CadastrarComercio } from './cadastrar-comercio';
import { ImportsModule } from './imports'; 
import { CommonModule } from '@angular/common';




@Component({
 selector: 'listar-comercios',
 templateUrl: 'listar-comercios.html',
 standalone: true,


  imports: [
   ImportsModule, 
   CommonModule, 
   CadastrarComercio              
  ], 
  
  providers: [ProjetoService, MessageService]
})

export class ListarComercios implements OnInit {
 
 
 listaComercios: CommerceDTO[] = [];


 comercioSelecionado: CommerceDTO = { nome: '', responsavel: '', tipo: '', cidadeId: 0 };
 mostraJanelaCadastro: boolean = false;

 constructor(private service: ProjetoService) {}

 ngOnInit(): void {
  this.carregarComercios();
 }

 carregarComercios() {
  this.service.pesquisarComercios().subscribe(dados => this.listaComercios = dados);
 }

 
 abreJanelaParaCadastrarNovoComercio() {
  this.comercioSelecionado = { nome: '', responsavel: '', tipo: '', cidadeId: 0 }; // Limpa os dados
  this.mostraJanelaCadastro = true; 
 }

 
 abreJanelaParaAlterarComercio(comercio: CommerceDTO) {
  this.comercioSelecionado = { ...comercio }; 
  this.mostraJanelaCadastro = true;
 }


 excluir(comercio: CommerceDTO) {
  if (comercio.id && confirm(`Deseja realmente excluir o comércio ${comercio.nome}?`)) {
   this.service.excluirComercio(comercio.id).subscribe(() => {
    this.carregarComercios(); 
   });
  }
}


 fechaJanelaCadastro(salvou: boolean) {
  this.mostraJanelaCadastro = false;
  if (salvou) {
   this.carregarComercios(); 
  }
 }
}