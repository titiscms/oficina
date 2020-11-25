package br.com.mjv.oficina.exception;

/**
 * Exceção genérica disparada quando violamos uma regra de neǵocio.
 * @author thiago
 *
 */
public class BusinnessException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public BusinnessException(String mensagem) {
		super(mensagem);
	}
	
}
