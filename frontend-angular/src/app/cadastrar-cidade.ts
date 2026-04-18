import { Component, EventEmitter, Input, Output } from '@angular/core';
import { ImportsModule } from './imports';
import { Cidade } from '@domain/cidade';
import { ProjetoService } from '@service/projeto-service';
import { MessageService } from 'primeng/api';

@Component({
    selector: 'cadastrar-cidade',
    templateUrl: 'cadastrar-cidade.html',
    standalone: true,
    imports: [ImportsModule],
    providers: [ProjetoService, MessageService]
})
export class CadastrarCidade {

    @Input() public cidade: Cidade = new Cidade();

    @Output('onClose') private eventoFechaJanela = new EventEmitter<boolean>();

    constructor(private service: ProjetoService, private messageService: MessageService) {}

    public salvar(): void {
        if (!this.cidade.nome || !this.cidade.uf) {
            this.messageService.add({ severity: 'warn', summary: 'Aviso', detail: 'Preencha todos os campos obrigatórios!' });
            return;
        }

        this.service.salvar(this.cidade).subscribe(
            (cidadeSalva: Cidade) => {
                this.messageService.add({ severity: 'success', summary: 'Sucesso', detail: `Cidade '${cidadeSalva.nome}' salva com sucesso!` });
                this.eventoFechaJanela.emit(true);
            },
            (error) => {
                this.messageService.add({ severity: 'error', summary: 'Erro', detail: 'Erro ao salvar a cidade!' });
                console.error('Erro ao salvar a cidade:', error);
            }
        );
    }

    public cancelar(): void {
        this.eventoFechaJanela.emit(false) ;
    }

    public reloadPage(): void {
      setTimeout(() => window.location.reload(), 100);
    }

}
