package br.luflix.streaming2.controller;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.luflix.streaming2.model.TipodeGenero;
import br.luflix.streaming2.repository.GenerosRepository;


@Controller
public class generosController {
	@Autowired
	private GenerosRepository genRepository;

	@RequestMapping("formGenero")
	public String formGen() {

		return "admin/formgenero";

	}

	@RequestMapping(value = "salvarGenero", method = RequestMethod.POST)
	public String salvarGenero(@Valid TipodeGenero tpGen, BindingResult result, RedirectAttributes attr) {

		try {
			genRepository.save(tpGen);
			attr.addFlashAttribute("mensagemSucesso", "Gênero cadastrado com sucesso. nome do genero que cadastrou: " + tpGen.getNome());
		} catch (Exception e) {
			attr.addFlashAttribute("mensagemErro",
					"Houve um erro ao cadastrar o gênero, os campos não pode estar Vazio.");

		}

		/*boolean alteracao = admin.getId() != null ? true : false;
		if (admin.getSenha().equals(HashUtil.hash256(""))) {
			if (!alteracao) {
				String parte = admin.getEmail().substring(0, admin.getEmail().indexOf("@"));
			} else {
				String senha = admRep.findById(admin.getId()).get().getSenha();
				admin.setSenhaComHash(senha);
			}
		}*/

		return "redirect:formGenero";
		// verifica se esta sendo feita uma alteração ao invés de uma inserção

	}

	// request mapping para listar, informando a página desejada
	@RequestMapping("listarGen/{pagina}")
	public String listar(Model model, @PathVariable("pagina") int page) {

		// criarr uma pageable ordenando com umero determinado por pagina, os objetos
		// pelo nome,de forma ascendente
		PageRequest pageable = PageRequest.of(page - 1, 6, Sort.by(Sort.Direction.ASC, "nome"));
		// cria a pagiana atual através do repository
		Page<TipodeGenero> pagina = genRepository.findAll(pageable);

		int totalPages = pagina.getTotalPages();

		List<Integer> pageNumbers = new ArrayList<Integer>();
		for (int i = 0; i < totalPages; i++) {
			pageNumbers.add(i + 1);
		}
		// adiciona as variaveis na Model
		model.addAttribute("gens", pagina.getContent());
		model.addAttribute("paginaAtual", page);
		model.addAttribute("totalPaginas", totalPages);
		model.addAttribute("numPaginas", pageNumbers);
		return "admin/listaGen";
	}

	@RequestMapping("excluirGen")
	public String excluirAdmin(Long id) {
		genRepository.deleteById(id);
		return "redirect:listarGen/1";
	}
	
	@RequestMapping("alterarGen")
	public String alterarGen(Model mod, Long id) {
		TipodeGenero gen = genRepository.findById(id).get();
		mod.addAttribute("genero", gen);
		return "forward:formGenero";
	}
	@RequestMapping("buscarChave")
	public String buscarPalavra(Model model, String palavraChave) {
	model.addAttribute("generos", genRepository.verGenero(palavraChave));
	return "admin/listaGen";
	}
}
