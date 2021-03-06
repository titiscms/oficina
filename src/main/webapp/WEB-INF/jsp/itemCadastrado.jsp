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
		<title>Item cadastrado</title>
	</head>
	<body>
		<div class="modal fade" id="modalItemCadastrado" data-backdrop="static">
			<div class="modal-dialog modal-dialog-centered">
				<div class="modal-content">
				
					<div class="modal-header">
						<h5 class="modal-title modalTitulo">Cadastro feito com sucesso.</h5>
					</div>
					
					<div class="form-row modal-footer--btn">
						<a href="/registro/novo" class="align-self-center linkVoltar">
							<span class="iconify" data-icon="bi:arrow-left-short" data-inline="false"></span>Registro dos defeitos
						</a>
						<a href="/<c:out value='${recurso}' />/novo" id="botaoSalvar" class="btn botao">Novo cadastro</a>
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
			$('#modalItemCadastrado').modal('show');
		</script>
	</body>
</html>