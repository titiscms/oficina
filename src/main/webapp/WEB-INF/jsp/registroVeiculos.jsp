<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
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
		<!-- CSS -->
		<style type="text/css">
			.acesso p {
				margin-bottom: 0;
				color: #808080;
				font-size: 14px;
			}
		</style>
		<title>Registro de Veiculos</title>
	</head>
	<body>
		
		<div class="modal fade" id="modalRegistroVeiculos" data-backdrop="static">
			<div class="modal-dialog modal-lg modal-dialog-centered">
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
						<h5 class="modal-title modalTitulo">Registros de defeitos veiculares</h5>
						<a type="button" class="close" href="/home">
							<span class="iconify" data-icon="bi-x-circle-fill" data-inline="false"></span>
						</a>
					</div>
					
					<div class="modal-body">
						<form action="/registro/cadastrar" method="post">
						
							<div class="form-row d-flex justify-content-between">
								<div class="form-group col-md-7">
									<label for="inputCliente" class="col-form-label labelTextField">Nome cliente</label>
									<input type="text" class="form-control inputGroupLarge js-input" id="inputCliente" 
										   name="cliente" autocomplete="off" />
								</div>
								<div class="form-group col-md-3 align-self-center acesso">
									<p class="text-right">Acesso: <fmt:formatDate value="${acesso}" pattern="dd/MM/yyyy"/></p>
									<p class="text-right">Horário: <fmt:formatDate value="${acesso}" pattern="HH:mm:ss"/></p>
								</div>
							</div>
							
							<div class="form-row">
								<div class="form-group col-md-6">
									<label for="inputTipoVeiculo" class="col-form-label labelTextField">Tipo de veículo</label>
									<select id="inputTipoVeiculo" class="form-control inputGroupLarge" name="tipoVeiculo">
										<option selected disabled>Tipo de veículo</option>
										<c:forEach var="veiculo" items="${veiculos}" >
											<option value='<c:out value="${veiculo.id}" />'><c:out value="${veiculo.tipo}" /></option>
										</c:forEach>
									</select>
								</div>
							</div>
							
 							<div class="form-row">
								<div class="form-group col-md scroll">
									<table class="table table-hover">
										<thead>
											<tr>
												<th scope="col" class="text-left">Itens</th>
												<th scope="col" class="text-left">Defeito</th>
												<th scope="col" class="text-left">Peças</th>
											</tr>
										</thead>
										<tbody>
											<c:forEach var="pecaDefeito" items="${listPecaDefeito}">
												<tr>
													<td class="text-left"><input class="form-check" type="checkbox" value="<c:out value="${pecaDefeito.defeitoId}_${pecaDefeito.pecaId}" />" name="idsItem" /></td>
													<td class="text-left"><c:out value="${pecaDefeito.defeito}" /></td>
													<td class="text-left"><c:out value="${pecaDefeito.peca}" /></td>
												</tr>
											</c:forEach>
										</tbody>
									</table>
								</div>
							</div>
							
							<div class="form-row modal-footer--btn">
								<a href="<c:out value="/home"></c:out>" class="align-self-center linkVoltar">
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
			// Abrir o modal da página
			$('#modalRegistroVeiculos').modal('show');

			// Dar foco no input do nome do Cliente quando o modal estiver aberto
			$('#modalRegistroVeiculos').on('shown.bs.modal', function (e) {
				$('#inputCliente').focus();
			})

			// Desabilitar o botão salvar
			$('#inputCliente').on('input', function() {
				if ($('#inputCliente').val() == '') {
					$('#botaoSalvar').attr('disabled', true);
				} else {
					$('#botaoSalvar').removeAttr('disabled');
				}
			});

			// Chamada para buscar os defeitos e peças quando o veiculo for selecionado
			$('#inputTipoVeiculo').on('input', function() {
				var veiculoId = $('#inputTipoVeiculo').val();
				
				$.ajax({
					url: '/veiculo/peca/defeito/buscar',
					method: 'POST',
					contentType: 'application/json',
					data: veiculoId,
					success: function(data, textStatus, xhr) {
						preencherTabela(data);
				    }
				});

				// função para preencher a tabela
				function preencherTabela(listDefeitoAndPecaFromVeiculo) {
				   $('.table tbody tr').remove();

				   $.each(listDefeitoAndPecaFromVeiculo, function(i, defeitoAndPecaFromVeiculo) {
				        var linha = $("<tr>");

				        linha.append(
				        	$('<td>').html('<input class="form-check" type="checkbox" value="' + defeitoAndPecaFromVeiculo.defeitoId + '_' + defeitoAndPecaFromVeiculo.pecaId + '" name="idsItem" />'),
							$('<td>').text(defeitoAndPecaFromVeiculo.defeito),
							$('<td>').text(defeitoAndPecaFromVeiculo.peca)
				        );

				        linha.appendTo('.table');
				   });
				}
			});
		</script>
	</body>
</html>