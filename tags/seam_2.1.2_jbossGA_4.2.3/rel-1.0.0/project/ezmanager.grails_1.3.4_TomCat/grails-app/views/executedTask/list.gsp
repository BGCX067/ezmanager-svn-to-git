

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'executedTask.label', default: 'ExecutedTask')}" />
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
                        
                            <g:sortableColumn property="executedTaskId" title="${message(code: 'executedTask.executedTaskId.label', default: 'Executed Task Id')}" />
                        
                            <g:sortableColumn property="totalHours" title="${message(code: 'executedTask.totalHours.label', default: 'Total Hours')}" />
                        
                            <g:sortableColumn property="amount" title="${message(code: 'executedTask.amount.label', default: 'Amount')}" />
                        
                            <g:sortableColumn property="startDate" title="${message(code: 'executedTask.startDate.label', default: 'Start Date')}" />
                        
                            <g:sortableColumn property="endDate" title="${message(code: 'executedTask.endDate.label', default: 'End Date')}" />
                        
                        </tr>
                    </thead>
                    <tbody>
                    <g:each in="${executedTaskInstanceList}" status="i" var="executedTaskInstance">
                        <tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
                        
                            <td><g:link action="show" id="${executedTaskInstance.executedTaskId}">${fieldValue(bean: executedTaskInstance, field: "executedTaskId")}</g:link></td>
                        
                            <td>${fieldValue(bean: executedTaskInstance, field: "totalHours")}</td>
                        
                            <td>${fieldValue(bean: executedTaskInstance, field: "amount")}</td>
                        
                            <td><g:formatDate date="${executedTaskInstance.startDate}" /></td>
                        
                            <td><g:formatDate date="${executedTaskInstance.endDate}" /></td>
                        
                        </tr>
                    </g:each>
                    </tbody>
                </table>
            </div>
            <div class="paginateButtons">
                <g:paginate total="${executedTaskInstanceTotal}" />
            </div>
        </div>
    </body>
</html>
