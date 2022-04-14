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

import br.luflix.streaming2.model.Adimistrador;
import br.luflix.streaming2.repository.AdminRepository;
import br.luflix.streaming2.util.HashUtil;

@Controller
public class indexcontroller {
	//@Autowired
	//private AdminRepository admRep;

	
	public String formLogin() {

		return "index";

	}

	/*@RequestMapping(value = "salvarAdministrador", method = RequestMethod.POST)
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

	}*/

	// request mapping para listar, informando a página desejada
	/*@RequestMapping("listarAdmin/{pagina}")
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
	}*/

	/*@RequestMapping("excluirAdmin")
	public String excluirAdmin(Long id) {
		admRep.deleteById(id);
		return "redirect:listarAdmin/1";
	}*/

	/*@RequestMapping("alterarAdmin")
	public String alterarAdmin(Model mod, Long id) {

		Adimistrador admin = admRep.findById(id).get();
		mod.addAttribute("admin", admin);
		return "forward:formAdmin";
	}*/
}
