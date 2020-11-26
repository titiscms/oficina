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
		<!-- CSS EXTERNO -->
		<style type="text/css">
			p span {
				margin-right: 5px;
				font-weight: 700;	
			}
			
			.cadastro {
				background-color: #DEDEDE;
				border-radius: 8px;
				padding: 30px;
			}
			
			.cadastro p:last-child {
				margin-bottom: 0;
			}
			
			.data p {
				margin-bottom: 0;	
				color: #808080;
				font-size: 12px;
			}
			
			div.form-group.col-md-6 {
				margin-bottom: 0;
				padding-left: 0;
			}
			
			div.form-group.col-md-12 {
				margin-bottom: 0;
			}
		</style>
		<title>Registro</title>
	</head>
	<body>
		<div class="modal fade" id="modalRegistro" data-backdrop="static">
			<div class="modal-dialog modal-dialog-centered">
				<div class="modal-content">
				
					<div class="modal-header">
						<div class="form-group col-md-6">
							<h1 class="modal-title modalTitulo titulo-inicial">Registro #<c:out value="${registro.id}" /></h1>
						</div>
						<div class="form-group col-md-6 align-self-center data">
							<p class="text-right">
								Data de inclusão: <fmt:formatDate value="${registro.dataInclusao}" pattern="dd/MM/yyyy" />
							</p>
							<p class="text-right">
								Última alteração: <fmt:formatDate value="${dataAlteracao}" pattern="dd/MM/yyyy" />
							</p>
						</div>
					</div>
					
					<div class="modal-body">
						
						<div class="form-row cadastro">
							<div class="form-group col-md-12">
								 <p>
								 	<span>Cliente:</span><c:out value="${registro.cliente}" />
								 </p>
								 <p>
								 	<span>Possui o seguinte veículo:</span><c:out value="${veiculo.tipo}" />
								 </p>
								 <p>
								 	<span>Peça com problema:</span><c:out value="${peca.descricao}" />
								 </p>
								 <p>
								 	<span>Defeito que precisa ser corrigido:</span><c:out value="${defeito.descricao}" />
								 </p>
							</div>
						</div>
					
						<div class="row modal-footer--btn">
							<a href="<c:out value="/registro"></c:out>" class="align-self-center linkVoltar">
								<span class="iconify" data-icon="bi:arrow-left-short" data-inline="false"></span>Voltar a pesquisa
							</a>
							<a href="/registro/<c:out value="${registro.id}" />" type="button" class="btn botao">Editar</a>
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
			$('#modalRegistro').modal('show')
		</script>
	</body>
</html>