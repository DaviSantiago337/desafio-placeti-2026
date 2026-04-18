package com.placeti.avaliacao.service;

import com.placeti.avaliacao.dto.CidadeDTO;
import com.placeti.avaliacao.dto.ComercioDTO;
import com.placeti.avaliacao.model.Cidade;
import com.placeti.avaliacao.model.Comercio;
import com.placeti.avaliacao.model.TipoComercio;
import com.placeti.avaliacao.repository.CidadeRepository;
import com.placeti.avaliacao.repository.ComercioRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class ProjetoServiceTest {

    @InjectMocks
    private ProjetoService projetoService;

    @Mock
    private CidadeRepository cidadeRepository;

    @Mock
    private ComercioRepository comercioRepository;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    // Testes para Cidade

    @Test
    public void testPesquisarCidadeExistente() {
        Cidade cidade = new Cidade();
        cidade.setId(1L);
        cidade.setNome("Florianópolis");
        cidade.setUf("SC");
        cidade.setCapital(true);

        when(cidadeRepository.findById(1L)).thenReturn(Optional.of(cidade));

        CidadeDTO result = projetoService.pesquisarCidade(1L);

        assertNotNull(result);
        assertEquals("Florianópolis", result.getNome());
        verify(cidadeRepository, times(1)).findById(1L);
    }

    @Test
    public void testPesquisarCidadeInexistente() {
        when(cidadeRepository.findById(1L)).thenReturn(Optional.empty());

        CidadeDTO result = projetoService.pesquisarCidade(1L);

        assertNull(result);
        verify(cidadeRepository, times(1)).findById(1L);
    }

    @Test
    public void testPesquisarTodasCidades() {
        Cidade cidade1 = new Cidade();
        cidade1.setId(1L);
        cidade1.setNome("Florianópolis");
        Cidade cidade2 = new Cidade();
        cidade2.setId(2L);
        cidade2.setNome("São Paulo");

        when(cidadeRepository.findAll()).thenReturn(Arrays.asList(cidade1, cidade2));

        List<CidadeDTO> result = projetoService.pesquisarCidades();

        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals("Florianópolis", result.get(0).getNome());
        assertEquals("São Paulo", result.get(1).getNome());
        verify(cidadeRepository, times(1)).findAll();
    }

    @Test
    public void testIncluirCidade() {
        CidadeDTO dto = new CidadeDTO();
        dto.setNome("Curitiba");
        dto.setUf("PR");
        dto.setCapital(false);

        Cidade cidade = new Cidade();
        cidade.setId(1L);
        cidade.setNome("Curitiba");
        cidade.setUf("PR");
        cidade.setCapital(false);

        when(cidadeRepository.save(any(Cidade.class))).thenReturn(cidade);

        CidadeDTO result = projetoService.incluirCidade(dto);

        assertNotNull(result);
        assertEquals(1L, result.getId());
        assertEquals("Curitiba", result.getNome());
        verify(cidadeRepository, times(1)).save(any(Cidade.class));
    }

    @Test
    public void testAlterarCidade() {
        CidadeDTO dto = new CidadeDTO();
        dto.setId(1L);
        dto.setNome("Florianópolis Atualizada");
        dto.setUf("SC");
        dto.setCapital(false);

        Cidade existingCidade = new Cidade();
        existingCidade.setId(1L);
        existingCidade.setNome("Florianópolis");
        existingCidade.setUf("SC");
        existingCidade.setCapital(true);

        when(cidadeRepository.findById(1L)).thenReturn(Optional.of(existingCidade));
        when(cidadeRepository.save(any(Cidade.class))).thenReturn(existingCidade);

        CidadeDTO result = projetoService.alterarCidade(dto);

        assertNotNull(result);
        assertEquals("Florianópolis Atualizada", result.getNome());
        assertFalse(result.getCapital());
        verify(cidadeRepository, times(1)).findById(1L);
        verify(cidadeRepository, times(1)).save(any(Cidade.class));
    }

    @Test
    public void testExcluirCidade() {
        doNothing().when(cidadeRepository).deleteById(1L);

        projetoService.excluirCidade(1L);

        verify(cidadeRepository, times(1)).deleteById(1L);
    }

    // Testes para Comercio

    @Test
    public void testPesquisarComercioExistente() {
        Cidade cidade = new Cidade();
        cidade.setId(1L);
        Comercio comercio = new Comercio();
        comercio.setId(1L);
        comercio.setNome("Farmácia Central");
        comercio.setTipo(TipoComercio.FARMACIA);
        comercio.setCidade(cidade);

        when(comercioRepository.findById(1L)).thenReturn(Optional.of(comercio));

        ComercioDTO result = projetoService.pesquisarComercio(1L);

        assertNotNull(result);
        assertEquals("Farmácia Central", result.getNome());
        assertEquals(TipoComercio.FARMACIA, result.getTipo());
        assertEquals(1L, result.getCidadeId());
        verify(comercioRepository, times(1)).findById(1L);
    }

    @Test
    public void testPesquisarComercioInexistente() {
        when(comercioRepository.findById(1L)).thenReturn(Optional.empty());

        ComercioDTO result = projetoService.pesquisarComercio(1L);

        assertNull(result);
        verify(comercioRepository, times(1)).findById(1L);
    }

    @Test
    public void testPesquisarTodosComercios() {
        Cidade cidade = new Cidade();
        cidade.setId(1L);

        Comercio comercio1 = new Comercio();
        comercio1.setId(1L);
        comercio1.setNome("Farmácia Central");
        comercio1.setCidade(cidade);

        Comercio comercio2 = new Comercio();
        comercio2.setId(2L);
        comercio2.setNome("Padaria do Zé");
        comercio2.setCidade(cidade);

        when(comercioRepository.findAll()).thenReturn(Arrays.asList(comercio1, comercio2));

        List<ComercioDTO> result = projetoService.pesquisarComercios();

        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals("Farmácia Central", result.get(0).getNome());
        assertEquals("Padaria do Zé", result.get(1).getNome());
        verify(comercioRepository, times(1)).findAll();
    }

    @Test
    public void testIncluirComercio() {
        Cidade cidade = new Cidade();
        cidade.setId(1L);

        ComercioDTO dto = new ComercioDTO();
        dto.setNome("Posto Ipiranga");
        dto.setResponsavel("João");
        dto.setTipo(TipoComercio.POSTO_GASOLINA);
        dto.setCidadeId(1L);

        Comercio comercio = new Comercio();
        comercio.setId(1L);
        comercio.setNome("Posto Ipiranga");
        comercio.setResponsavel("João");
        comercio.setTipo(TipoComercio.POSTO_GASOLINA);
        comercio.setCidade(cidade);

        when(cidadeRepository.findById(1L)).thenReturn(Optional.of(cidade));
        when(comercioRepository.save(any(Comercio.class))).thenReturn(comercio);

        ComercioDTO result = projetoService.incluirComercio(dto);

        assertNotNull(result);
        assertEquals(1L, result.getId());
        assertEquals("Posto Ipiranga", result.getNome());
        verify(comercioRepository, times(1)).save(any(Comercio.class));
        verify(cidadeRepository, times(1)).findById(1L);
    }

    @Test
    public void testAlterarComercio() {
        Cidade cidade = new Cidade();
        cidade.setId(1L);

        ComercioDTO dto = new ComercioDTO();
        dto.setId(1L);
        dto.setNome("Posto Shell");
        dto.setResponsavel("Maria");
        dto.setTipo(TipoComercio.POSTO_GASOLINA);
        dto.setCidadeId(1L);

        Comercio existingComercio = new Comercio();
        existingComercio.setId(1L);
        existingComercio.setNome("Posto Ipiranga");
        existingComercio.setResponsavel("João");
        existingComercio.setTipo(TipoComercio.POSTO_GASOLINA);
        existingComercio.setCidade(cidade);

        when(comercioRepository.findById(1L)).thenReturn(Optional.of(existingComercio));
        when(cidadeRepository.findById(1L)).thenReturn(Optional.of(cidade));
        when(comercioRepository.save(any(Comercio.class))).thenReturn(existingComercio);

        ComercioDTO result = projetoService.alterarComercio(dto);

        assertNotNull(result);
        assertEquals("Posto Shell", result.getNome());
        assertEquals("Maria", result.getResponsavel());
        verify(comercioRepository, times(1)).findById(1L);
        verify(cidadeRepository, times(1)).findById(1L);
        verify(comercioRepository, times(1)).save(any(Comercio.class));
    }

    @Test
    public void testExcluirComercio() {
        doNothing().when(comercioRepository).deleteById(1L);

        projetoService.excluirComercio(1L);

        verify(comercioRepository, times(1)).deleteById(1L);
    }
}
