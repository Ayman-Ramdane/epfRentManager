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
				<h1>Utilisateur (id = ${clientId})</h1>
			</section>

			<!-- Main content -->
			<section class="content">
				<div class="row">
					<div class="col-md-12">
						<!-- Horizontal Form -->
						<div class="box">
							<!-- form start -->
							<form class="form-horizontal" method="post">
								<div class="box-body">
									<div class="form-group">
										<label for="last_name" class="col-sm-2 control-label">Nom</label>

										<div class="col-sm-10">
											<input type="text" class="form-control" id="lastname"
												name="lastname" placeholder="Nom" maxlength="100"
												pattern="[a-zA-Z-]{3,}" pattern="[a-zA-Z-]+"
												value="${lastName}" required>
										</div>
									</div>
									<div class="form-group">
										<label for="first_name" class="col-sm-2 control-label">Prenom</label>

										<div class="col-sm-10">
											<input type="text" class="form-control" id="firstname"
												name="firstname" placeholder="Prenom"
												pattern="[a-zA-Z-]{3,}" value="${firstName}" required>
										</div>
									</div>
									<div class="form-group">
										<label for="email" class="col-sm-2 control-label">Email</label>

										<div class="col-sm-10">
											<input type="email" class="form-control" id="email"
												name="email" placeholder="Email" value="${email}" required>
										</div>
									</div>
									<div class="form-group">
										<label for="birthDate" class="col-sm-2 control-label">Date
											de naissance</label>

										<div class="col-sm-10">
											<input type="date" class="form-control" id="birthdate"
												name="birthdate" placeholder="birthDate"
												value="${birthDate}" max="${maxBirthDate}" required>
										</div>
									</div>
								</div>
								<!-- /.box-body -->
								<div class="box-footer">
									<a href="${pageContext.request.contextPath}/users"
										class="btn btn-danger pull-right">Annuler</a>
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
