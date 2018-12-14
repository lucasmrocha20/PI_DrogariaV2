package com.pi.drogaria.util;


import java.io.IOException;

import javax.faces.application.ResourceHandler;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
//
//@WebFilter(filterName = "FiltroConexao", urlPatterns = {"/*"})
//public class Filtro implements Filter {
//	
//	public void doFilter(ServletRequest req, ServletResponse resp,
//
//			FilterChain chain) throws IOException, ServletException {
//
//		HttpServletRequest requisicao = (HttpServletRequest) req;
//
//		HttpServletResponse resposta = (HttpServletResponse) resp;
//
//		HttpSession sessao = requisicao.getSession(false);
//
//		String loginURI = requisicao.getContextPath() + "/pages/autenticacao.xhtml";
//		
//		boolean logado = sessao != null && sessao.getAttribute("usuario") != null;
//		
//		boolean paginaLogin = requisicao.getRequestURI().equals(loginURI);
//		
//		boolean requisicaoRecurso = requisicao.getRequestURI()
//				.startsWith(requisicao.getContextPath() + ResourceHandler.RESOURCE_IDENTIFIER);
//		if (logado || paginaLogin || requisicaoRecurso) {
//			chain.doFilter(requisicao, resposta);
//		} else {
//			resposta.sendRedirect(loginURI);
//		}
//	}
//
//	@Override
//	public void destroy() {
//	}
//
//	@Override
//	public void init(FilterConfig arg0) throws ServletException {
//		// TODO Auto-generated method stub
//	}
//}