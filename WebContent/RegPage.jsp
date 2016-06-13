<html>
<body style="background:#F0F0F0">
	<%@ page import="com.osd.util.HTTPpostRequest"%>
    <%@ page import="com.osd.util.DatabaseOperation"%>
	<%@ page import="com.osd.util.DatabaseConnection"%>
	<%@ page import="java.sql.Timestamp"%>
	<%@ page import="java.util.ArrayList"%>
	<%@ page import="com.osd.entity.Korisnici"%>
	<%@ page import="com.osd.entity.DataOperation"%>
	<%@ page import="com.osd.entity.DataOperation"%>
	<%
		// Grab the variables from the form.
		String firstName = request.getParameter("firstName");
		String lastName = request.getParameter("lastName");
		String email = request.getParameter("email");
		String drzava = request.getParameter("drzava");
		Timestamp time = DataOperation.vratiTimeStamp();
		Korisnici k = new Korisnici();
		k.setFirstname(firstName);
		k.setLastname(lastName);
		k.setEmail(email);
		k.setDrzava(drzava);
		k.setDatum(time);

		HTTPpostRequest req = new HTTPpostRequest();
		String link = "http://boriskremenovic.com/api/mail.php";

		req.sendPost(link, firstName, lastName, email);

		DataOperation o = new DataOperation();		
		DatabaseOperation op=new DatabaseOperation();
		//int rows=op.rowcount();
		o.addUser(k);
		o.findUser(15);
		o.findUser(5);
		int rows=op.rowcount();
		//DatabaseConnection.getEntityManager().close();
	%>
	<%-- Print out the variables. --%>
	<h1>
		Zdravo,
		<%=firstName%>
		<%=lastName%>!
	</h1>
	Email poslat na
	<%=email%>. E da,jel se
	<%=drzava%>
	plasirala na Euro?

	<h1 align="center">Lista Korisnika</h1>
	<TABLE border="1" style="background:#D8D6D6" align="center">
	<tr>
<th>ID</th>
<th>First Name</th>
<th>Last Name</th>
<th>E-mail</th>
<th>Drzava</th>
</tr>
<% for(int row=1;row<=rows+2; row++) { %>
    <TR>
<%  Korisnici user=o.getUser(row);    
if(user!=null) { %>
        <TD> <%=user.getId()%>
        </TD>
        <TD> <%=user.getFirstname()%>
        </TD>
        <TD> <%=user.getLastname()%>
        </TD>
        <TD> <%=user.getEmail()%>
        </TD>
        <TD> <%=user.getDrzava()%>
        </TD>
        <% } %>
    </TR>
<% } %>
</TABLE>

</body>
</html>