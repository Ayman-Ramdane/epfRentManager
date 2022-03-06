<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<%@include file="/WEB-INF/views/common/head.jsp"%>
<body class="hold-transition skin-blue sidebar-mini">
	<div class="wrapper">

		<%@ include file="/WEB-INF/views/common/header.jsp"%>
		<!-- Left side column. contains the logo and sidebar -->
		<%@ include file="/WEB-INF/views/common/sidebar.jsp"%>

		<!-- Content Wrapper. Contains page content -->
		<div class="content-wrapper">
			<!-- Content Header (Page header) -->
			<section class="content-header">
				<h1>Voiture (Id = ${vehicleId})</h1>
			</section>

			<!-- Main content -->
			<section class="content">
				<div class="row">
					<div class="col-md-12">
						<!-- Horizontal Form -->
						<div class="box">

							<form class="form-horizontal" method="post">
								<div class="box-body">
									<div class="form-group">
										<label for="manufacturer" class="col-sm-2 control-label">Marque</label>

										<div class="col-sm-10">
											<input type="text" class="form-control" id="manufacturer"
												name="manufacturer" placeholder="Marque" maxlength="100"
												pattern="[a-zA-Z0-9-]+" value="${Manufacturer}" required>
										</div>
									</div>
									<div class="form-group">
										<label for="modele" class="col-sm-2 control-label">Modele</label>

										<div class="col-sm-10">
											<input type="text" class="form-control" id="modele"
												name="modele" placeholder="Modele" maxlength="100"
												pattern="[a-zA-Z0-9-]+" value="${Modele}" required>
										</div>
									</div>
									<div class="form-group">
										<label for="seats" class="col-sm-2 control-label">Nombre
											de places</label>

										<div class="col-sm-10">
											<input type="text" class="form-control" id="seats"
												name="seats" placeholder="Nombre de places" value="${Seats}"
												required>
										</div>
									</div>
								</div>
								<!-- /.box-body -->
								<div class="box-footer">
									<a class="btn btn-danger pull-right"
										href="${pageContext.request.contextPath}/cars">Annuler</a>
									<button type="reset" class="btn btn-warning pull-right">Reinitialiser</button>
									<button type="submit" class="btn btn-info pull-right">Modifier</button>
								</div>
								<!-- /.box-footer -->
							</form>
						</div>
						<!-- /.box -->
					</div>
					<!-- /.col -->
				</div>
			</section>
			<!-- /.content -->
		</div>

		<%@ include file="/WEB-INF/views/common/footer.jsp"%>
	</div>
	<!-- ./wrapper -->

	<%@ include file="/WEB-INF/views/common/js_imports.jsp"%>
</body>
</html>
