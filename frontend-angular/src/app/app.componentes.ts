import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ImportsModule } from './imports';
import { ListarCidades } from './listar-cidades';
import { ListarComercios } from './listar-comercios'; 

@Component({
    selector: 'app-root',
    standalone: true,
    imports: [CommonModule, ImportsModule, ListarCidades, ListarComercios],
    template: `
        <div class="layout-container">
            <!-- Barra de Navegação no Topo -->
            <div class="topbar">
                <div class="logo">
                    <h2 style="color: white; margin: 0;">PlaceTI - Desafio</h2>
                </div>
                <div class="menu">
                    <button pButton type="button" label="Cidades" icon="pi pi-map-marker" 
                            (click)="abaAtiva = 'cidades'" 
                            [class.p-button-text]="abaAtiva !== 'cidades'" class="p-button-secondary"></button>
                    
                    <button pButton type="button" label="Comércios" icon="pi pi-shopping-bag" 
                            (click)="abaAtiva = 'comercios'" 
                            [class.p-button-text]="abaAtiva !== 'comercios'" class="p-button-secondary"></button>
                </div>
            </div>

            <!-- Conteúdo Dinâmico -->
            <div class="content">
                <listar-cidades *ngIf="abaAtiva === 'cidades'"></listar-cidades>
                <listar-comercios *ngIf="abaAtiva === 'comercios'"></listar-comercios>
            </div>
        </div>

        <style>
            .topbar {
                background-color: #2c3e50;
                padding: 1rem 2rem;
                display: flex;
                justify-content: space-between;
                align-items: center;
                box-shadow: 0 2px 5px rgba(0,0,0,0.1);
            }
            .menu { display: flex; gap: 1rem; }
            .content { padding: 2rem; }
        </style>
    `
})
export class AppComponent {
    abaAtiva: 'cidades' | 'comercios' = 'cidades';
}
