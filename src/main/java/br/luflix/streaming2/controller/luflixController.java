package br.luflix.streaming2.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.luflix.streaming2.model.Adimistrador;
import br.luflix.streaming2.repository.AdminRepository;

@Controller
public class luflixController {
	
	
	@Autowired
	private AdminRepository admRep;
	@RequestMapping("formAdmin")
	public String formCli() {
	
		return "admin/formAdmin";
		
	}
	@RequestMapping(value = "salvarAdministrador", method = RequestMethod.POST)
	public String salvarAdmin( @Valid Adimistrador admin, BindingResult result, RedirectAttributes attr) {
		
		try {
			admRep.save(admin);
			attr.addFlashAttribute("mensagemSucesso", "Administrador cadastro com sucesso. ID:"+admin.getId());
		} catch (Exception e) {
			attr.addFlashAttribute("mensagemErro","Houve um erro ao cadastrar o Adiminstrador:"+e.getMessage());
			
		}
		return "redirect:formAdmin";
	}
}
