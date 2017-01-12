<%@page language="java" contentType="text/html; charset=ISO-8859-1"	pageEncoding="ISO-8859-1"%>

<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<jsp:useBean id="date" class="java.util.Date" />

<footer class="page-footer">
	<div class="container">
		<div class="row">
        	<div class="col l6 s12">
            	<h5 class="white-text">EGC - �gora US</h5>
                <h5 class="white-text">Recuento y modificaci�n de resultados</h5>
                <p class="white-text">Este subsistema realizar� el recuento de una votaci�n determinada. Para llevarlo a cabo tendr� que pedir los votos al subsistema de almacenamiento y deber� lanzar la tarea de recuento sincronizando las diferentes autoridades.</p>
            </div>
        	<div class="col l4 offset-l2 s12">
        		<h5 class="white-text">Miembros</h5>
        		<ul class="white-text">
        			<li>Pablo Mac�as Lopez <b>(Coordinador)</b></li>
					<li>Carlos Alberto Mata Gil</li>
					<li>Guillermo Alcal� Gamero</li>
					<li>Alberto Arenillas Luna</li>
					<li>Jorge David Cabrera Cruz</li>
					<li>Jos� Giraldo Ruiz</li>
        		</ul>
			</div>
		</div>
	</div>
    <div class="footer-copyright">
    	<div class="container">
        	� <fmt:formatDate value="${date}" pattern="yyyy" /> Universidad de Sevilla
        	<a class="grey-text text-lighten-4 right" href="https://github.com/AgoraUS-G2-1617/G26" target="blank">Repositorio del c�digo en GitHub</a>
        </div>
	</div>
</footer>
