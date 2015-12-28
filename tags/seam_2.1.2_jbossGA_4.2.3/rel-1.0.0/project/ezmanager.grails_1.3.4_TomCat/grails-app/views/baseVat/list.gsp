

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'baseVat.label', default: 'BaseVat')}" />
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
                        
                            <g:sortableColumn property="vatId" title="${message(code: 'baseVat.vatId.label', default: 'Vat Id')}" />
                        
                            <g:sortableColumn property="name" title="${message(code: 'baseVat.name.label', default: 'Name')}" />
                        
                            <g:sortableColumn property="value" title="${message(code: 'baseVat.value.label', default: 'Value')}" />
                        
                            <th><g:message code="baseVat.currencyIdBaseCurrency.label" default="Currency" /></th>
                        
                        </tr>
                    </thead>
                    <tbody>
                    <g:each in="${baseVatInstanceList}" status="i" var="baseVatInstance">
                        <tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
                        
                            <td><g:link action="show" id="${baseVatInstance.vatId}">${fieldValue(bean: baseVatInstance, field: "vatId")}</g:link></td>
                        
                            <td>${fieldValue(bean: baseVatInstance, field: "name")}</td>
                        
                            <td>${fieldValue(bean: baseVatInstance, field: "value")}</td>
                        
                            <td>${fieldValue(bean: baseVatInstance, field: "currencyIdBaseCurrency.name")}</td>
                        
                        </tr>
                    </g:each>
                    </tbody>
                </table>
            </div>
            <div class="paginateButtons">
                <g:paginate total="${baseVatInstanceTotal}" />
            </div>
        </div>
    </body>
</html>
