package br.com.mjv.oficina.exception;

public class DataInvalidaException extends BusinnessException {

	private static final long serialVersionUID = 1L;

	public DataInvalidaException(String mensagem) {
		super(mensagem);
	}
	
	public DataInvalidaException(String dataInicio, String dataFim) {
		this(String.format("Verifique as dataInicio '%s' e a dataFim %s. Podem estar com o formato inválido.", dataInicio, dataFim));
	}
	
}
