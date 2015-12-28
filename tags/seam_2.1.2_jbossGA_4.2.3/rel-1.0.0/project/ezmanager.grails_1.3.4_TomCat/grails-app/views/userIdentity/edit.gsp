


<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'userIdentity.label', default: 'UserIdentity')}" />
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
            <g:hasErrors bean="${userIdentityInstance}">
            <div class="errors">
                <g:renderErrors bean="${userIdentityInstance}" as="list" />
            </div>
            </g:hasErrors>
            <g:form method="post" >
                <g:hiddenField name="id" value="${userIdentityInstance?.userIdentityId}" />
                <g:hiddenField name="version" value="${userIdentityInstance?.version}" />
                <div class="dialog">
                    <table>
                        <tbody>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                  <label for="userIdentityId"><g:message code="userIdentity.userIdentityId.label" default="User Identity Id" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: userIdentityInstance, field: 'userIdentityId', 'errors')}">
                                    <g:textField name="userIdentityId" value="${fieldValue(bean: userIdentityInstance, field: 'userIdentityId')}" />
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                  <label for="name"><g:message code="userIdentity.name.label" default="Name" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: userIdentityInstance, field: 'name', 'errors')}">
                                    <g:textField name="name" maxlength="50" value="${userIdentityInstance?.name}" />
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                  <label for="password"><g:message code="userIdentity.password.label" default="Password" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: userIdentityInstance, field: 'password', 'errors')}">
                                    <g:textField name="password" maxlength="50" value="${userIdentityInstance?.password}" />
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                  <label for="isActive"><g:message code="userIdentity.isActive.label" default="Is Active" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: userIdentityInstance, field: 'isActive', 'errors')}">
                                    <g:textField name="isActive" maxlength="1" value="${userIdentityInstance?.isActive}" />
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                  <label for="roleIdentityList"><g:message code="userIdentity.roleIdentityList.label" default="Roles" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: userIdentityInstance, field: 'roleIdentityList', 'errors')}">
                                    
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                  <label for="taskList"><g:message code="userIdentity.taskList.label" default="Tasks" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: userIdentityInstance, field: 'taskList', 'errors')}">
                                    
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
