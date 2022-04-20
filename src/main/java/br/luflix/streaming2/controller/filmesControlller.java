package br.luflix.streaming2.controller;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.swing.RepaintManager;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.luflix.streaming2.model.TipodeFilmes;
import br.luflix.streaming2.model.TipodeGenero;
import br.luflix.streaming2.repository.AdminRepository;
import br.luflix.streaming2.repository.FilmesRepository;
import br.luflix.streaming2.repository.GenerosRepository;
import br.luflix.streaming2.util.FireBaseUtil;
import br.luflix.streaming2.util.HashUtil;

@Controller
public class filmesControlller {
	@Autowired
	private FilmesRepository filmRep;
	@Autowired
	private GenerosRepository genrep;
    @Autowired
	private FireBaseUtil firebase;
	@RequestMapping("formFilmes")
	public String formFilmes(Model model) {

		model.addAttribute("TipodeGenero", genrep.findAll());
		return "admin/formFilmes";

	}

	@RequestMapping("salvarFilm")
	public String salvarfilmes(TipodeFilmes film, @RequestParam("fileFotos") MultipartFile[] fileFotos) {
		String fotos = film.getFotos();
		
		System.out.println(fileFotos.length);
		for (MultipartFile arquivo : fileFotos) {
			// verirficar se o arquivo esta vazio
			if (arquivo.getOriginalFilename().isEmpty()) {
				// vai para o proximo arquivo
				continue;
			}
			//faz o upload para a nuvem e obtem a url gerada
			try {
				fotos += firebase.uploadFile(arquivo)+";";
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
		}
		
		film.setFotos(fotos);
		filmRep.save(film);
		return "redirect:formFilmes";
		
	}
	@RequestMapping("listarFilmes/{pagina}")
	public String listar(Model model, @PathVariable("pagina") int page) {

		// criarr uma pageable ordenando com umero determinado por pagina, os objetos
		// pelo nome,de forma ascendente
		PageRequest pageable = PageRequest.of(page - 1, 6, Sort.by(Sort.Direction.ASC, "nome"));
		// cria a pagiana atual através do repository
		Page<TipodeFilmes> pagina = filmRep.findAll(pageable);

		int totalPages = pagina.getTotalPages();

		List<Integer> pageNumbers = new ArrayList<Integer>();
		for (int i = 0; i < totalPages; i++) {
			pageNumbers.add(i + 1);
		}
		// adiciona as variaveis na Model
		model.addAttribute("films", pagina.getContent());
		model.addAttribute("paginaAtual", page);
		model.addAttribute("totalPaginas", totalPages);
		model.addAttribute("numPaginas", pageNumbers);
		return "admin/listaFilmes";
	}
	@RequestMapping("alterarFilm")
	public String alterarFilm(Model mod, Long id) {
		TipodeFilmes film = filmRep.findById(id).get();
		mod.addAttribute("film", film);
		return "forward:formFilmes";
	}
	@RequestMapping("excluirFilm")
	public String excluirFilm(Long id) {
		TipodeFilmes film = filmRep.findById(id).get();
		if (film.getFotos().length() > 0) {
			for(String foto : film.verFotos()) {
				firebase.deletar(foto);
			}
		} 
		filmRep.delete(film);
		return "redirect:listarFilmes/1";
	}
	@RequestMapping("excluirFotoFilm")
	public String excluirFoto(Long id, int numFoto,Model model) {
		TipodeFilmes tpfilmes = filmRep.findById(id).get();
		
		String fotoUrl = tpfilmes.verFotos()[numFoto];
		firebase.deletar(fotoUrl);
		
		tpfilmes.setFotos(tpfilmes.getFotos().replace(fotoUrl+";", ""));
		
		filmRep.save(tpfilmes);
		
		model.addAttribute("film", tpfilmes);
		
		return "forward:formFilmes";
		
	}
	public void alterarFoto() {
		
	}
	@RequestMapping("buscarTipoFilmes")
	public String buscarTipoFilmes(Model model, String genero) {
	model.addAttribute("tipoFilmes", FilmesRepository.class);
	return "";
	}
	@RequestMapping("buscarDiretorFilmes")
	public String buscarDiretorFilmes(Model model, String diretor) {
	model.addAttribute("diretorFilmes", FilmesRepository.class);
	return "";
	}
}
