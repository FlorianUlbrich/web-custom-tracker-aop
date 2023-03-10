<%--
  Created by IntelliJ IDEA.
  User: florian
  Date: 2019-06-13
  Time: 12:48
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>Udemy Spring MVC with Hibernate</title>
    <link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/style.css"/>
</head>
<body>
    <div id="wrapper">
        <div id="header">
            <h2>CRM - Customer Relationship Manager</h2>
        </div>
    </div>
    <div id="container">
        <div id="content">

            <!-- button for adding customers -->
            <input type="button" value="Add Customer"
                   onclick="window.location.href='showFormForAdd'; return false;" class="addButton"/>

            <form:form action="search" method="get">
                Search Customer: <input type="text" name="theSearchName"/>
                <input type="submit" value="Search" class="add-button"/>
            </form:form>

            <table>
                <tr>
                    <th>First Name</th>
                    <th>Last Name</th>
                    <th>Email</th>
                    <th>Action</th>
                </tr>

                <!-- loop over customers in model -->
                <c:forEach var="customer" items="${customers}">

                    <!-- construct an update link with customer id -->
                    <c:url var="updateLink" value="/customer/showFormForUpdate">
                        <c:param name="customerId" value="${customer.id}"/>
                    </c:url>

                    <c:url var="deleteLink" value="/customer/delete">
                        <c:param name="customerId" value="${customer.id}"/>
                    </c:url>

                    <tr>
                        <td> ${customer.firstName} </td>
                        <td> ${customer.lastName} </td>
                        <td> ${customer.email} </td>
                        <td>
                            <!-- display the update link -->
                            <a href="${updateLink}">Update</a>
                            | <a href="${deleteLink}" onclick="if (!(confirm('Are you sure you want to delete this customer?'))) return false;">Delete</a>
                        </td>
                    </tr>
                </c:forEach>

            </table>

        </div>
    </div>
</body>
</html>
