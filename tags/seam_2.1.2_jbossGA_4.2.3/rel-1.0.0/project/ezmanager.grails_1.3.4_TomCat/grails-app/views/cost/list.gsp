

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'cost.label', default: 'Cost')}" />
        <title><g:message code="default.list.label" args="[entityName]" /></title>
    </head>
    <body>
        <div class="nav">
            <span class="menuButton"><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></span>
            <span class="menuButton"><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></span>
        </div>
        <div class="body">
            <h1><g:message code="default.list.label" args="[entityName]" /></h1>
            <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
            </g:if>
            <div class="list">
                <table>
                    <thead>
                        <tr>
                        
                            <g:sortableColumn property="costId" title="${message(code: 'cost.costId.label', default: 'Cost Id')}" />
                        
                            <g:sortableColumn property="name" title="${message(code: 'cost.name.label', default: 'Name')}" />
                        
                            <g:sortableColumn property="amountProposed" title="${message(code: 'cost.amountProposed.label', default: 'Amount Proposed')}" />
                        
                            <g:sortableColumn property="totalHours" title="${message(code: 'cost.totalHours.label', default: 'Total Hours')}" />
                        
                            <th><g:message code="cost.taskIdTask.label" default="Task" /></th>
                        
                        </tr>
                    </thead>
                    <tbody>
                    <g:each in="${costInstanceList}" status="i" var="costInstance">
                        <tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
                        
                            <td><g:link action="show" id="${costInstance.costId}">${fieldValue(bean: costInstance, field: "costId")}</g:link></td>
                        
                            <td>${fieldValue(bean: costInstance, field: "name")}</td>
                        
                            <td>${fieldValue(bean: costInstance, field: "amountProposed")}</td>
                        
                            <td>${fieldValue(bean: costInstance, field: "totalHours")}</td>
                        
                            <td>${fieldValue(bean: costInstance, field: "taskIdTask.name")}</td>
                        
                        </tr>
                    </g:each>
                    </tbody>
                </table>
            </div>
            <div class="paginateButtons">
                <g:paginate total="${costInstanceTotal}" />
            </div>
        </div>
    </body>
</html>
