package com.placeti.avaliacao.service;
import com.placeti.avaliacao.dto.CidadeDTO;
import com.placeti.avaliacao.dto.ComercioDTO;
import com.placeti.avaliacao.model.Cidade;
import com.placeti.avaliacao.model.Comercio;
import com.placeti.avaliacao.repository.CidadeRepository;
import com.placeti.avaliacao.repository.ComercioRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProjetoService {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private CidadeRepository cidadeRepository;

    @Autowired
    private ComercioRepository comercioRepository;


    @Transactional(readOnly = true)
    public CidadeDTO pesquisarCidade(Long id) {
        Optional<Cidade> cidade = cidadeRepository.findById(id);
        return cidade.map(this::convertToCidadeDTO).orElse(null);
    }

    @Transactional(readOnly = true)
    public List<CidadeDTO> pesquisarCidades() {
        return cidadeRepository.findAll().stream()
                .map(this::convertToCidadeDTO)
                .collect(Collectors.toList());
    }

    @Transactional
    public CidadeDTO incluirCidade(CidadeDTO dto) {
        Cidade cidade = convertToCidadeEntity(dto);
        cidade = cidadeRepository.save(cidade);
        return convertToCidadeDTO(cidade);
    }

    @Transactional
    public CidadeDTO alterarCidade(CidadeDTO dto) {
        Optional<Cidade> existingCidade = cidadeRepository.findById(dto.getId());
        if (existingCidade.isPresent()) {
            Cidade cidade = existingCidade.get();
            cidade.setNome(dto.getNome());
            cidade.setUf(dto.getUf());
            cidade.setCapital(dto.getCapital());
            cidade = cidadeRepository.save(cidade);
            return convertToCidadeDTO(cidade);
        }
        return null; 
    }

    @Transactional
    public void excluirCidade(Long idCidade) {
        cidadeRepository.deleteById(idCidade);
    }



    @Transactional(readOnly = true)
    public ComercioDTO pesquisarComercio(Long id) {
        Optional<Comercio> comercio = comercioRepository.findById(id);
        return comercio.map(this::convertToComercioDTO).orElse(null);
    }

    @Transactional(readOnly = true)
    public List<ComercioDTO> pesquisarComercios() {
        return comercioRepository.findAll().stream()
                .map(this::convertToComercioDTO)
                .collect(Collectors.toList());
    }

    @Transactional
    public ComercioDTO incluirComercio(ComercioDTO dto) {
        Comercio comercio = convertToComercioEntity(dto);
        comercio = comercioRepository.save(comercio);
        return convertToComercioDTO(comercio);
    }

    @Transactional
    public ComercioDTO alterarComercio(ComercioDTO dto) {
        Optional<Comercio> existingComercio = comercioRepository.findById(dto.getId());
        if (existingComercio.isPresent()) {
            Comercio comercio = existingComercio.get();
            comercio.setNome(dto.getNome());
            comercio.setResponsavel(dto.getResponsavel());
            comercio.setTipo(dto.getTipo());
           
            if (dto.getCidadeId() != null) {
                cidadeRepository.findById(dto.getCidadeId()).ifPresent(comercio::setCidade);
            }
            comercio = comercioRepository.save(comercio);
            return convertToComercioDTO(comercio);
        }
        return null; 
 }

    @Transactional
    public void excluirComercio(Long idComercio) {
        comercioRepository.deleteById(idComercio);
    }

    
    private CidadeDTO convertToCidadeDTO(Cidade cidade) {
        CidadeDTO dto = new CidadeDTO();
        dto.setId(cidade.getId());
        dto.setNome(cidade.getNome());
        dto.setUf(cidade.getUf());
        dto.setCapital(cidade.getCapital());
        return dto;
    }

    private Cidade convertToCidadeEntity(CidadeDTO dto) {
        Cidade cidade = new Cidade();
        cidade.setId(dto.getId());
        cidade.setNome(dto.getNome());
        cidade.setUf(dto.getUf());
        cidade.setCapital(dto.getCapital());
        return cidade;
    }

    private ComercioDTO convertToComercioDTO(Comercio comercio) {
        ComercioDTO dto = new ComercioDTO();
        dto.setId(comercio.getId());
        dto.setNome(comercio.getNome());
        dto.setResponsavel(comercio.getResponsavel());
        dto.setTipo(comercio.getTipo());
        if (comercio.getCidade() != null) {
            dto.setCidadeId(comercio.getCidade().getId());
        }
        return dto;
    }

    private Comercio convertToComercioEntity(ComercioDTO dto) {
        Comercio comercio = new Comercio();
        comercio.setId(dto.getId());
        comercio.setNome(dto.getNome());
        comercio.setResponsavel(dto.getResponsavel());
        comercio.setTipo(dto.getTipo());
        if (dto.getCidadeId() != null) {
            cidadeRepository.findById(dto.getCidadeId()).ifPresent(comercio::setCidade);
        }
        return comercio;
    }
}
