package br.luflix.streaming2.controller;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties.Admin;
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

import br.luflix.streaming2.annotation.Publico;
import br.luflix.streaming2.model.Adimistrador;
import br.luflix.streaming2.repository.AdminRepository;
import br.luflix.streaming2.util.HashUtil;


@Controller
public class luflixController {

	@Autowired
	private AdminRepository admRep;

	@RequestMapping("formAdmin")
	public String formCli() {

		return "admin/formAdmin";

	}

	@RequestMapping(value = "salvarAdministrador", method = RequestMethod.POST)
	public String salvarAdmin(@Valid Adimistrador admin, BindingResult result, RedirectAttributes attr) {

		try {
			admRep.save(admin);
			attr.addFlashAttribute("mensagemSucesso", "Administrador cadastrado com sucesso. nome: " + admin.getNome());
		} catch (Exception e) {
			attr.addFlashAttribute("mensagemErro",
					"Houve um erro ao cadastrar o Adiminstrador: os campos não pode estar Vazio.");

		}

		boolean alteracao = admin.getId() != null ? true : false;
		if (admin.getSenha().equals(HashUtil.hash256(""))) {
			if (!alteracao) {
				String parte = admin.getEmail().substring(0, admin.getEmail().indexOf("@"));
			} else {
				String senha = admRep.findById(admin.getId()).get().getSenha();
				admin.setSenhaComHash(senha);
			}
		}

		return "redirect:formAdmin";
		// verifica se esta sendo feita uma alteração ao invés de uma inserção

	}

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
	}
	@Publico
	@RequestMapping("login")
	public String login(Adimistrador admLogin, RedirectAttributes attr, HttpSession session) {
		//buscar o adm no BD, através da senha  e  email
		Adimistrador admin = admRep.findByEmailAndSenha(admLogin.getEmail(), admLogin.getSenha());
		if (admin == null) {
			attr.addFlashAttribute("mensagemErro", "Login e/ou senha invalio(s)");
			return "redirect:/";
		} else {
			session.setAttribute("usuarioLogado", admin);
			return "redirect:listarFilmes/1";
		}
	}
	
	@RequestMapping("logout")
	public String logout(HttpSession session) {
		//elimina do usuario da session
		session.invalidate();
		return "redirect:/";
	}
}
