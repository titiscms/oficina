<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
		<!-- BOOTSTRAP -->
		<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" integrity="sha384-JcKb8q3iqJ61gNV9KGb8thSsNjpSL0n8PARn9HuZOnIxN0hoP+VmmDGMN5t9UJ0Z" crossorigin="anonymous">
		<!-- GOOGLE FONTS -->
		<link rel="preconnect" href="https://fonts.gstatic.com">
		<link href="https://fonts.googleapis.com/css2?family=Catamaran:wght@400;700&display=swap" rel="stylesheet">
		<link href="https://fonts.googleapis.com/css2?family=Baloo+2:wght@400;700&display=swap" rel="stylesheet">
		<!-- CSS EXTERNO -->
		<link rel="stylesheet" href="/css/layout.css">
		<title>Cadastro Veiculo</title>
	</head>
	<body>
		<div class="modal fade" id="modalCadastroVeiculo" data-backdrop="static">
			<div class="modal-dialog modal-dialog-centered">
				<div class="modal-content">
				
					<c:if test='${not empty mensagem}'>
						<div class="alert alert-danger alert-dismissible" style="padding-bottom: 30px;" role="alert">
							<c:out value="${mensagem}" />
							<button type="button" class="close" data-dismiss="alert" aria-label="Close">
								<span aria-hidden="true">&times;</span>
							</button>
						</div>
					</c:if>
				
					<div class="modal-header">
						<h5 class="modal-title modalTitulo">Cadastrar tipo de veículo</h5>
						<a type="button" class="close" href="/home">
							<span class="iconify" data-icon="bi-x-circle-fill" data-inline="false"></span>
						</a>
					</div>
					
					<div class="modal-body">
						<form action="/veiculo/cadastrar" method="post">
						
							<div class="form-row">
								<div class="form-group col-md-12">
									<label for="inputVeiculo" class="col-form-label labelTextField">Veículo</label>
									<input type="text" class="form-control inputGroupLarge" id="inputVeiculo" name="veiculo" 
										   placeholder="Digite aqui o tipo de veículo" autocomplete="off" />
								</div>
							</div>
							
 							<div class="form-row">
								<div class="form-group col-md scroll">
									<table class="table table-hover">
										<thead>
											<tr>
												<th scope="col" class="text-left"></th>
												<th scope="col" class="text-left">Peças</th>
											</tr>
										</thead>
										<tbody>
											<c:forEach var="peca" items="${pecas}">
												<tr>
													<td class="textAlign">
														<input class="form-check" type="checkbox" value="<c:out value='${peca.id}' />" name="pecasId" />
													</td>
													<td class="text-left"><c:out value='${peca.descricao}' /></td>
												</tr>
											</c:forEach>
										</tbody>
									</table>
								</div>
							</div>
							
							<div class="form-row modal-footer--btn">
								<a href="/home" class="align-self-center linkVoltar">
									<span class="iconify" data-icon="bi:arrow-left-short" data-inline="false"></span>Voltar ao menu
								</a>
								<button id="botaoSalvar" type="submit" class="btn botao" disabled="disabled">Salvar</button>
							</div>
							
						</form>
					</div>
					
				</div>
			</div>
		</div>
		
		<!-- JQUERY -->
		<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
		<!-- BOOTSTRAP -->
		<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js" integrity="sha384-B4gt1jrGC7Jh4AgTPSdUtOBvfO8shuf57BaghqFfPlYxofvL8/KUEfYiJOMMV+rV" crossorigin="anonymous"></script>
		<!-- ICONES -->
		<script src="https://code.iconify.design/1/1.0.7/iconify.min.js"></script>
		<!-- JS -->
		<script type="text/javascript">
			// Abrir o modal
			$('#modalCadastroVeiculo').modal('show');

			// Dar foco no input no tipo do veiculo quando abrir o modal
			$('#modalCadastroVeiculo').on('shown.bs.modal', function (e) {
				$('#inputVeiculo').focus();
			});

			// Desabilitar botão Salvar
			$('#inputVeiculo').on('input', function() {
				if($('#inputVeiculo').val() == '') {
					$('#botaoSalvar').attr('disabled', true);
				} else {
					$('#botaoSalvar').removeAttr('disabled');
				}
			});
		</script>
	</body>
</html>