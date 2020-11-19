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
		<title>Cadastro Veiculo</title>
	</head>
	<body>
		<div class="modal fade" id="modalCadastroVeiculo" data-backdrop="static">
			<div class="modal-dialog modal-dialog-centered">
				<div class="modal-content">
					<div class="modal-header">
						<h5 class="modal-title modalTitulo">Cadastrar tipo de veículo</h5>
						<a type="button" class="close" href="/home">
							<span class="iconify" data-icon="bi-x-circle-fill" data-inline="false"></span>
						</a>
					</div>
					<div class="modal-body">
						<form>
							<div class="form-row">
								<div class="form-group col-md-12">
									<label for="inputVeiculo" class="col-form-label labelTextField js-label" hidden="hidden">Veículo</label>
									<input type="text" class="form-control inputGroupLarge js-input" id="inputVeiculo" name="inputVeiculo" placeholder="Digite aqui o tipo de veículo" required>
								</div>
							</div>
 							<div class="form-row">
								<div class="form-group col-md scroll">
									<table class="table table-hover">
										<thead>
											<tr>
												<th scope="col" class="text-left"><input class="form-check" type="radio" checked></th>
												<th scope="col" class="text-left">Peças</th>
											</tr>
										</thead>
										<tbody>
											<tr>
												<td class="textAlign"><input class="form-check" type="radio" checked></td>
												<td class="text-left">Rodas</td>
											</tr>
											<tr>
												<td class="textAlign"><input type="radio"></td>
												<td class="text-left">Motor</td>
											</tr>
											<tr>
												<td class="textAlign"><input type="radio" checked></td>
												<td class="text-left">Para-choque</td>
											</tr>
											<tr>
												<td class="textAlign"><input type="radio"></td>
												<td class="text-left">Motor</td>
											</tr>
											<tr>
												<td class="textAlign"><input type="radio" checked></td>
												<td class="text-left">Para-choque</td>
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
			$('#modalCadastroVeiculo').modal('show')
		</script>
	</body>
</html>