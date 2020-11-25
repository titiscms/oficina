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
		<!-- CSS -->
		<style>
			.titulo-inicial {
				font-size: 32px;
				text-align: center;
			}
		
			.link-inicial {
				height: 129px;
				color: #FFFFFF;
				background-color: #702094;
				display: flex;
				justify-content: center;
				align-items: center;
				border-radius: 8px;
				font-weight: 700;
				text-align: center;
				padding: 40px;
				font-size: 18px;
				margin-bottom: 25px
			}
				
			.link-inicial:hover {
				color: #5C3566;
				text-decoration: none;
				background-color: #450445;
			}
			
			.modal-header {
				display: block;
			}

		</style>
		<title>Home</title>
	</head>
	<body>
		<div class="modal fade" id="modalHome" data-backdrop="static">
			<div class="modal-dialog modal-lg modal-dialog-centered">
				<div class="modal-content">
				
					<div class="modal-header">
						<h1 class="modal-title modalTitulo titulo-inicial">Sistema da oficina da MJV!</h1>
					</div>
					
					<div class="modal-body">
					
						<div class="row">
							<div class="col-sm">
								<a href="/registro/novo" class="link-inicial btn-block">Registro de defeitos veiculares</a>
							</div>
							<div class="col-sm">
								<a href="/registro" class="link-inicial btn-block">Pesquisar registros de defeitos veiculares</a>
							</div>
						</div>
						
						<div class="row">
							<div class="col-sm">
								<a href="/veiculo/novo" class="link-inicial btn-block">Cadastrar tipos de veículos</a>
							</div>
							<div class="col-sm">	
								<a href="/peca/novo" class="link-inicial btn-block">Cadastrar peças</a>
							</div>
						</div>
						
						<div class="row">
							<div class="col-sm">		
								<a href="/defeito/novo" class="link-inicial btn-block" style="margin-bottom: 0;">Cadastrar defeitos</a>
							</div>
						</div>	
							
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
			$('#modalHome').modal('show');
		</script>
	</body>
</html>