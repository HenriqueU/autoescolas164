package br.com.senai.autoescolas164.shared.vo.endereco.mapper;

import br.com.senai.autoescolas164.shared.vo.endereco.Endereco;
import br.com.senai.autoescolas164.shared.vo.endereco.dto.DadosEndereco;
import org.springframework.stereotype.Component;

@Component
public class EnderecoMapper {
    public Endereco toEndereco(DadosEndereco dados) {
        return new Endereco(
                dados.cep(),
                dados.logradouro(),
                dados.numero(),
                dados.complemento(),
                dados.bairro(),
                dados.cidade(),
                dados.uf()
        );
    }

    public DadosEndereco toDto(Endereco endereco) {
        return new DadosEndereco(
                endereco.getCep(),
                endereco.getLogradouro(),
                endereco.getNumero(),
                endereco.getComplemento(),
                endereco.getBairro(),
                endereco.getCidade(),
                endereco.getUf()
        );
    }
}
