package br.com.webjsp.controller;

import java.io.IOException;
import javax.servlet.DispatcherType;
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

@WebFilter(dispatcherTypes = {
				DispatcherType.REQUEST, 
				DispatcherType.FORWARD
		}
					, urlPatterns = { "/*" })
public class FiltroAutentica implements Filter {

    public FiltroAutentica() {
        
    }

	public void destroy() {
		
	}
	
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		
		//Casting do httpservlet request
		HttpServletRequest httpServletRequest = (HttpServletRequest) request;
		
		String url = httpServletRequest.getRequestURI();
		//Capturando sessao
		HttpSession sessao = httpServletRequest.getSession();
		//está logado?
		if(sessao.getAttribute("usulogado")!=null || url.lastIndexOf("login.html")>-1 || url.lastIndexOf("autcontroller.do")>-1){
			chain.doFilter(request, response);
		} else {
			((HttpServletResponse)response).sendRedirect("login.html");
		}
	}

	public void init(FilterConfig fConfig) throws ServletException {
		
	}

}
