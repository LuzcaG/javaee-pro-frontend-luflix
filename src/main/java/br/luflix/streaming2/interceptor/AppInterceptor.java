package br.luflix.streaming2.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import br.luflix.streaming2.annotation.Publico;

@Component
public class AppInterceptor implements HandlerInterceptor {
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		String uri = request.getRequestURI();
		
		System.out.println(uri);
		
		if(handler instanceof HandlerMethod) {
			//liberação da tela inicial
			if (uri.equals("/")) {
				return true;
			}
			if (uri.endsWith("/error")) {
				return true;
			}
			//fazer o casting para HandlerMethod
			HandlerMethod metodoChamado = (HandlerMethod) handler;
			//se o metodo for publico ,libera
			
			if(metodoChamado.getMethodAnnotation(Publico.class) != null) {
				return true;
			}
			//verificar se existe um usuario logado 
			if(request.getSession().getAttribute("usuarioLogado") != null) {
				return true;
			}else {
				response.sendRedirect("/");
				return false;
			}
			
		}
		return true;
	}
}
