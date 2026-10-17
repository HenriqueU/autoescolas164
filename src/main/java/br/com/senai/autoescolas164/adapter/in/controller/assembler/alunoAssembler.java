package br.com.senai.autoescolas164.adapter.in.controller.assembler;

import br.com.senai.autoescolas164.adapter.in.controller.alunoController;
import br.com.senai.autoescolas164.adapter.in.controller.instrutorController;
import br.com.senai.autoescolas164.adapter.in.controller.response.aluno.DadosDetalhamentoAluno;
import br.com.senai.autoescolas164.adapter.in.controller.response.aluno.DadosListagemAluno;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.hateoas.PagedModel;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.EntityModel;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
@RequiredArgsConstructor
public class alunoAssembler {
    private final PagedResourcesAssembler<DadosListagemAluno> pagedResourcesAssembler;

    public EntityModel<DadosDetalhamentoAluno> toCreate(DadosDetalhamentoAluno dados) {
        EntityModel<DadosDetalhamentoAluno> model = EntityModel.of(
                dados,
                linkTo(methodOn(alunoController.class)
                        .listar(null))
                        .withRel("listar")
        );
        return model;
    }

    public PagedModel<EntityModel<DadosListagemAluno>> toList(Page<DadosListagemAluno> page) {
        PagedModel<EntityModel<DadosListagemAluno>> pagedModel = pagedResourcesAssembler.toModel(
                page,
                dados -> EntityModel.of(
                        dados,
                        linkTo(methodOn(alunoController.class)
                                .detalhar(dados.id()))
                                .withSelfRel(),
                        linkTo(methodOn(alunoController.class)
                                .atualizar(null))
                                .withRel("atualizar")
                )
        );
        return pagedModel;
    }

    public EntityModel<DadosDetalhamentoAluno> toDetail(DadosDetalhamentoAluno dados) {
        EntityModel<DadosDetalhamentoAluno> model = EntityModel.of(
                dados,
                linkTo(methodOn(alunoController.class)
                        .atualizar(null))
                        .withRel("atualizar")
        );
        if (dados.ativo()) {
            model.add(
                    linkTo(methodOn(alunoController.class)
                            .excluir(dados.id()))
                            .withRel("excluir")
            );
        }
        return model;
    }
}
