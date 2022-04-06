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

import br.luflix.streaming2.model.TipodeFilmes;
import br.luflix.streaming2.repository.AdminRepository;
import br.luflix.streaming2.repository.FilmesRepository;
import br.luflix.streaming2.repository.GenerosRepository;
import br.luflix.streaming2.util.HashUtil;

@Controller
public class filmesControlller {
	@Autowired
	private FilmesRepository filmRep;
	@Autowired
	private GenerosRepository genrep;

	@RequestMapping("formFilmes")
	public String formFilmes(Model model) {
		
		model.addAttribute("TipodeGenero", genrep.findAll());
		return "admin/formFilmes";

	}

	@RequestMapping(value = "salvarFilm", method = RequestMethod.POST)
	public String salvarfilmes(@Valid TipodeFilmes film, BindingResult result, RedirectAttributes attr) {

		try {
			filmRep.save(film);
			attr.addFlashAttribute("mensagemSucesso", "Filme cadastrado com sucesso. nome: " + film.getNome());
		} catch (Exception e) {
			attr.addFlashAttribute("mensagemErro",
					"Houve um erro ao cadastrar o filme: os campos não pode estar Vazio.");

		}


		return "redirect:formFilmes";

	}
	
	/*

	// request mapping para listar, informando a página desejada
	@RequestMapping("listarAdmin/{pagina}")
	public String listar(Model model, @PathVariable("pagina") int page) {

		// criarr uma pageable ordenando com umero determinado por pagina, os objetos
		// pelo nome,de forma ascendente
		PageRequest pageable = PageRequest.of(page - 1, 6, Sort.by(Sort.Direction.ASC, "nome"));
		// cria a pagiana atual através do repository
		Page<Adimistrador> pagina = admRep.findAll(pageable);

		int totalPages = pagina.getTotalPages();

		List<Integer> pageNumbers = new ArrayList<Integer>();
		for (int i = 0; i < totalPages; i++) {
			pageNumbers.add(i + 1);
		}
		// adiciona as variaveis na Model
		model.addAttribute("admins", pagina.getContent());
		model.addAttribute("paginaAtual", page);
		model.addAttribute("totalPaginas", totalPages);
		model.addAttribute("numPaginas", pageNumbers);
		return "admin/listaAdmin";
	}

	@RequestMapping("excluirAdmin")
	public String excluirAdmin(Long id) {
		admRep.deleteById(id);
		return "redirect:listarAdmin/1";
	}

	@RequestMapping("alterarAdmin")
	public String alterarAdmin(Model mod, Long id) {

		Adimistrador admin = admRep.findById(id).get();
		mod.addAttribute("admin", admin);
		return "forward:formAdmin";
	}*/
}
