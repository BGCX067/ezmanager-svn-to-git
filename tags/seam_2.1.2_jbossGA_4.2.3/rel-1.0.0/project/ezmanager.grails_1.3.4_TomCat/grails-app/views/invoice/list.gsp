

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'invoice.label', default: 'Invoice')}" />
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
                        
                            <g:sortableColumn property="invoiceId" title="${message(code: 'invoice.invoiceId.label', default: 'Invoice Id')}" />
                        
                            <g:sortableColumn property="position" title="${message(code: 'invoice.position.label', default: 'Position')}" />
                        
                            <th><g:message code="invoice.executedTaskIdExecutedTask.label" default="Executed Task" /></th>
                        
                        </tr>
                    </thead>
                    <tbody>
                    <g:each in="${invoiceInstanceList}" status="i" var="invoiceInstance">
                        <tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
                        
                            <td><g:link action="show" id="${invoiceInstance.invoiceId}">${fieldValue(bean: invoiceInstance, field: "invoiceId")}</g:link></td>
                        
                            <td>${fieldValue(bean: invoiceInstance, field: "position")}</td>
                        
                            <td>${fieldValue(bean: invoiceInstance, field: "executedTaskIdExecutedTask.name")}</td>
                        
                        </tr>
                    </g:each>
                    </tbody>
                </table>
            </div>
            <div class="paginateButtons">
                <g:paginate total="${invoiceInstanceTotal}" />
            </div>
        </div>
    </body>
</html>
