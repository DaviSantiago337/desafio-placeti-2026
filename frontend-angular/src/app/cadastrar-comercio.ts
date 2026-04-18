import { Component, EventEmitter, Input, Output, OnInit } from '@angular/core';
import { ImportsModule } from './imports';
import { ProjetoService } from '../service/projeto-service'; 
import { MessageService } from 'primeng/api';
import { CommonModule } from '@angular/common';
import { CommerceDTO } from '../domain/commerce'; 

@Component({
    selector: 'app-cadastrar-comercio', 
    standalone: true,
    templateUrl: 'cadastrar-comercio.html',
    imports: [ImportsModule, CommonModule],
    providers: [ProjetoService, MessageService]
})
export class CadastrarComercio implements OnInit {
    
    @Input() public comercio: CommerceDTO = { nome: '', responsavel: '', tipo: '', cidadeId: 0 }; 
    @Input() public mostrar: boolean = false; 
    
    @Output('onClose') private eventoFechaJanela = new EventEmitter<boolean>();

    listaCidades: any[] = [];
    
    tiposComercio = [
        { label: 'Farmacia', value: 'FARMACIA' }, 
        { label: 'Padaria', value: 'PADARIA' },
        { label: 'Posto de Gasolina', value: 'POSTO_GASOLINA' },
        { label: 'Lanchonete', value: 'LANCHONETE' }
    ];

    constructor(private service: ProjetoService, private messageService: MessageService) {}

    ngOnInit() {
        
        this.service.pesquisarCidades().subscribe(res => this.listaCidades = res);
    }

    public salvar(): void {
        
        if (!this.comercio.nome || !this.comercio.tipo || !this.comercio.cidadeId) {
            this.messageService.add({ 
                severity: 'warn', 
                summary: 'Atenção', 
                detail: 'Nome, Tipo e Cidade são obrigatórios!' 
            });
            return;
        }

        this.service.salvarComercio(this.comercio).subscribe({
            next: () => {
                this.messageService.add({ severity: 'success', summary: 'Sucesso', detail: 'Comércio salvo com sucesso!' });
                this.eventoFechaJanela.emit(true);
            },
            error: () => {
                this.messageService.add({ severity: 'error', summary: 'Erro', detail: 'Erro ao salvar comércio.' });
            }
        });
    }

    public cancelar(): void {
        this.eventoFechaJanela.emit(false); 
    }
}