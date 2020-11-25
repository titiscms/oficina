package br.com.mjv.oficina.exception;

/**
 * Exceção disparada quando tentamos cadastrar um veiculo já cadastrado em nossa base de dados.
 * @author thiago
 *
 */
public class VeiculoJaCadastradoException extends BusinnessException {

	private static final long serialVersionUID = 1L;

	public VeiculoJaCadastradoException(String veiculo) {
		super(String.format("Veiculo %s já cadastrado na base de dados", veiculo));
	}

}
