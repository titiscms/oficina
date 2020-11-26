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
		<title>Pesquisa de Veiculos</title>
	</head>
	<body>
		<div class="modal fade" id="modalPesquisaVeiculos" data-backdrop="static">
			<div class="modal-dialog modal-lg modal-dialog-centered">
				<div class="modal-content">
				
					<div class="modal-header">
						<h5 class="modal-title modalTitulo">Registros de defeitos veiculares</h5>
						<a type="button" class="close" href="/home">
							<span class="iconify" data-icon="bi-x-circle-fill" data-inline="false"></span>
						</a>
					</div>
					
					<div class="modal-body">
						<form>
						
							<div class="form-row">
								<div class="form-group col-md-6">
									<label for="inputTipoVeiculo" class="col-form-label labelTextField">Tipo de veículo</label>
									<select id="inputTipoVeiculo" class="form-control inputGroupLarge" name="tipoVeiculo">
										<option selected disabled>Tipo de veículo</option>
										<option></option>
										<c:forEach var="veiculo" items="${veiculos}" >
											<option value='<c:out value="${veiculo.id}" />'><c:out value="${veiculo.tipo}" /></option>
										</c:forEach>
									</select>
								</div>
							</div>
							
							<div class="form-row">
								<div class="form-group col-md-6">
									<label for="inputDataInicio" class="col-form-label labelTextField">Data início</label>
									<input type="text" class="form-control inputDate inputGroupLarge date" id="inputDataInicio" name="dataInicio" 
										   placeholder="Data início" autocomplete="off" />
								</div>
								<div class="form-group col-md-6">
									<label for="inputDataFim" class="col-form-label labelTextField">Data fim</label>
									<input type="text" class="form-control inputDate inputGroupLarge date" id="inputDataFim" name="dataFim" 
										   placeholder="Data fim" autocomplete="off" />
								</div>
							</div>

 							<div class="form-row">
								<div class="form-group col-md scroll">
									<table class="table table-hover">
										<thead>
											<tr>
												<th scope="col" class="text-left">Cliente</th>
												<th scope="col" class="text-left">Data</th>
												<th scope="col" class="text-left">Veículo</th>
												<th scope="col"></th>
											</tr>
										</thead>
										<tbody>
											<c:forEach var="registro" items="${registros}">
												<tr>
													<td class="text-left"><c:out value="${registro.cliente}" /></td>
													<td class="text-left">
														<fmt:formatDate value="${registro.dataInclusao}" pattern="dd/MM/yyyy" />
													</td>
													<td class="text-left"><c:out value="${registro.veiculo}" /></td>
													<td class="text-center"><a href="/registro/<c:out value="${registro.id}" />" class="linkDetalhes">Ver detalhes</a></td>
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
								<button type="button" class="btn botao" onclick="pesquisar()">Pesquisar</button>
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
		<!-- JS EXTERNO -->
		<script src="/js/jquery.mask.min.js"></script>
		<!-- JS -->
		<script type="text/javascript">
			// Abrir o modal
			$('#modalPesquisaVeiculos').modal('show');

			// Dar foco no input do tipo do veiculo quando o modal estiver aberto
			$('#modalPesquisaVeiculos').on('shown.bs.modal', function (e) {
				$('#inputTipoVeiculo').focus();
	  			$('#inputDataInicio').mask('00/00/0000');
	  			$('#inputDataFim').mask('00/00/0000');
			});

			// Formatar o input datas
			
			// Função para pesquisar dados passando o filtro
			function pesquisar() {
				var filtro = JSON.stringify({
					"veiculoId": $('#inputTipoVeiculo').val(),
					"dataInicio": $('#inputDataInicio').val(), 
					"dataFim": $('#inputDataFim').val()
				}); 

				// chamada para o método que vai buscar os registros filtrados
				$.ajax({
					url: '/registro/buscar',
					method: 'POST',
					contentType: 'application/json',
					data: filtro,
					success: function(data, textStatus, xhr) {
						preencherTabela(data);
					},
				    error: function (jqXhr, textStatus, errorMessage) {
				    	$('#inputDataInicio').html("<i>Data início inválida</i>"),
				    	$('#inputDataFim').html("<i>Data fim inválida</i>")
				    }
				});
	
				// Preencher a tabela quando passar o filtro
				function preencherTabela(listRegistro) {
					$('.table tbody tr').remove();

					console.log(listRegistro.length);
					console.log($('.table tbody')	)	
					
					// Verificação se pesquisa retorna vazia
					if (listRegistro.length == 0) {
						$('.table tbody').append(
							'<tr><td colspan="3">Não existem dados para a pesquisa atual.</td></tr>'
						);
					}

					// Conversão da data
					function formatData(data) {
						  var dia  = data.split("-")[0];
						  var mes  = data.split("-")[1];
						  var ano  = data.split("-")[2];

						  return ano + '/' + ("0"+mes).slice(-2) + '/' + ("0"+dia).slice(-4);
					}

					// Inserindo linhas na tabela
					$.each(listRegistro, function(i, registro) {
					     var linha = $("<tr>");
				
					     linha.append(
					     	$('<td class="text-left">').text(registro.cliente),
							$('<td class="text-left">').text(formatData(registro.dataInclusao)),
							$('<td class="text-left">').text(registro.veiculo),
							$('<td class="text-center">').html('<a href="/registro/' + registro.id + '" class="linkDetalhes">Ver detalhes</a>'),
					     );
							
					     linha.appendTo('.table');
					});
					
				}
			}
		</script>
	</body>
</html>