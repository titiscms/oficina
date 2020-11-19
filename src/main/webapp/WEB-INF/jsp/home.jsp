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
		<title>Home</title>
	</head>
	<body>
		<div class="modal fade" id="modalHome" data-backdrop="static">
			<div class="modal-dialog modal-lg modal-dialog-centered">
				<div class="modal-content">
					<div class="modal-header">
						<h1 class="modal-title modalTitulo">Sistema da oficina da MJV!</h1>
					</div>
					<div class="modal-body">
						<div class="container-fluid">
							<a href="/home/registro" class="btn btn-primary">Registro de defeitos veiculares</a>
							<a href="/home/pesquisa" class="btn btn-primary">Pesquisar registros de defeitos veiculares</a>
							<a href="/home/cadastro-veiculo" class="btn btn-primary">Cadastrar tipos de veículos</a>
							<a href="/home/cadastro-peca" class="btn btn-primary">Cadastrar peças</a>
							<a href="/home/cadastro-defeito" class="btn btn-primary">Cadastrar defeitos</a>
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
		<script src="/js/layout/layout-geral.js"></script>
		<script type="text/javascript">
			$('#modalHome').modal('show')
		</script>
	</body>
</html>