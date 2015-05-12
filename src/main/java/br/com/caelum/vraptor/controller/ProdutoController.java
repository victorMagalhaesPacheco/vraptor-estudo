package br.com.caelum.vraptor.controller;

import javax.inject.Inject;

import br.com.caelum.vraptor.Controller;
import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Post;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.dao.ProdutoDao;
import br.com.caelum.vraptor.model.Produto;
import br.com.caelum.vraptor.view.Results;

@Controller
public class ProdutoController {
	
	private final Result result;
	private final ProdutoDao dao;
	
	@Inject
	public ProdutoController(Result result, ProdutoDao dao) {
		this.result = result;
		this.dao = dao;
	}
	
	/**
	 *  para evitar que alguém chame esse construtor por engano em testes e até mesmo
	 *  para deixar explicito que é apenas para o uso do CDI
	 */
	@Deprecated
	ProdutoController() {
		this(null, null);
	}
		
	@Get("/")
	public void inicio() {
		
	}
	
	@Get
	public void lista() {
		this.result.include("produtoList", this.dao.lista());
	}
	
	@Get
	public void listaXML() {
		this.result.use(Results.xml()).from(this.dao.lista()).serialize();
	}
	
	@Get
	public void listaJSON() {
		this.result.use(Results.json()).from(this.dao.lista()).serialize();
	}
	
	@Get
	public void formulario() {
		
	}
	
	@Post
	public void adiciona(Produto produto) {
		dao.adiciona(produto);
		
		this.result.include("mensagem", "Produto adicionado com sucesso!");
		this.result.redirectTo(this).lista();
		
	}
	
	@Get
	public void remove(Produto produto) {
		dao.remove(produto);
		
		this.result.include("mensagem", "Produto excluído com sucesso!");
		this.result.redirectTo(this).lista();
	}
}