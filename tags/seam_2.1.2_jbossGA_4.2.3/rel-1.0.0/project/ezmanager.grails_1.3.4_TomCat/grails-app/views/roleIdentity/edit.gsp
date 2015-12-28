


<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'roleIdentity.label', default: 'RoleIdentity')}" />
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
            <g:hasErrors bean="${roleIdentityInstance}">
            <div class="errors">
                <g:renderErrors bean="${roleIdentityInstance}" as="list" />
            </div>
            </g:hasErrors>
            <g:form method="post" >
                <g:hiddenField name="id" value="${roleIdentityInstance?.id}" />
                <g:hiddenField name="version" value="${roleIdentityInstance?.version}" />
                <div class="dialog">
                    <table>
                        <tbody>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                  <label for="roleIdentityId"><g:message code="roleIdentity.roleIdentityId.label" default="Role Identity Id" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: roleIdentityInstance, field: 'roleIdentityId', 'errors')}">
                                    <g:textField name="roleIdentityId" value="${fieldValue(bean: roleIdentityInstance, field: 'roleIdentityId')}" />
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                  <label for="name"><g:message code="roleIdentity.name.label" default="Name" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: roleIdentityInstance, field: 'name', 'errors')}">
                                    <g:textField name="name" maxlength="20" value="${roleIdentityInstance?.name}" />
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                  <label for="userIdentityList"><g:message code="roleIdentity.userIdentityList.label" default="Users" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: roleIdentityInstance, field: 'userIdentityList', 'errors')}">
                                    <g:select name="userIdentityList" from="${UserIdentity.list()}" multiple="yes" optionKey="userIdentityId" optionValue="name" size="5" value="${roleIdentityInstance?.userIdentityList*.userIdentityId}" />
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
