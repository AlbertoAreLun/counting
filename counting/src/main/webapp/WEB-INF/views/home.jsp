<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="false"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:masterpage>
    <jsp:body>
    	<div class="container">
		<h1>Subsistema "Recuento y Modificaci�n de Resultados"</h1>

		<p class="flow-text">Subsistema encargado de la realizaci�n del
			recuento y la modificaci�n de los resultados de una votaci�n
			determinada. Para la realizaci�n del recuento se acceder� a la base
			de datos proporcionada por almacenamiento y utilizar� los m�todos
			proporcionados por verificaci�n para descifrar los votos obtenidos.</p>
		<br>

		<h4>API REST del subsistema:</h4>
		<table class="table-responsive striped centered bordered">
			<thead>
				<tr>
					<th>M�todo URL</th>
					<th>Descripci�n</th>
					<th>Par�metros</th>
					<th>Respuesta</th>
					<th>Ejemplo</th>
				</tr>
			</thead>
			<tbody>
				<tr>
					<td>*/count/basic/{pollId}</td>
					<td>Recuento b�sico.</td>
					<td>{pollId}: id de la votaci�n a la que hacer recuento</td>
					<td>JSON con el resultado del recuento de esa votaci�n.</td>
					<td>{"status":"OK.","pollId":3,"votes":[{"id":31,"count":2},{"id":32,"count":1}]}</td>
				</tr>
				<tr>
					<td>*/count/ageMayority/{pollId}</td>
					<td>Recuento por mayor�a de edad.</td>
					<td>{pollId}: id de la votaci�n a la que hacer recuento</td>
					<td>JSON con el resultado del recuento de esa votaci�n.</td>
					<td>{"status":"OK.","pollId":3,"ageMayorityVotes":[{"id":31,"minorsCount":1,"mayorityCount":1},{"id":32,"minorsCount":0,"mayorityCount":1}]}</td>
				</tr>
				<tr>
					<td>*/count/usualAgeRange/{pollId}</td>
					<td>Recuento por rangos habituales de edad.</td>
					<td>{pollId}: id de la votaci�n a la que hacer recuento</td>
					<td>JSON con el resultado del recuento de esa votaci�n.</td>
					<td>{"status":"OK.","pollId":3,"usualAgeRangesVotes":[{"id":31,"countUnder30":1,"count30To60":1,"countOver60":0},{"id":32,"countUnder30":0,"count30To60":1,"countOver60":0}]}</td>
				</tr>
				<tr>
					<td>*/count/ageRange/{pollId}/{minRange}/{maxRange}</td>
					<td>Recuento por rango dado de edad.</td>
					<td>{pollId}: id de la votaci�n a la que hacer recuento</td>
					<td>JSON con el resultado del recuento de esa votaci�n.</td>
					<td>{"status":"OK.","pollId":3,"votes":[{"id":31,"count":0,"nocount":2},{"id":32,"count":1,"nocount":0}]}</td>
				</tr>
				<tr>
					<td>*/count/gender/{pollId}</td>
					<td>Recuento por g�nero.</td>
					<td>{pollId}: id de la votaci�n a la que hacer recuento</td>
					<td>JSON con el resultado del recuento de esa votaci�n.</td>
					<td>{"status":"OK.","pollId":3,"votes":[{"id":31,"femaleCount":1,"maleCount":1},{"id":32,"femaleCount":0,"maleCount":1}]}</td>
				</tr>
			</tbody>
		</table>
	</div>
    </jsp:body>
</t:masterpage>
