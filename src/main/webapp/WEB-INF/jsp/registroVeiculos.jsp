<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
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
		<!-- CSS -->
		<link rel="stylesheet" href="/css/layout/layout-geral.css">
		<title>Registro de Veiculos</title>
	</head>
	<body>
		<div class="modal fade" id="modalRegistroVeiculos" data-backdrop="static">
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
								<div class="form-group col-md-8">
									<label for="inputNomeCliente" class="col-form-label labelTextField js-label" hidden="hidden">Nome do cliente</label>
									<input type="text" class="form-control inputGroupLarge js-input" id="inputNomeCliente" name="inputNomeCliente" placeholder="Nome do cliente" required>
								</div>
								<div class="form-group col-md-4 ">
									<p>Acesso: 11/11/2020</p>
									<p>Horário: 18:06:53</p>
								</div>
							</div>
							<div class="form-row">
								<div class="form-group col-md-6">
									<label for="inputTipoVeiculo" class="col-form-label labelTextField js-label" hidden="hidden">Tipo de veículo</label>
									<select id="inputTipoVeiculo" class="form-control inputGroupLarge js-input" name="inputDataInicio">
										<option selected disabled>Tipo de veículo</option>
										<option>Carro</option>
										<option>Barco</option>
										<option>Moto</option>
										<option>Avião</option>
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
											<tr>
												<td class="textAlign"><input class="form-check" type="radio" checked></td>
												<td class="text-left">Ferrugem</td>
												<td class="text-left">Motor</td>
											</tr>
											<tr>
												<td class="textAlign"><input type="radio"></td>
												<td class="text-left">Furo</td>
												<td class="text-left">Mangueira</td>
											</tr>
											<tr>
												<td class="textAlign"><input type="radio" checked></td>
												<td class="text-left">Furo</td>
												<td class="text-left">Roda</td>
											</tr>
											<tr>
												<td class="textAlign"><input type="radio" checked></td>
												<td class="text-left">Vazamento de gás</td>
												<td class="text-left">Ar condicionado</td>
											</tr>
											<tr>
												<td class="textAlign"><input type="radio" checked></td>
												<td class="text-left">Vazamento de gás</td>
												<td class="text-left">Ar condicionado</td>
											</tr>
										</tbody>
									</table>
								</div>
							</div>
						</form>
					</div>

					<div class="modal-footer">
						<a href="/home" class="linkVoltar">
							<span class="iconify" data-icon="bi:arrow-left-short" data-inline="false"></span>Voltar ao menu
						</a>
						<button type="submit" class="btn botao">Salvar</button>
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
		<script src="/js/layout/layout-geral.js"></script>
		<script type="text/javascript">
			$('#modalRegistroVeiculos').modal('show')
		</script>
	</body>
</html>