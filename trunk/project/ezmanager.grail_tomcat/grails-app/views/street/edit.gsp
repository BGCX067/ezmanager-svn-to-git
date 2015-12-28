<%@page import="ezmanager.*"%>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'street.label', default: 'Street')}" />
        <title><g:message code="default.edit.label" args="[entityName]" /></title>
    </head>
    <body>
        <div class="nav">
            <span class="menuButton"><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></span>
            <span class="menuButton"><g:link class="list" action="list"><g:message code="default.list.label" args="[entityName]" /></g:link></span>
            <span class="menuButton"><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></span>
        </div>
        <div class="body">
            <h1><g:message code="default.edit.label" args="[entityName]" /></h1>
            <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
            </g:if>
            <g:hasErrors bean="${streetInstance}">
            <div class="errors">
                <g:renderErrors bean="${streetInstance}" as="list" />
            </div>
            </g:hasErrors>
            <g:form method="post" >
                <g:hiddenField name="id" value="${streetInstance?.streetId}" />
                <div class="dialog">
                    <table>
                        <tbody>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                  <label for="streetId"><g:message code="street.streetId.label" default="Street Id" /></label>
                                </td>
                                <td valign="top" class="value">${fieldValue(bean: streetInstance, field: "streetId")}</td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                  <label for="name"><g:message code="street.name.label" default="Name" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: streetInstance, field: 'name', 'errors')}">
                                    <g:textField name="name" maxlength="200" value="${streetInstance?.name}" />
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                  <label for="remark"><g:message code="street.remark.label" default="Remark" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: streetInstance, field: 'remark', 'errors')}">
                                    <g:textArea name="remark" cols="40" rows="5" value="${streetInstance?.remark}" />
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                  <label for="city"><g:message code="street.city.label" default="City" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: streetInstance, field: 'city', 'errors')}">
                                    <g:select name="city.cityId" from="${City.listOrderByName()}" optionKey="cityId" optionValue="name" value="${streetInstance?.city?.cityId}"  />
                                </td>
                            </tr>
                        
                        </tbody>
                    </table>
                </div>
                <div class="buttons">
                    <span class="button"><g:actionSubmit class="save" action="update" value="${message(code: 'default.button.update.label', default: 'Update')}" /></span>
                    <span class="button"><g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" /></span>
                </div>
            </g:form>
        </div>
    </body>
</html>