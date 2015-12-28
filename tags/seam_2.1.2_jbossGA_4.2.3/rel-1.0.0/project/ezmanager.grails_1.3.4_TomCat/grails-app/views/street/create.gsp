


<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'street.label', default: 'Street')}" />
        <title><g:message code="default.create.label" args="[entityName]" /></title>
    </head>
    <body>
        <div class="nav">
            <span class="menuButton"><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></span>
            <span class="menuButton"><g:link class="list" action="list"><g:message code="default.list.label" args="[entityName]" /></g:link></span>
        </div>
        <div class="body">
            <h1><g:message code="default.create.label" args="[entityName]" /></h1>
            <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
            </g:if>
            <g:hasErrors bean="${streetInstance}">
            <div class="errors">
                <g:renderErrors bean="${streetInstance}" as="list" />
            </div>
            </g:hasErrors>
            <g:form action="save" >
                <div class="dialog">
                    <table>
                        <tbody>
                        
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
                                    <label for="cityIdCity"><g:message code="street.cityIdCity.label" default="City" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: streetInstance, field: 'cityIdCity', 'errors')}">
                                    <g:select name="cityIdCity.cityId" from="${City.list()}" optionKey="cityId" optionValue="name" value="${streetInstance?.cityIdCity?.cityId}"  />
                                </td>
                            </tr>
                        
                        </tbody>
                    </table>
                </div>
                <div class="buttons">
                    <span class="button"><g:submitButton name="create" class="save" value="${message(code: 'default.button.create.label', default: 'Create')}" /></span>
                </div>
            </g:form>
        </div>
    </body>
</html>
